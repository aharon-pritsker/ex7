import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class IntVariable extends Variable {

    private final Pattern checkValue = Pattern.compile("[0-9]+");
    private final static String type = "int";

    IntVariable(String name,String value) throws Exception{
        super(name);
        initialized = checkValue(value);
        if(!initialized){
            throw new Exception();
        }
    }



    public boolean checkValue(String value){
        Matcher matcher = checkValue.matcher(value);
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
