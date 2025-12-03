package ee.kristiina.card_game.repository;

import ee.kristiina.card_game.entity.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GameResultRepository extends JpaRepository<GameResult,Long> {
    // sortimiseks
    @Query("SELECT g FROM GameResult g ORDER BY g.durationOfPlay ASC")
    List<GameResult> findAllGameResultsByDurationAsc();

    @Query("SELECT g FROM GameResult g ORDER BY g.correctAnswers DESC")
    List<GameResult> findAllGameResultsByCorrectAnswersDesc();

    // mängija järgi
    List<GameResult> findByPlayerFirstNameAndPlayerLastName(String firstName, String lastName);

    Optional<GameResult> findTopByOrderByIdDesc();
}
