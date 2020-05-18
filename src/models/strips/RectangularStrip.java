package models.strips;

public class RectangularStrip extends Strip{

    public static final String MULTIPLY_SYMBOL = "*";

    private int amount;
    private String diameter;
    private String sides;
    private String hook;
    private double sizeInMeters;

    public RectangularStrip(int amount, String diameter, String sides, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.sides = sides;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        double weight, height, doubHook;
        int index = sides.indexOf(MULTIPLY_SYMBOL);
        hook = hook.replaceAll("G", "");
        String sideA = Strip.correctNumber(sides.substring(0, index));
        String sideB = Strip.correctNumber(sides.substring(index+1, sides.length()));
        hook = Strip.correctNumber(hook);
        weight = Double.parseDouble(sideA);
        height = Double.parseDouble(sideB);
        doubHook = Double.parseDouble(hook);
        double tempSize = (weight*2) + (height*2) + (doubHook*2);
        sizeInMeters = Strip.roundDoubles(tempSize, 2);
    }
}
