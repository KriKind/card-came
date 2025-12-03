package ee.kristiina.card_game.service;

import ee.kristiina.card_game.entity.GameResult;
import ee.kristiina.card_game.repository.GameResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameResultService {

    private final GameResultRepository gameResultRepository;

    public GameResultService(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
    }

    public List<GameResult> getScoreboard(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case "durationofplay"-> gameResultRepository.findAllGameResultsByDurationAsc();
            case "correctanswers" -> gameResultRepository.findAllGameResultsByCorrectAnswersDesc();
            default -> gameResultRepository.findAll();
        };
    }

}
