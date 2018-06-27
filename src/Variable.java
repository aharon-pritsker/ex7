import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Variable {

    protected  String name;
    protected String value;
    protected boolean initialized = false;
    protected static final Pattern LegalName = Pattern.compile("^_\\w+|[A-Za-z]\\w*");

    Variable(){}

    Variable(String name) throws Exception{
        Matcher nameMatcher = LegalName.matcher(name);
        if(nameMatcher.matches()){
            this.name = name;
        }else{
            throw new Exception();
        }
    }


    public String getName(){
        return this.name;
    }


    abstract boolean checkValue(String value) throws Exception;

    public boolean isIinitialized(){
        return this.initialized;
    }

    abstract  String getType();
}
