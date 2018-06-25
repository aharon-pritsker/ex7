import java.util.regex.Pattern;

public abstract class Verifier {

    protected static final Pattern LegalName = Pattern.compile("^_\\w+|[A-Za-z]\\w*");

    abstract boolean verify(String toCheck);
}
