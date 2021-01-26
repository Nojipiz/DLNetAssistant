package presenters;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import exceptions.NotElementsSelectedException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.core.Roll;
import views.gui.Config;
import views.gui.LoadingFrame;
import views.gui.PrincipalApp;
import java.util.ArrayList;

public class ControllerGUI{

    public static final String CALCULATE = "calculate";

    private PrincipalApp app;
    private Controller controller;
    private LoadingFrame loading;
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
        loading = new LoadingFrame();
        loading.start();
        configGUIInit();
    }

    private void configGUIInit(){
        configGUI = new Config(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.setStockSize(configGUI.getSize());
                app.setUnits(configGUI.getUnits());
                app.setMethod(configGUI.getMethod());
                app.closeConfigWindow();
            }}, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                app.closeConfigWindow();
            }});
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
    public void calculate(){
         loadingInit();
         if(!app.isElementsSelected(elementsPane)) {
             loading.setVisible(true);
             app.showException(new NotElementsSelectedException(), stackPane);
             return;
         }
        ObservableList<Node> paneElements = elementsPane.getChildren();
        ArrayList<Roll> rollList = controller.optimization(app.getStripsSelected(paneElements), app.getMethod());
        app.setRollBars(rollList, barsPane);
        //Waste Dialog
         loading.setVisible(false);
         ArrayList<String[]> totalList = controller.wasteCalculation();
         double totalWeight = controller.getWeight(totalList, controller.getStockSize());
        app.showWaste(totalList, totalWeight , stackPane);
    }

     @FXML
    public void verifySize(){
        app.verifySize(elementsPane.getChildren(), 1200);
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

    private void setElements(){
        app.setStripElements(controller.readCalculation(), elementsPane);
        verifySize();
    }

    private void loadingInit(){
        loading.setVisible(true);
        app.clearBarPanel(barsPane);
    }

    public void setCutMethod(String method){
        controller.setCutMethod(method);
    }

}
