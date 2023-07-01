package org.proskura.tictactoeserver.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.proskura.tictactoeserver.model.common.Player;
import org.proskura.tictactoeserver.model.session.Setting;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.proskura.tictactoeserver.service.GameProcessorImpl.FIGURE_SETTING;

class GameProcessorImplTest {

    private GameProcessorImpl gameProcessor;

    @BeforeEach
    void setUp() {
        gameProcessor = new GameProcessorImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void makeAction() {
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void shouldGameSettingsBeGot(boolean isBot) {
        Player player = new Player();
        player.setBot(isBot);

        Setting figureSetting = gameProcessor.getGameSettings(player).stream()
                .filter(setting -> Objects.equals(setting.getSetting(), FIGURE_SETTING))
                .findFirst()
                .orElseThrow();


        if (isBot) {
            assertEquals("O", figureSetting.getValue());
        } else {
            assertEquals("X", figureSetting.getValue());
        }
    }
}