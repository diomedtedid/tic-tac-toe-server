package org.proskura.tictactoeserver.service;

import org.proskura.tictactoeserver.model.Figure;
import org.proskura.tictactoeserver.model.GameAction;
import org.proskura.tictactoeserver.model.Game;
import org.proskura.tictactoeserver.model.Position;

import java.util.*;

public interface GameService {
    Game createGame();

    Game doGameAction(UUID gameId, GameAction gameAction);

    static Map<Position, Figure> generateNewBoard() {
        Map<Position, Figure> board = new HashMap<>();
        Arrays.stream(Position.values()).forEach(position -> board.put(position, Figure.EMPTY));
        return board;
    }
}
