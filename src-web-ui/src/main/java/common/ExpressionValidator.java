package common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionValidator {

    /**
     * A method for verifying text using a regular expression
     * @param template Template for checking by regular expression
     * @param text Text that is checked by a regular expression
     * @return Number of matches with a pattern
     */
    public static List<String> checkByRegularExpression(String template,  String text) {
        List<String> emailList = new ArrayList<>();
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(template);
        matcher = pattern.matcher(text);
         while(matcher.find()) {
             emailList.add(matcher.group());
        }
        return emailList;
    }
}
