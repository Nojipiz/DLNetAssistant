package exceptions;

public class InvalidStripSizeException extends Exception{

    public static final String INVALID_STRIP_SIZE_MSG = "Existe(n) figura(s) con una medida superior a la materia prima";

    public InvalidStripSizeException(){
        super(INVALID_STRIP_SIZE_MSG);
    }
}
