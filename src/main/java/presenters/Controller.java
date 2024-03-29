package presenters;

import models.StripManager;
import models.core.Optimizator;
import models.core.Roll;
import models.strips.*;
import persistence.DliReader;
import views.gui.PrincipalApp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private StripManager stripManager;
    private DliReader dliReader;
    private Optimizator optimizator;
    private static Controller controller;
    private int stockSize;

    // GUI

    private void initClasses(){
        dliReader = new DliReader();
    }

    private void start(){
        initClasses();
        PrincipalApp.initApp();
    }

    public Controller (){
        optimizator = new Optimizator();
        controller = this;
        stockSize = 1200;
    }

    private void contentRead(){
        try{
            String content = dliReader.getFileContent();
            stripManager = new StripManager(content);
            stripManager.readContent();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<Strip> stripCalc(){
        try {
            stripManager.stripInfo();
            stripManager.cleanStripInfo();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            stripManager.calculateStrips();
            return stripManager.getStripList();
        }
    }

    private void optimizationConversion(ArrayList<Strip> stripList, String method){
        optimizator = new Optimizator();
        optimizator.setCutMethod(method);
        optimizator.setStockSize(new int[]{stockSize});
        optimizator.setStripList(stripList);
        optimizator.optimizeStrips(optimizator.getStripLists());
    }

    public void setStockSize(int size){
        stockSize = size;
        optimizator.setStockSize(new int[]{stockSize});
    }

    //Controller GUI methods

    protected static Controller singletonController(){
        return controller;
    }

    protected void readingDef(String filePath) throws Exception{
        dliReader.setFilePath(filePath);
    }

    protected ArrayList<Strip> readCalculation(){
        contentRead();
        return stripCalc();
    }

    public ArrayList<Roll> optimization(ArrayList<Strip> stripList, String method){
        optimizationConversion(stripList, method);
        return optimizator.getRollsList();
    }

    protected ArrayList<String[]> wasteCalculation(){
        return optimizator.getWaste();
    }

    protected int getStockSize(){
        return stockSize;
    }

    protected String getCutMethod(){
        return optimizator.getCutMethod();
    }

    protected void setCutMethod(String method){
        optimizator.setCutMethod(method);
    }

    protected double getWeight(ArrayList<String[]> totalList, int barLength){
        return StripManager.elementsToWeight(totalList, barLength);
    }
    //Main

    public static void main(String[] args){
        controller = new Controller();
        controller.start();
    }
}
