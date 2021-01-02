package views.gui.boxes;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;

public class BarBox extends GridPane {

    private HashMap<Integer, ArrayList<Integer>> rollInformation;
    private int amount;
    private Label amountLabel;

    public BarBox(HashMap<Integer, ArrayList<Integer>> roll){
        amount = 1;
        rollInformation = roll;
        initComponents(roll);
    }

    private void initComponents(HashMap<Integer, ArrayList<Integer>> roll){
        getStylesheets().add("/styles.css");
        getStyleClass().add("elementBox");
        getStyleClass().add("floatingPane");
        setMinSize(489, 90);
        setMaxSize(489, 90);
        setPadding(new Insets(0,12,12,12));
        getRowConstraints().add(new RowConstraints());

        add(rollEntryToBarGUI(roll), 0,0);

        Label infoMetrics = new Label(roll.toString());
        infoMetrics.setFont(Font.font("Cantarell Regular", 15));
        infoMetrics.setPadding(new Insets(-280,0,0,0));
        add(infoMetrics, 1, 0);

        amountLabel = new Label("X" + amount);
        amountLabel.setFont(Font.font("Cantarell Regular", 15));
        amountLabel.setPadding(new Insets(-280,0,0,40));
        add(amountLabel, 2, 0);
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

    public HashMap<Integer, ArrayList<Integer>> getRollInformation() {
        return rollInformation;
    }

    public void addAmount(){
        amount++;
        amountLabel.setText("X" + amount);
    }
}
