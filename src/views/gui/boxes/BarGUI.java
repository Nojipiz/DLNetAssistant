package views.gui.boxes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BarGUI extends StackedBarChart<String, Number> {

    private ArrayList<Series<String, Number>> seriesList;
    private HashMap<Integer, ArrayList<Integer>> roll;

    public BarGUI(Axis<Number> axis, Axis<String> axis1, HashMap<Integer, ArrayList<Integer>> roll) {
        super(axis1, axis);
        seriesList = new ArrayList<>();
        this.roll = roll;
        initComponents();
    }

    private void initComponents(){
        rollsToSeries();
        dataFormatting();

        setRotate(90);
        getStylesheets().add("/styles.css");
        getStyleClass().add("barChart");
        getXAxis().setOpacity(0);
        getYAxis().setOpacity(0);
        setHorizontalGridLinesVisible(false);
        setHorizontalZeroLineVisible(false);
        setVerticalGridLinesVisible(false);
        setVerticalZeroLineVisible(false);
        setLegendVisible(false);
        setPadding(new Insets(-275,150,170,-225));
        setMinHeight(420);
        setMaxHeight(420);
        setMaxWidth(40);
        setMinWidth(40);
    }

    private void rollsToSeries(){
        for(Map.Entry<Integer, ArrayList<Integer>> entry : roll.entrySet()){
            for(Integer element : entry.getValue()){
                Series<String, Number> series = new Series<>();
                series.getData().addAll(new XYChart.Data("test", element));
                seriesList.add(series);
            }
        }
    }

    private void dataFormatting(){
        Platform.runLater(() -> {
            for(Series series : seriesList) {
                series.getData().forEach(seriesData -> {
                    XYChart.Data data = (XYChart.Data) seriesData;
                    StackPane node = (StackPane) data.getNode();
                    Number value = (Number) data.getYValue();
                    Text valueText = new Text(String.valueOf(value));
                    valueText.setRotate(270);
                    node.getChildren().add(valueText);
                });
            }
        });
        this.getData().addAll(seriesList);
    }

}
