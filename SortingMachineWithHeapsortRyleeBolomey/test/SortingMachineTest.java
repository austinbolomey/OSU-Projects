import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author bolomey.1 fraser.116
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    @Test
    public final void constructorZero() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        assertEquals(mExpected, m);
    }

    @Test
    public final void constructorOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a");

        assertEquals(mExpected, m);
    }

    @Test
    public final void constructorMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c", "d");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c", "d");

        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddToEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddToOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddToMoreThanOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "c");
        m.changeToExtractionMode();
        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstSizeOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String x = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(x, "a");
    }

    @Test
    public final void testRemoveFirstSizeMoreThanOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "c");

        String x = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals(x, "a");
    }

    @Test
    public final void testIsInInsertionModeTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");

        boolean inInsertionMode = m.isInInsertionMode();
        assertEquals(mExpected, m);
        assertEquals(inInsertionMode, true);
    }

    @Test
    public final void testIsInInsertionModeFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "c");

        boolean inInsertionMode = m.isInInsertionMode();
        assertEquals(mExpected, m);
        assertEquals(inInsertionMode, false);
    }

    @Test
    public final void testSizeZero() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(size, 0);
    }

    @Test
    public final void testSizeOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a");

        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(size, 1);
    }

    @Test
    public final void testSizeMoreThanOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");

        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(size, 3);
    }

    @Test
    public final void testSizeZeroInsertion() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(size, 0);
    }

    @Test
    public final void testSizeOneInsertion() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a");

        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(size, 1);
    }

    @Test
    public final void testSizeMoreThanOneInsertion() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "c");

        int size = m.size();
        assertEquals(mExpected, m);
        assertEquals(size, 3);
    }

    @Test
    public final void testOrder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");

        Comparator<String> orderM = m.order();
        Comparator<String> orderMExpected = mExpected.order();

        assertEquals(mExpected, m);
        assertEquals(orderM, orderMExpected);
    }
}
