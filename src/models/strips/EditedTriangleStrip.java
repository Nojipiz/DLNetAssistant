package models.strips;

public class EditedTriangleStrip extends Strip{

    private int amount;
    private String diameter;
    private String sides;
    private String hook;
    private String height;
    private double sizeInMeters;

    public EditedTriangleStrip(int amount, String diameter, String sides,String height, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.sides = sides;
        this.height = height;
        this.hook = hook;
    }

    public void calculateSize(){

    }
}
