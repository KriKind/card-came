package ee.kristiina.card_game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GameResult {
    @Id
    private Long id;
    @ManyToOne
    private Player player;
    int correctAnswers;
    float timeOfPlay;

}
