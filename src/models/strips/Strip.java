package models.strips;

public abstract class Strip {

    private int amount;
    private String diameter;
    private double sizeInMeters;

    public Strip(int amount, String diameter){
        this.amount = amount;
        this.diameter = diameter;
    }

    public abstract void calculateSize();

    public double getSize(){
        return this.sizeInMeters;
    }
}
