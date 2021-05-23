package models.core;

import models.strips.Strip;

public class Bar {

    private final int length;
    private int amount;
    private final String diameter;

    public Bar(Strip strip){
        length = strip.getSizeCentimeters();
        amount = strip.getAmount();
        diameter = strip.getDiameter();
    }

    public void add(Bar bar){
        this.amount += bar.getAmount();
    }

    public int getAmount(){
        return amount;
    }

    public int getLength(){
        return length;
    }

    public String getDiameter(){
        return diameter;
    }

    public String toString(){
        return amount + "." + length;
    }
}
