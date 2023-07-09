package org.proskura.tictactoeserver.controller;

import lombok.RequiredArgsConstructor;
import org.proskura.tictactoeserver.model.GameAction;
import org.proskura.tictactoeserver.model.Game;
import org.proskura.tictactoeserver.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/")
    public ResponseEntity<Game> startNeGame() {
        return new ResponseEntity<>(gameService.createGame(), HttpStatus.CREATED);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<Game> doGameAction(@PathVariable UUID gameId, @RequestBody GameAction gameAction) {
        return new ResponseEntity<>(gameService.doGameAction(gameId, gameAction), HttpStatus.ACCEPTED);
    }
}
