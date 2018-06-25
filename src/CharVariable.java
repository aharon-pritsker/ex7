import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharVariable extends Variable {

    private final Pattern pattern = Pattern.compile("[^\\s]");

    public boolean checkValue(String value){
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches()){
            initialized = true;
            return true;
        }else{
            initialized = false;
            return false;
        }
    }
}
