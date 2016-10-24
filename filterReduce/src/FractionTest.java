import static org.junit.Assert.*;

/**
 * Created by semanticer on 4. 11. 2015.
 */
public class FractionTest {

    @org.junit.Test
    public void testIsSimple() throws Exception {

        Fraction simple = new Fraction(1,2);
        Fraction simple2 = new Fraction(7,3);
        Fraction nonSimple = new Fraction(3,6);
        Fraction nonSimple2 = new Fraction(4,6);

        assertTrue(simple.isSimple());
        assertTrue(simple2.isSimple());
        assertFalse(nonSimple.isSimple());
        assertFalse(nonSimple2.isSimple());

    }

    @org.junit.Test
    public void testSimplify() throws Exception {
        Fraction nonSimple = new Fraction(3,6);
        Fraction nonSimple2 = new Fraction(4,6);

        Fraction s1 = nonSimple.simplify();
        Fraction s2 = nonSimple2.simplify();

        assertEquals(1, s1.num());
        assertEquals(2, s1.den());

        assertEquals(2, s2.num());
        assertEquals(3, s2.den());

    }

    @org.junit.Test
    public void test$times() throws Exception {

        Fraction nonSimple = new Fraction(3,12);
        Fraction nonSimple2 = new Fraction(4,6);

        Fraction r= nonSimple.$times(nonSimple2);

        assertEquals(1, r.num());
        assertEquals(6, r.den());
    }

    @org.junit.Test
    public void test$div() throws Exception {
        Fraction nonSimple = new Fraction(14,18);
        Fraction nonSimple2 = new Fraction(1,7);

        Fraction r =  nonSimple.$div(nonSimple2);

        assertEquals(49, r.num());
        assertEquals(9, r.den());
    }

    @org.junit.Test
    public void test$plus() throws Exception {
        Fraction nonSimple = new Fraction(2,9);
        Fraction nonSimple2 = new Fraction(1,7);

        Fraction r =  nonSimple.$plus(nonSimple2);

        assertEquals(23, r.num());
        assertEquals(63, r.den());
    }

    @org.junit.Test
    public void testCompareTo() throws Exception {
        Fraction nonSimple1 = new Fraction(1,4);
        Fraction nonSimple2 = new Fraction(1,6);

        assertEquals(-2,nonSimple1.compareTo(nonSimple2));
    }

    @org.junit.Test
    public void test$minus() throws Exception {
        Fraction ns1 = new Fraction(50,15);
        Fraction ns2 = new Fraction(9,3);
        Fraction r =ns1.$minus(ns2);

              assertEquals(1,r.num());
        assertEquals(3,r.den());
    }
}