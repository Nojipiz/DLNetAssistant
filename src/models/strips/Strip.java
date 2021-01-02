package models.strips;

public abstract class Strip {

    public static final String CERO = "0";
    protected static final String MULTIPLY_SYMBOL = "*";
    protected static final String G = "*";
    protected static final String VOID = "";
    protected static final int ONE = 1;
    protected static final int TWO = 2;
    protected static final int THREE = 3;
    protected static final String FLAT_BAR_ID = "OM";
    protected static final String U_HOOK_ID = "U";
    protected static final String L_HOOK_ID = "L";

    private int amount;
    private double sizeInMeters;
    private String diameter;

    public Strip(int amount, String diameter){
        this.amount = amount;
        this.diameter = diameter;
    }

    public abstract void calculateSize();

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getSizeCentimeters(){
        return (int)(sizeInMeters*100);
    }

    public int getAmount(){
        return this.amount;
    }

    public String getDiameter(){
        return this.diameter;
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

    protected void setSizeInMeter(double sizeInMeters){
        this.sizeInMeters = sizeInMeters;
    }



}
