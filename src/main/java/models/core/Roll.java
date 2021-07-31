package models.core;

import utilities.Utilities;
import java.util.ArrayList;
import java.util.HashMap;

public class Roll {

    private final String diameter;
    private ArrayList<HashMap<Integer, ArrayList<Integer>>> rolls;

    public Roll(String diameter, String rollsData){
        this.diameter = diameter;
        stringToRolls(rollsData);
    }

    private void stringToRolls(String rollsData){
        rolls = new ArrayList<>();
        String[] tempList = rollsData.split("],");
        for(String element : tempList) {
            String[] list = element.split(", ");
            int waste = (int) Utilities.stringToDouble(list[0]);
            ArrayList<Integer> intList = new ArrayList<>();
            for(int i = 1; i < list.length; i++)
                intList.add(Utilities.stringToInteger(list[i]));
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            map.put(waste, intList);
            rolls.add(map);
        }

    }

    public ArrayList<HashMap<Integer, ArrayList<Integer>>> getSizeList(){
        return this.rolls;
    }

    public String toString(){
        return "Diameter: " + diameter + " Rolls: " + rolls ;
    }

    public String getDiameter() {
        return diameter;
    }

    public int getBarAmount(){
        return rolls.size();
    }
}
