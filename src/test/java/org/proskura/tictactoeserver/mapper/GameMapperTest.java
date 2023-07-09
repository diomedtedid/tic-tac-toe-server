package org.proskura.tictactoeserver.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proskura.tictactoeserver.entity.GameEntity;
import org.proskura.tictactoeserver.model.Figure;
import org.proskura.tictactoeserver.model.Game;
import org.proskura.tictactoeserver.model.Position;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GameMapperTest {

    private GameMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = GameMapper.INSTANT;
    }

    @Test
    void shouldGameEntityBeMapped() {
        GameEntity gameEntity = GameEntity.builder()
                .id(UUID.randomUUID())
                .nextMove(Figure.X)
                .board(Arrays.stream(Position.values()).collect(Collectors.toMap(position -> position, position -> Figure.EMPTY)))
                .build();

        Game game = mapper.map(gameEntity, Figure.X);

        assertEquals(gameEntity.getId(), game.getGameId());
        assertEquals(gameEntity.getNextMove(), game.getNextMove());
        assertEquals(Figure.X, game.getWinner());

        gameEntity.getBoard().forEach((position, figure) -> assertEquals(game.getBoard().get(position), figure));
    }
}