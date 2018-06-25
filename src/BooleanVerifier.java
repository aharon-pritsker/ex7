import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanVerifier extends Verifier {

    private final static Pattern LegalValue = Pattern.compile("true|false|[0-9]+.?[0-9]+");
    private final static Pattern BooleanDefinition = Pattern.compile(" *boolean +(.*) *= *(.*);");

    public boolean verify(String toCheck){
        Matcher matcher = BooleanDefinition.matcher(toCheck);
        if(LegalName.matcher(matcher.group(0)).matches()&&LegalValue.matcher(matcher.group(1)).matches()){
            verifiedName = matcher.group(0);
            return true;
        }
        return false;
    }
}
