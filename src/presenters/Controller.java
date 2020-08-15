package presenters;

import models.StripManager;
import views.Console;
import views.DliReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    private Console console;
    private StripManager stripManager;
    private DliReader dliReader;

    private Controller(){
        console = new Console();
        readingDef();
        contentRead();
        stripCalc();
        stripPrint();
    }

    private void readingDef(){
        try {
            String filePath = console.getFilePath();
            dliReader = new DliReader(filePath);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
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

    private void stripCalc(){
        try {
            stripManager.stripInfo();
            stripManager.cleanStripInfo();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            stripManager.calculateStrips();
        }
    }

    private void stripPrint(){
        console.showContent(stripManager.getStripList());
    }

    public static void main(String args[]){
        new Controller();
    }
}
