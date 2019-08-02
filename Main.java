package ivan.samoylov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        final String FILE = "urls.txt";
        BufferedReader reader = null;
        String line;
        String domainName = "";
        int tmpValue;
        Map<String, String> dictionary = new HashMap<String, String>();

        try{
            File file = new File(FILE);

            if(!file.exists()) {
                System.out.println("File " + FILE + " does not exist.");
                return;
            }

            reader = new BufferedReader(new FileReader(FILE));

            while((line = reader.readLine()) != null){
                for (int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) != '/'){
                        domainName += line.charAt(i);
                    }
                    else {
                        break;
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

        System.out.println("All objects: " + dictionary);
        System.out.println("All inclusions: " + dictionary.size());
        System.out.println("Example of the number of occurrences for an object m.youtube.com: " + dictionary.get("m.youtube.com"));

    }
}
