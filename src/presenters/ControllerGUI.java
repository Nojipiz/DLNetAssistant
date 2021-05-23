package presenters;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import exceptions.NotElementsSelectedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.core.Roll;
import views.gui.Config;
import views.gui.PrincipalApp;
import java.util.ArrayList;
import java.util.Arrays;

public class ControllerGUI{

    public static final String CALCULATE = "calculate";

    private PrincipalApp app;
    private Controller controller;
    private Config configGUI;

    @FXML
    private VBox barsPane, elementsPane;

    @FXML
    private JFXToggleButton selectAllButton;

    @FXML
    private JFXButton clearButton, playButton, configButton, extraButton;

    @FXML
    private StackPane stackPane;

    public ControllerGUI(PrincipalApp app){
        this.app = app;
        configGUIInit();
    }

    private void configGUIInit(){
        configGUI = new Config(actionEvent -> {
            controller.setStockSize(configGUI.getSize());
            app.setUnits(configGUI.getUnits());
            app.setMethod(configGUI.getMethod());
            app.closeConfigWindow();
        }, actionEvent -> app.closeConfigWindow());
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

     @FXML
    public void selectAllElements(){
        app.selectAllElements(elementsPane.getChildren(),  selectAllButton.isSelected());
    }

     @FXML
    public synchronized void calculate(){
         stackPane.setMouseTransparent(false);
         if(!app.isElementsSelected(elementsPane)) {
             app.showException(new NotElementsSelectedException(), stackPane);
             return;
         }
         loadingInit();
        ObservableList<Node> paneElements = elementsPane.getChildren();
        ArrayList<Roll> rollList = controller.optimization(app.getStripsSelected(paneElements), app.getMethod());
        app.setRollBars(rollList, barsPane);
        //Waste Dialog
         ArrayList<String[]> totalList = controller.wasteCalculation();
         double totalWeight = controller.getWeight(totalList, controller.getStockSize());
        app.showWaste(totalList, totalWeight , stackPane);
    }

     @FXML
    public void verifySize(){
        app.verifySize(elementsPane.getChildren(), controller.getStockSize());
     }

     @FXML
    public void clear(){
        app.cleanConfirmation(stackPane, barsPane, elementsPane);
    }

     @FXML
    public void showConfigWindow(){
        try {
            app.showConfigWindow(configGUI);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            configGUI.loadElements(controller.getStockSize(), app.getUnits(), controller.getCutMethod());
        }
     }

     @FXML
     public void showHelpWindow(){
         try {
             app.showHelpWindow();
         }catch(Exception e){
             e.printStackTrace();
         }
     }

    private void setElements(){
        ObservableList<JFXToggleButton> selectAllButtonObs =  FXCollections.observableArrayList(Arrays.asList(selectAllButton));
        app.setStripElements(controller.readCalculation(), elementsPane, selectAllButtonObs);
        verifySize();
    }

    private void loadingInit(){
        app.clearBarPanel(barsPane);
    }

    public void setCutMethod(String method){
        controller.setCutMethod(method);
    }

}
