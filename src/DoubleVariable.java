import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DoubleVariable extends Variable {

    private final Pattern valueCheck = Pattern.compile("[0-9]+\\.?[0-9]+");
    private final static String type = "Double";

    DoubleVariable(String name,String value) throws Exception{
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

    public static String getType(){
        return type;
    }

}
