package models.strips;

public class ZigZagStrip extends Strip{

    private String height;
    private String sideA, sideB;
    private double sizeInMeters;

    public ZigZagStrip(int amount, String diameter, String height, String sideA, String sideB){
        super(amount, diameter);
        this.height = height;
        this.sideA = sideA;
        this.sideB = sideB;
        calculateSize();
    }

    public void calculateSize(){
        double h, a ,b, aAmount, bAmount;
        String tempAAmount = sideA.substring(0, sideA.indexOf('/'));
        String tempBAmount = sideB.substring(0, sideB.indexOf('/'));
        aAmount = Integer.parseInt(tempAAmount);
        bAmount = Integer.parseInt(tempBAmount);
        String tempA = Strip.correctNumber(sideA.substring(sideA.indexOf('/')+1));
        String tempB = Strip.correctNumber(sideB.substring(sideB.indexOf('/')+1));
        a = Double.parseDouble(tempA);
        b = Double.parseDouble(tempB);
        h = Double.parseDouble(Strip.correctNumber(height));
        double tempSize = (aAmount+bAmount)*h + (aAmount-1)*a + bAmount * b;
        sizeInMeters = Strip.roundDoubles(tempSize, 2);
        super.setSizeInMeter(sizeInMeters);
    }
}
