
package tongwa.rockpaperscissors;

public class SummativeSums {
    public static void main(String []args){

        int[] arr = new int[] { 1, 90, -33, -55, 67, -16, 28, -55, 15 }; //declare int array with values 
        int[] arr2 = new int[] { 999, -60, -77, 14, 160, 301 }; 
        int[] arr3 = new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99 };
        System.out.print("#1 Array sum: " + addArray(arr) + "\n"); // compute summation and print
        System.out.print("#2 Array sum: " + addArray(arr2) + "\n");
        System.out.print("#3 Array sum: " + addArray(arr3));
    }

    private static int addArray(int[] arr) {
        int result = 0; //store sum variable
        for (int i=0; i<arr.length; i++){ // for through the array 
            result += arr[i]; //add values in array to the result
        }
        
        return result; 
    }
}
