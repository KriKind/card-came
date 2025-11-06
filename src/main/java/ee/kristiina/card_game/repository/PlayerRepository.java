package ee.kristiina.card_game.repository;

import ee.kristiina.card_game.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
