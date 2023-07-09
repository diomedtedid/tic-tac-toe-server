package org.proskura.tictactoeserver.task;

import lombok.RequiredArgsConstructor;
import org.proskura.tictactoeserver.service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeartbeatTask {

    private final NotificationServiceImpl notificationService;

    @Value("${app.heartbeat.message}")
    private String message;

    @Scheduled(fixedRateString = "${app.heartbeat.period}")
    public void heartbeat() {
        notificationService.notifyHeartbeat(message);
    }
}
