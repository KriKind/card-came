package ee.kristiina.card_game.service;

import ee.kristiina.card_game.entity.Card;
import ee.kristiina.card_game.entity.Game;
import ee.kristiina.card_game.entity.GameResult;
import ee.kristiina.card_game.entity.Player;
import ee.kristiina.card_game.repository.GameResultRepository;
import ee.kristiina.card_game.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final PlayerRepository playerRepository;
    private final GameResultRepository gameResultRepository;

    private Game game;
    private Player currentPlayer;

    public GameService(PlayerRepository playerRepository, GameResultRepository gameResultRepository) {
        this.playerRepository = playerRepository;
        this.gameResultRepository = gameResultRepository;
    }

    public Player registerPlayer(String firstName, String lastName) {
        Optional<Player> optionalPlayer = playerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        }
        Player newPlayer = new Player(firstName, lastName);
        playerRepository.save(newPlayer);
        return newPlayer;

        // sama asi lühemalt
        // return playerRepository.findByFirstNameAndLastName(firstName, lastName)
        //        .orElseGet(() -> playerRepository.save(new Player(firstName, lastName)));
    }

    public Game startGame(String firstName, String lastName) {
        this.currentPlayer = registerPlayer(firstName, lastName);

        if (game == null || game.isGameOver()) {
            game = new Game();
            game.setDeck(createShuffledDeck());
            game.setLives(3);
            game.setCorrectAnswers(0);
            game.setGameOver(false);
            game.setBaseCard(drawCard());
            game.setStartTime(System.currentTimeMillis());
        }
        return game;
    }

    private List<Card> createShuffledDeck() {
        List<Card> deck = new ArrayList<>();
        String[] suits = {"Clubs", "Hearts", "Diamonds", "Spades"};
        String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                int value;
                if (rank.matches("\\d+")) value = Integer.parseInt(rank);
                else value = 10;
                deck.add(new Card(suit,rank,value));
            }
        }

        Collections.shuffle(deck);
        return deck;
    };

    public Game makeGuess(String action)  {
        if (game == null) {
            throw  new IllegalStateException("Game not started. Start the game!!");
        }
        if (game.isGameOver()) {
            throw  new IllegalStateException("Game is over. Please try again later.");
        }
        if (game.getDeck().isEmpty()){
            endGame();
            return game;
        }

        Card nextCard = drawCard();
        boolean correct = compareCards(game.getBaseCard(), nextCard, action);

        if (correct) {
            game.setCorrectAnswers(game.getCorrectAnswers() + 1);
        }else  {
            game.setLives(game.getLives() - 1);
            if (game.getLives() <= 0) {
                endGame();
            }
        }
        game.setBaseCard(nextCard);
        return game;
    }

    private boolean compareCards(Card base, Card next, String action) {
        return switch (action.toLowerCase()) {
            case "higher" -> next.value() > base.value();
            case "lower" -> next.value() < base.value();
            case "equal" -> next.value() == base.value();
            default -> false;
        };
    }

    private void endGame() {
        game.setGameOver(true);
        long endTime = System.currentTimeMillis();
        long duration = endTime - game.getStartTime();
        game.setDuration(duration);
        System.out.println("Mäng kestis: " + (duration / 1000.0) + " sekundit");

        if (currentPlayer != null) {
            GameResult result = new GameResult();
            result.setPlayer(currentPlayer);
            result.setCorrectAnswers(game.getCorrectAnswers());
            result.setDurationOfPlay(duration);
            result.setStartTime(LocalDateTime.now());
            System.out.println(result);
            gameResultRepository.save(result);
        }
    }

    private Card drawCard() {
        if (game.getDeck().isEmpty()) {
            throw  new IllegalStateException("Deck is empty");
        }
        return game.getDeck().removeFirst();
    }
}
