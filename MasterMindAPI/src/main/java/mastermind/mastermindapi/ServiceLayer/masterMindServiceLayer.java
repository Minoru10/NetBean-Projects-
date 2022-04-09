package mastermind.mastermindapi.ServiceLayer;

import java.util.List;
import mastermind.mastermindapi.Model.Game;
import mastermind.mastermindapi.Model.Round;


public interface masterMindServiceLayer {
    
    List<Game> getAllGames();
    
    Game getGameById(int id);
    
    Game createGame(Game game);
    
    List<Round> getRoundsForGames(int id);
    
}
