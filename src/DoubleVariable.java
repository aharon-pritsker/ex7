import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DoubleVariable extends Variable {

    private final Pattern pattern = Pattern.compile("[0-9]+//.[0-9]+");

    private String value;

    DoubleVariable(String name,int scope,String value){
        this.name = name;
        this.scope = scope;
        this.value = value;
    }


    public boolean checkValue(String value){
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
