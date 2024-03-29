package models.strips;

import java.util.ArrayList;

public class BarStrip extends Strip{

    private ArrayList<String> modifiers;

    public BarStrip(int amount, String diameter, ArrayList<String> modifiers){
        super(amount, diameter);
        this.modifiers = modifiers;
        calculateSize();
    }

    public void calculateSize(){
        double sizeInMeters = calculateSimpleMod(modifiers.get(0));
        super.setSizeInMeter(sizeInMeters);
    }

    private double calculateSimpleMod(String mod){
        String identifier = mod.replaceAll("[a-zA-Z]*", "");
        identifier = identifier.replaceAll("[^0-9.]", "");
        return Double.parseDouble(Strip.correctNumber(identifier));
    }

    @Override
    public String toString(){
        return  "Cantidad: " + this.getAmount() + " Longitud: " + this.getSizeCentimeters() + "cm Diametro: " + this.getDiameter();
    }
}
