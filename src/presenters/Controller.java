package presenters;

import models.StripManager;
import models.core.Optimizator;
import models.core.Roll;
import models.strips.Strip;
import views.console.Console;
import persistence.DliReader;
import views.gui.PrincipalApp;

import javax.naming.ldap.Control;
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
    private ControllerGUI controllerGUI;

    private void initClasses(){
        console = new Console();
        dliReader = new DliReader();
    }

    private void backendProcess(){
        optimizatorConvertion();
        printResutls();
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

    private void optimizatorConvertion(){
        optimizator = new Optimizator();
        optimizator.setStripList(stripManager.getStripList());
        optimizator.setStockSize(new int[]{1200});
        optimizator.optimizeStrips(optimizator.getStripLists());
    }

    private void printResutls(){
        console.printRolls(optimizator.getRollsList());
    }

    //Controller GUI methods

    protected static Controller singletonController(){
        return controller;
    }

    protected void readingDef(String filePath){
        try {
            dliReader.setFilePath(filePath);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    protected ArrayList<Strip> readCalculation(){
        contentRead();
        return stripCalc();
    }

    protected ArrayList<Roll> optimization(){
        optimizatorConvertion();
        return optimizator.getRollsList();
    }

    //Main

    public static void main(String[] args){
        controller = new Controller();
        controller.start();
    }
}
