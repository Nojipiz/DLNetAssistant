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

    public static double stringToDouble(String text){
        if(text != null) {
            text = (text.replaceAll("[\\D]", ""));
            if (!text.equals(""))
                return Double.parseDouble(text);
        }
        return -1;
    }

    public static double roundDouble (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
