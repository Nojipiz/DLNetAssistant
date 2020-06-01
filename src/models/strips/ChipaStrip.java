package models.strips;

import models.BarDiameter;

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
        calculateSize();
    }

    public void calculateSize(){
        for(BarDiameter barDiameter : BarDiameter.values()){
            if(diameter.equalsIgnoreCase(barDiameter.getIndicator()))
                sizeInMeters = Double.parseDouble(weight) * barDiameter.getWeightPerMeter();
        }
    }
}
