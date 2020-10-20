package models.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OptimizationThread extends Thread{

    private double barLength;
    private OptimizationManager manager;
    protected double bestSurplus;
    private ArrayList<Piece> bestList = new ArrayList<>();
    protected boolean calculate;

    protected Piece[] opList;

    public OptimizationThread(double barLength, OptimizationManager manager){
        this.barLength = barLength;
        this.manager = manager;
        this.opList = new Piece[0];
    }


    protected Piece[] calculateOptimized(){
        HashMap<Double, Piece[]> result = new HashMap<>();
        ArrayList<Piece> elements = new ArrayList<>();
        bestSurplus = 400;
        return opCore(barLength, elements, 0, manager.pieceList);
    }

    private Piece[] toArray(ArrayList<Piece> piece){
        Piece[] result = new Piece[piece.size()];
        for(int i = 0; i < piece.size(); i++){
            result[i] = piece.get(i);
        }
        return result;
    }

    private Piece[] opCore(double number, ArrayList<Piece> elements, double sum, ArrayList<Piece> pieceList) {
        double actualSurplus = number - sum;
        if (actualSurplus < bestSurplus) {
            bestSurplus = actualSurplus;
            bestList = elements;
        }
        if (sum == number)
            return toArray(elements);
         else {
            for (Piece element : pieceList) {
                if (!elements.contains(element)) {
                    sum += element.getSize();
                    if (sum <= number) {
                        elements.add(element);
                        Piece[] returList = opCore(number, elements, sum, pieceList);
                        elements.remove(elements.indexOf(element));
                        return returList;
                    }
                    sum -= element.getSize();
                }
            }
        }
        return toArray(bestList);
    }


}
