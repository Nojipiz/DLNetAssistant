package views.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.core.Roll;
import models.strips.Strip;
import presenters.ControllerGUI;
import views.gui.boxes.BarBox;
import views.gui.boxes.ElementBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;


public class PrincipalApp extends Application {

    private Stage primaryStage;
    private ControllerGUI controllerGUI;

    public static void initApp(){
        launch(new String[]{});
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/initialWindow.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainWindow.fxml"));
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

    public void verifySize(ObservableList<Node> elementsPane, int materialSize){
        for(Node node : elementsPane){
            ElementBox box = (ElementBox) node;
            if(box.getStripSize() >= materialSize)
                box.setFullDisable(true);
        }
    }

    public ArrayList<Strip> getStripsSelected(ObservableList<Node> elementsPane){
        ArrayList<Strip> selectedList = new ArrayList<>();
        for(Node node : elementsPane){
            ElementBox box = (ElementBox) node;
            if(box.getButtonState())
                selectedList.add(box.getStrip());
        }
        return selectedList;
    }

    public void clearBarPanel(VBox barsPane){
        barsPane.getChildren().clear();
    }

    public void clearPanels(VBox barsPane, VBox elementsPane){
        barsPane.getChildren().clear();
        elementsPane.getChildren().clear();
    }

    //Cambiar por confirmacion adecuada
    public boolean cleanConfirmation(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Desea limpiar todos los elementos");
        alert.setContentText("Esta accion es irreversible y borrará elementos y resultados");
        Optional<ButtonType> option = alert.showAndWait();
        return option.get() == ButtonType.OK;
    }

    public void showException(FileNotFoundException e){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText("Error al cargar el archivo");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

}
