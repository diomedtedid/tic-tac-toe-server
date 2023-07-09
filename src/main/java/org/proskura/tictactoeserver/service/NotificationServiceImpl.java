package org.proskura.tictactoeserver.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.proskura.tictactoeserver.model.Game;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationServiceImpl implements NotificationService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Value("${app.ws.gameTopic}")
    private String gameTopicPrefix;

    @Value("${app.ws.heartbeatTopic}")
    private String heartbeatTopic;

    public void notifyPlayers(Game game) {
        log.info("Notifying players for game: {}", game.getGameId());
        log.info("Topic: {}", gameTopicPrefix + game.getGameId());
        simpMessagingTemplate.convertAndSend(gameTopicPrefix + game.getGameId(), new Gson().toJson(game));
    }

    public void notifyHeartbeat(String message) {
        log.info("Heartbeat. topic: {}, message {}", heartbeatTopic, message);
        simpMessagingTemplate.convertAndSend(heartbeatTopic, message);
    }

    @Override
    public String getGameTopicPrefix() {
        return gameTopicPrefix;
    }
}
