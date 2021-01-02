package views.gui.boxes;

public enum StripImages {
    Bar("BarStrip", "/images/strips/bar.png"),
    Chipa("ChipaStrip", "/images/strips/chipa.png"),
    Curved("CurvedStrip", "/images/strips/curved.png"),
    DualFoot("DualFootStrip", "/images/strips/dualFoot.png"),
    Hook("HookStrip", "/images/strips/hook.png"),
    Rectangular("RectangularStrip", "/images/strips/rectangular.png"),
    Round("RoundStrip", "/images/strips/round.png"),
    Triangle("TriangleStrip", "/images/strips/triangle.png"),
    TriangleV2("TriangleV2Strip", "/images/strips/triangle.png"),
    ZigZag("ZigZagStrip", "/images/strips/zigzag.png");


    private String id;
    private String path;

    private StripImages(String id, String path){
        this.id = id;
        this.path = path;
    }

    public String getId(){
        return id;
    }

    public String getPath(){
        return path;
    }
}
