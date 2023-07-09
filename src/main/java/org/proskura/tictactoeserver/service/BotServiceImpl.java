package org.proskura.tictactoeserver.service;

import lombok.RequiredArgsConstructor;
import org.proskura.tictactoeserver.model.Figure;
import org.proskura.tictactoeserver.model.Game;
import org.proskura.tictactoeserver.model.GameAction;
import org.proskura.tictactoeserver.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {
    private final static Figure BOT_FIGURE = Figure.O;

    @Autowired
    @Lazy
    private GameService gameService;


    @Override
    public void getBotAction(Game game) {
        CompletableFuture.runAsync(() -> {
            boolean isBotTurn = Objects.equals(BOT_FIGURE, game.getNextMove());
            boolean isGameNotFinished = Objects.isNull(game.getWinner());

            if (isGameNotFinished && isBotTurn) {
                delay(3);
                Position emptyPosition = game.getBoard().entrySet().stream()
                        .filter(entry -> Objects.equals(Figure.EMPTY, entry.getValue()))
                        .findFirst().orElseThrow()
                        .getKey();
                gameService.doGameAction(game.getGameId(), new GameAction(BOT_FIGURE, emptyPosition));
            }
        });


    }

    private void delay(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
