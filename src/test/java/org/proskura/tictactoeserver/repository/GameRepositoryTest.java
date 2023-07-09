package org.proskura.tictactoeserver.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.proskura.tictactoeserver.entity.GameEntity;
import org.proskura.tictactoeserver.model.Figure;
import org.proskura.tictactoeserver.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GameRepositoryTest {

    @Autowired
    private GameRepository repository;

    @AfterEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldGameEntityBePersisted() {
        assertEquals(0, repository.count());
        GameEntity saved = repository.save(createGame());
        assertEquals(1, repository.count());
        assertNotNull(saved.getId());

    }

    @Test
    void shouldGameEntityBeFoundById() {
        GameEntity newGame = createGame();
        repository.save(newGame);

        GameEntity gameEntity = repository.findById(newGame.getId()).get();
        assertEquals(newGame, gameEntity);


    }

    private GameEntity createGame() {
        return GameEntity.builder()
                .board(Arrays.stream(Position.values()).collect(Collectors.toMap(position -> position, position -> Figure.EMPTY)))
                .nextMove(Figure.X).build();
    }
}