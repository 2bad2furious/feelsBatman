package com.company;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Created by user on 20.11.2016.
 */

public class CManagerTest {
    @org.junit.Test
    public void testCompare(){
        CompareManager cm = createTestManager();

        CompareResult cr = cm.compare(createTestField());
        assertEquals(2,cr.getMatchColors());
    }

    @org.junit.Test
    public void testCompare2(){
        CompareManager cm = createTestManager();

        CompareResult cr = cm.compare(createBadTestField());
        assertNotEquals(2,cr.getMatchColors());
    }

    private CompareManager createTestManager(){
        ArrayList<Color> colors = new ArrayList<Color>();
        Color red = Color.parse('R');
        colors.add(red);
        Color blue = Color.parse('B');
        colors.add(blue);
        return new CompareManager(new Treasure(colors));
    }
    private Color[] createBadTestField(){
        Color[] arr = new Color[2];
        arr[0] = Color.parse('G');

        arr[1] = Color.parse('Y');
        return arr;
    }
    private Color[] createTestField(){
        Color[] arr = new Color[2];
        arr[0] = Color.parse('R');

        arr[1] = Color.parse('B');
        return arr;
    }
}
