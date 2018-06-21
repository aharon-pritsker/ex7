public class Variable {

    private final int  scope;
    private int value;
    private final String name;

    Variable(int scope,String name){
        this.scope = scope;
        this.name = name;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public int getScope(){
        return this.scope;
    }
}
