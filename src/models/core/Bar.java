package models.core;

import models.strips.Strip;

public class Bar {

    private int length;
    private int amount;
    private String diameter;

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
}
