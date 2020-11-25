package models.core;

import models.strips.Strip;

import java.util.ArrayList;

public class Optimizator {

    private ArrayList<ArrayList<Strip>> stripLists;
    private int[] stockSize;

    public Optimizator() {
        stripLists = new ArrayList<>();
        stripLists = new ArrayList<>();
        stockSize = new int[1];
    }

    public void setStripList(ArrayList<Strip> stripList){
        stripRating(stripList);
    }

    public void setStockSize(int[] stockSize){
        this.stockSize = stockSize;
    }

    public ArrayList<ArrayList<Strip>> getStripLists(){
        return stripLists;
    }

    private void stripRating(ArrayList<Strip> stripList){
        ArrayList<String> diameters = new ArrayList<>();
        for(Strip element : stripList){
            String diameter = element.getDiameter();
            if(!diameters.contains(diameter)){
                diameters.add(diameter);
                stripLists.add(new ArrayList<>());
                stripLists.get(stripLists.size()-1).add(element);
            }else{
                stripLists.get(diameters.indexOf(diameter)).add(element);
            }
        }
    }

    public void optimizeStrips(ArrayList<ArrayList<Strip>> actualStripList){
        for(ArrayList<Strip> list : actualStripList)
            calculateList(list);
    }

    private void calculateList(ArrayList<Strip> list){
        for(int element : stockSize){
            ArrayList<Strip> elements = calculateList(list, element);
            core(elements);
        }
    }

    private ArrayList<Strip> calculateList(ArrayList<Strip> list, int stock){
        ArrayList<Strip> result = new ArrayList<>();
        for(Strip element : list){
            int times = (int) (stock / element.getSizeCentimeters());
            if(element.getAmount() < times)
                times = element.getAmount();
            element.setAmount(times);
            result.add(element);
        }
        return result;
    }

    private ArrayList<Strip> core(ArrayList<Strip> list){
        return null;
    }

}
