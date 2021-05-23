package views.gui.boxes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import views.gui.ConstantsUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarBox extends GridPane {

    private HashMap<Integer, ArrayList<Integer>> rollInformation;
    private String diameter;
    private int amount;
    private Label amountLabel;

    public BarBox(HashMap<Integer, ArrayList<Integer>> roll, String diameter){
        amount = 1;
        rollInformation = roll;
        this.diameter = diameter;
        initComponents(roll);
    }

    private void initComponents(HashMap<Integer, ArrayList<Integer>> roll){
        getStylesheets().add(ConstantsUI.STYLES_PATH);
        getStyleClass().add(ConstantsUI.STYLES_ELEMENT_BOX);
        getStyleClass().add(ConstantsUI.STYLES_FLOATING_PANE);
        setMinWidth(600);
        setMinHeight(100);
        setPrefHeight(100);
        setPadding(new Insets(0,12,12,12));
        setAlignment(Pos.BASELINE_CENTER);

        getRowConstraints().add(new RowConstraints());

        ColumnConstraints columnOne = new ColumnConstraints();
        columnOne.setPercentWidth(10);
        ColumnConstraints columnTwo = new ColumnConstraints();
        columnTwo.setPercentWidth(90);
        ColumnConstraints columnThree = new ColumnConstraints();
        columnThree.setPercentWidth(10);
        getColumnConstraints().addAll(columnOne, columnTwo, columnThree);

        add(rollEntryToBarGUI(roll), 1,0);

        String waste = "";

        for(Map.Entry<Integer, ArrayList<Integer>> entry : roll.entrySet())
            waste = String.valueOf(entry.getKey());

        Label infoMetrics = new Label(ConstantsUI.WASTE_TEXT + waste + ConstantsUI.CENTIMETERS_AND_DIAMETER +diameter);
        infoMetrics.setFont(Font.font(ConstantsUI.FONT_REGULAR, 15));
        infoMetrics.setPadding(new Insets(-280,0,0,0));
        add(infoMetrics, 1, 0);

        amountLabel = new Label("x " + amount + ConstantsUI.UNITS_TEXT);
        amountLabel.setFont(Font.font(ConstantsUI.FONT_BOLD, 18));
        amountLabel.setPadding(new Insets(-280,0,0,350));
        add(amountLabel, 1, 0);
    }

    private BarGUI rollEntryToBarGUI(HashMap<Integer, ArrayList<Integer>> roll){
        BarGUI bar = new BarGUI(new NumberAxis(), new CategoryAxis(), roll);
        return bar;
    }

    private Label getSpacer(int spaces){
        String text = "";
        for(int i = 0; i < spaces; i++)
            text += "-";
        Label label = new Label(text);
        label.setVisible(false);
        return label;
    }

    public ArrayList<Object> getRollInformation() {
        ArrayList<Object> fullInfo = new ArrayList<>();
        fullInfo.add(rollInformation);
        fullInfo.add(diameter);
        return fullInfo;
    }

    public void addAmount(){
        amount++;
        amountLabel.setText("x " + amount + ConstantsUI.UNITS_TEXT);
    }
}
