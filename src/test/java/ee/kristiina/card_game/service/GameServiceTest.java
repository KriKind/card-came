package ee.kristiina.card_game.service;

import ee.kristiina.card_game.entity.Player;
import ee.kristiina.card_game.repository.GameResultRepository;
import ee.kristiina.card_game.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GameServiceTest {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    GameService gameService;

    Player player;
    GameResultRepository gameResultRepository;

    @BeforeEach
    void setUp() {
        player = new Player("Kuri", "Kari");
    }

    // registerPlayer testid
    @Test
    void givenPlayerAlreadyExists_whenRegisterPlayer_thenPlayerAlreadyExists() {
        // Given
        when(playerRepository.findByFirstNameAndLastName("Kuri", "Kari"))
                .thenReturn(Optional.of(player));

        // When
        Player result = gameService.registerPlayer("Kuri", "Kari");

        // Then
        verify(playerRepository,never()).save(any());

        assertEquals(player,result);
    }

    @Test
    void givenPlayerDoesNotExist_whenRegisterPlayer_thenInsertPlayer() {
        // Given

        when(playerRepository.findByFirstNameAndLastName("Kuri", "Kari"))
                .thenReturn(Optional.empty());
        Player newPlayer = new Player("Kuri", "Kari");
        when(playerRepository.save(any(Player.class))).thenReturn(newPlayer);

        // When
        Player result = gameService.registerPlayer("Kuri", "Kari");

        // Then
        verify(playerRepository).save(any(Player.class));
        assertEquals("Kuri", result.getFirstName());
        assertEquals("Kari", result.getLastName());
    }

    @Test
    void Given_WhenStartingGame_then() {

    }

    @Test
    void makeGuess() {
    }
}