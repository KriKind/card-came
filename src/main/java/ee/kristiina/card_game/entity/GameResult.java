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

//    public GameResult() {}
//
//    public GameResult(Player player, int correctAnswers, long durationOfPlay) {
//        this.player = player;
//        this.correctAnswers = correctAnswers;
//        this.durationOfPlay = durationOfPlay;
//        this.startTime = LocalDateTime.now();
//    }
}
