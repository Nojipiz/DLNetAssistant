package views.gui.boxes;

import com.jfoenix.controls.JFXToggleButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import models.strips.Strip;


public class ElementBox extends GridPane {

    private JFXToggleButton button;
    private Strip strip;
    private TextFlow infoMetrics;

    public ElementBox(Strip strip){
        this.strip = strip;
        initComponents();
    }

    private void initComponents(){
        getStylesheets().add("/styles.css");
        getStyleClass().add("elementBox");
        getStyleClass().add("floatingPane");
        setMinSize(489, 80);
        setPrefHeight(80);
        setPadding(new Insets(12,12,12,12));

        ImageView image = new ImageView(imagePath(strip.getClass().toString()));
        image.setFitHeight(40);
        image.setFitWidth(40);
        add(image, 0,0);

        add(getSpacer(5), 1, 0);

        infoMetrics = new TextFlow();
        Text text = new Text(strip.toString());
        text.setFont(Font.font("Cantarell Regular", 17));
        infoMetrics.getChildren().add(text);
        add(infoMetrics, 2, 0);

        add(getSpacer(20),3,0);

        button = new JFXToggleButton();
        button.setToggleColor(Paint.valueOf("#e5322d"));
        button.setToggleLineColor(Paint.valueOf("#bf312f"));
        add(button,4,0);
    }

    private Label getSpacer(int spaces){
        String text = "";
        for(int i = 0; i < spaces; i++)
            text += "-";
        Label label = new Label(text);
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
