package md2html;

import java.util.Map;

public class Converter {
    private final Map<String, String> markdownTags = Map.of(
            "*", "em", "_", "em", "__", "strong",
            "**", "strong", "--", "s", "`", "code",
            "<", "&lt;", ">", "&gt;", "&", "&amp;", "%", "var"
    );
    private final StringBuilder result;
    private int index = 0;
    private int headerLevel;
    private final String str;

    public Converter(String str) {
        result = new StringBuilder();
        this.str = str;
        headerLevel = getHeader(this.str);
        if (headerLevel > 0) {
            Header(headerLevel);
        } else {
            Paragraph(this.str);
        }
    }

    private void Header(int headerLevel) {
        result.append("<h").append(headerLevel).append(">");
        index = headerLevel + 1;
        result.append(parse(""));
        result.append("</h").append(headerLevel).append(">");
    }

    private void Paragraph(String str) {
        result.append("<p>");
        result.append(parse(""));
        result.append("</p>");
    }

    private String parse(String tag) {
        StringBuilder result = new StringBuilder();
        String markdownTag = "";
        String htmlTag = "";
        while (index < str.length()) {
            int ch = str.charAt(index);
            if (ch == '*' || ch == '_') {
                if (index + 1 < str.length() && ch == str.charAt(index + 1)) {
                    markdownTag = str.substring(index, index + 2);
                    htmlTag = markdownTags.get(markdownTag);
                    index++;
                } else {
                    markdownTag = Character.toString(ch);
                    htmlTag = markdownTags.get(markdownTag);
                }
            } else if (ch == '-') {
                if (index + 1 < str.length() && ch == str.charAt(index + 1)) {
                    markdownTag = str.substring(index, index + 2);
                    htmlTag = markdownTags.get(markdownTag);
                    index++;
                } else {
                    result.append(Character.toString(ch));
                }
            } else if (ch == '`') {
                markdownTag = Character.toString(ch);
                htmlTag = markdownTags.get(markdownTag);
            } else if (ch == '&' || ch == '>' || ch == '<') {
                result.append(markdownTags.get(Character.toString(ch)));
            } else if (ch == '\\') {
                index++;
                result.append(str.charAt(index));
            } else if (ch == '%') {
                markdownTag = Character.toString(ch);
                htmlTag = markdownTags.get(markdownTag);
            } else {
                result.append((char) ch);
            }
            if (markdownTag.equals(tag) && !markdownTag.isEmpty()) {
                result.append("</").append(htmlTag).append(">");
                return result.toString();
            }
            index++;
            if (!markdownTag.isEmpty()) {
                String newLine = parse(markdownTag);
                if (newLine.length() >= htmlTag.length() && newLine.substring(newLine.length() - htmlTag.length() - 1,
                        newLine.length()).equals(htmlTag + '>')) {
                    result.append("<").append(htmlTag).append(">").append(newLine);
                    index++;
                } else {
                    result.append(markdownTag).append(newLine);
                }
                markdownTag = "";
            }
        }
        return result.toString();
    }

    private static int getHeader(String str) {
        int headerLevel = 0;
        while (headerLevel < str.length() && str.charAt(headerLevel) == '#') {
            headerLevel++;
        }
        if (headerLevel < str.length() && str.charAt(headerLevel) == ' ') {
            return headerLevel;
        } else {
            return 0;
        }
    }

    public String get() {
        return result.toString();
    }

}
