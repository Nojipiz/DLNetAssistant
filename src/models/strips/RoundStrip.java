package models.strips;

public class RoundStrip extends Strip{

    private int amount;
    private String diameter;
    private String size;
    private String hook;
    private double sizeInMeters;

    public RoundStrip(int amount, String diameter, String size, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.size = size;
        this.hook = hook;
    }

    public void calculateSize(){

    }
}
