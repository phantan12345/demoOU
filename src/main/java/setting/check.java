package setting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class check {
    Pattern p = Pattern.compile("[^A-Za-z0-9]");


    public int checkusers(String s) {
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if (b)
            return 1;
        else
            return 0;
    }
}
