package org.proskura.tictactoeserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.proskura.tictactoeserver.entity.GameEntity;
import org.proskura.tictactoeserver.mapper.GameMapper;
import org.proskura.tictactoeserver.model.*;
import org.proskura.tictactoeserver.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.proskura.tictactoeserver.model.Figure.O;
import static org.proskura.tictactoeserver.model.Figure.X;
import static org.proskura.tictactoeserver.model.Position.*;
import static org.proskura.tictactoeserver.service.GameService.generateNewBoard;

@Service
@RequiredArgsConstructor
@Log4j2
public class GameServiceImpl implements GameService {
    public static final Set<Set<Position>> WON_COMBINATIONS = Set.of(
            Set.of(A1, B1, C1),
            Set.of(A2, B2, C2),
            Set.of(A3, B3, C3),
            Set.of(A1, A2, A3),
            Set.of(B1, B2, B3),
            Set.of(C1, C2, C3),
            Set.of(A1, B2, C3),
            Set.of(A3, B2, C1)
    );

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final NotificationService notificationService;
    private final BotService botService;

    @Override
    public Game createGame() {
        GameEntity newGame = GameEntity.builder()
                .board(generateNewBoard())
                .nextMove(X)
                .build();

        gameRepository.save(newGame);

        return gameMapper.map(newGame, null);
    }

    @Override
    public Game doGameAction(UUID gameId, GameAction gameAction) {
        GameEntity gameEntity = gameRepository.findById(gameId).orElseThrow();

        validateGameAction(gameEntity, gameAction);
        doAction(gameEntity, gameAction);
        gameRepository.save(gameEntity);

        Figure winner = checkWinner(gameEntity);
        Game game = gameMapper.map(gameEntity, winner);

        notificationService.notifyPlayers(game);

        if (Objects.isNull(winner)) {
            botService.getBotAction(game);
        }

        log.info("Game: {}, figure: {}, action: {}", gameId, gameAction.getFigure(), gameAction.getPosition());

        return game;
    }

    private void doAction(GameEntity gameEntity, GameAction gameAction) {
        gameEntity.getBoard().put(gameAction.getPosition(), gameAction.getFigure());
        gameEntity.setNextMove(revertFigure(gameAction.getFigure()));

    }

    private void validateGameAction(GameEntity gameEntity, GameAction gameAction) {
        if (!Objects.equals(gameAction.getFigure(), gameEntity.getNextMove())) {
            throw new RuntimeException("Now isn't your turn");
        }

        if (!Objects.equals(gameEntity.getBoard().get(gameAction.getPosition()), Figure.EMPTY)) {
            throw new RuntimeException("Sector is already used");
        }
    }

    private Figure revertFigure(Figure figure) {
        if (Objects.equals(figure, O)) {
            return X;
        }

        if (Objects.equals(figure, X)) {
            return O;
        }

        return Figure.EMPTY;
    }

    Figure checkWinner(GameEntity gameEntity) {
        Figure winner = null;
        Map<Position, Figure> board = gameEntity.getBoard();

        winCheckLoop:
        for (Figure figure : Arrays.asList(X, O)) {
            for (Set<Position> combination : WON_COMBINATIONS) {
                boolean isWonCombination = combination.stream().allMatch(position -> Objects.equals(board.get(position), figure));
                if (isWonCombination) {
                    winner = figure;
                    gameEntity.setState(GameState.FINISHED);
                    log.info("Game {} finished. The winner is {}", gameEntity.getId(), winner);

                    break winCheckLoop;
                }
            }
        }

        return winner;
    }


}
