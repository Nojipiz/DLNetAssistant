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
    }

    public void calculateSize(){

    }
}
