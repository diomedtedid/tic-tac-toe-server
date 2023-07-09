package org.proskura.tictactoeserver.service;

import org.proskura.tictactoeserver.model.Game;

public interface BotService {
    void getBotAction(Game game);
}
