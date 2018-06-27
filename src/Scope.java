import java.util.ArrayList;

public class Scope {

    Scope childScope;
    int scopeDepth;
    boolean closed;
    Scope parentSCope;
    private ArrayList<String> parsedLines = new ArrayList<>();
    ArrayList<Variable> scopeVariables;

    /**
     * This is the scope's constructor
     * @param depth the scope's depth
     * @param parent the scope's parent
     */
    public Scope(int depth, Scope parent) {
        scopeDepth = depth;
        scopeVariables = new ArrayList<Variable>();
        childScope = null;
        parentSCope = parent;
        closed = false;
    }

    /**
     * This method returns the depth of the scope.
     * @return the depth of the scope
     */
    public int getDepth() {
        return scopeDepth;
    }

    /**
     * This method sets a child scope to the given one
     * @param childScope the child scope
     */
    public void setChildScope(Scope childScope) {
        this.childScope = childScope;
    }

    /**
     * This method finds  a certain variable in the variables list
     * @param varName the variable's name
     * @return the variable' if it exists in the list, null if it doesn't
     */
    public Variable findVarInList(String varName) {
        for (Variable var: scopeVariables) {
            if(var.getName().equals(varName)) {
                return var;
            }
        }
        Scope scope = parentSCope;
        while(scope != null){
            for(Variable var:scope.getVariableList()){
                if(var.getName().equals(varName)){
                    return var;
                }
            }
        }
        return null;
    }

    /**
     * This method add a new variable to the scope
     * @param newVar the new variable
     */
    public void addVariable(Variable newVar) {
        Variable varIndex = findVarInList(newVar.getName());
        scopeVariables.add(newVar);
    }

    /**
     * This method closes the current scope.
     */
    public void closeScope() {
        closed = true;
    }

    public ArrayList<Variable> getVariableList(){
        return scopeVariables;
    }

    public void addLine(String line){
        parsedLines.add(line);
    }

    public boolean CheckCall(Variable[] args) {
        if(args.length != numOfArgs) {
            return false;
        }
        for(int i = 0; i < numOfArgs; i++) {
            if(scopeVariables.get(i).getType().equals(args[i].getType()));
            {
                return false;
            }
        }
        return true;
    }
}
