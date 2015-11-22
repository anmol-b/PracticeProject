/**
 * Created by anmolb77 on 10/11/2015.
 */
import java.io.*;
import java.util.Scanner;

class dateCalc
{
    public static void main(String args[])
    {
        int incrememtByDays;
        String[] dateEntered = new String[];
        System.out.println("Enter date in format mm/dd/yyy");
        Scanner s = new Scanner(System.in);
        dateEntered = s.next().split("/");
        System.out.println("Enter the number of days to be incremented.");
        incrememtByDays = s.nextInt();

        dateCalc d = new dateCalc();

        System.out.print(" \n\n The new date is : " + d.calcNextDate(dateEntered, incrememtByDays));
    }

    public String calcNextDate(String dateEntered[], int incrementByDays){
        int month, day, year, incrementbyYears, tmp, daysAlreadySpent, incrementedDays, yearDifference;
        int newDay = 0;
        int newMonth = 0;
        int newYear = 0;
        String newDate = "";

        month = Integer.parseInt(dateEntered[0]);
        day = Integer.parseInt(dateEntered[1]);
        year = Integer.parseInt(dateEntered[2]);

        int[] yearByMonths = new int[12];

        yearByMonths[0] = 31;
        yearByMonths[1] = checkLeapYear(year);
        yearByMonths[2] = 31;
        yearByMonths[3] = 30;
        yearByMonths[4] = 31;
        yearByMonths[5] = 30;
        yearByMonths[6] = 31;
        yearByMonths[7] = 31;
        yearByMonths[8] = 30;
        yearByMonths[9] = 31;
        yearByMonths[10] = 30;
        yearByMonths[11] = 31;



        incrementbyYears = (incrementByDays/365);

       // year = year + incrementbyYears;

        tmp = incrementByDays - (incrementbyYears * 365);
        daysAlreadySpent = 0;

        for (int i = 0; i < month; i++){
            daysAlreadySpent = daysAlreadySpent + yearByMonths[i];
        }

        daysAlreadySpent = daysAlreadySpent + day;

        incrementedDays = daysAlreadySpent + tmp;

        while(incrementedDays > 365){
            incrementedDays = incrementedDays - 365;
            incrementbyYears++;
        }

        int j = 0;

        while(incrementedDays > yearByMonths[j]){
            incrementedDays = incrementedDays - yearByMonths[j];
            j++;
        }

        newDay = incrementedDays;
        newMonth = j;
        newYear = year + incrementbyYears;

        yearDifference = newYear - year;
//adjusting date by considering the leap years in between the incremented years.
        int tempYear = year;

        for (int i = 1; i <= yearDifference; i++) {
            tempYear = tempYear + 1;
            if (checkLeapYear(tempYear) == 29){
                if (yearDifference <= newDay) {
                    newDay--;
                }else{
                    break;
                }
            }

        }



        newDate = Integer.toString(newMonth) + '/' + Integer.toString(newDay) + '/' + Integer.toString(newYear);

            return newDate;

    }

    public int checkLeapYear(int year){
        int daysInFeb;

        if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)){
            daysInFeb = 29;
        }else{
            daysInFeb = 28;
        }

        return daysInFeb;
    }
}