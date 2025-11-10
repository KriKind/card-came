package ee.kristiina.card_game.controller;

import ee.kristiina.card_game.entity.Game;
import ee.kristiina.card_game.entity.GameResult;
import ee.kristiina.card_game.repository.GameResultRepository;
import ee.kristiina.card_game.repository.PlayerRepository;
import ee.kristiina.card_game.service.GameService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.awt.*;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private GameResultRepository gameResultRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/start")
    public Game startGame(@RequestParam String firstName, @RequestParam String lastName) {
        return gameService.startGame(firstName, lastName);
    }

    @PostMapping("/guess")
    public Game makeGuess(@RequestParam String action) {
        return gameService.makeGuess(action);
    }

    @GetMapping("results")
    public List<GameResult> getGameResult() {
        return gameResultRepository.findAll();
    }
}
