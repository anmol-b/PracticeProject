import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anmolb77 on 3/8/2017.
 */
public class DirectoryCheck {
    private String regex = "";
    private Pattern P;
    private Matcher M;
    private String name = "";
    private String number = "";
    private int telNumStrtIndex = 0;
    private String input = "";
    private String command = "";
    private FileWriter fwObj = null;
    private FileReader frObj = null;
    private BufferedReader br = null;
    private String str = "";
    private HashMap<String, String> parseString;
    private boolean isNumber = false;


    private void add(){
        if(isValidName() && isValidNumber()){
            System.out.println("Valid name and number");
            try{
                if(writeToFile(this.name + "@" +  this.number)){
                    System.out.print("Successfully added entry. Bye!");
                    System.exit(0);
                }
            }catch (IOException e){
                error("An error occured while writing to file.");
            }
        }else{
            error("Invalid input.");
        }
    }

    private void list(){
        System.out.print("List of Members :\n");
        try{
            HashMap<String, String> map = readFromFile();
            Iterator i = map.entrySet().iterator();
            while (i.hasNext()){
                HashMap.Entry entry = (HashMap.Entry)i.next();
                System.out.println(entry.getKey().toString() + " " + entry.getValue().toString());
            }
        }catch (IOException e){
            error("Error occured while reading the file");
        }
    }

    private void delName() {
        try{
            this.parseString = readFromFile();
            String temp = parseString.get(this.name);
            if(!temp.equals(null)){
                parseString.remove(this.name);
                reWriteToFile(parseString);
                System.out.println("Successfully deleted entry for " + this.name);
            }else{
                error("Couldn't find the given name.");
            }
        }catch (IOException e){

        }
    }

    private void delTelephone() {
        try{
            this.parseString = readFromFile();
            Iterator i = this.parseString.entrySet().iterator();
            while (i.hasNext()){
                HashMap.Entry e = (HashMap.Entry)i.next();
                if(e.getValue().equals(this.number)){
                    this.name = e.getKey().toString();
                }
            }
            if(!this.name.equals("")){
                parseString.remove(this.name);
                reWriteToFile(parseString);
                System.out.println("Successfully deleted entry for " + this.number);
            }else{
                error("Couldn't find the given number.");
            }
        }catch (IOException e){

        }
    }

    private boolean isValidName(){
        this.regex = "[^a-zA-Z,\\s'-]+";
        P = Pattern.compile(this.regex);
        M = P.matcher(name);
        if(M.find()){
            error("Invalid Name.");
        }else{
            this.regex = "[a-zA-Z]+[,'-.]?[ ]?[a-zA-Z]*['-]?[a-zA-Z]*['-]?[a-zA-Z]*[ ]?[a-zA-Z]*['-]?[a-zA-Z]*['-]?[a-zA-Z]*";
            P = Pattern.compile(this.regex);
            M = P.matcher(name);
            if(M.find()){
                name = M.group(0);
                return true;
            }else{
                error("Invalid name format.");
            }
        }
        return false;
    }

    private boolean isValidNumber() {
        this.regex = "[^0-9\\s\\(\\)\\+-]+";
        P = Pattern.compile(this.regex);
        M = P.matcher(number);
        if(M.find()){
            error("Invalid Number.");
        }else{
            this.regex = "([\\+]?[0-9]{1,3}[ ]?[\\(]?[0-9]{2,3}[\\)]?[ ]?[0-9]*[-]?[ ]?[0-9]*)|([\\(]?[0-9]+[\\)]?[ ]?[0-9]*[-]?[ ]?[0-9]*[ ]?[0-9]*)";
            P = Pattern.compile(this.regex);
            M = P.matcher(number);
            if(M.find()){
                number = M.group(0);
                return true;
            }else{
                error("Invalid number format.");
            }
        }
        return false;
    }

    private void error(String m){
        System.out.print( m + "\nPlease use appropriate commands \n ADD <Name> <Telephone number> \n DEL <Name> \n DEL <Number> \n LIST \n");
        System.exit(0);
    }

    private boolean writeToFile(String entry) throws IOException {
        try{
            File writeToFile = new File("C:\\TelephoneDirectory\\telephone.directory");
            if(writeToFile.createNewFile()){
                this.fwObj = new FileWriter(writeToFile);
                this.fwObj.write(entry+"\n");
                return true;
            }
            else{
                //writeToFile.createNewFile();
                this.frObj = new FileReader("C:\\TelephoneDirectory\\telephone.directory");
                this.br = new BufferedReader(frObj);
                this.str = new String();
                String temp;
                while((temp = br.readLine())!= null){
                    this.str += temp + "\n";
                }

                this.fwObj = new FileWriter(writeToFile);
                this.fwObj.write(this.str + entry+"\n");
                return true;
            }

        }
        catch(Exception e){
            error("Error occured while writing to file." + e);
        }
        finally{
            if(fwObj != null){
                fwObj.flush();
                fwObj.close();
            }
        }
        return false;
    }

    private boolean reWriteToFile(HashMap<String,String> map) throws IOException {
        try{
            File writeToFile = new File("C:\\TelephoneDirectory\\telephone.directory");
            //writeToFile.createNewFile();
            this.frObj = new FileReader("C:\\TelephoneDirectory\\telephone.directory");
            this.br = new BufferedReader(frObj);
            this.str = new String();
            String temp;
            Iterator i = this.parseString.entrySet().iterator();
            while(i.hasNext()){
                HashMap.Entry e = (HashMap.Entry)i.next();
                this.str += e.getKey().toString() + "@" + e.getValue() + "\n";
            }

            this.fwObj = new FileWriter(writeToFile);
            this.fwObj.write(this.str);
            return true;
        }
        catch(Exception e){
            error("Error occured while writing to file." + e);
        }
        finally{
            if(fwObj != null){
                fwObj.flush();
                fwObj.close();
            }
        }
        return false;
    }

    private HashMap<String,String> readFromFile() throws IOException{
        try{

            File checkFile = new File("C:\\TelephoneDirectory\\telephone.directory");
            if(!checkFile.createNewFile()){
                this.frObj = new FileReader("C:\\TelephoneDirectory\\telephone.directory");
                this.br = new BufferedReader(this.frObj);
                this.str = new String();
                //ArrayList<String> parseString = new ArrayList();
                this.parseString = new HashMap<String, String>();
                while((this.str = this.br.readLine())!=null){
                    parseString.put(this.str.split("@")[0],this.str.split("@")[1]);
                }
                return parseString;
            }

        }
        catch(Exception e){
            error("Error occured while reading file." + e);
        }
        finally{
            if(frObj != null){
                frObj.close();
            }
        }
        return null;
    }

    public static void main(String a[]){
        System.out.print("Please enter commands: \t ADD \t DEL \t LIST\n");
        //----------Initialize Objects-----------
        Scanner scanner = new Scanner(System.in);
        DirectoryCheck obj = new DirectoryCheck();


        //-----------Action!-------------
        try {
            obj.input = scanner.nextLine().trim();
            obj.command = obj.input.split(" ")[0];
            obj.input = obj.input.substring(obj.input.lastIndexOf(obj.command) + obj.command.length());
            
            if(!obj.input.equals("")) {
                for (int i = 0; i < obj.input.length(); i++) {
                    if (obj.input.charAt(i) == ' ') {
                        if (obj.input.charAt(i + 1) == 43 || obj.input.charAt(i + 1) == 40 || (obj.input.charAt(i + 1) >= 48 && obj.input.charAt(i + 1) <= 57)) {
                            obj.telNumStrtIndex = i;
                            obj.isNumber = true;
                            break;
                        }
                    }
                }
                
                if (obj.telNumStrtIndex != 0) {
                    obj.number = obj.input.substring(obj.telNumStrtIndex + 1, obj.input.length()).trim();
                    obj.name = obj.input.substring(1, obj.telNumStrtIndex).trim();
                } else if (obj.command.equals("DEL")){
                    if(obj.isNumber){
                        obj.number = obj.input.trim();
                    }else{
                        obj.name = obj.input.trim();
                    }
                }else if(!obj.command.equals("DEL")){
                    obj.error("Invalid Input.");
                }
            }
            switch (obj.command) {
                case "ADD":
                    obj.add();
                    break;
                case "DEL":
                    if(!obj.isNumber){
                        obj.delName();
                    }else{
                        obj.delTelephone();
                    }
                    break;
                case "LIST":
                    obj.list();
                    break;
                default:
                    obj.error("");
                    break;
            }
        }catch(Exception e){
            obj.error("Error Occured While parsing Input" + "\n" + e);
        }
    }

}
