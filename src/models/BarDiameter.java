package models;

public enum BarDiameter {
    ONE_AND_CUARTER("\"1-1/4''\"", 0.15615), ONE("\"1''\"", 0.2517), SEVEN_EIGHTHS("\"7/8''\"", 0.32873), THREE_CUARTERS("\"3/4''\"", 0.44743), FIVE_EIGHTHS("\"5/8''\"", 0.64433), HALF("\"1/2''\"", 1.00604), THREE_EIGHTHS("\"3/8''\"", 1.78571), CUARTER("\"1/4''\"", 4.01606);

    private String indicator;
    private double weightPerMeter;

    private BarDiameter(String indicator, double weightPerMeter){
        this.indicator = indicator;
        this.weightPerMeter = weightPerMeter;
    }

    public String getIndicator(){
        return this.indicator;
    }

    public double getWeightPerMeter(){
        return this.weightPerMeter;
    }
}
