package models.strips;

public class TriangleV2Strip extends Strip{

    private String sideA, sideB, sideC;
    private String hook;

    public TriangleV2Strip(int amount, String diameter, String sideA, String sideB, String sideC, String hook){
        super(amount, diameter);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        double sideATemp, sideBTemp, sideCTemp, hookTemp;
        hook = hook.replaceAll("G", "");
        sideA = Strip.correctNumber(sideA);
        sideB = Strip.correctNumber(sideB);
        sideC = Strip.correctNumber(sideC);
        hook = Strip.correctNumber(hook);
        sideATemp = Double.parseDouble(sideA);
        sideBTemp = Double.parseDouble(sideB);
        sideCTemp = Double.parseDouble(sideC);
        hookTemp = Double.parseDouble(hook);
        double tempSize = sideATemp + sideBTemp + sideCTemp + (hookTemp*2);
        double sizeInMeters = Strip.roundDoubles(tempSize, 2);
        super.setSizeInMeter(sizeInMeters);
    }
}
