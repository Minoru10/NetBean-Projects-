package mastermind.mastermindapi.controller;

import java.util.List;
import mastermind.mastermindapi.Model.Game;
import mastermind.mastermindapi.Model.Round;
import mastermind.mastermindapi.ServiceLayer.masterMindServiceLayerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/masterMind")
public class masterMindController {
    
    private final masterMindServiceLayerImpl service;

    public masterMindController(masterMindServiceLayerImpl service) {
        this.service = service;
    }
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame(@RequestBody Game game) {
            return service.createGame(game).getId();
    }
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round createGuess(@RequestBody Round round) {
            return service.createGuess(round);
    }
    
    @GetMapping("/game")
    public List<Game> all() {
        return service.getAllGames();
    }
    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game result = service.getGameById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/round/{id}")
    public List<Round> roundsForGame(@PathVariable int id) {
        return service.getRoundsForGames(id);
    }
    
}
