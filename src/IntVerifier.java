import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntVerifier extends Verifier {

    private static final Pattern IntDefinition = Pattern.compile(" *int +(.*) *= *(.*);");
    private static final Pattern LegalValue = Pattern.compile("[0-9]+");
    private static String verifiedName;

    public boolean verify(String toCheck){
        Matcher matcher = IntDefinition.matcher(toCheck);
        if(matcher.matches()){
            if(LegalName.matcher(matcher.group(0)).matches()){
                if(LegalValue.matcher(matcher.group(1)).matches()){
                    verifiedName = matcher.group(0);
                    return true;
                }
            }
        }
        return false;
    }
}
