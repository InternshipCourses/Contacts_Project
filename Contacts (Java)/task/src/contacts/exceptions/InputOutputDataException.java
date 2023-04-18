package contacts.exceptions;

public class InputOutputDataException extends RuntimeException {
    public InputOutputDataException(Throwable cause) {
        super(cause);
    }

    public InputOutputDataException(String message) {
        super(message);
    }

    public InputOutputDataException(String message, Throwable cause) {
        super(message, cause);
    }
}