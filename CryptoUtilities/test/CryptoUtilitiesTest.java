import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Austin Bolomey.1
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("0", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber m = new NaturalNumber2(21);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("3", n.toString());
        assertEquals("0", m.toString());
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("0", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("1", n.toString());
        assertTrue(!result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("2", m.toString());
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("18", p.toString());
        assertEquals("19", m.toString());
    }

    @Test
    public void testGenerateNextLikelyPrime8_11() {
        NaturalNumber n = new NaturalNumber2(8);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("11", n.toString());
    }

    @Test
    public void testGenerateNextLikelyPrime23_23() {
        NaturalNumber n = new NaturalNumber2(23);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("23", n.toString());
    }

    @Test
    public void testIsPrime2_563() {
        NaturalNumber n = new NaturalNumber2(563);
        boolean test = CryptoUtilities.isPrime2(n);
        assertEquals(true, test);
    }

    @Test
    public void testIsPrime2_59() {
        NaturalNumber n = new NaturalNumber2(59);
        boolean test = CryptoUtilities.isPrime2(n);
        assertEquals(true, test);
    }

    @Test
    public void testIsWitnessToCompositeness6_131() {
        NaturalNumber n = new NaturalNumber2(131);
        NaturalNumber w = new NaturalNumber2(6);
        boolean test = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(false, test);
    }

    @Test
    public void testIsWitnessToCompositeness5_135() {
        NaturalNumber n = new NaturalNumber2(135);
        NaturalNumber w = new NaturalNumber2(5);
        boolean test = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(true, test);
    }

}
