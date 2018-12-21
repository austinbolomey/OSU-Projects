import static org.junit.Assert.*;

import org.junit.Test;

public class random {

    /**
     * Test add.
     */
    @Test
    public final void testAddNonEmpty1() {
        /*
         * ,* Set up variables ,
         */
        Set<String> s = this.createFromArgsTest("red", "blue");
        Set<String> sExpected = this.createFromArgsRef("green", "red", "blue");
        /*
         * ,* Call method under test ,
         */
        s.add("green");
        /*
         * ,* Assert that values of variables match expectations ,
         */
        assertEquals(s, sExpected);
    }

    /**
     * Test remove.
     */
    @Test
    public final void testRemoveNonEmpty() {
        /*
         * ,* Set up variables ,
         */
        Set<String> s = this.createFromArgsTest("green", "red", "blue");
        Set<String> sExpected = this.createFromArgsRef("red", "blue");
        /*
         * ,* Call method under test ,
         */
        s.remove("green");
        /*
         * ,* Assert that values of variables match expectations ,
         */
        assertEquals(s, sExpected);
    }

    /**
     * Test removeAny.
     */
    @Test
    public final void testRemoveAny() {
        /*
         * ,* Set up variables ,
         */
        Set<String> s = this.createFromArgsTest("green", "red", "blue");
        Set<String> sExpected = this.createFromArgsRef("green", "red", "blue");
        /*
         * ,* Call method under test ,
         */
        String digit = s.removeAny();
        assertTrue(
                sExpected.contains(digit) && s.size() == sExpected.size() - 1);

    }

    /**
     * Test Contains.
     */
    @Test
    public final void testContains() {
        /*
         * ,* Set up variables ,
         */
        Set<String> s = this.createFromArgsTest("green", "red", "blue");
        Set<String> sExpected = this.createFromArgsRef("green", "red", "blue");
        /*
         * ,* Call method under test ,
         */
        String digit = "red";
        assertTrue(sExpected.contains(digit) && s.contains(digit));

    }

    /**
     * Test Size.
     */
    @Test
    public final void testSize() {
        /*
         * ,* Set up variables ,
         */
        Set<String> s = this.createFromArgsTest("green", "red", "blue");
        Set<String> sExpected = this.createFromArgsRef("green", "red", "blue");
        /*
         * ,* Call method under test ,
         */

        int size1 = s.size();
        int size2 = sExpected.size();
        assertTrue(size1 == size2);
    }
}

}
