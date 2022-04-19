
package UserIO;

import java.util.Scanner;

public class implimentationIO implements interfaceIO{
    Scanner console = new Scanner (System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
    
    @Override
    public String readString(String Prompt) {
        System.out.println(Prompt);
        String userInput = console.nextLine();
        return userInput;
    }
}
