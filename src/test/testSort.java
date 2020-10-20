package test;

import models.core.OptimizationManager;
import models.core.Piece;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class testSort {

    @Test
    public void test(){
        ArrayList<Piece> list = new ArrayList<>();
        list.add(new Piece(0.7));
        list.add(new Piece(0.7));
        list.add(new Piece(0.3));
        list.add(new Piece(0.3));
        list.add(new Piece(0.6));
        list.add(new Piece(0.6));
        list.add(new Piece(0.6));
        list.add(new Piece(0.4));
        list.add(new Piece(0.4));
        OptimizationManager opManager = new OptimizationManager(list);
        opManager.opTest();
        opManager.printResult();
    }
}
