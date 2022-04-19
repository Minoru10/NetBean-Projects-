package classroster.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    
    Scanner console = new Scanner (System.in);

    /*
    * This is a hard coded print method
    */
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
    /*
    * This method displays an output string asking the user for some type of information and returns the input the user enters.
    */
    @Override
    public String readString(String Prompt) {
        System.out.println(Prompt);
        String userInput = console.nextLine();
        return userInput;
    }
    /*
    * This method validates a user's input to be an integer and returns that input
    */
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
    /*
    * This method uses the previous readInt method to recieve a valid 
    *  integer input and validates it is between a min and max value.
    */
    @Override
    public int readInt(String prompt, int min, int max) {
        int num=0;
        do{
            num = this.readInt(prompt);
        }while (num < min || num > max);
        return num;
    }
    /*
    * This method validates a user's input to be a double and returns that input
    */
    @Override
    public double readDouble(String prompt) {
        
        while (true){ // loop to keep asking user for valid double
           try { // try/catch to catch any errors
               String userInput = readString(prompt); // ask the user for a double entered as a string and store the input in variable
               return Double.parseDouble(userInput); //parse the user input from string to double and return the it
           } catch (NumberFormatException e){  //catch invalid inputs.
               this.print("error: Invalid input for double"); // display message for any invalid inputs
           }
       }
        
    }
    /*
    * This method uses the previous readDouble method to recieve a valid 
    *  double input and validates it is between a min and max value.
    */
    @Override
    public double readDouble(String prompt, double min, double max) {
        double num=0;
        do{
            num = this.readDouble(prompt);
        }while (num < min || num > max);
        return num;  
    }
    /*
    * This method validates a user's input to be a float and returns that input
    */
    @Override
    public float readFloat(String prompt) {
        
        while (true){ // loop to keep asking user for valid float
           try { // try/catch to catch any errors
               String userInput = readString(prompt); // ask the user for a float entered as a string and store the input in variable
               return Float.parseFloat(userInput); //parse the user input from string to float and return the it
           } catch (NumberFormatException e){  //catch invalid inputs.
               this.print("error: Invalid input for float"); // display message for any invalid inputs
           }
       }
        
    }
    /*
    * This method uses the previous readFloat method to recieve a valid 
    *  float input and validates it is between a min and max value.
    */
    @Override
    public float readFloat(String prompt, float min, float max) {
        float num=0;
        do{
            num = this.readFloat(prompt);
        }while (num < min || num > max);
        return num;    
    }
    /*
    * This method validates a users input to be a long integer and returns the input
    */
    @Override
    public long readLong(String prompt) {
      
       while (true){ // loop to keep asking user for valis long
           try { // try/catch to catch any errors
               String userInput = readString(prompt); // ask the user for a long entered as a string and store the input in variable
               return Long.parseLong(userInput); //parse the user input from string to long and return the it
           } catch (NumberFormatException e){  //catch invalid inputs.
               this.print("error: Invalid input for long"); // display message for any invalid inputs
           }
       }
       
    }
    /*
    * This method uses the previous readLong method to recieve a valid 
    *  long input and validates it is between a min and max value.
    */
    @Override
    public long readLong(String prompt, long min, long max) {
        long num=0;
        do{
            num = this.readLong(prompt);
        }while (num < min || num > max);
        return num;
    }
}
