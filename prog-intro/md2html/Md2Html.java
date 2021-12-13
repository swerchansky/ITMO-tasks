package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0]), StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), StandardCharsets.UTF_8));
            String line = reader.readLine();
            while (line != null) {
                StringBuilder str = new StringBuilder();
                while (line.isEmpty()) {
                    line = reader.readLine();
                }
                while(line != null && !line.isEmpty()) {
                    if (str.length() != 0) {
                        str.append(System.lineSeparator());
                    }
                    str.append(line);
                    line = reader.readLine();
                }
                if (str.length() > 0) {
                    Converter result = new Converter(str.toString());
                    writer.write(result.get());
                    writer.newLine();
//                    writer.newLine();
                }
            }
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Wrong encoding: " + e.getMessage());
        }catch (IOException e) {
            System.out.print("i/o error: " + e.getMessage());
        }
    }
}
