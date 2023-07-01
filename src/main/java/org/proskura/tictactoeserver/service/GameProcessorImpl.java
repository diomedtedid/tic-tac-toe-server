package org.proskura.tictactoeserver.service;

import lombok.RequiredArgsConstructor;
import org.proskura.tictactoeserver.model.common.Player;
import org.proskura.tictactoeserver.model.common.PlayerAction;
import org.proskura.tictactoeserver.model.session.Setting;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GameProcessorImpl implements GameProcessor {
    public static String FIGURE_SETTING = "FIGURE";
    @Override
    public void makeAction(PlayerAction playerAction) {

    }

    @Override
    public List<Setting> getGameSettings(Player player) {
        List<Setting> settingList = new ArrayList<>();
        return List.of(new Setting(FIGURE_SETTING, player.isBot() ? "O" : "X"));
    }
}
