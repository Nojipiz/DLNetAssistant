package presenters;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXToggleButton;
import exceptions.NotElementsSelectedException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.core.CoreTask;
import models.core.Roll;
import views.gui.Config;
import views.gui.ConstantsUI;
import views.gui.PrincipalApp;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private JFXButton clearButton, playButton, configButton, extraButton, gitBtn, emailBtn, webBtn;

    @FXML
    private StackPane stackPane;

    @FXML
    private Label infoLabel;

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
        }catch (FileNotFoundException e){
            app.setInfoText(ConstantsUI.NO_FILE_FOUND, infoLabel);
        }
        catch (NullPointerException e){
            app.setInfoText(ConstantsUI.NO_FILE_CHOOSED, infoLabel);
        }
        catch (Exception e){
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
        calculateFun();
    }

    private synchronized void calculateFun(){
        stackPane.setMouseTransparent(false);
        if(!app.isElementsSelected(elementsPane)) {
            app.showException(new NotElementsSelectedException(), stackPane);
            return;
        }
        loadingInit();
        ObservableList<Node> paneElements = elementsPane.getChildren();
        CoreTask task = new CoreTask(controller, app, paneElements);

        task.setOnRunning((succeesesEvent) -> {
            System.out.println("Running");
        });

        task.setOnSucceeded((succeededEvent) -> {
            finishCalc();
            ArrayList<Roll> rollList = task.getValue();
            app.setRollBars(rollList, barsPane);
            //Waste Dialog
            ArrayList<String[]> totalList = controller.wasteCalculation();
            double totalWeight = controller.getWeight(totalList, controller.getStockSize());
            app.showWaste(totalList, totalWeight , stackPane);
            //Waste Dialog
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
        executorService.shutdown();
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
         }finally {
             linkInit();
         }
     }

    private void setElements(){
        ObservableList<JFXToggleButton> selectAllButtonObs =  FXCollections.observableArrayList(Arrays.asList(selectAllButton));
        app.setStripElements(controller.readCalculation(), elementsPane, selectAllButtonObs);
        verifySize();
    }

    private void loadingInit(){
        playButton.setDisable(true);
        app.clearBarPanel(barsPane);
    }

    private void finishCalc(){
        playButton.setDisable(false);
    }

    public void setCutMethod(String method){
        controller.setCutMethod(method);
    }

    private void linkInit(){
        emailBtn.setOnAction(e -> { app.getHostServices().showDocument(ConstantsUI.EMAIL_LINK);});
        gitBtn.setOnAction(e -> { app.getHostServices().showDocument(ConstantsUI.GITHUB_LINK);});
        webBtn.setOnAction(e -> { app.getHostServices().showDocument(ConstantsUI.WEB_LINK);});
    }

}
