package tk.mwacha.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private static final String DEFAULT_MSG = "Registry not found.";

    public EntityNotFoundException() {
        super(DEFAULT_MSG);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}