package tongwa.rockpaperscissors;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    
    static int playerWins=0, computerWins=0, Ties=0;
    static String answer;
    
    public static void main(String[] args) {
        rockPaperScissors(); // Run the rockPaperScissors method
    }

    public static void rockPaperScissors() {
        
        Scanner in = new Scanner(System.in); //declare input class
        Random randomizer = new Random(); //declare random class
        int rounds=0, user, computer; //variables storing #rounds, user choice, computer choice
        
        //Enter a valid number of rounds (1 <= #rounds <= 10)
        //do{
            System.out.print("Enter number of rounds: ");
            rounds = in.nextInt();
            if (rounds<1 || rounds>10){
                System.out.println("Error, invalid number of rounds");
                System.exit(0);
            }
        //}while (rounds<1 || rounds>10 );
        
        // Prompt user to enter a valid choice of rock(1), paper(2), or scissors(3)
        for (int i=0; i<rounds; i++){
            do{
                System.out.print("Enter 1(rock), 2(paper), or 3(scissors): ");
                user = in.nextInt();
            }while(user<1 || user>3);
        
        // computer randomly pick rock(1), paper(2), or scissors(3) and print to console
            computer = randomizer.nextInt(3)+1;
            System.out.print("Computer chose: " + computer + "\n");
        
        // Use the getWinner metod to get a winner for each round and update scores.
            if ( getWinner(user, computer).equals("user")) 
                playerWins += 1;
            else if ( getWinner(user,computer).equals("computer"))
                    computerWins += 1;
            else
                    Ties += 1;
        }
        System.out.print("Game Ties:" + Ties + " User Wins:" + playerWins + " Computer Wins:" + computerWins + "\n");
        
        //Ask user if they want to play another game
        System.out.println("\nPlay again?");
        answer = in.next();
        //reset scores and start over
        if (answer.equals("yes")){
            playerWins=0;
            computerWins=0; 
            Ties=0;
            rockPaperScissors();
        }
        else {
            System.out.print("Thank you for playing");
        }
    }
    
    public static String getWinner(int user, int computer){
        String result = "";
        if (user == computer)
            result = "Tie";
        else if (user == 1 && computer == 2)
            result = "computer";
        else if (user == 1 && computer == 3)
            result = "user";
        else if (user == 2 && computer == 1)
            result = "user";
        else if (user == 2 && computer == 3)
            result = "computer";
        else if (user == 3 && computer == 1)
            result = "computer";
        else if (user == 3 && computer == 2)
            result = "user";
        
        return result;
    }
}
