package models.strips;

import java.util.ArrayList;

public class BarStrip extends Strip{

    private int amount;
    private String diameter;
    private ArrayList<String> modifiers;
    private double sizeInMeters;

    public BarStrip(int amount, String diameter, ArrayList<String> modifiers){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.modifiers = modifiers;
    }

    public void calculateSize(){

    }
}
