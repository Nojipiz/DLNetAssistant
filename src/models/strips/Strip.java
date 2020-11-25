package models.strips;

public abstract class Strip {

    public static final String CERO = "0";
    protected static final String MULTIPLY_SYMBOL = "*";
    public static final String G = "*";
    public static final String VOID = "";
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final String FLAT_BAR_ID = "OM";
    public static final String U_HOOK_ID = "U";
    public static final String L_HOOK_ID = "L";

    private int amount;
    private double sizeInMeters;
    private String diameter;

    public Strip(int amount, String diameter){
        this.amount = amount;
        this.diameter = diameter;
    }

    public abstract void calculateSize();

    public Strip substractStrip(Strip element){
        this.amount -= element.amount;
        return this;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public double getSize(){
        return this.sizeInMeters;
    }

    public double getSizeCentimeters(){
        return this.sizeInMeters*100;
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
