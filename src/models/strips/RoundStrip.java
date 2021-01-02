package models.strips;

public class RoundStrip extends Strip{

    private String size;
    private String hook;

    private double hookInCentimeters;

    public RoundStrip(int amount, String diameter, String size, String hook){
        super(amount, diameter);
        this.size = size;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        int indexSize = size.indexOf("/");
        String turns = size.substring(0, indexSize);
        String diameter = size.substring(indexSize+1);
        double doubTurns = Double.parseDouble(Strip.correctNumber(turns));
        double doubDiameter = Double.parseDouble(Strip.correctNumber(diameter));
        hookInCentimeters = Double.parseDouble(Strip.correctNumber(hook.replaceAll("G", "")));
        double tempSize = (2 * Math.PI * (doubDiameter / 2) * doubTurns);
        tempSize += (hookInCentimeters * 2);
        double sizeInMeters = Strip.roundDoubles(tempSize, 2);
        super.setSizeInMeter(sizeInMeters);
    }

    private String getSizesInfo(){
        return size + " Gancho: " + (int)(hookInCentimeters*100);
    }

    @Override
    public String toString(){
        return  "Cantidad: " + this.getAmount() + " Longitud: " + this.getSizesInfo() + "cm Diametro: " + this.getDiameter();
    }
}
