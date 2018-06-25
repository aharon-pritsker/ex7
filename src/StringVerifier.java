import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringVerifier extends Verifier {

    private static final Pattern LegalValue = Pattern.compile(" *'.*' *");
    private static final Pattern StringDefinition = Pattern.compile(" *String +(.*) *= *(.*);");


    public boolean verify(String toCehck){
        Matcher matcher = StringDefinition.matcher(toCehck);
        if(matcher.matches()){
            if(LegalName.matcher(matcher.group(0)).matches()&&LegalValue.matcher(matcher.group(1)).matches()){
                verifiedName = matcher.group(0);
                return true;
            }
        }
        return false;
    }
}
