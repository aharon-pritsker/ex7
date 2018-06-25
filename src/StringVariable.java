import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringVariable extends Variable {

    private final Pattern pattern = Pattern.compile("'([^\\s]| )*'");

    public boolean checkValue(String value){
        Matcher matcher = pattern.matcher(value);
        if(matcher.matches()){
            this.initialized = true;
            return true;
        }
        this.initialized = false;
        return false;
    }
}
