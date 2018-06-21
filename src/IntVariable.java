import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class IntVariable extends Variable {

    private final Pattern pattern = Pattern.compile("[0-9]+");


    IntVariable(int scope,String name,String value){

        this.scope = scope;
        this.name = name;
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public void setValue(String value)throws Exception {
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            this.value = value;
        }else{
            throw new Exception();
        }
    }

}
