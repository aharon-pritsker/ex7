import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class IntVariable extends Variable {

    private final Pattern pattern = Pattern.compile("[0-9]+");



    public boolean checkValue(String value){
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
