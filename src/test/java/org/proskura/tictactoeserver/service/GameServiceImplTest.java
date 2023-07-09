package org.proskura.tictactoeserver.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proskura.tictactoeserver.entity.GameEntity;
import org.proskura.tictactoeserver.mapper.GameMapper;
import org.proskura.tictactoeserver.model.Figure;
import org.proskura.tictactoeserver.model.Game;
import org.proskura.tictactoeserver.model.Position;
import org.proskura.tictactoeserver.repository.GameRepository;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.proskura.tictactoeserver.model.GameState.FINISHED;
import static org.proskura.tictactoeserver.service.GameServiceImpl.WON_COMBINATIONS;

class GameServiceImplTest {

    private GameServiceImpl gameService;
    private GameRepository gameRepository;
    private GameMapper gameMapper;
    private NotificationService notificationService;
    private BotService botService;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        gameMapper = spy(GameMapper.INSTANT);
        notificationService = mock(NotificationService.class);
        botService = mock(BotService.class);
        gameService = new GameServiceImpl(gameRepository, gameMapper, notificationService, botService);
    }

    @Test
    void shouldGameBeCreated() {
        Game game = gameService.createGame();
        verify(gameRepository).save(any(GameEntity.class));
        verify(gameMapper).map(any(GameEntity.class), eq(null));

        assertNull(game.getWinner());
        assertEquals(Figure.X, game.getNextMove());
        assertNull(game.getWinner());

        Map<Position, Figure> board = game.getBoard();
        assertEquals(9, board.size());
        Arrays.stream(Position.values()).forEach(position -> {
            assertTrue(board.containsKey(position));
            assertEquals(Figure.EMPTY, board.get(position));
        });
    }

    @Test
    void shouldWinnerBeFound() {
        final Figure figure = Figure.X;
        WON_COMBINATIONS.forEach(winCombination -> {
            Map<Position, Figure> board = GameService.generateNewBoard();
            winCombination.forEach(position -> board.put(position, figure));

            GameEntity gameEntity = new GameEntity();
            gameEntity.setId(UUID.randomUUID());
            gameEntity.setBoard(board);

            Figure winner = gameService.checkWinner(gameEntity);
            assertEquals(FINISHED, gameEntity.getState());
            assertEquals(figure, winner);
        });

    }
}