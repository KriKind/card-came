package ee.kristiina.card_game.controller;

import ee.kristiina.card_game.entity.Player;
import ee.kristiina.card_game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @PostMapping("player")
    public Player signupPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }


    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

}
