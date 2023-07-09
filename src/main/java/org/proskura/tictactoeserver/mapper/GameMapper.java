package org.proskura.tictactoeserver.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.proskura.tictactoeserver.entity.GameEntity;
import org.proskura.tictactoeserver.model.Figure;
import org.proskura.tictactoeserver.model.Game;
import org.proskura.tictactoeserver.model.Position;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GameMapper {
    GameMapper INSTANT = Mappers.getMapper(GameMapper.class);

    @Mapping(source = "source.id", target = "gameId")
    Game map (GameEntity source, Figure winner);
}
