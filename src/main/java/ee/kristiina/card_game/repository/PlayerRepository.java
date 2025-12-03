package ee.kristiina.card_game.repository;

import ee.kristiina.card_game.entity.GameResult;
import ee.kristiina.card_game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findByFirstNameAndLastName(String firstName, String lastName);

//    @Query("SELECT firstName, lastName FROM Player WHERE id={id}")
//    Player findFirstNameAndLastName(Long id);
}
