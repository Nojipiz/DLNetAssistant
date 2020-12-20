package models.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CoreManager {

    private int[] stockSize;
    private ArrayList<String> results;
    private BarList list;
    private String localPath = System.getProperty("user.dir");

    public CoreManager(){
        results = new ArrayList<>();
        list = new BarList();
    }

    public void setStockSize(int[] stockSize){
        this.stockSize = stockSize;
    }

    public void setList(BarList list){
        this.list = list;
    }

    public ArrayList<String> run() {
        ArrayList<String> result = new ArrayList<>();
        try {
            String command = "python " + localPath + "/src/models/core/coreCutter.py " + list.toString() +" "+ stockSize[0];
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                if(s.charAt(0) == '[')
                    result.add(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
