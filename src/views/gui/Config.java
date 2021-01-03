package views.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.DepthTest;
import javafx.scene.control.TextFormatter;


public class Config {

    private EventHandler<ActionEvent> acceptEvent;
    private EventHandler<ActionEvent> cancelEvent;

    @FXML
    private JFXButton cancelButton, acceptButton;

    @FXML
    private JFXCheckBox cmBox, mBox;

    @FXML
    private JFXTextField sizeField;

    public Config(EventHandler<ActionEvent> acceptEvent, EventHandler<ActionEvent> cancelEvent){
        this.acceptEvent = acceptEvent;
        this.cancelEvent = cancelEvent;
    }

    public void loadElements(int size, String units){
        if(units.equals("cm"))
            cmBox.setSelected(true);
        else
            mBox.setSelected(true);
        sizeField.setPromptText(String.valueOf(size));
        IntegerValidator validator = new IntegerValidator("Solo numeros enteros");
        sizeField.setValidators(validator);
        sizeField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(!t1)
                    sizeField.validate();
            }
        });

        acceptButton.setOnAction(acceptEvent);
        cancelButton.setOnAction(cancelEvent);
        cmBox.setOnAction(actionEvent -> {
            mBox.setSelected(false);
            cmBox.setSelected(true);
        });
        mBox.setOnAction(actionEvent -> {
            cmBox.setSelected(false);
            mBox.setSelected(true);
        });
    }

    public int getSize(){
        if(sizeField.getText() != null && sizeField.getText().matches("\\d+"))
            return Integer.parseInt(sizeField.getText());
        else
            return Integer.parseInt(sizeField.getPromptText());
    }

    public String getUnits(){
        if(cmBox.isSelected())
            return "cm";
        else
            return "m";
    }
}
