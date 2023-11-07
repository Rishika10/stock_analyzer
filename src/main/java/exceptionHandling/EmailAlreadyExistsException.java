package exceptionHandling;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException() {
        super("Email address already exists");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}

