package PracticeProjectNew;

import java.util.Scanner;

/**
 * Created by anmolb77 on 11/21/2015.
 */
public class ConcentricCircle {
    public static void main(String arg[]){
        Scanner input = new Scanner(System.in);
        //System.out.println("Enter the width of the square. \n It should be an odd number starting from 1 and increasing by multiples of 4 greater that 5.");
        int length = 6;//Math.abs(input.nextInt());
        if( length % 2 == 1 && length >= 5){
            for (int i = 1; i <= length; i++){
                System.out.println();
                for (int j = 1; j <= length; j++){

                    if(i % 2 == 0){
                        if(j == 1 || j == length) {
                            System.out.print(" * ");
                        }else if((i < ((length / 2) + 1) && (j % 2 == 1 && (j < i || j > (length - i)))) || (i >= ((length / 2) + 1) && (j % 2 == 1 && (j > i || j < ((length - (i - 1) ))))) ){
                            System.out.print(" * ");
                        }else{
                            System.out.print("   ");
                        }
                    }else if(i % 2 == 1 && i != 1 && i != length){

                        if( ((i < (length/2))&&(j % 2 == 0 && ((j < i || j > (length - i)))) || ((i > length/2)&&(j % 2 == 0 && ((j > i || j <= (length - i))))))){
                            System.out.print("   ");
                        }else{
                            System.out.print(" * ");
                        }

                    }else{
                        System.out.print(" * ");
                    }
                }
            }
        }else{
            System.out.println("\n ============ \n Please Enter Proper Input as instructed \n ============= \n");
        }
    }
}
