import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author bolomey.1 xu.3052
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /**
     * Test no argument constructor.
     */
    @Test
    public final void testnoargumentconstructor() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(n, nExpected);

    }

    /**
     * Test integer argument constructor with zero.
     */
    @Test
    public final void testintegerconstructorzero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);

        assertEquals(n, nExpected);

    }

    /**
     * Test integer argument constructor.
     */
    @Test
    public final void testintegerconstructor() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(24);
        NaturalNumber nExpected = this.constructorRef(24);

        assertEquals(n, nExpected);

    }

    /**
     * Test string argument constructor with zero.
     */
    @Test
    public final void teststringconstructorzero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");

        assertEquals(n, nExpected);

    }

    /**
     * Test string argument constructor.
     */
    @Test
    public final void teststringconstructor() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("254");
        NaturalNumber nExpected = this.constructorRef("254");

        assertEquals(n, nExpected);

    }

    /**
     * Test string argument constructor over max int value.
     */
    @Test
    public final void teststringconstructorMax() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest("99999999999999999");
        NaturalNumber nExpected = this.constructorRef("99999999999999999");

        assertEquals(n, nExpected);

    }

    /**
     * Test naturalnumber argument constructor with zero.
     */
    @Test
    public final void testnnconstructorzero() {
        /*
         * Set up variables
         */

        NaturalNumber tester = new NaturalNumber1L(0);
        NaturalNumber n = this.constructorTest(tester);
        NaturalNumber nExpected = this.constructorRef(tester);

        assertEquals(n, nExpected);

    }

    /**
     * Test naturalnumber argument constructor.
     */
    @Test
    public final void testnnconstructor() {
        /*
         * Set up variables
         */

        NaturalNumber tester = new NaturalNumber1L(16);
        NaturalNumber n = this.constructorTest(tester);
        NaturalNumber nExpected = this.constructorRef(tester);

        assertEquals(n, nExpected);

    }

    /**
     * Test naturalnumber argument constructor with large number.
     */
    @Test
    public final void testnnconstructorMax() {
        /*
         * Set up variables
         */

        NaturalNumber tester = new NaturalNumber1L("1231231231231231231231");
        NaturalNumber n = this.constructorTest(tester);
        NaturalNumber nExpected = this.constructorRef(tester);

        assertEquals(n, nExpected);

    }

    /**
     * Test multiply by 10.
     */
    @Test
    public final void testMultiplyBy10() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest("24");
        NaturalNumber nExpected = this.constructorRef("243");
        /*
         * Call multiplyby10 method under test
         */
        n.multiplyBy10(3);
        assertEquals(n, nExpected);

    }

    /**
     * Test multiply by 10.
     */
    @Test
    public final void testMultiplyBy10Zero() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(3);
        /*
         * Call multiplyby10 method under test
         */
        n.multiplyBy10(3);
        assertEquals(n, nExpected);

    }

    /**
     * Test multiply by 10.
     */
    @Test
    public final void testMultiplyBy10Empty() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(3);
        /*
         * Call multiplyby10 method under test
         */
        n.multiplyBy10(3);
        assertEquals(n, nExpected);

    }

    /**
     * Test multiply by 10 on max int.
     */
    @Test
    public final void testMultiplyBy10Max() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef("21474836476");
        /*
         * Call multiplyby10 method under test
         */
        n.multiplyBy10(6);
        assertEquals(n, nExpected);

    }

    /**
     * Test Divide by 10 with zero.
     */
    @Test
    public final void testDivideBy10Zero() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        /*
         * Call multiplyby10 method under test
         */
        int remainder = n.divideBy10();
        assertEquals(n, nExpected);
        assertEquals(remainder, 0);

    }

    /**
     * Test Divide by 10 with single digit.
     */
    @Test
    public final void testDivideBy10SingleDigit() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest("2");
        NaturalNumber nExpected = this.constructorRef("0");
        /*
         * Call multiplyby10 method under test
         */
        int remainder = n.divideBy10();
        assertEquals(n, nExpected);
        assertEquals(remainder, 2);

    }

    /**
     * Test Divide by 10.
     */
    @Test
    public final void testDivideBy10() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest("243");
        NaturalNumber nExpected = this.constructorRef("24");
        /*
         * Call multiplyby10 method under test
         */
        int remainder = n.divideBy10();
        assertEquals(n, nExpected);
        assertEquals(remainder, 3);

    }

    /**
     * Test isZero true.
     */
    @Test
    public final void testIsZerotrue() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest("0");

        /*
         * Call multiplyby10 method under test
         */
        boolean tester = n.isZero();

        assertEquals(tester, true);

    }

    /**
     * Test isZero false.
     */
    @Test
    public final void testIsZeroFalse() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest("6");

        /*
         * Call multiplyby10 method under test
         */
        boolean tester = n.isZero();

        assertEquals(tester, false);

    }

    /**
     * Test isZero empty.
     */
    @Test
    public final void testIsZeroEmpty() {
        /*
         * ,* Set up variables ,
         */
        NaturalNumber n = this.constructorTest();

        /*
         * Call multiplyby10 method under test
         */
        boolean tester = n.isZero();

        assertEquals(tester, true);

    }
}
