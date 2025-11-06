package ee.kristiina.card_game.controller;

import ee.kristiina.card_game.entity.Game;
import ee.kristiina.card_game.service.GameService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public Game startGame() {
        return gameService.startGame();
    }

    @PostMapping("/guess")
    public Game makeGuess(@RequestParam String action) {
        return gameService.makeGuess(action);
    }
}
