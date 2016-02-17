package PracticeProjectNew;

import java.util.Scanner;

/**
 * Created by anmolb77 on 2/16/2016.
 */
public class LesserNumber {
    public static void main(String a[]){
        int inputNumber;

        Scanner in = new Scanner(System.in);
        LesserNumber obj = new LesserNumber();
        System.out.print("Enter a number.");
        inputNumber = in.nextInt();
        String z = String.valueOf(inputNumber);
        int digit_n = 0;
        int digit_prev = 0;
        int temp = 0;
        int[] digitsOfNumber = new int[z.length()];
        for (int i = 1; i < z.length(); i++ ){
            digit_n = inputNumber % ((int)(Math.pow(10,i)));
            digit_prev = (inputNumber % ((int)Math.pow(10,i+1)) - digit_n) / ((int)Math.pow(10,i));

            digitsOfNumber[i-1] = digit_n;
            digitsOfNumber[i] = digit_prev;

            if (digit_prev > digit_n){
                temp = digit_prev;
                digit_prev = digit_n;
                digit_n = temp;
                digitsOfNumber[i-1] = digit_n;
                digitsOfNumber[i] = digit_prev;

                temp = inputNumber - inputNumber % (int)Math.pow(10,i+1);
                int temp1 =  obj.getResidualNumber(digitsOfNumber);
                inputNumber = temp + temp1;

                break;
            }else{
                continue;
            }
        }

        System.out.println("\n The closest number lesser than the input is :" + inputNumber);
    }

    int getResidualNumber(int[] digitsOfNumber){
        String concatNum ="";
        for (int j = digitsOfNumber.length-1; j >= 0; j--){
            concatNum = concatNum + String.valueOf(digitsOfNumber[j]);
        }

        return Integer.valueOf(concatNum);
    }
}
