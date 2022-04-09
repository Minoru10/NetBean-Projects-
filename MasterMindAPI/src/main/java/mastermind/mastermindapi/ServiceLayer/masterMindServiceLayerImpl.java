package mastermind.mastermindapi.ServiceLayer;

import java.util.List;
import java.util.Random;
import mastermind.mastermindapi.Dao.gameDbDao;
import mastermind.mastermindapi.Dao.roundDbDao;
import mastermind.mastermindapi.Model.Game;
import mastermind.mastermindapi.Model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class masterMindServiceLayerImpl implements masterMindServiceLayer {
    
    @Autowired
    private gameDbDao gameDao;
    
    @Autowired
    private roundDbDao roundDao;
    
    @Autowired
    public masterMindServiceLayerImpl(gameDbDao gameDao, roundDbDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    
    Random rand = new Random(123);
// ==================================== Create and add a game to the table ===============================
    @Override
    public Game createGame(Game game) {
        String answer = String.format("%04d", rand.nextInt(10000));
        game.setInProgress(true);
        game.setAnswer(answer);
        gameDao.createGame(game);
        return game;
    }
// ================================== Get All Games in the table =======================================
    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }
    @Override
    public Game getGameById(int id) {
        Game game = gameDao.getGameById(id);
        String tempanswer = game.getAnswer();
        if (game.isInProgress())
            game.setAnswer("****");
        return game;
    }

// ==================================== Create and add a game to the table ===============================
    @Override
    public List<Round> getRoundsForGames(int id) {
        return roundDao.getRoundsForGame(id);
    }
    
//    public Round createGuess(Round round) {
//        round = roundDao.addRound(round);
//        int gameID = round.getGameID();
//        
//        Game gameLinkedToRound = getAllGames().stream()
//                                              .filter(g -> g.getId() == gameID)
//                                              .findFirst()
//                                              .get();
//        String answer = gameLinkedToRound.getAnswer();
//        String result = getResult(round.getGuess(), answer);
//        if (result.equals("e:4:p:0")){
//            gameLinkedToRound.setInProgress(false);
//            round.setResult(result);
//            return round;
//        }
//        return round;
//    }
    public Round createGuess(Round round) {
        
        Round r = roundDao.addRound(round);
        int gameID = r.getGameID();
//        round.setGameID(round.getGameID());
////        round.setGuess(guess);
//        
        Game gameLinkedToRound = getAllGames().stream()
                                              .filter(g -> g.getId() == round.getGameID())
                                              .findFirst()
                                              .get();
        String answer = gameLinkedToRound.getAnswer();
        String result = getResult(r.getGuess(), answer);
        r.setResult(result);
        if (result.equals("e:4:p:0")){
            gameLinkedToRound.setInProgress(false);
            return r;
        }
        //return roundDao.addRound(round);
        return r;
    }
    
    public String getResult (String guess, String answer){
        
        int e=0, p=0;
        if (guess.equals(answer))
            return "e:4:p:0";
        
        for (int i=0; i<answer.length(); i++){
            if (guess.charAt(i) == answer.charAt(i))
                e += 1;
        }
        if ( guess.charAt(0)==answer.charAt(1) || guess.charAt(0) == answer.charAt(2) || guess.charAt(0) == answer.charAt(3))
            p += 1;
        if ( guess.charAt(1)==answer.charAt(0) || guess.charAt(1) == answer.charAt(2) || guess.charAt(1) == answer.charAt(3))
            p += 1;
        if ( guess.charAt(2)==answer.charAt(0) || guess.charAt(2) == answer.charAt(1) || guess.charAt(2) == answer.charAt(3))
            p += 1;
        if ( guess.charAt(3)==answer.charAt(0) || guess.charAt(3) == answer.charAt(1) || guess.charAt(3) == answer.charAt(2))
            p += 1;
        
        
        return "e:"+e+"p:"+p;
    }
    
    
}
