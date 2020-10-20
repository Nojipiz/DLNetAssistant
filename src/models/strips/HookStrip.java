package models.strips;

public class HookStrip extends Strip{

    private String sizes;
    private String hook;

    public HookStrip(int amount, String diameter, String sizes, String hook){
        super(amount, diameter);
        this.sizes = sizes;
        this.hook = hook;
        calculateSize();
    }

    public void calculateSize(){
        int indexSize = sizes.indexOf("/");
        String tempDiameter = sizes.substring(0, indexSize);
        String tempOver = sizes.substring(indexSize+1);
        double diameter = Double.parseDouble(Strip.correctNumber(tempDiameter));
        double overlap = Double.parseDouble(Strip.correctNumber(tempOver));
        double doubHook = Double.parseDouble(Strip.correctNumber(hook.replaceAll("G", "")));
        double tempSize = (2 * Math.PI * (diameter / 2) + overlap + (doubHook*2));
        double sizeInMeters = Strip.roundDoubles(tempSize, 2);
        super.setSizeInMeter(sizeInMeters);
    }
}
