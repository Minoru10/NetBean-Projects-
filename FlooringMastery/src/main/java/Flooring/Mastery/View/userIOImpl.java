package Flooring.Mastery.View;

import java.math.BigDecimal;
import java.util.Scanner;

public class userIOImpl implements userIO{

    Scanner console = new Scanner (System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String userInput = console.nextLine();
        return userInput;    
    }

    @Override
    public int readInt(String prompt) {
        boolean notValid = true; 
        int num = 0;
        while (notValid){
            try{
                String userInput = this.readString(prompt);
                num = Integer.parseInt(userInput);
                notValid = false;
            } catch (NumberFormatException e){
                this.print("Input error. Please try again.");
            }
        }
        return num;    
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num=0;
        do{
            num = this.readInt(prompt);
        }while (num < min || num > max);
        return num;    
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
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

    @Override
    public BigDecimal readBigDecimal(String prompt, int min, int max) {
        BigDecimal num = null;
        do{
            num = this.readBigDecimal(prompt);
        }while (num.intValue() < min || num.intValue() > max);
        return num;
    }
    
}
