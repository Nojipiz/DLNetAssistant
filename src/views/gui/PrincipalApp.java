package views.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.core.Roll;
import models.strips.Strip;
import presenters.Controller;
import presenters.ControllerGUI;
import views.gui.boxes.BarBox;
import views.gui.boxes.ElementBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class PrincipalApp extends Application {

    private Stage primaryStage;
    private ControllerGUI controllerGUI;

    public static void initApp(){
        launch(new String[]{});
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/initialWindow.fxml"));
        controllerGUI = new ControllerGUI(this);
        loader.setController(controllerGUI);
        Parent root = loader.load();
        primaryStage.setTitle("DLI-A");
        primaryStage.setScene(new Scene(root, 650, 425 ));
        primaryStage.setMaxWidth(650);
        primaryStage.setMaxHeight(425);
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(425);
        primaryStage.setResizable(false);
        primaryStage.show();
        this.primaryStage = primaryStage;
    }

    public String showFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(ConstantsUI.FILE_CHOOSER_TITLE);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(ConstantsUI.DLI_FILE, ConstantsUI.DLI_FILE_EXT));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null)
            return selectedFile.getAbsolutePath();
        else
            return null;
    }

    public void setMainScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/mainWindow.fxml"));
        loader.setController(controllerGUI);
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.setMaxWidth(3840);
        primaryStage.setMinWidth(852);
        primaryStage.setMaxHeight(2160);
        primaryStage.setMinHeight(480);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public void setStripElements(ArrayList<Strip> list,VBox elements){
        for(Strip e : list)
            elements.getChildren().add(new ElementBox(e));
    }

    public void selectAllElements(ObservableList<Node> elementsPane, boolean isSelected){
        for(Node node : elementsPane){
            ElementBox box = (ElementBox) node;
            box.setToggledButton(isSelected);
        }
    }

    public void setRollBars(ArrayList<Roll> rolls, VBox bars){
        for(Roll roll : rolls) {
            for(HashMap<Integer, ArrayList<Integer>>  element : roll.getSizeList()) {
                bars.getChildren().add(new BarBox(element));
            }
        }
    }


}
