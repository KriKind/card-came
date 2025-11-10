package ee.kristiina.card_game.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Game {

    private Card baseCard;
    private int correctAnswers;
    private int lives;
    private List<Card> deck;
    private boolean gameOver;
    private long startTime;  // 💡 algusaeg
    private long duration;   // 💡 kestus millisekundites
}
