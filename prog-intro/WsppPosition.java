import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WsppPosition {
    public static void main(String[] args) {
        Map<String, List<String>> mapArray = new LinkedHashMap<>();
        try {
            FastScanner stringScanner = new FastScanner(new File(args[0]));
            int countWord = 0;
            int countLines = stringScanner.countCurrentLines();
            while (stringScanner.hasNextWord()) {
                String str = stringScanner.nextWord().toLowerCase();
                if (countLines < stringScanner.countCurrentLines()) {
                    countLines = stringScanner.countCurrentLines();
                    countWord = 0;
                }
                if (!str.equals("")) {
                    countWord++;
                    var a = mapArray.getOrDefault(str, new ArrayList<>());
                    a.add(countLines + ":" + countWord);
                    mapArray.put(str, a);
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
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream(args[1]), StandardCharsets.UTF_8));
            for (Map.Entry<String, List<String>> output : mapArray.entrySet()) {
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