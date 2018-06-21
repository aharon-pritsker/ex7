import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DoubleVariable extends Variable {

    private final Pattern pattern = Pattern.compile("[0-9]*//.[0-9]+");

    private String value;

    DoubleVariable(String name,int scope,String value){
        this.name = name;
        this.scope = scope;
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public void setValue(String value) throws Exception {
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            this.value = value;
        }else{
            throw new Exception();
        }
    }

}
