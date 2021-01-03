package exceptions;

public class NotElementsSelectedException extends Exception{

    public static final String NOT_SELECTED_MSG = "No se ha seleccionado ningún elemento";

    public NotElementsSelectedException(){
        super(NOT_SELECTED_MSG);
    }
}
