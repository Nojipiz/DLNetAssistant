package models.strips;

import models.BarDiameter;

public class ChipaStrip extends Strip{

    private String diameter;
    private String weight;

    public ChipaStrip(int amount, String diameter, String weight){
        super(amount, diameter);
        this.diameter = diameter;
        this.weight = weight;
        calculateSize();
    }

    public void calculateSize(){
        for(BarDiameter barDiameter : BarDiameter.values()){
            if(diameter.equalsIgnoreCase(barDiameter.getIndicator())) {
                double sizeInMeters = Double.parseDouble(weight) * barDiameter.getWeightPerMeter();
                sizeInMeters = Strip.roundDoubles(sizeInMeters, 2);
                super.setSizeInMeter(sizeInMeters);
            }
        }
    }
}
