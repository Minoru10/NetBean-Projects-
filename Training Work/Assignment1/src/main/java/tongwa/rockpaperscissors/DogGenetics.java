package tongwa.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

public class DogGenetics {
    static int p1, p2, p3, p4, p5; //Breed persentage variables 
    public static void main(String []args){
        Scanner input = new Scanner (System.in); //Scanner object declaration 
        
        System.out.print("What is your dogs Name: "); //Ask dog name
        String name = input.nextLine(); //store dog name 
        
        System.out.print("Well then, I have this highly reliable report on " + name + " prestigious background right here.\n"); //display information
        getReport(name); //calculate dog breed report and percentages
    }
    
    
    public static void getReport(String name){
        Random randomizer = new Random(); //declare randon generator object
        System.out.println("Sir Fluffy McFlufferkins Esquire is:\n");
        
        p1 = randomizer.nextInt(0,101); //generate random percentage between 0 to 100
        System.out.println(p1 + "% St. Bernard");
        p2 = randomizer.nextInt(0, 100-p1 + 1); //generate random % between 0 to 100-p1
        System.out.println(p2 + "% Chihuahua");
        p3 = randomizer.nextInt(0, 100-p1-p2 + 1);//generate random % between 0-100-p1-p2
        System.out.println(p3 + "% Dramatic RedNosed Asian Pug");
        p4 = randomizer.nextInt(0, 100-p1-p2-p3 + 1); //generate random % between 0-100-p1-p2-p3
        System.out.println(p4 + "% Common Cur"); 
        p5 = 100-p1-p2-p3-p4; //store remaining sum adding to 100 in p5
        System.out.println(p5 + "% King Doberman"); 
        
        System.out.println("\nWow, that's QUITE the dog!");
    }
}
