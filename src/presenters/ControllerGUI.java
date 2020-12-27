package presenters;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import views.gui.PrincipalApp;

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

    private void setElements(){
        app.setStripElements(controller.readCalculation(), elementsPane);
    }

    @FXML
    public void selectAllElements(){
        app.selectAllElements(elementsPane.getChildren(),  selectAllButton.isSelected());
    }

    @FXML
    public void calculate(){
        app.setRollBars(controller.optimization(), barsPane);
    }
}
