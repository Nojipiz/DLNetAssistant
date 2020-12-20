package utilities;

public class Utilities {

    public static int stringToInteger(String text){
        if(text != null) {
            text = (text.replaceAll("[\\D]", ""));
            if (!text.equals("")) {
                return Integer.parseInt(text);
            }
        }else {
            return -1;
        }
        return -1;
    }
}
