package models.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CoreManager {

    private int[] stockSize;
    private BarList list;
    private String cutMethod = "True";

    public CoreManager(){
        list = new BarList();
    }

    public void setStockSize(int[] stockSize){
        this.stockSize = stockSize;
    }

    public void setList(BarList list){
        this.list = list;
    }

    public String getCutMethod() {
        return cutMethod;
    }

    public void setCutMethod(String cutMethod) {
        this.cutMethod = cutMethod;
    }

    public ArrayList<String> run() {
        ArrayList<String> result = new ArrayList<>();

        try {
            String command = "python " +"coreCutter.py " + list.toString() +" "+ stockSize[0]+" "+cutMethod;
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                if(s.charAt(0) == '[')
                    result.add(s);
            }
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            p.destroy();
        } catch(Exception e){ e.printStackTrace();}
        return result;
    }
}

