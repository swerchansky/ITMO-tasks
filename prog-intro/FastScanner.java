import java.io.*;
import java.nio.charset.StandardCharsets;

public class FastScanner {
    private int countLines = 1;
    private final Reader reader;
    private char[] Buff;
    private int currentChar = 0;
    private int lenBuff = 0;
    private String nextInt = "";
    private String tmp;
    private String nextLine = "";
    private String nextWord = "";

    //reader for string
    public FastScanner(String str) throws IOException {
        this.reader = new InputStreamReader(
                new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        BufferIncrease();
    }

    //reader for System.in
    public FastScanner(InputStream stream) throws IOException {
        this.reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferIncrease();
    }

    //reader for File
    public FastScanner(File name) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(name), StandardCharsets.UTF_8);
        BufferIncrease();
    }

    //buffer update
    private boolean BufferIncrease() throws IOException {
        if (reader.ready()) {
            Buff = new char[120];
            lenBuff = reader.read(Buff);
            return true;
        } else {
            return false;
        }
    }

    //read next char from buffer
    public int nextChar() throws IOException {
        if (currentChar == 120) {  //if char == end of buffer
            currentChar = 0;
            if (!BufferIncrease()) {
                return -1;
            }
        }
        return Buff[currentChar++]; //next index of buffer
    }

    public boolean hasNext() throws IOException {
        return currentChar < lenBuff || reader.ready(); //if buffer isn't empty or reader.ready
    }

    public int countCurrentLines() {
        return countLines;
    }

    public boolean hasNextWord() throws IOException {
        if (nextWord.length() != 0) {
            return true;
        }
        StringBuilder str = new StringBuilder();
        boolean existSymbol = false;
        while (hasNext()) {
            char ch = (char) nextChar();
            if (Character.isWhitespace(ch) || !(Character.isLetter(ch) ||
                    Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'')) {
                if (existSymbol) { //if string have any char
                    currentChar--;
                    break;
                }
            } else {
                str.append(ch);
                existSymbol = true;
            }
            if (ch == '\n') {
                countLines++;
            }
        }
        if (str.length() > 0) {
            nextWord = str.toString();
            return true;
        } else {
            return false;
        }
    }

    //read next word
    public String nextWord() throws IOException {
        if (hasNextWord()) {
            tmp = nextWord;
            nextWord = "";
            return tmp;
        } else {
            throw new IOException("No more word to read");
        }
    }

    //read next
    public String next() throws IOException {
        StringBuilder string = new StringBuilder();
        char ch;
        boolean existSymbol = false;
        while (hasNext()) {
            ch = (char) nextChar();
            if (Character.isWhitespace(ch)) {
                if (existSymbol) { //if string have any char
                    break;
                }
            } else {
                string.append(ch);
                existSymbol = true;
            }
        }
        if (string.length() > 0) {
            return string.toString();
        } else {
            return "";
        }
    }

    //read next Int
    public int nextInt() throws IOException {
        if (hasNextInt()) {
            tmp = nextInt;
            nextInt = "";
            return Integer.parseInt(tmp);
        } else {
            throw new IOException("No more int to read");
        }
    }

    public boolean hasNextInt() throws IOException {
        if (nextInt.length() != 0) {
            try {
                Integer.parseInt(nextInt);
                return true;
            } catch (NumberFormatException e) {
                nextInt = "";
                return false;
            }
        }
        StringBuilder string = new StringBuilder();
        char ch;
        boolean existSymbol = false;
        while (hasNext()) {
            ch = (char) nextChar();
            if (Character.isWhitespace(ch)) {
                if (existSymbol) { //if string have any char
                    break;
                }
            } else {
                string.append(ch);
                existSymbol = true;
            }
        }
        nextInt = string.toString();
        try {
            Integer.parseInt(nextInt);
            return true;
        } catch (NumberFormatException e) {
            nextInt = "";
            return false;
        }
    }

    public boolean hasNextLine() throws IOException {
        if (nextLine.length() != 0) {
            return true;
        }
        StringBuilder line = new StringBuilder();
        char ch = ' ';
        boolean notEmptyLine = false;
        while (ch != '\n' && hasNext()) {
            ch = (char) nextChar();
            if (ch != '\n') {
                line.append(ch);
            }
            if (!Character.isWhitespace(ch)) { //if any symbol in line
                notEmptyLine = true;
            }
            if (ch == '\n' & !notEmptyLine) { //if line is empty
                line.append(ch);
            }
        }
        nextLine = line.toString();
        return nextLine.length() > 0;
    }

    public String nextLine() throws IOException {
        if (hasNextLine()) {
            tmp = nextLine;
            nextLine = "";
            return tmp;
        } else {
            throw new IOException("No more line to read");
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}
