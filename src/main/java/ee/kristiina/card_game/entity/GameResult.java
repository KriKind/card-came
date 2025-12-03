package ee.kristiina.card_game.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int correctAnswers;
    private long durationOfPlay;
    private LocalDateTime startTime;

    @ManyToOne
    //@JoinColumn(name = "player_id")
    private Player player;


}
