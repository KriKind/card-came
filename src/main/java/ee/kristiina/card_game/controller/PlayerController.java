package ee.kristiina.card_game.controller;

import ee.kristiina.card_game.entity.Player;
import ee.kristiina.card_game.repository.PlayerRepository;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @PostMapping("player")
    public Player signupPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

//    @GetMapping("players")
//    public List<Player> getPlayers() {
//        return playerRepository.findAll();
//    }
}
