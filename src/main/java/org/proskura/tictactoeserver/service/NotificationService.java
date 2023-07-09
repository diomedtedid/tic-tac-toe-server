package org.proskura.tictactoeserver.service;

import org.proskura.tictactoeserver.model.Game;

public interface NotificationService {
    void notifyPlayers(Game game);
    void notifyHeartbeat(String message);
    String getGameTopicPrefix();
}
