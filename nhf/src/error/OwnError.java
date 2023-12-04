package error;

@SuppressWarnings("serial")
public class OwnError extends Exception {
    public OwnError(String message) {
        super(message);
    }
}
