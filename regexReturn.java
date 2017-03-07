package PracticeProjectNew;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anmolb77 on 3/6/2017.
 */
public class regexReturn {

    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
        System.out.print("Enter two Strings on seperate lines \n");
        Scanner s = new Scanner(System.in);
        String a = "";
        String b = "";

         a = s.nextLine();
         b = s.nextLine();

        String regex ="[^";
        String temp;
        for(int i = 0; i<a.length(); i++){
            temp = "";
            temp += a.charAt(i);
            if(!regex.contains(temp)){
                regex += "(" + a.charAt(i) + ")*";
            }
        }
        regex += "]";
        Pattern P = Pattern.compile(regex);
        Matcher m = P.matcher(b);
        System.out.print( "The set of extra characters in \""+b+"\"  as compared to \""+a+"\" are :: ");
        while (m.find()){
            System.out.print(m.group(0));
        }
    }
}
