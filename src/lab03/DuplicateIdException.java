package lab03;

public class DuplicateIdException extends Exception { 
    public DuplicateIdException(String errorMessage) {
        super(errorMessage);
    }
}