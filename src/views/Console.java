package views;

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
        String filePath = scan.nextLine();
        return filePath;
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