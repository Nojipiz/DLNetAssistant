package presenters;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import models.core.Roll;
import views.gui.PrincipalApp;

import java.util.ArrayList;

public class ControllerGUI {

    private PrincipalApp app;
    private Controller controller;

    @FXML
    private VBox barsPane, elementsPane;

    @FXML
    private JFXToggleButton selectAllButton;

    @FXML
    private JFXButton clearButton;

    public ControllerGUI(PrincipalApp app){
        this.app = app;
    }

    @FXML
    public void loadFileAction(){
        String filePath = app.showFileChooser();
        this.controller = Controller.singletonController();
        try {
            controller.readingDef(filePath);
            app.setMainScene();
            setElements();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void addFile(){
        String filePath = app.showFileChooser();
        try {
            controller.readingDef(filePath);
            setElements();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setElements(){
        app.setStripElements(controller.readCalculation(), elementsPane);
        verifySize();
    }

    @FXML
    public void selectAllElements(){
        app.selectAllElements(elementsPane.getChildren(),  selectAllButton.isSelected());
    }

    @FXML
    public void calculate(){
        app.clearBarPanel(barsPane);
        ObservableList<Node> paneElements = elementsPane.getChildren();
        ArrayList<Roll> rollList = controller.optimization(app.getStripsSelected(paneElements));
        app.setRollBars(rollList, barsPane);
    }

     @FXML
    public void verifySize(){
        app.verifySize(elementsPane.getChildren(), 1200);
     }

     @FXML
    public void clear(){
        if(app.cleanConfirmation())
            app.clearPanels(barsPane, elementsPane);
    }
}
