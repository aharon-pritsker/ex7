import java.util.ArrayList;

public class Scope {

    Scope childScope;
    int scopeDepth;
    boolean closed;
    Scope parent;
    ArrayList<Variable> scopeVariables;

    /**
     * This is the scope's constructor
     *
     * @param depth  the scope's depth
     * @param parent the scope's parent
     */
    public Scope(int depth, Scope parent) {
        scopeDepth = depth;
        scopeVariables = new ArrayList<Variable>();
        childScope = null;
        parent = parent;
        closed = false;
    }

    /**
     * @return the depth of the scope
     */
    public int getDepth() {
        return scopeDepth;
    }

    /**
     * This method sets a child scope to the given one
     *
     * @param childScope the child scope
     */
    public void setChildScope(Scope childScope) {
        this.childScope = childScope;
    }

    /**
     * This method finds the index of a certain variable in the variables list
     *
     * @param varName the variable's name
     * @return the variable's index if it exists in the list, -1 if it doesn't
     */
    private int findVarInList(String varName) {
        for (Variable var : scopeVariables) {
            if (var.getName().equals(varName)) {
                return scopeVariables.indexOf(var);
            }
        }
        return -1;
    }

    /**
     * This method add a new variable to the scope
     *
     * @param newVar the new variable
     */
    public void addVariable(Variable newVar) {
        int varIndex = findVarInList(newVar.getName());
        if (varIndex != -1) {
            scopeVariables.remove(varIndex);
        }
        scopeVariables.add(newVar);
    }

    public Scope getParent() {
        return this.parent;
    }

    public ArrayList<Variable> getVariableList() {
        return this.scopeVariables;
    }


    /**
     * This method closes the current scope.
     */
    public void closeScope() {
        closed = true;
    }

    public boolean variableSearch(String name) {
        Scope scope = this;
        while (scope != null) {
            for (Variable variable : scope.getVariableList()) {
                if (variable.getName().equals(name)) {
                    return true;
                }
            }
            scope = scope.getParent();
        }
        return false;
    }
}
