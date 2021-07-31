package views.gui.boxes;

import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import models.strips.Strip;
import views.gui.ConstantsUI;


public class ElementBox extends BorderPane {

    private JFXToggleButton button;
    private final Strip strip;
    private TextFlow infoMetrics;
    private final ObservableList<Node> elementsBox; // Parent VBOX
    private final ObservableList<JFXToggleButton> selectAllButton;

    public ElementBox(Strip strip, ObservableList<Node> elementsBox, ObservableList<JFXToggleButton> selectAllButton){
        this.strip = strip;
        this.elementsBox = elementsBox;
        this.selectAllButton = selectAllButton;
        initComponents();
    }

    private void initComponents(){
        GridPane mainPane = new GridPane();
        getStylesheets().add(ConstantsUI.STYLES_PATH);
        getStyleClass().add(ConstantsUI.STYLES_ELEMENT_BOX);
        getStyleClass().add(ConstantsUI.STYLES_FLOATING_PANE);

        setMinHeight(70);
        setPrefHeight(80);

        setPadding(new Insets(12,12,12,12));

        ImageView image = new ImageView(imagePath(strip.getClass().toString()));
        image.setFitHeight(40);
        image.setFitWidth(40);
        mainPane.add(image, 0,0);

        mainPane.add(getSpacer(5), 1, 0);

        infoMetrics = new TextFlow();
        Text text = new Text(strip.toString());
        text.setFont(Font.font(ConstantsUI.FONT_REGULAR, 17));
        infoMetrics.getChildren().add(text);
        mainPane.add(infoMetrics, 2, 0);

        mainPane.add(getSpacer(20),3,0);

        button = new JFXToggleButton();
        button.setToggleColor(Paint.valueOf("#e5322d"));
        button.setToggleLineColor(Paint.valueOf("#bf312f"));
        button.setOnAction(actionEvent -> {
            for(Node node : elementsBox){
                ElementBox box = (ElementBox) node;
                if(!box.getButtonState()) {
                    selectAllButton.get(0).setSelected(false);
                    return;
                }
            }
            selectAllButton.get(0).setSelected(true);
        });
        this.setCenter(mainPane);
        this.setRight(button);
    }

    private Label getSpacer(int spaces){
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < spaces; i++)
            text.append("-");
        Label label = new Label(text.toString());
        label.setVisible(false);
        return label;
    }

    public void setToggledButton(boolean isSelected){
        if(button.isSelected() != isSelected && !this.isDisable())
            button.setSelected(isSelected);
    }

    public int getStripSize(){
        return strip.getSizeCentimeters();
    }

    public boolean getButtonState(){
        return button.isSelected();
    }

    public Strip getStrip(){
        return strip;
    }

    public void setFullDisable(boolean exp){
        infoMetrics.getChildren().removeAll();
        this.setDisable(exp);
        button.setDisable(exp);
        if(exp) {
            Text warningText = new Text("Elemento deshabilitado, su largo total es mayor que la materia prima");
            warningText.setFont(Font.font("Cantarell Bold", 14));
            warningText.setStyle("-fx-text-inner-color: red;");
            infoMetrics.getChildren().add(warningText);
            getStyleClass().add("disabledElement");
        }else{
            infoMetrics.getChildren().add(new Text(strip.toString()));
            getStyleClass().remove("disabledElement");
        }
    }

    private String imagePath(String stripClass){
        for(StripImages image : StripImages.values()){
            if(stripClass.contains(image.getId()))
                return image.getPath();
        }
        return StripImages.Bar.getId();
    }



}
