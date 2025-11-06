package ee.kristiina.card_game.repository;

import ee.kristiina.card_game.entity.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameResultRepository extends JpaRepository<GameResult,Long> {
}
