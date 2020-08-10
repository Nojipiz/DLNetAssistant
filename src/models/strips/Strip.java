package models.strips;

public abstract class Strip {

    public static final String CERO = "0";
    public static final String MULTIPLY_SYMBOL = "*";
    public static final String G = "*";
    public static final String VOID = "";
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final String FLAT_BAR_ID = "OM";
    public static final String U_HOOK_ID = "U";
    public static final String L_HOOK_ID = "L";

    private int amount;
    private String diameter;
    private double sizeInMeters;

    public Strip(int amount, String diameter){
        this.amount = amount;
        this.diameter = diameter;
    }

    protected abstract void calculateSize();

    public double getSize(){
        return this.sizeInMeters;
    }

    public static String correctNumber(String number){
        return (number.charAt(0) == '.') ? (CERO + number) : (number);
    }

    public static double roundDoubles(double value, int decimalNumber) {
        double integerPart, result;
        result = value;
        integerPart = Math.floor(result);
        result=(result-integerPart)*Math.pow(10, decimalNumber);
        result=Math.round(result);
        result=(result/Math.pow(10, decimalNumber))+integerPart;
        return result;
    }

}
