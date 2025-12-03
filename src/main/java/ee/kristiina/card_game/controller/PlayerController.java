package ee.kristiina.card_game.controller;

import ee.kristiina.card_game.entity.Player;
import ee.kristiina.card_game.repository.PlayerRepository;
import ee.kristiina.card_game.service.GameResultService;
import ee.kristiina.card_game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173/")
@RestController
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameService gameService;

    @PostMapping("player")
    public Player signupPlayer(@RequestBody Player player) {
        return gameService.registerPlayer(player.getFirstName(),  player.getLastName());
    }

    //localhost:8080/player
    @PutMapping("player/{id}")
    public Player editPlayer(@PathVariable Long id, @RequestBody Player updatedPlayer){

//        if(player.getId() == null){
//            throw new RuntimeException("Cannot edit when id missing");
//        }
//        if(player.getFirstName() == null || player.getFirstName().isEmpty()){
//            throw new RuntimeException("First name cannot be empty");
//        }
//        if(player.getLastName() == null || player.getLastName().isEmpty()){
//            throw new RuntimeException("Last name cannot be empty");
//        }

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        player.setFirstName(updatedPlayer.getFirstName());
        player.setLastName(updatedPlayer.getLastName());
        return playerRepository.save(player);
    }

    @GetMapping("/players")
    public Page<Player> getPlayers(Pageable pageable) {

        return playerRepository.findAll(pageable);
    }

    @GetMapping("/player/{id}")
    public Optional<Player> getPlayer(@PathVariable Long id) {

        return playerRepository.findById(id);
    }

    @DeleteMapping("player/{id}")
    public List<Player> deletePlayer(@PathVariable Long id){
        playerRepository.deleteById(id);
        return playerRepository.findAll();
    }
}
