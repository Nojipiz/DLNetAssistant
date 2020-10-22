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
        list.add(new Piece(0.98));
        list.add(new Piece(0.72));
        list.add(new Piece(0.72));
        list.add(new Piece(0.56));
        list.add(new Piece(0.51));
        list.add(new Piece(0.51));
        list.add(new Piece(0.56));
        list.add(new Piece(0.98));
        OptimizationManager opManager = new OptimizationManager(list);
        opManager.opTest();
    }
}
