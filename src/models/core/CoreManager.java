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

    public void run() {
        try {
            String command = "python " + localPath + "/src/models/core/coreCutter.py 1.20-1.30 50";
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            System.out.println("Output:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
