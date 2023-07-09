package org.proskura.tictactoeserver.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class Game {
    private UUID gameId;
    private Map<Position, Figure> board;
    private Figure nextMove;
    private Figure winner;
}
