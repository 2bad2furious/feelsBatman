package com.company;


import static org.junit.Assert.*;

/**
 * Created by semanticer on 26. 11. 2015.
 */
public class ColorTest {

    @org.junit.Test
    public void testParse() throws Exception {
        Color red = Color.parse('R');
        Color yellow = Color.parse('Y');

        assertEquals(Color.RED, red);
        assertEquals(Color.YELLOW, yellow);
    }
    /*@org.junit.Test
    public void testComparator()throws Exception{
        CompareManager myCompare = new CompareManager(new Treasure())
    }*/
}