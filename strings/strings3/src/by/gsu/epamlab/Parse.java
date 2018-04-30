package by.gsu.epamlab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class Parse {

    private Pattern pattern;

    public Parse(String regex) {
        pattern = Pattern.compile(regex);
    }

    public String format(String line) {

        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            line = line.replace(matcher.group(group()), format(matcher));
        }

        return line;
    }

    public static String replaceAll(String str, String regex, String dest) {
        return Pattern.compile(regex).matcher(str).replaceAll(dest);
    }

    abstract protected String format(Matcher matcher);

    abstract protected int group();
}
