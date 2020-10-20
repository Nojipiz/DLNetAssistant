package models.strips;

public class RoundStrip extends Strip{

    private String size;
    private String hook;

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
        double doubHook = Double.parseDouble(Strip.correctNumber(hook.replaceAll("G", "")));
        double tempSize = (2 * Math.PI * (doubDiameter / 2) * doubTurns);
        tempSize += (doubHook * 2);
        double sizeInMeters = Strip.roundDoubles(tempSize, 2);
        super.setSizeInMeter(sizeInMeters);
    }
}
