package ee.kristiina.card_game.controller;

import ee.kristiina.card_game.entity.Game;
import ee.kristiina.card_game.entity.GameResult;
import ee.kristiina.card_game.repository.GameResultRepository;
import ee.kristiina.card_game.repository.PlayerRepository;
import ee.kristiina.card_game.service.GameResultService;
import ee.kristiina.card_game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173/")
@RestController
public class GameResultController {

    @Autowired
    GameResultRepository gameResultRepository;

    @Autowired
    GameResultService gameResultService;

    @GetMapping("allResults")
    public Page<GameResult> getGameResult(Pageable pageable) {
        return gameResultRepository.findAll(pageable);
    }

    @GetMapping("lastResult")
    public ResponseEntity<GameResult> getLastGameResult() {
        return gameResultRepository.findTopByOrderByIdDesc()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Scoreboard
    // localhost:8080/scoreboard?sortBy=durationofplay
    // localhost:8080/scoreboard?sortBy=correctanswers
    @GetMapping("scoreboard")
    public List<GameResult> getScoreboard(@RequestParam(defaultValue = "CorrectAnswers") String sortBy) {
        return gameResultService.getScoreboard(sortBy);
    }

    // Player's games
    // localhost:8080/player?firstName=Kristiina&lastName=Kask
    @GetMapping("playerResults")
    public List<GameResult> getGameResultsByPlayer(@RequestParam String firstName, @RequestParam String lastName) {
        return gameResultRepository.findByPlayerFirstNameAndPlayerLastName(firstName, lastName);
    }
}
