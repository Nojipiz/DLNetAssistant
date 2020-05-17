package models;

import exceptions.InvalidStripTypeException;
import models.strips.*;

import java.util.ArrayList;

public class Manager {

    public static final String NEW_ELEMENT_INDICATOR = "Nuevo Elemento";
    public static final String FILE_END_INDICATOR = "Total";
    public static final char STRIP_SEPARATION = '@';

    public static final String NOT_SUPPORTED_STRIP = "P";
    public static final String STRIP_RECTANGLE = "E";
    public static final String STRIP_CURVED = "G";
    public static final String STRIP_EB = "EB";
    public static final String STRIP_ROUND = "R";
    public static final String STRIP_ROUND_HOOKS = "C";
    public static final String STRIP_EDITED_TRIANGLE = "ET";
    public static final String STRIP_CHIPA = "CH";
    public static final String STRIP_ZIGZAG = "A";
    public static final String STRIP_TRIANGLE = "T";
    public static final String STRIP_TRIANGLE_V2 = "T2";

    private ArrayList<Strip> stripList;
    private String barInformation;
    private String[] stripInfoList;

    public Manager(String content){
        this.barInformation = content;
        stripList = new ArrayList<Strip>();
        clearBarInformation();
    }

    public String clearBarInformation(){
        int startIndex = barInformation.indexOf(NEW_ELEMENT_INDICATOR) + 27;
        int endIndex = barInformation.indexOf(FILE_END_INDICATOR);
        barInformation = barInformation.substring(startIndex, endIndex);
        String clearBarInfo = "";
        for(int i = 0; i < barInformation.length(); i++){
            char element = barInformation.charAt(i);
            if(element > 31 && element < 123)
                clearBarInfo += element;
        }
        clearBarInfo = clearBarInfo.replaceAll(">", "");
        barInformation = clearBarInfo;
        return barInformation;
    }

    public void readContent(){
        String barTypes = barInformation.substring(0, 5);
        barInformation = barInformation.substring(5, barInformation.length() - 1);
        barInformation = repairBarInformation(barInformation);
        barTypes = barTypes.replaceAll("[^0-9]", "");
        int typeBarsAmount = Integer.parseInt(barTypes);
        stripInfoList = new String[typeBarsAmount];
    }

    private String repairBarInformation(String barInfo){
        if(barInfo.charAt(0) != '&'){
            barInfo = "&" + barInfo;
        }
        for(int i = 0; i < barInfo.length(); i++){
            char element = barInfo.charAt(i);
            if(i < barInfo.length() - 1){
                char nextElement = barInfo.charAt(i+1);
                if(element == STRIP_SEPARATION && nextElement == STRIP_SEPARATION){
                    barInfo = barInfo.substring(0, i) + barInfo.substring(i+1,barInfo.length()-1);
                    i++;
                }
            }
        }
        return barInfo;
    }

    public void stripInfo(){
        String stripInfo = "";
        int stripListIndex = 0;
        for(int i = 0; i < barInformation.length(); i++){
            char element = barInformation.charAt(i);
            if(element == '@'){
                if(stripListIndex < stripInfoList.length){
                    if(stripInfo.length() < 5) {
                        stripInfo = "";
                    }else{
                        stripInfoList[stripListIndex] = stripInfo;
                        stripInfo = "";
                        stripListIndex++;
                    }
                }else
                    return;
            }else{
                stripInfo += element;
            }
        }
    }

    public void cleanStripInfo() throws Exception{
        for(int i = 0; i < stripInfoList.length; i++){
            String strip = stripInfoList[i];
            if(strip != null){
                boolean active = true;
                while (active) {
                    if (strip.charAt(0) < 48 || strip.charAt(0) > 57) {
                        strip = strip.substring(1, strip.length() - 1);
                    }else{
                        active = false;
                    }
                }
                stripInfoList[i] = strip;
            }else{
                throw new InvalidStripTypeException();
            }
        }
    }

    public void calculateStrips(){
        for(String strip : stripInfoList){
            if(strip != null) {
                stripList.add(generateStrips(strip));
            }
        }
    }

    private Strip generateStrips(String stripInfo){
        ArrayList<String> stripInformation = stripInformationIdentifier(stripInfo, 0);
        int modulesAmount = modulesCounter(stripInfo);
        stripInformation = new ArrayList<>(stripInformation.subList(0, modulesAmount));
        ArrayList<String> stripInformationAux = new ArrayList<String>();
        for(int i = 0; i < stripInformation.size(); i++){
            String element = stripInformation.get(i);
            if(!element.equals(""))
                stripInformationAux.add(element);
        }
        System.out.println(stripInformationAux);
        return null;
    }

    private ArrayList<String> stripInformationIdentifier(String stripInfo, int index){
        ArrayList<String> list = new ArrayList<String>();
        String stripInfoModule = "";
        for(; index < stripInfo.length(); index++){
            char element = stripInfo.charAt(index);
            if(element == ' '){
                list.add(stripInfoModule);
                list.addAll(stripInformationIdentifier(stripInfo, index + 1));
                if(index >= stripInfo.length()-1){
                    return list;
                }
            }else{
                stripInfoModule += element;
            }
        }
        stripInfoModule.replaceAll("\\s+", "");
        list.add(stripInfoModule);
        return list;
    }

    private int modulesCounter(String stripInfo){
        int counter = 0;
        for(int i = 0; i < stripInfo.length(); i++){
            char element = stripInfo.charAt(i);
            if(element == ' ')
                counter++;
        }
        return counter + 1;
    }

    private Strip stripGenerator(ArrayList<String> stripInformation){
        String stripType = stripInformation.get(1);
        if(stripType.equalsIgnoreCase(NOT_SUPPORTED_STRIP)){
            return null;
        }else {
            int stripAmount = Integer.parseInt(stripInformation.get(0));
            String diameter,sides,height,hook,weight,sideA,sideB;
            switch (stripType) {
                case STRIP_RECTANGLE:
                    diameter = stripInformation.get(2);
                    sides = stripInformation.get(3);
                    hook = stripInformation.get(4);
                    return new RectangularStrip(stripAmount, diameter, sides, hook);
                case STRIP_CURVED:
                    diameter = stripInformation.get(2);
                    sides = stripInformation.get(3);
                    hook = stripInformation.get(4);
                    return new CurvedStrip(stripAmount, diameter, sides, hook);
                case STRIP_EB:
                    diameter = stripInformation.get(2);
                    sides = stripInformation.get(3);
                    hook = stripInformation.get(4);
                    return new DualFootStrip(stripAmount, diameter, sides, hook);
                case STRIP_ROUND:
                    diameter = stripInformation.get(2);
                    sides = stripInformation.get(3);
                    hook = stripInformation.get(4);
                    return new RoundStrip(stripAmount, diameter, sides, hook);
                case STRIP_ROUND_HOOKS:
                    diameter = stripInformation.get(2);
                    sides = stripInformation.get(3);
                    hook = stripInformation.get(4);
                    return new HookStrip(stripAmount, diameter, sides, hook);
                case STRIP_EDITED_TRIANGLE:
                    diameter = stripInformation.get(2);
                    sides = stripInformation.get(3);
                    height = stripInformation.get(4);
                    hook = stripInformation.get(5);
                    return new EditedTriangleStrip(stripAmount, diameter, sides, height, hook);
                case STRIP_CHIPA:
                    diameter = stripInformation.get(2);
                    weight = stripInformation.get(3);
                    return new ChipaStrip(stripAmount, diameter, weight);
                case STRIP_ZIGZAG:
                    diameter = stripInformation.get(2);
                    height = stripInformation.get(3);
                    sideA = stripInformation.get(4);
                    sideB = stripInformation.get(5);
                    return new ZigZagStrip(stripAmount, diameter, height, sideA, sideB);
                case STRIP_TRIANGLE:
                    diameter = stripInformation.get(2);
                    sides = stripInformation.get(3);
                    hook = stripInformation.get(4);
                    return new TriangleStrip(stripAmount, diameter, sides, hook);
                case STRIP_TRIANGLE_V2:
                    diameter = stripInformation.get(2);
                    sides = stripInformation.get(3);
                    sideA = stripInformation.get(4);
                    sideB = stripInformation.get(5);
                    hook = stripInformation.get(6);
                    return new TriangleV2Strip(stripAmount, diameter, sides, sideA, sideB, hook);
                default:
                    diameter = stripInformation.get(1);
                    ArrayList<String> modifiersList = (ArrayList<String>) stripInformation.subList(2, stripInformation.size()-1);
                    return new BarStrip(stripAmount, diameter, modifiersList);
            }
        }
    }

}