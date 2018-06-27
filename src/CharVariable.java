import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharVariable extends Variable {

    private final Pattern checkValue = Pattern.compile("'[^\\s]| *'");
    private final static String type = "Char";

    CharVariable(String name,String value) throws Exception{
        super(name);
        initialized = checkValue(value);
        if(!initialized){
            throw new Exception();
        }
    }

    public boolean checkValue(String value){
        Matcher matcher = checkValue.matcher(value);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }

    public String getType(){
        return type;
    }
}
