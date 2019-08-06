package ivan.samoylov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        final String FILE = "urls.txt";
        final int TOP_DOMAINS = 10;
        BufferedReader reader = null;
        String line;
        String domainName = "";
        int tmpValue;
        Map<String, String> dictionary = new HashMap<String, String>();
        TreeSet<Integer> setAllDomains = new TreeSet<>(Comparator.reverseOrder());

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
                    if(line.charAt(i) == '/' || line.charAt(i) == ' ' || line == "" ){
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

        for (Map.Entry<String, String> elem: dictionary.entrySet()) {
            setAllDomains.add(Integer.parseInt(elem.getValue()));
        }

        System.out.println("Top " + TOP_DOMAINS + " domains :");

        for (int i = 0; i < TOP_DOMAINS; i++){
            System.out.println(getKeysByValue(dictionary, setAllDomains.pollFirst().toString()) + " - " + setAllDomains.pollFirst());
            setAllDomains.remove(setAllDomains.pollFirst());
        }
    }

    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<T>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }
}
