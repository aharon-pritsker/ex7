import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanVariable extends Variable {

    private final Pattern  valueCheck= Pattern.compile("true|false|[0-9]+\\.?[0-9]+");

    BooleanVariable(String name,String value) throws Exception{
        super(name);
        checkValue(value);
    }

    public void checkValue(String value) throws Exception{
        Matcher matcher = valueCheck.matcher(value);
        if(matcher.matches()){
            initialized = true;
        }else{
            throw new Exception();
        }
    }
}
