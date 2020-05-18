package test;

import models.strips.Strip;

import org.junit.Test;
import static org.junit.Assert.*;

public class StripTest {

    @Test
    public void roundDoubles() {
        String expected = "0.89";
        double result = Strip.roundDoubles(0.8809, 2);
        String strResult = String.valueOf(result);
        assertEquals(expected, strResult);
    }
}