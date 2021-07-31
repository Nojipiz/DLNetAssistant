package models.strips;

public class RectangularStrip extends Strip{

    private String sides;
    private String hook;

    private double hookInCentimeters;

    public RectangularStrip(int amount, String diameter, String sides, String hook){
        super(amount, diameter);
        this.sides = sides;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        double weight, height;
        int index = sides.indexOf(MULTIPLY_SYMBOL);
        hook = hook.replaceAll("G", "");
        String sideA = Strip.correctNumber(sides.substring(0, index));
        String sideB = Strip.correctNumber(sides.substring(index+1));
        hook = Strip.correctNumber(hook);
        weight = Double.parseDouble(sideA);
        height = Double.parseDouble(sideB);
        hookInCentimeters = Double.parseDouble(hook);
        double tempSize = (weight*2) + (height*2) + (hookInCentimeters*2);
        double sizeInMeters = Strip.roundDoubles(tempSize, 2);
        super.setSizeInMeter(sizeInMeters);
    }

    private String getSizesInfo(){
        return sides + " Gancho: " + (int)(hookInCentimeters*100);
    }

    @Override
    public String toString(){
        return  "Cantidad: " + this.getAmount() + " Longitud: " + this.getSizesInfo() + "cm Diametro: " + this.getDiameter();
    }
}
