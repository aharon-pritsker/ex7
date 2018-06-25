import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringVariable extends Variable {

    private final Pattern checkValue = Pattern.compile("'([^\\s]| )*'");

    StringVariable(String name,String value)throws Exception{
        super(name);
        checkValue(value);
    }

    public void checkValue(String value) throws Exception {
        Matcher matcher = checkValue.matcher(value);
        if (matcher.matches()) {
            this.initialized = true;
        } else {
            throw new Exception();
        }
    }
}
