import java.io.*;
import java.util.regex.Pattern;

public class Parser {

     private static final Pattern IntDefinition = Pattern.compile(" *int +(.*)= *(.*);");
     private static final Pattern BoolDefinition = Pattern.compile("\" *boolean +(.*) *= *(.*);\"");
     private static final Pattern DoubleDefinition = Pattern.compile("\" *double +(.*) *= *(.*);\"");
     private static final Pattern CharDefinition = Pattern.compile("\\s*char\\s(\\w+((\\s=\\s'\\S*')|(,\\s))*)+;");
     private static final Pattern StringDefinition = Pattern.compile(" *String +(.*) *= *(.*);");


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

}
