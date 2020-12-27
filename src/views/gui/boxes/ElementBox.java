package views.gui.boxes;

import com.jfoenix.controls.JFXToggleButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import models.strips.Strip;


public class ElementBox extends GridPane {

    private JFXToggleButton button;

    public ElementBox(Strip strip){
        initComponents(strip);
    }

    private void initComponents(Strip strip){
        getStylesheets().add("styles.css");
        getStyleClass().add("elementBox");
        getStyleClass().add("floatingPane");
        setMinSize(489, 80);
        setPrefHeight(80);
        setPadding(new Insets(12,12,12,12));

        ImageView image = new ImageView("resources/images/folder.png");
        image.setFitHeight(40);
        image.setFitWidth(40);
        add(image, 0,0);

        add(getSpacer(5), 1, 0);

        Label infoMetrics = new Label(strip.toString());
        infoMetrics.setFont(Font.font("Cantarell Regular", 20));
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
        if(button.isSelected() != isSelected)
            button.setSelected(isSelected);
    }

    public boolean getButtonState(){
        return button.isSelected();
    }
}
