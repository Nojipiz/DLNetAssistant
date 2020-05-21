package models.strips;

public class DualFootStrip extends Strip{

    private int amount;
    private String diameter;
    private String sides;
    private String hook;
    private double sizeInMeters;

    public DualFootStrip(int amount, String diameter, String sides, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.sides = sides;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        double topSide, verticalSide, doubleHook;
        int index = sides.indexOf(MULTIPLY_SYMBOL);
        hook = hook.replaceAll("G", "");
        String sideA = Strip.correctNumber(sides.substring(0, index));
        String sideB = Strip.correctNumber(sides.substring(index+1, sides.length()));
        hook = Strip.correctNumber(hook);
        topSide = Double.parseDouble(sideA);
        verticalSide = Double.parseDouble(sideB);
        doubleHook = Double.parseDouble(hook);
        sizeInMeters = topSide + (verticalSide * 2) + (doubleHook * 2);
    }
}
