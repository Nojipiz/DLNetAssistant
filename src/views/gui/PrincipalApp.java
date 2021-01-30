package views.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.core.Roll;
import models.strips.Strip;
import presenters.ControllerGUI;
import views.gui.boxes.BarBox;
import views.gui.boxes.ElementBox;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class PrincipalApp extends Application {

    private Stage primaryStage;
    private ControllerGUI controllerGUI;
    private Stage configStage;
    private Stage helpStage;
    private NumberFormat formatter;

    protected String units;
    protected String method = "True";

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
        this.units = "cm";
        formatter = new DecimalFormat("#0.0");
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

    public void setStripElements(ArrayList<Strip> list, VBox elements, ObservableList<JFXToggleButton> selectAllButton){
        for(Strip e : list)
            elements.getChildren().add(new ElementBox(e, elements.getChildren(), selectAllButton ));
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
                addRollBar(element,roll.getDiameter(), bars);
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

    public void cleanConfirmation(StackPane stackPane, VBox barsPane, VBox elementsPane){
        stackPane.setMouseTransparent(false);

        JFXDialogLayout layout = new JFXDialogLayout();

        Text header = new Text("Desea limpiar toda la información");
        header.setFont(Font.font("Cantarell Bold", 18));
        layout.setHeading(header);

        Text body = new Text("Esta acción no se puede revertir y eliminará todos los elementos y resultados de la pantalla");
        body.setFont(Font.font("Cantarell Regular", 15));
        layout.setBody(body);

        JFXDialog wasteResult = new JFXDialog(stackPane, layout, JFXDialog.DialogTransition.CENTER);

        JFXButton cancelButton = new JFXButton("Cancelar");
        cancelButton.setOnAction(actionEvent -> {
            stackPane.setMouseTransparent(true);
            wasteResult.close();
        });

        JFXButton acceptButton = new JFXButton("Aceptar");
        acceptButton.setOnAction(actionEvent -> {
            stackPane.setMouseTransparent(true);
            this.clearPanels(barsPane, elementsPane);
            wasteResult.close();
        });

        layout.setActions(acceptButton, cancelButton);
        wasteResult.show();
    }

    public void showException(Exception e, StackPane stackPane){
        stackPane.setMouseTransparent(false);
        JFXDialogLayout layout = new JFXDialogLayout();

        Text header = new Text("Error!");
        header.setFont(Font.font("Cantarell Bold", 18));
        layout.setHeading(header);
        Text body = new Text();
        body.setFont(Font.font("Cantarell Regular", 15));
        body.setText(e.getMessage());
        layout.setBody(body);

        JFXDialog wasteResult = new JFXDialog(stackPane, layout, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Aceptar");
        button.setOnAction(actionEvent -> {
            stackPane.setMouseTransparent(true);
            wasteResult.close();
        });
        layout.setActions(button);
        wasteResult.show();
    }

    private void addRollBar(HashMap<Integer, ArrayList<Integer>> element,String diameter, VBox bars){
        for(Node node : bars.getChildren()){
            BarBox box = (BarBox) node;
            ArrayList<Object> list = new ArrayList<>();
            list.add(element);
            list.add(diameter);
            if(box.getRollInformation().equals(list)) {
                box.addAmount();
                return;
            }
        }
        bars.getChildren().add(new BarBox(element, diameter));
    }

    public void showWaste(ArrayList<String[]> waste, double weight, StackPane stackPane){
        stackPane.setMouseTransparent(false);
        JFXDialogLayout layout = new JFXDialogLayout();

        Text header = new Text("CANTIDAD DE MATERIAL NECESARIO");
        header.setFont(Font.font("Cantarell Bold", 18));
        layout.setHeading(header);
        Text body = new Text();
        body.setFont(Font.font("Cantarell Regular", 15));
        StringBuilder text = new StringBuilder();

        for(String[] element : waste){
            text.append(element[0] + " Barras de " + element[1] );
            text.append("\n");
        }

        text.append("Peso Total: " + formatter.format(weight/100) + "Kg");
        body.setText(text.toString());
        layout.setBody(body);

        JFXDialog wasteResult = new JFXDialog(stackPane, layout, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Aceptar");
        button.setOnAction(actionEvent -> {
            stackPane.setMouseTransparent(true);
            wasteResult.close();
        });
        layout.setActions(button);
        wasteResult.show();
    }

    public void showConfigWindow(Config config) throws Exception{
        configStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/configWindow.fxml"));
        loader.setController(config);
        Parent root = loader.load();
        configStage.setTitle("Configuración");
        configStage.setScene(new Scene(root, 650, 425 ));
        configStage.setMaxWidth(450);
        configStage.setMaxHeight(582);
        configStage.setMinWidth(450);
        configStage.setMinHeight(582);
        configStage.setResizable(false);
        configStage.show();
    }

    public void showHelpWindow() throws Exception{
        helpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/helpWindow.fxml"));
        Parent root = loader.load();
        helpStage.setTitle("Ayuda");
        helpStage.setScene(new Scene(root, 600, 600 ));
        helpStage.setMaxWidth(600);
        helpStage.setMaxHeight(600);
        helpStage.setMinWidth(600);
        helpStage.setMinHeight(600);
        helpStage.setResizable(false);
        helpStage.show();
    }

    public void closeConfigWindow(){
        configStage.close();
    }

    public void setUnits(String units){
        this.units = units;
    }

    public String getUnits(){
        return units;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
        controllerGUI.setCutMethod(method);
    }

    public boolean isElementsSelected(VBox elementsPane){
        for(Node e : elementsPane.getChildren()){
            ElementBox element = (ElementBox) e;
            if(element.getButtonState())
                return true;
        }
        return false;
    }



}
