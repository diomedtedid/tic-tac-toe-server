package org.proskura.tictactoeserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GameAction {
    Figure figure;
    Position position;
}
