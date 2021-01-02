package models.strips;

public class CurvedStrip extends Strip{

    private String length;
    private String hook;

    private double hookInMeters;

    public CurvedStrip(int amount, String diameter, String length, String hook){
        super(amount, diameter);
        this.length = length;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        double totalLength;
        totalLength = Double.parseDouble(Strip.correctNumber(length));
        hook = hook.replaceAll("G", "");
        int index = hook.indexOf("/");
        hook = hook.substring(0, index);
        hookInMeters = Double.parseDouble(Strip.correctNumber(hook));
        double sizeInMeters = totalLength + (hookInMeters * 2);
        super.setSizeInMeter(sizeInMeters);
    }

    private String getSizesInfo(){
        return length + " Gancho: " + (int)(hookInMeters *100);
    }

    @Override
    public String toString(){
        return  "Cantidad: " + this.getAmount() + " Longitud: " + this.getSizesInfo() + "cm Diametro: " + this.getDiameter();
    }
}
