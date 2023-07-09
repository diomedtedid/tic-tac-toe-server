package org.proskura.tictactoeserver.repository;

import org.proskura.tictactoeserver.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {
}
