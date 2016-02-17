package PracticeProjectNew;

/**
 * Created by anmolb77 on 1/30/2016.
 */
public class Compress{

    public static void main(String arg[]){
    String inp = "aaaaabbbbccccddddfghjuuuuuuLLLLLLLz"; //Sample String input.
    char[] arr;
    arr = inp.toCharArray();
    int counter = 1;
    String res = "";
    for(int i = 0; i < inp.length() - 1; i++){

        if (arr[i] == arr[i + 1]) {
            counter++;
            if (i == inp.length() - 2){
                res += String.valueOf(arr[i]) + ((counter == 1) ? ("") : (String.valueOf(counter)));
            }
        } else {
            res += String.valueOf(arr[i]) + ((counter == 1) ? ("") : (String.valueOf(counter)));
            counter = 1;
            if(i == inp.length() - 2){
                res = res + String.valueOf(arr[i + 1]);
            }
            continue;
        }
    }
        System.out.println(res);
    }
}
