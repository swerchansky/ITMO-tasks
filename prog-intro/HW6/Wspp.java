import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class Wspp {
    public static void main(String[] args) {
        LinkedHashMap<String, ArrayList<Integer>> mapArray = new LinkedHashMap<>();
        try {
            FastScanner stringScanner = new FastScanner(new File(args[0]));
            int countWord = 0;
            while (stringScanner.hasNextWord()) {
                String str = stringScanner.nextWord().toLowerCase();
                if (!str.equals("")) {
                    countWord++;
                    if (mapArray.containsKey(str)) {
//                        mapArray.get(str).set(0, mapArray.get(str).get(0) + 1);
                        mapArray.get(str).add(countWord);
                    } else {
                        ArrayList<Integer> newWord = new ArrayList<>();
//                        newWord.add(1);
                        newWord.add(countWord);
                        mapArray.put(str, newWord);
                    }
                }
            }
            stringScanner.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.print("Unsupported encoding: " + e.getMessage());
        } catch (IOException e) {
            System.out.print("IOexception: " + e.getMessage());
        }
        String num = "0";
        int num1 = num.hashCode();
        System.out.println(num1);
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            for (Map.Entry<String, ArrayList<Integer>> output : mapArray.entrySet()) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < output.getValue().size(); i++) {
                    str.append(" ");
                    str.append(output.getValue().get(i));
                }
                writer.write(output.getKey() + " " + output.getValue().size() + str.toString());
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.print("Unsupported encoding: " + e.getMessage());
        } catch (IOException e) {
            System.out.print("i/o error: " + e.getMessage());
        }
    }
}
