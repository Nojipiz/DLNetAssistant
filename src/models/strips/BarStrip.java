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
        calculateSize();
    }

    public void calculateSize(){
        int modifiersSize = modifiers.size();
        if(modifiersSize <= ONE)
            sizeInMeters = calculateSimpleMod(modifiers.get(0));
        else
            sizeInMeters = calculateMultipleMod();
    }

    private double calculateMultipleMod(){
        double result = 0;
        for(String element : modifiers)
            result += calculateSimpleMod(element);
        return result;
    }

    private double calculateSimpleMod(String mod){
        String identifier = mod.replaceAll("[a-zA-Z]*", "");
        identifier = identifier.replaceAll("[^0-9.]", "");
        return Double.parseDouble(Strip.correctNumber(identifier));
    }
}
