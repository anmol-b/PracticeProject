package PracticeProjectNew;

/**
 * Created by anmolb77 on 11/22/2015.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Scanner;

public class PrintDesign2 {
    Scanner scan;

    public PrintDesign2() {
        this.scan = new Scanner(System.in);
    }

    public static void main(String[] args) {
        PrintDesign2 print = new PrintDesign2();
        print.generateSquare();
    }

    public void generateSquare() {
        boolean lines = false;
        System.out.print("\nEnter odd number: ");
        int var6 = this.scan.nextInt();
        System.out.println("\n");

        for(int i = 0; i < var6; ++i) {
            int y = i;

            for(int j = 0; j < var6; ++j) {
                if((i % 2 != 0 || j < i || j > var6 - 1 - i) && (i % 2 != 0 || j > i || j < var6 - 1 - i)) {
                    if((j % 2 != 0 || y < j || y > var6 - 1 - j) && (j % 2 != 0 || y > j || y < var6 - 1 - j)) {
                        System.out.print("  ");
                    } else {
                        System.out.print("* ");
                    }
                } else {
                    System.out.print("* ");
                }
            }

            System.out.println();
        }

    }
}

