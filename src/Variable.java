public abstract class Variable {

    protected  String name;
    protected String value;
    protected boolean initialized = false;

    Variable(){}

    Variable(String name){
        this.name = name;
    }


    public String getName(){
        return this.name;
    }

    public int getScope(){
        return this.scope;
    }

    abstract boolean checkValue(String value);

    public boolean isIinitialized(){
        return this.initialized;
    }
}
