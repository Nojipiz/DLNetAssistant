package models.strips;

public class CurvedStrip extends Strip{

    private int amount;
    private String diameter;
    private String length;
    private String hook;
    private double sizeInMeters;

    public CurvedStrip(int amount, String diameter, String length, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.length = length;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        double totalLength, totalHook;
        totalLength = Double.parseDouble(Strip.correctNumber(length));
        hook = hook.replaceAll("G", "");
        int index = hook.indexOf("/");
        hook = hook.substring(0, index);
        totalHook = Double.parseDouble(Strip.correctNumber(hook));
        sizeInMeters = totalLength + (totalHook*2);
    }
}
