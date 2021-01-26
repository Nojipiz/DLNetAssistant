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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Config {

    private EventHandler<ActionEvent> acceptEvent;
    private EventHandler<ActionEvent> cancelEvent;

    @FXML
    private JFXButton cancelButton, acceptButton;

    @FXML
    private JFXCheckBox cmBox, mBox, exactBox, minimizeBox;

    @FXML
    private JFXTextField sizeField;

    @FXML
    private TextField warningField;

    public Config(EventHandler<ActionEvent> acceptEvent, EventHandler<ActionEvent> cancelEvent){
        this.acceptEvent = acceptEvent;
        this.cancelEvent = cancelEvent;
    }

    public void loadElements(int size, String units, String method){
        setCheckBoxes(units, method);
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
    }

    private void setCheckBoxes(String units, String method){
        if(units.equals("cm"))
            cmBox.setSelected(true);
        else
            mBox.setSelected(true);
        cmBox.setOnAction(actionEvent -> {
            mBox.setSelected(false);
            cmBox.setSelected(true);
        });
        mBox.setOnAction(actionEvent -> {
            cmBox.setSelected(false);
            mBox.setSelected(true);
        });

        if(method.equals("True"))
            minimizeBox.setSelected(true);
        else
            exactBox.setSelected(true);
        minimizeBox.setOnAction(actionEvent -> {
            exactBox.setSelected(false);
            minimizeBox.setSelected(true);
            warningField.setText("");
        });
        exactBox.setOnAction(actionEvent -> {
            minimizeBox.setSelected(false);
            exactBox.setSelected(true);
            warningField.setText("Cortes Exactos requiere una gran cantidad de calculos, esto afecta directamente el rendimiento del programa");
        });
    }

    public int getSize(){
        if(sizeField.getText() != null && sizeField.getText().matches("\\d+"))
            return Integer.parseInt(sizeField.getText());
        else
            return Integer.parseInt(sizeField.getPromptText());
    }

    public String getUnits(){
        return (cmBox.isSelected()) ? ("cm") :("m");
    }

    public String getMethod(){
        return (exactBox.isSelected()) ? ("False"):("True");
    }
}
