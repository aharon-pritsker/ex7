import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanVariable extends Variable {

    private final Pattern  valueCheck= Pattern.compile("true|false|[0-9]+\\.?[0-9]+");
    private static final String type = "boolean";

    BooleanVariable(String name,String value) throws Exception{
        super(name);
        initialized = checkValue(value);
    }

    public boolean checkValue(String value){
        Matcher matcher = valueCheck.matcher(value);
        if(matcher.matches()){
            this.value = value;
            return true;
        }else{
            return false;
        }
    }

    public String getType(){
        return type;
    }
}
