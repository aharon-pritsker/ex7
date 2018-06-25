import java.util.regex.Pattern;

public class StringVerifier extends Verifier {

    private static final Pattern LegalValue = Pattern.compile(" *'.*' *");
    private static final Pattern StringDefinition = Pattern.compile(" *String +(.*) *= *(.*);");
    private static final Pattern 
}
