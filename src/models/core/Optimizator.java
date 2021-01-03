package models.core;

import models.strips.Strip;

import java.util.ArrayList;

public class Optimizator {

    private ArrayList<ArrayList<Strip>> stripLists;
    private int[] stockSize;
    private CoreManager coreManager;
    private ArrayList<Roll> rollsList;

    public Optimizator() {
        stripLists = new ArrayList<>();
        rollsList = new ArrayList<>();
        coreManager = new CoreManager();
    }

    public void setStripList(ArrayList<Strip> stripList){
        stripRating(stripList);
        rollsList = new ArrayList<>();
    }

    public void setStockSize(int[] stockSize){
        this.stockSize = stockSize;
        coreManager.setStockSize(stockSize);
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
        for(ArrayList<Strip> list : actualStripList){
            BarList barList = stripToBar(list);
            coreManager.setList(barList);
            Roll roll = new Roll(barList.get(0).getDiameter(), coreManager.run().toString(), list);
            rollsList.add(roll);
        }
    }

    public ArrayList<Roll> getRollsList(){
        return rollsList;
    }

    private BarList stripToBar(ArrayList<Strip> list){
        BarList result = new BarList();
        for(Strip element : list)
            result.add(new Bar(element));
        return result;
    }

    public ArrayList<String[]> getWaste(){
        ArrayList<String[]> list = new ArrayList<>();
        for(Roll roll : rollsList){
            list.add(new String[]{String.valueOf(roll.getBarAmount()), roll.getDiameter()});
        }
        return list;
    }



}
