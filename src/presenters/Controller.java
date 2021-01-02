package presenters;

import models.StripManager;
import models.core.Optimizator;
import models.core.Roll;
import models.strips.Strip;
import views.console.Console;
import persistence.DliReader;
import views.gui.PrincipalApp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private Console console;
    private StripManager stripManager;
    private DliReader dliReader;
    private Optimizator optimizator;
    private static Controller controller;

    // GUI

    private PrincipalApp applicationGUI;

    private void initClasses(){
        console = new Console();
        dliReader = new DliReader();
    }

    private void start(){
        initClasses();
        applicationGUI = new PrincipalApp();
        applicationGUI.initApp();
    }

    private Controller (){
        controller = this;
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

    private void optimizationConversion(ArrayList<Strip> stripList){
        optimizator = new Optimizator();
        optimizator.setStripList(stripList);
        optimizator.setStockSize(new int[]{1200});
        optimizator.optimizeStrips(optimizator.getStripLists());
    }

    //Controller GUI methods

    protected static Controller singletonController(){
        return controller;
    }

    protected void readingDef(String filePath){
        try {
            dliReader.setFilePath(filePath);
        }catch(FileNotFoundException e){
            applicationGUI.showException(e);
        }
    }

    protected ArrayList<Strip> readCalculation(){
        contentRead();
        return stripCalc();
    }

    protected ArrayList<Roll> optimization(ArrayList<Strip> stripList){
        optimizationConversion(stripList);
        return optimizator.getRollsList();
    }

    //Main

    public static void main(String[] args){
        controller = new Controller();
        controller.start();
    }
}
