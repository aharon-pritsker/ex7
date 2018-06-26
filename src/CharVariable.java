import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharVariable extends Variable {

    private final Pattern checkValue = Pattern.compile("'[^\\s]| *'");
    private final static String type = "Char";

    public void checkValue(String value) throws Exception{
        Matcher matcher = checkValue.matcher(value);
        if(matcher.matches()){
            initialized = true;
        }else{
            throw new Exception();
        }
    }

    public static String getType(){
        return type;
    }
}
