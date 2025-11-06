package ee.kristiina.card_game.controller;

import ee.kristiina.card_game.entity.Game;
import ee.kristiina.card_game.entity.GameResult;
import ee.kristiina.card_game.repository.GameResultRepository;
import ee.kristiina.card_game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameResultController {
    @Autowired
    GameResultRepository gameResultRepository;

    @Autowired
    PlayerRepository playerRepository;

    @PostMapping
    public GameResult makeGame(@RequestBody GameResult gameResult) {
        return gameResultRepository.save(gameResult);
    }
}
