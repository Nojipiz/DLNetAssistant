package models.strips;

public class HookStrip extends Strip{

    private int amount;
    private String diameter;
    private String sizes;
    private String hook;
    private double sizeInMeters;

    public HookStrip(int amount, String diameter, String sizes, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.sizes = sizes;
        this.hook = hook;
    }

    public void calculateSize(){

    }
}
