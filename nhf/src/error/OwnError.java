package error;

/**
 * Saját error üzenet, konstruktornak meghelet adni egy saját üzenetet
 */
@SuppressWarnings("serial")
public class OwnError extends Exception {
    public OwnError(String message) {
        super(message);
    }
}
