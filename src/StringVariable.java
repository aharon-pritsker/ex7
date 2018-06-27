import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringVariable extends Variable {

    private final Pattern checkValue = Pattern.compile("'([^\\s]| )*'");
    private final static String type = "String";

    StringVariable(String name,String value)throws Exception{
        super(name);
        initialized = checkValue(value);
        if(!initialized){
            throw new Exception();
        }
    }

    public boolean checkValue(String value){
        Matcher matcher = checkValue.matcher(value);
        if (matcher.matches()) {
            this.value = value;
            return true;
        } else {
            return false;
        }
    }

    public String getType(){
        return type;
    }
}
