package views.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.IntegerValidator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Config {

    private final EventHandler<ActionEvent> acceptEvent;
    private final EventHandler<ActionEvent> cancelEvent;

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
        size = (mBox.isSelected()) ? (size / 100) : (size);
        sizeField.setPromptText(String.valueOf(size));
        IntegerValidator validator = new IntegerValidator(ConstantsUI.ONLY_INTEGERS_TEXT);
        sizeField.setValidators(validator);
        sizeField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(!t1)
                sizeField.validate();
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
            unitsToCentimeters();
            mBox.setSelected(false);
            cmBox.setSelected(true);
        });
        mBox.setOnAction(actionEvent -> {
            unitsToMeters();
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
            warningField.setText(ConstantsUI.EXACT_CUTS_WARNING_TEXT);
        });
    }

    private void unitsToCentimeters(){
        if(cmBox.isSelected())
            sizeField.setPromptText(String.valueOf(Integer.parseInt(sizeField.getPromptText()) * 100));
    }

    private void unitsToMeters(){
        if(mBox.isSelected()) {
            double number = Math.ceil(Double.parseDouble(sizeField.getPromptText()) / 100);
            sizeField.setPromptText(String.valueOf((int)number));
        }
    }

    public int getSize(){
        if(validSize()) {
            if(mBox.isSelected())
                return Integer.parseInt(sizeField.getText()) * 100; //Conversion de m a cm
            else
                return Integer.parseInt(sizeField.getText());
        }
        else {
            if (mBox.isSelected())
                return Integer.parseInt(sizeField.getPromptText()) * 100;
            else
                return Integer.parseInt(sizeField.getPromptText());
        }
    }
    
    public boolean validSize(){
        return (sizeField.getText() != null && sizeField.getText().matches("\\d+"));
    }

    public String getUnits(){
        return (cmBox.isSelected()) ? ("cm") :("m");
    }

    public String getMethod(){
        return (exactBox.isSelected()) ? ("False"):("True");
    }
}
