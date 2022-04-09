package mastermind.mastermindapi.Dao;

import java.util.List;
import mastermind.mastermindapi.Model.Round;

public interface roundDao {
    
    Round addRound(Round round);
    
    List<Round> getAllRounds();
    
    List<Round> getRoundsForGame(int gameID);
    
}
