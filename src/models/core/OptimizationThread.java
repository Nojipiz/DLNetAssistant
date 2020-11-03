package models.core;

import java.lang.reflect.Array;
import java.util.*;

public class OptimizationThread extends Thread{

    private double barLength;
    private OptimizationManager manager;
    protected double bestSurplus;

    private ArrayList<Piece> defList = null;

    protected Piece[] opList;

    public OptimizationThread(double barLength, OptimizationManager manager){
        this.barLength = barLength;
        this.manager = manager;
        this.opList = new Piece[0];
    }

    protected ArrayList<Piece>calculateOptimized(){
        ArrayList<Piece> elements = new ArrayList<>();
        bestSurplus = 400;
        defList = null;
        opCore(barLength, elements, 0, manager.pieceList);
        return defList;
    }

    private void opCore(double number, ArrayList<Piece> elements, double sum, ArrayList<Piece> pieceList) {
        double actualSurplus = number - sum;
        if (actualSurplus < bestSurplus) {
            bestSurplus = actualSurplus;
            setList(elements);
        }
        else {
            for (Piece element : pieceList) {
                if (!elements.contains(element)) {
                    sum += element.getSize();
                    if (sum <= number) {
                        elements.add(element);
                        opCore(number, elements, sum, pieceList);
                        elements.remove(elements.indexOf(element));
                    }
                    else
                        return;
                    sum -= element.getSize();
                }
            }
        }
    }

    private void setList(ArrayList<Piece> list){
        defList = new ArrayList<>();
        for(Piece element : list) {
            defList.add(element);
        }
    }

}
