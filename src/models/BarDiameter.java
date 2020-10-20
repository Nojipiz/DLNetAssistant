package models;

public enum BarDiameter {
    ONE_AND_CUARTER("\"1-1/4''\"","\"#10\"",0.15615),
    ONE("\"1''\"", "\"#8\"",0.25169),
    SEVEN_EIGHTHS("\"7/8''\"", "\"#7\"",0.32873),
    THREE_CUARTERS("\"3/4''\"", "\"#6\"",0.44743),
    FIVE_EIGHTHS("\"5/8''\"", "\"#5\"",0.64433),
    HALF("\"1/2''\"", "\"#4\"",1.00604),
    THREE_EIGHTHS("\"3/8''\"", "\"#3\"",1.78571),
    CUARTER("\"1/4''\"", "\"#2\"",4.01606),

    THREE_POINT_FIVE("\"D3.5\"", "\"3.5M\"",12.94498),
    FOURTH("\"D4.0\"", "\"4.0M\"",10.14884),
    FOURTH_POINT_FIVE("\"D4.5\"", "\"4.5M\"",8.01924),
    FIVE("\"D5.0\"", "\"5.0M\"",6.49561),
    FIVE_POINT_FIVE("\"D5.5\"", "\"5.5M\"",5.36816),
    SIX("\"D6.0\"", "\"6.0M\"",4.51093),
    SIX_POINT_FIVE("\"D6.5\"", "\"6.5M\"",3.84344),
    SEVEN("\"D7.0\"", "\"7.0M\"",3.31400),
    SEVEN_POINT_FIVE("\"D7.5\"", "\"7.5M\"",2.88697),
    EIGTH("\"D8.0\"", "\"8.0M\"",2.53731),
    EIGTH_POINT_FIVE("\"D8.5\"", "\"8.5M\"",2.24761),
    NINE("\"D9.0\"", "\"9.0M\"",2.01000),
    NINE_POINT_FIVE("\"D9.5\"", "\"9.5M\"",1.78571),
    TEN("\"D10.0\"", "\"10.0M\"",1.62074),
    ;

    private String indicator;
    private double weightPerMeter;
    private String subIndicator;

    BarDiameter(String indicator, String subIndicator, double weightPerMeter){
        this.indicator = indicator;
        this.weightPerMeter = weightPerMeter;
        this.subIndicator = subIndicator;
    }

    public String getIndicator(){
        return this.indicator;
    }

    public String getSubIndicator(){
        return this.subIndicator;
    }

    public double getWeightPerMeter(){
        return this.weightPerMeter;
    }
}
