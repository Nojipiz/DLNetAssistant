package test;

import models.core.OptimizationManager;
import models.core.Piece;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class testSort {

    @Test
    public void test(){
        ArrayList<Piece> list = new ArrayList<>();
        list.add(new Piece(40));
        list.add(new Piece(40));
        list.add(new Piece(40));
        list.add(new Piece(270));
        list.add(new Piece(270));
        list.add(new Piece(240));
        list.add(new Piece(240));
        list.add(new Piece(220));
        list.add(new Piece(220));
        list.add(new Piece(190));
        list.add(new Piece(190));
        list.add(new Piece(40));
        list.add(new Piece(40));
        list.add(new Piece(40));
        list.add(new Piece(270));
        list.add(new Piece(270));
        list.add(new Piece(240));
        list.add(new Piece(240));
        list.add(new Piece(220));
        list.add(new Piece(220));
        list.add(new Piece(190));
        list.add(new Piece(190));
        OptimizationManager opManager = new OptimizationManager(list);
    }
}
