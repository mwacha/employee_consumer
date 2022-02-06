package tk.mwacha.exceptions;

public class CanNotParseException extends RuntimeException {

    public CanNotParseException() {
        super("Cannot parse the message");
    }

    public CanNotParseException(String message) {
        super(message);
    }
}