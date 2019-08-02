package ivan.samoylov;

import com.sun.jdi.connect.Connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final String FILE = "urls.txt";
        BufferedReader reader = null;
        String line;
        String domainName = "";
        int tmpValue;
        String tmpArr = "";
        Map<String, String> dictionary = new HashMap<String, String>();
        TreeSet<String> setTopDomains = new TreeSet<>();

        try{
            File file = new File(FILE);

            if(!file.exists()) {
                System.out.println("File " + FILE + " does not exist.");
                return;
            }

            reader = new BufferedReader(new FileReader(FILE));

            while((line = reader.readLine()) != null){
                if(line == ""){
                    continue;
                }
                for (int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == '/' || line.charAt(i) == ' '){
                        break;
                    }
                    else {
                        domainName += line.charAt(i);
                    }
                }
                if (!dictionary.containsKey(domainName)){
                    dictionary.put(domainName, "1");
                }
                else {
                    tmpValue = Integer.parseInt(dictionary.get(domainName));
                    tmpValue ++;
                    dictionary.remove(domainName);
                    dictionary.put(domainName, Integer.toString(tmpValue));
                }
                domainName = "";
            }
        }
        catch(Exception error){
            System.out.println(error.getMessage());
        }

    }
}
