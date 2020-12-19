package models.core;

import models.strips.Strip;

import java.util.ArrayList;
import java.util.Scanner;

public class Optimizator {

    private ArrayList<ArrayList<Strip>> stripLists;
    private int[] stockSize;
    private CoreManager coreManager;

    public Optimizator() {
        stripLists = new ArrayList<>();
        stripLists = new ArrayList<>();
        coreManager = new CoreManager();
        stockSize = new int[1];
    }

    public void setStripList(ArrayList<Strip> stripList){
        stripRating(stripList);
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
            System.out.println(coreManager.run());
        }

    }

    private BarList stripToBar(ArrayList<Strip> list){
        BarList result = new BarList();
        for(Strip element : list)
            result.add(new Bar(element));
        return result;
    }

    private void calculateList(BarList list){
        for(int element : stockSize){
            //ArrayList<Bar> elements = calculateList(list, element);
            //core();
        }
    }

    private ArrayList<Strip> core(ArrayList<Strip> list){
        return null;
    }



}
