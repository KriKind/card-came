package ee.kristiina.card_game.controller;

import ee.kristiina.card_game.entity.Game;
import ee.kristiina.card_game.entity.GameResult;
import ee.kristiina.card_game.repository.GameResultRepository;
import ee.kristiina.card_game.repository.PlayerRepository;
import ee.kristiina.card_game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class GameResultController {

    @Autowired
    GameResultRepository gameResultRepository;

//    @PostMapping("result")
//    public GameResult makeGame(@RequestBody GameResult gameResult) {
//        Game game = null;
//        gameResult.setDurationOfPlay(game.getDuration());
//        gameResult.setStartTime(LocalDateTime.now());
//        return gameResultRepository.save(gameResult);
 //   }

    @GetMapping("results")
    public List<GameResult> getGameResult() {
        return gameResultRepository.findAll();
    }
}
