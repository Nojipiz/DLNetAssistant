package models.core;

import models.strips.Strip;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Roll {

    private String diameter;
    private ArrayList<HashMap<Integer, ArrayList<Integer>>> rolls;
    private final ArrayList<Strip> originalStripList;

    public Roll(String diameter, String rollsData, ArrayList<Strip> originalStripList){
        this.diameter = diameter;
        stringToRolls(rollsData);
        this.originalStripList = originalStripList;
    }

    public String[] stripInfo(){
        String[] result = new String[originalStripList.size()*2];
        int i = 0;
        for(Strip strip : originalStripList){
            result[i] = String.valueOf(strip.getAmount());
            result[i+1] = String.valueOf("medidas");
            i += 2;
        }
        return result;
    }

    private void stringToRolls(String rollsData){
        rolls = new ArrayList<>();
        List<String> tempList = Arrays.asList(rollsData.split("],"));
        for(String element : tempList) {
            String[] list = element.split(" ");
            int waste = Utilities.stringToInteger(list[0]);
            ArrayList<Integer> intList = new ArrayList<>();
            for(int i = 1; i < list.length; i++)
                intList.add(Utilities.stringToInteger(list[i]));
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            map.put(waste, intList);
            rolls.add(map);
        }

    }

    public String toString(){
        return "Diameter: " + diameter + " Rolls: " + rolls ;
    }
}
