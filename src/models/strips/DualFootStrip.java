package models.strips;

public class DualFootStrip extends Strip{

    private int amount;
    private String diameter;
    private String sides;
    private String hook;
    private double sizeInMeters;

    public DualFootStrip(int amount, String diameter, String sides, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.sides = sides;
        this.hook = hook;
    }

    public void calculateSize(){

    }
}
