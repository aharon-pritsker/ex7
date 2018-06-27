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
    private static final Pattern VariableDefinition = Pattern.compile(" *(final )? *([^\\s]+) *(.+) *;");
    private static final Pattern LegalName = Pattern.compile("^_\\w+|[A-Za-z]\\w*");
    private static final Pattern methodInput = Pattern.compile(" *([A-Za-z]+) +(^_\\w+|[A-Za-z]\\w*)");
    private static final Pattern variableSeperation = Pattern.compile("(\\w+) *(=([^,]))?");
    private static final Pattern methodCall = Pattern.compile(" *(.+) *\\((.+)\\);");
    private static final Pattern whileTest = Pattern.compile(" *while *\\((.+)\\) *\\{");
    private static final Pattern ifTest = Pattern.compile(" *if *\\((.+)\\) *\\{");
    private static final Pattern isBoolean = Pattern.compile(" *true *| *false *| *-?[0-9]+.?[0-9]+ *");
    private static final Pattern isClosed = Pattern.compile("^( *\\} *)\\n");
    private static final Pattern returnTest = Pattern.compile("^ *return *;\\n");
    private static final ArrayList<String> booleanTypes = new ArrayList<String>(Arrays.asList(new String[]{"boolean","Double","int"}));
    private static int depth = 0;
    private static int openParantheses = 0;




    private static void declarationParse(File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
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
                    //variableChecker
                }
            }
        }
    }

    private static void methodChecker(String name,String arguments) throws Exception{
            Matcher nameMatcher = LegalName.matcher(name);
            Matcher argumentMatcher;
            Variable newVariable;
            ArrayList<Variable> variables = new ArrayList<>();
            String[] inputArguments = arguments.split(",");
            int numOfArgs = 0;
            if(nameMatcher.matches()){
                for(String argument:inputArguments){
                    argumentMatcher = methodInput.matcher(argument);
                    if(argumentMatcher.matches()){
                        newVariable = VariableFactory.MakeVariable(argumentMatcher.group(0),argumentMatcher.group(1),null,false);
                        variables.add(newVariable);
                        numOfArgs++;
                    }
                }
            }
            MethodScope method = new MethodScope(depth,globalScope,name);
            for(Variable variable:variables){
                method.addVariable(variable);
                method.setNumOfArgs(numOfArgs);
            }
        }

        private static void variableChecker(Matcher variableMatch) throws Exception{
            Matcher matcher = variableSeperation.matcher(variableMatch.group(3));
            boolean finalFlag = false;
            if(variableMatch.group(1).equals("final ")){
                finalFlag = true;
            }
            while(matcher.find()){
                currentScope.addVariable(VariableFactory.MakeVariable(variableMatch.group(2),matcher.group(1),matcher.group(2),finalFlag));
            }
        }

        private static void methodParser(File file)throws IOException,Exception{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            String lastLine = line;
            while (line != null) {
                Matcher MethodCheck = methodCall.matcher(line);
                Matcher whileCheck = whileTest.matcher(line);
                Matcher ifCheck = ifTest.matcher(line);
                Matcher closedCheck = isClosed.matcher(line);
                Matcher returnCheck = returnTest.matcher(lastLine);
                if (ifCheck.matches()) {
                    String[] conditions = ifCheck.group(1).split("\\|\\||&&");
                    booleanConditionTester(conditions);
                } else if (whileCheck.matches()) {
                    String[] conditions = whileCheck.group(1).split("\\|\\||&&");
                    booleanConditionTester(conditions);
                } else if (closedCheck.matches()){
                    if(returnCheck.matches()){
                       //closeScope
                    }else{
                        throw new Exception();
                    }
                }else if(MethodCheck.matches()){
                    //
                }
            }
        }

        private void MethodCall(String line) {
            
        }

        private void VariableCall(String line) {

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



        private static boolean methodParser(String line)throws Exception {
            Matcher definitionMatcher = MethodDefinition.matcher(line);
            Matcher callMatcher = MethodCall.matcher(line);
            if (definitionMatcher.matches() && depth == 0) {
                methodList.add(new MethodScope(depth, currentScope, definitionMatcher.group(1)));
                if (!definitionMatcher.group(2).equals("''")) {
                    String[] inputVariables = definitionMatcher.group(2).split(",");
                    //pass each one on to factory
                }
            }
            if (callMatcher.matches() && depth >= 1) {
                String[] input = callMatcher.group(2).split(",");
                for (MethodScope method : methodList) {
                    if (callMatcher.group(1).equals(method.getName())) {
                        if (input.length == method.getTypeList().size()) {
                            for (int i = 0; i < input.length; i++) {
                                Variable searchResult = currentScope.findVarInList(input[i]);
                                if (!(searchResult != null)) {
                                    if (searchResult.getType().equals(method.getTypeList().get(i).getType())) {
                                        return false;
                                    }
                                } else {
                                    suspectVariables.add(input[i]);
                                }
                            }
                        }
                    }
                }
            }
}
