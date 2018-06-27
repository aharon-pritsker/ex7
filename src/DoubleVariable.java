import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DoubleVariable extends Variable {

    private final Pattern valueCheck = Pattern.compile("[0-9]+\\.?[0-9]+");
    private final static String type = "Double";

    DoubleVariable(String name,String value) throws Exception{
        super(name);
        initialized = checkValue(value);
        if(!initialized){
            throw new Exception();
        }
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
