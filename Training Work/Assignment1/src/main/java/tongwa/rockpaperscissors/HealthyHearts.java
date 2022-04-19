
package tongwa.rockpaperscissors;

import java.util.Scanner;

public class HealthyHearts {
    
    static int max = 220;
    public static void main(String []args){
        
//        Their maximum heart rate should be 220 - their age.
//        The target heart rate zone is the 50 - 85% of the maximum.
//                
//        What is your age? 50
//        Your maximum heart rate should be 170 beats per minute
//        Your target HR Zone is 85 - 145 beats per minute
        Scanner in = new Scanner (System.in);
        System.out.print("What is your age? ");
        int age = in.nextInt();
        calcMaxHeartRate(age);
     
    }
    
    public static void calcMaxHeartRate(int age){
        
         max -= age;
         double minTargetZone = Math.round(max*.50);
         double maxTargetZone = Math.round(max*.85);
         
         System.out.println("Your maximum heart rate should be " + max + " beats per minute");
         System.out.println("Your target HR Zone is " + minTargetZone + " - " + maxTargetZone + " beats per minute");
    }
}
