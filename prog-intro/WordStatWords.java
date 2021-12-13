import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class WordStatWords {
    public static void main(String[] args) {
        TreeMap<String, Integer> mapArray = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8));
            String line = reader.readLine();
            while (line != null) {
                int pos = 0;
                line += " ";
                for (int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);
                    boolean is_dash = Character.getType(ch) == Character.DASH_PUNCTUATION;
                    if (!(Character.isLetter(ch) || ch == '\'' || is_dash)) {
                        String str = line.substring(pos, j).toLowerCase();
                        if (!str.isEmpty()) {
                            if (mapArray.containsKey(str)) {
                                int num = mapArray.get(str);
                                num++;
                                mapArray.replace(str, num);
                            } else {
                                mapArray.put(str, 1);
                            }
                        }
                        pos = j + 1;
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.print("Unsupported encoding: " + e.getMessage());
        } catch (IOException e) {
            System.out.print("i/o error: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8));
            for (Map.Entry<String, Integer> outArr : mapArray.entrySet()) {
                writer.write(outArr.getKey() + " " + outArr.getValue());
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