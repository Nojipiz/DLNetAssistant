package models.strips;

public class ChipaStrip extends Strip{

    private int amount;
    private String diameter;
    private String weight;
    private double sizeInMeters;

    public ChipaStrip(int amount, String diameter, String weight){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.weight = weight;
    }

    public void calculateSize(){

    }
}
