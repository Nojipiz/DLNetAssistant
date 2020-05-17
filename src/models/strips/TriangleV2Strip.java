package models.strips;

public class TriangleV2Strip extends Strip{

    private int amount;
    private String diameter;
    private String sideA, sideB, sideC;
    private String hook;
    private double sizeInMeters;

    public TriangleV2Strip(int amount, String diameter, String sideA, String sideB, String sideC, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.hook = hook;
    }

    public void calculateSize(){

    }
}
