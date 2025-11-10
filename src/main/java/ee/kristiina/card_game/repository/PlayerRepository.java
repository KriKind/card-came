package ee.kristiina.card_game.repository;

import ee.kristiina.card_game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findByFirstNameAndLastName(String firstName, String lastName);

}
