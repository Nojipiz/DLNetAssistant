package exceptions;

public class InvalidStripTypeException extends Exception {

    public static final String INVALID_STRIP_MSG = "The file contents a non supported strip";

    public InvalidStripTypeException(){
        super(INVALID_STRIP_MSG);
    }

    public void printStackTrace(){
        System.err.println(INVALID_STRIP_MSG);
    }
}
