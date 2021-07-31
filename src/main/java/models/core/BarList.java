package models.core;

import java.util.ArrayList;

public class BarList extends ArrayList<Bar> {

    public BarList(){
        super();
    }

    @Override
    public boolean add(Bar e) {
        int length = e.getLength();
        for(Bar bar : this){
            if(bar.getLength() == length){
                bar.add(e);
                return true;
            }
        }
        return super.add(e);
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Bar bar : this)
            result.append(bar.toString()).append("-");
        String resultStr = result.toString();
        return resultStr.substring(0, resultStr.length()-1);
    }

}
