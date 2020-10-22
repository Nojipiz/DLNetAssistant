package models.core;

import java.util.ArrayList;
import java.util.*;

public class OptimizationManager {

    protected ArrayList<Piece> pieceList;

    private final ArrayList<Piece[]> total;
    private ArrayList<OptimizationThread> threadList;
    private double bestSurplus = 400;
    private ArrayList<Piece[]> bestList;

    public static final double[] barList = {2.77};

    public OptimizationManager(ArrayList<Piece> stripList) {
        this.pieceList = stripList;
        total = new ArrayList<>();
        threadListInit();
    }

    private void threadListInit(){
        threadList = new ArrayList<>();
        for(Double element : barList) {
            threadList.add(new OptimizationThread(element, this));
            threadList.get(threadList.size()-1).start();
        }
    }

    private ArrayList<Piece> mergeThreadsResults(){
        double bestSurplus = 400;
        ArrayList<Piece> bestList = new ArrayList<>();
        for(OptimizationThread thread : threadList){
            ArrayList<Piece> list = thread.calculateOptimized();
            double surplus = thread.bestSurplus;
            if(surplus == 0)
                return list;
            if(surplus < bestSurplus){
                bestList = list;
                bestSurplus = surplus;
            }
        }
        return bestList;
    }

    public void opTest() {
        while(pieceList.size() != 0){
            ArrayList<Piece> actualList = mergeThreadsResults();
            printList(actualList);
            pieceList.removeAll(actualList);
        }
    }

    private void printList( ArrayList<Piece> list){
        for(Piece element : list){
            System.out.print(element.getSize());
        }
        System.out.println();
    }

}
