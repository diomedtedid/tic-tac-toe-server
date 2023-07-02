package org.proskura.tictactoeserver.model.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "player")
public class PlayerEntity {

    @Id
    private UUID id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "is_bot")
    private Boolean isBot;
}
