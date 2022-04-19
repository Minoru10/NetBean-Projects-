package vMachineView;

import java.math.BigDecimal;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class userIOimpl implements userIO{
    Scanner console = new Scanner (System.in);

// This is a hard coded print method. prints any statement 
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
// This method displays an output string asking the user for some type of information and returns the input the user enters.
    @Override
    public String readString(String Prompt) {
        System.out.println(Prompt);
        String userInput = console.nextLine();
        return userInput;
    }
// This method validates a user's input to be an integer and returns that input
    @Override
    public int readInt(String Prompt){
        boolean notValid = true; 
        int num = 0;
        while (notValid){
            try{
                String userInput = this.readString(Prompt);
                num = Integer.parseInt(userInput);
                notValid = false;
            } catch (NumberFormatException e){
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }
//This method uses the previous readInt method to recieve a valid integer input and validates it is between a min and max value.
    @Override
    public int readInt(String prompt, int min, int max) {
        int num=0;
        do{
            num = this.readInt(prompt);
        }while (num < min || num > max);
        return num;
    }
// This method validates user input to be compatible with BigDecimal and returns that input.
    @Override
    public BigDecimal readBidDecimal(String prompt) {
        boolean notValid = true;
        BigDecimal value = null;
        while (notValid){
            try{
                value = new BigDecimal(readString(prompt));
                notValid = false;
            } catch (NumberFormatException e){
                this.print("Input error. Please try again.");
            }
        }
        return value;
    }
    
}
