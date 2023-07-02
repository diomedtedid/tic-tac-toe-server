package org.proskura.tictactoeserver.model.session;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class Player {
    @NotNull
    private UUID id;
    private String userName;
    @NotNull
    private Boolean isBot;
}
