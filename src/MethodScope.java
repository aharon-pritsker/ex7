import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodScope extends Scope {

    boolean returnFlag;
    private final String name;
    private ArrayList<Variable> typeList = new ArrayList<Variable>();
    private static final Pattern nameCheck = Pattern.compile("^_\\w+|[A-Za-z]\\w*");
    private int numOfArgs;

    public MethodScope(int depth, Scope parent,String name)throws Exception{
        super(depth, parent);
        returnFlag = false;
        Matcher matcher = nameCheck.matcher(name);
        if(matcher.matches()){
            this.name = name;
        }else{
            throw new Exception();
        }
    }

    public ArrayList<Variable> getTypeList(){
        return typeList;
    }

    public void markReturn() {
        returnFlag = true;
    }

    public String getName(){
        return this.name;
    }

    public boolean CheckCall(String[] values) {
        if(values.length != numOfArgs) {
            return false;
        }
        for(int i = 0; i < numOfArgs; i++) {
            if(scopeVariables.get(i).checkValue(values[i]));
            {
                return false;
            }
        }
        return true;
    }

    public void setNumOfArgs(int numOfArgs) {
        this.numOfArgs = numOfArgs;
    }

    public int getNumOfArgs() {
        return numOfArgs;
    }
}
