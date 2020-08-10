package models.strips;

public class ZigZagStrip extends Strip{

    private int amount;
    private String diameter;
    private String height;
    private String sideA, sideB;
    private double sizeInMeters;

    public ZigZagStrip(int amount, String diameter, String height, String sideA, String sideB){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.height = height;
        this.sideA = sideA;
        this.sideB = sideB;
        calculateSize();
    }

    public void calculateSize(){

    }
}
