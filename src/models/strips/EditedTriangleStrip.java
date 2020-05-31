package models.strips;

public class EditedTriangleStrip extends Strip{

    private int amount;
    private String diameter;
    private String sides;
    private String hook;
    private String height;
    private double sizeInMeters;

    public EditedTriangleStrip(int amount, String diameter, String sides,String height, String hook){
        super(amount, diameter);
        this.amount = amount;
        this.diameter = diameter;
        this.sides = sides;
        this.height = height;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        int indexSize = sides.indexOf("/");
        String bOne = sides.substring(0, indexSize);
        String bTwo = sides.substring(indexSize+1);
        double one = Double.parseDouble(Strip.correctNumber(bOne));
        double two = Double.parseDouble(Strip.correctNumber(bTwo));
        hook = hook.replaceAll("G", "");
        double h = Double.parseDouble(Strip.correctNumber(height));
        double g = Double.parseDouble(Strip.correctNumber(hook));
        double side = Math.sqrt(Math.pow(Math.abs(one - two) / 2, 2) + Math.pow(h, 2)) * 2;
        double tempTotalSize = one + two + (2 * g) + side;
        sizeInMeters = Strip.roundDoubles(tempTotalSize, 2);
    }
}
