import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanVariable extends Variable {

    private final Pattern  pattern= Pattern.compile("true|false|[0-9]+\\.[0-9]+|[0-9]+");

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
