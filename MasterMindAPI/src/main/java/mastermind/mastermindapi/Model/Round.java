package mastermind.mastermindapi.Model;


public class Round {
    
    private int Id;
    private String result;
    private String timeStamp;
    private String guess;
    private int gameId;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getGameID() {
        return gameId;
    }

    public void setGameID(int game) {
        this.gameId = game;
    }
    
    
}
