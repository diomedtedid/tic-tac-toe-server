package org.proskura.tictactoeserver.service.player;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.proskura.tictactoeserver.model.player.PlayerEntity;
import org.proskura.tictactoeserver.model.session.Player;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerMapper {

    PlayerMapper INSTANT = Mappers.getMapper(PlayerMapper.class);

    Player map(PlayerEntity source);
    List<Player> map (List<PlayerEntity> source);
}
