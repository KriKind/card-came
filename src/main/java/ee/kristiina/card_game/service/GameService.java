package ee.kristiina.card_game.service;

import ee.kristiina.card_game.entity.Card;
import ee.kristiina.card_game.entity.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GameService {

    private Game game;

    public Game startGame() {
        if (game == null || game.isGameOver()) {
            game = new Game();
            game.setDeck(createShuffledDeck());
            game.setLives(3);
            game.setCorrectAnswers(0);
            game.setGameOver(false);
            game.setBaseCard(drawCard());
        }

        return game;
    }

    private List<Card> createShuffledDeck() {
        List<Card> deck = new ArrayList<>();
        String[] suits = {"clubs", "hearts", "diamonds", "spades"};
        String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                int value;
                if (rank.matches("[0-9]+")) value = Integer.parseInt(rank);
                else value = 10;
                deck.add(new Card(suit,rank,value));
            }
        }

        Collections.shuffle(deck);
        return deck;
    };

    public Game makeGuess(String action) {
        if (game == null || game.isGameOver()) {
            throw  new RuntimeException("Game is not started or already over");
        }

        if (game.getDeck().isEmpty()){
            game.setGameOver(true);
            return game;
        }

        Card nextCard = drawCard();
        boolean correct = compareCards(game.getBaseCard(), nextCard, action);

        if (correct) {
            game.setCorrectAnswers(game.getCorrectAnswers() + 1);
        }else  {
            game.setLives(game.getLives() - 1);
            if (game.getLives() <= 0) {
                game.setGameOver(true);
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

    private Card drawCard() {
        return game.getDeck().removeFirst();
    }
}
