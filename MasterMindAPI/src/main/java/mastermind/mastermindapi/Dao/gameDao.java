package mastermind.mastermindapi.Dao;

import java.util.List;
import mastermind.mastermindapi.Model.Game;


public interface gameDao {
    
    List<Game> getAllGames();
    
    Game getGameById(int id);
    
    Game createGame(Game game);
    
    
}
