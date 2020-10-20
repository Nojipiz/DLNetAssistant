package models.strips;

public class TriangleStrip extends Strip{

    private String sides;
    private String hook;

    public TriangleStrip(int amount, String diameter, String sides, String hook){
        super(amount, diameter);
        this.sides = sides;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        double base, height, doubHook;
        int index = sides.indexOf(MULTIPLY_SYMBOL);
        hook = hook.replaceAll("G", "");
        String baseA = Strip.correctNumber(sides.substring(0, index));
        String sideB = Strip.correctNumber(sides.substring(index+1));
        hook = Strip.correctNumber(hook);
        base = Double.parseDouble(baseA);
        height = Double.parseDouble(sideB);
        doubHook = Double.parseDouble(hook);
        double sideA = Math.sqrt( Math.pow((base / 2),2) + Math.pow(height, 2));
        double tempSize = base + (sideA*2) + (doubHook*2);
        double sizeInMeters = Strip.roundDoubles(tempSize, 2);
        super.setSizeInMeter(sizeInMeters);
    }
}
