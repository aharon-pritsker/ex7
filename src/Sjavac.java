import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Scope currentScope;
    private static ArrayList<MethodScope> methodList = new ArrayList<>();
    private static final Pattern MethodDefinition = Pattern.compile(" *void *(.+) *\\((.+)\\) *\\{");
    private static final Pattern VariableDefinition = Pattern.compile(" *(final )? *([^\\s]+) *(.+) *;\n");
    private static final Pattern LegalName = Pattern.compile("^_\\w+|[A-Za-z]\\w*");
    private static final Pattern methodInput = Pattern.compile(" *([A-Za-z]+) +(^_\\w+|[A-Za-z]\\w*)");
    private static final Pattern variableSeperation = Pattern.compile("(\\w+) *(=([^,]))?");
    private static final Pattern methodCall = Pattern.compile(" *(.+) *\\((.+)\\);\n");
    private static final Pattern whileTest = Pattern.compile(" *while *\\((.+)\\) *\\{");
    private static final Pattern ifTest = Pattern.compile(" *if *\\((.+)\\) *\\{");
    private static final Pattern conditionTest = Pattern.compile("\\|\\||&&");
    private static final Pattern isBoolean = Pattern.compile(" *true *| *false *| *-?[0-9]+.?[0-9]+ *");
    private static final Pattern isClosed = Pattern.compile("^( *} *)\\n");
    private static final Pattern valueCallTest = Pattern.compile(" *(.+) *= *(.+);\n");
    private static final Pattern returnTest = Pattern.compile("^ *return *;\n");
    private static final ArrayList<String> booleanTypes = new ArrayList<String>(Arrays.asList(new String[]{"boolean", "Double", "int"}));
    private static int depth = 0;
    private static int openParantheses = 0;


    public static int main(String[] args){
        try {
            File file = new File(args[0]);
            if (!declarationParse(file)) {
                return 1;
            }
            if (!methodParser(file)) {
                return 1;
            }
        }catch (IOException e){
            return 2;
        }catch (Exception e){
            return 1;
        }
        return 0;
    }

    private static boolean declarationParse(File file) throws IOException, Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        currentScope = new Scope(depth, null);
        while (line != null) {
            Matcher methodMatcher = MethodDefinition.matcher(line);
            Matcher variableMatcher = VariableDefinition.matcher(line);
            if (line.contains("{")) {
                openParantheses++;
            }
            if (line.equals("}")) {
                openParantheses--;
            }
            if (openParantheses == 0) {
                if (methodMatcher.matches()) {
                    methodChecker(methodMatcher.group(1), methodMatcher.group(2));
                } else if (variableMatcher.matches()) {
                    variableChecker(variableMatcher);
                }
            }
            line = reader.readLine();
        }
        return (openParantheses == 0);
    }


    private static boolean methodChecker(String name, String arguments) throws Exception {
        Matcher nameMatcher = LegalName.matcher(name);
        Matcher argumentMatcher;
        Variable newVariable;
        ArrayList<Variable> variables = new ArrayList<>();
        String[] inputArguments = arguments.split(",");
        int numOfArgs = 0;
        if (nameMatcher.matches()) {
            for (String argument : inputArguments) {
                argumentMatcher = methodInput.matcher(argument);
                if (argumentMatcher.matches()) {
                    newVariable = VariableFactory.MakeVariable(argumentMatcher.group(0), argumentMatcher.group(1), null, false);
                    variables.add(newVariable);
                    numOfArgs++;
                } else {
                    return false;
                }
            }
            MethodScope method = new MethodScope(currentScope.getDepth() + 1, currentScope, name);
            for (Variable variable : variables) {
                method.addVariable(variable);
                method.setNumOfArgs(numOfArgs);
            }
            return true;
        } else {
            return false;
        }
    }

    private static boolean variableChecker(Matcher variableMatch) {
        Matcher matcher = variableSeperation.matcher(variableMatch.group(3));
        boolean finalFlag = false;
        if (variableMatch.group(1).equals("final ")) {
            finalFlag = true;
        }
        try {
            while (matcher.find()) {
                currentScope.addVariable(VariableFactory.MakeVariable(variableMatch.group(2), matcher.group(1), matcher.group(2), finalFlag));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static boolean methodParser(File file) throws IOException, Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String lastLine = line;
        boolean isIfOrWhile = false;
        while (line != null) {
            Matcher valueChangeTest = valueCallTest.matcher(line);
            Matcher closedCheck = isClosed.matcher(line);
            Matcher returnCheck = returnTest.matcher(lastLine);
            Matcher valueDefinitionTest = VariableDefinition.matcher(line);
            Matcher methodDefinitionTest = MethodDefinition.matcher(line);
           if(ifCheck(line)){
               isIfOrWhile = true;
           }else if(whileCheck(line)){
               isIfOrWhile = true;
            }else if(closedCheck(line)){
               if(returnCheck.matches()&&!isIfOrWhile){
                   currentScope = currentScope.parentSCope;
               }
            } else if (valueChangeTest.matches()) {
                VariableCall(valueChangeTest.group(1), valueChangeTest.group(2));
            } else if (valueDefinitionTest.matches()) {
                variableChecker(valueDefinitionTest);
            } else if (methodDefinitionTest.matches()) {
                return false;
            } else {
                return false;
            }
            line = reader.readLine();
        }
        return true;
    }

    private static boolean ifCheck(String line) {
        Matcher ifCheck = ifTest.matcher(line);
        if (ifCheck.matches()) {
            String[] conditions = ifCheck.group(1).split("\\|\\||&&");
            if (booleanConditionTester(conditions)) {
                currentScope = new Scope(currentScope.getDepth() + 1, currentScope);
                return true;
            }
        }
        return false;
    }

    private static boolean whileCheck(String line){
        Matcher whileCheck = whileTest.matcher(line);
        if (whileCheck.matches()) {
            String[] conditions = whileCheck.group(1).split("\\|\\||&&");
            if (booleanConditionTester(conditions)) {
                currentScope = new Scope(currentScope.getDepth() + 1, currentScope);
                return true;
            }
        }
        return false;
    }

    private static boolean closedCheck(String line){
        Matcher closedCheck = isClosed.matcher(line);
        if (closedCheck.matches()) {
            return true;
            }
        return false;
    }

    private static boolean MethodCall(String name,String arguments) {
        Matcher nameMatcher = LegalName.matcher(name);
        String[] splitArguments = arguments.split(",");
        if(nameMatcher.matches()){
            for(MethodScope method:methodList){
                if(method.getName().equals(name)){
                    for(int i=0;i<splitArguments.length;i++){
                        Variable currentVariable = method.findVarInList(splitArguments[i]);
                        if(currentVariable != null){
                            splitArguments[i] = currentVariable.getValue();
                        }
                    }
                    if(method.CheckCall(splitArguments)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

        private static boolean VariableCall(String name,String value) throws Exception{
            Matcher nameMatcher = LegalName.matcher(name);
            if(nameMatcher.matches()){
                Variable currentVariable = currentScope.findVarInList(name);
                if(currentVariable != null){
                    if(currentVariable.checkValue(value)){
                        currentVariable.PutValue(value);
                        return true;
                    }else{
                        return false;
                    }

                }
            }
            return false;
        }

        private static boolean booleanConditionTester(String[] conditions){
            for(String condition:conditions){
                Matcher booleanCheck = isBoolean.matcher(condition);
                Matcher nameCheck = LegalName.matcher(condition);
                if(!booleanCheck.matches()){
                    if(nameCheck.matches()){
                        Variable variable = currentScope.findVarInList(condition);
                        if(!booleanTypes.contains(variable.getType())&&variable.isIinitialized()){
                            return false;
                        }
                        return true;
                    }
                    return false;
                }
            }
            return true;
        }
}
