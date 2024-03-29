package views.console;

import models.core.Roll;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    public static final String WELCOME_MSG = "Welcome to DLAssistant";
    public static final String GETFILEPATH_MSG = "Enter DLI file path:";

    private Scanner scan;

    public Console() {
        System.out.println(WELCOME_MSG);
        scan = new Scanner(System.in);
    }

    public String getFilePath(){
        System.out.println(GETFILEPATH_MSG);
        //CAMBIO TEMPORAL PARA AHORRAR TIEMPO
        //String filePath = scan.nextLine();
        return "/home/nojipiz/Proyectos/DL.net/Test/ALL_STRIPS.dli";
    }

    public void printRolls(ArrayList<Roll> list){
         for(Roll roll : list){
             System.out.println(roll.toString());
         }
    }

    public void showContent(String content){
        System.out.println(content);
    }

    public void showContent(String[] content){
        for(int i = 0; i < content.length; i++){
            System.out.println(content[i]);
        }
    }
}
