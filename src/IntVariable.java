import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class IntVariable extends Variable {

    private final Pattern checkValue = Pattern.compile("[0-9]+");
    private final static String type = "int";

    IntVariable(String name,String value) throws Exception{
        super(name);
        checkValue(value);
    }



    public void checkValue(String value)throws Exception{
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
