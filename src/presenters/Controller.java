package presenters;

import models.Manager;
import views.Console;
import views.DliReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    private Console console;
    private Manager manager;
    private DliReader dliReader;

    private Controller(){
        console = new Console();
        readingDef();
        contentRead();
        stripCalc();

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
            manager = new Manager(content);
            manager.readContent();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void stripCalc(){
        try {
            manager.stripInfo();
            manager.cleanStripInfo();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            manager.calculateStrips();
        }
    }

    public static void main(String args[]){
        new Controller();
    }
}
