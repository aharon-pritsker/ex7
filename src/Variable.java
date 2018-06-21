public abstract class Variable {

    protected  String name;
    protected  int scope;
    protected String value;

    Variable(String name,int scope){
        this.name = name;
        this.scope = scope;
    }

    Variable(){}

    public String getName(){
        return this.name;
    }

    public int getScope(){
        return this.scope;
    }

    abstract String getValue();

    abstract void setValue(String value) throws Exception;
}
