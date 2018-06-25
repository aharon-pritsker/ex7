import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Scope currentScope;
    private static ArrayList<MethodScope> methodList = new ArrayList<>();
    private static ArrayList<String> suspectMethods = new ArrayList<>();
    private static final Pattern MethodDefinition = Pattern.compile(" *void *(.+) *\\((.+)\\) *\\{");
    private static final Pattern MethodCall = Pattern.compile(" *(.+)\\((.+)\\);");
    private static int depth = 0;


    public static void main(String[] args) throws FileNotFoundException,IOException{
        BufferedReader reader = new BufferedReader(new FileReader(args[1]));
        String line = reader.readLine();
        Scope currentScope;
        while(line != null){
            if(line.contains("void")){
                //MethodVerifier
            }else if(line.contains("int")){
                //IntVerifier
            }else if(line.contains("boolean")){
                //BooleanVerifier
            }else if(line.contains("double")){
                //DoubleVerifier
            }else if(line.contains("char")){
                //CharVerifier
            }else if(line.contains("String")){
                //StringVerifier
            }else if(line.contains("=")){
                //
            }
            else if(line.contains("While")){
                //WhileVerifier
            }else if(line.contains("if")){
                //ifVerifier
            }
        }
    }

    private static boolean methodParser(String line)throws Exception{
        Matcher definitionMatcher = MethodDefinition.matcher(line);
        Matcher callMatcher = MethodCall.matcher(line);
        if(definitionMatcher.matches() && depth == 0){
            methodList.add(new MethodScope(depth,currentScope,definitionMatcher.group(0)));
            if(definitionMatcher.group(1) != "''"){
                //
            }
        }if(callMatcher)
    }

}
