package org.proskura.tictactoeserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.proskura.tictactoeserver.converter.JpaMapConverter;
import org.proskura.tictactoeserver.model.Figure;
import org.proskura.tictactoeserver.model.GameState;
import org.proskura.tictactoeserver.model.Position;

import java.util.Map;
import java.util.UUID;

@Entity
@Table (name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "board")
    @Convert(converter = JpaMapConverter.class)
    private Map<Position, Figure> board;

    @Column(name = "next_move")
    @Enumerated(EnumType.STRING)
    private Figure nextMove;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private GameState state = GameState.NEW;
}
