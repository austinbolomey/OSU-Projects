import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author bolomey.1 fraser.116
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testNoArgumentConstructorSize() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef();

        int size = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, 0);
    }

    @Test
    public final void test1pairConstructor() {

        Map<String, String> s = this.createFromArgsTest("1", "2");
        Map<String, String> sExpected = this.createFromArgsRef("1", "2");

        assertEquals(sExpected, s);
    }

    /**
     * test constructor.
     */
    @Test
    public final void testConstructor() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        assertEquals(m, mExpected);

    }

    /**
     * Tests for add.
     */
    @Test
    public final void testAddToEmpty() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");

        m.add("a", "b");

        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddToNonEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");

        m.add("e", "f");

        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddTwoToNonEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f", "g", "h");

        m.add("e", "f");
        m.add("g", "h");
        assertEquals(m, mExpected);
    }

    @Test
    public final void testAddTwoToEmpty() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");

        m.add("a", "b");
        m.add("c", "d");
        assertEquals(m, mExpected);
    }

    /**
     * Tests for Remove.
     */
    @Test
    public final void testRemoveToNonEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("c", "d");

        m.remove("a");
        assertEquals(mExpected, m);

    }

    @Test
    public final void testRemoveToEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef();

        m.remove("a");
        assertEquals(mExpected, m);

    }

    @Test
    public final void testRemoveTwoToNonEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");
        Map<String, String> mExpected = this.createFromArgsRef("c", "d");

        m.remove("a");
        m.remove("e");
        assertEquals(mExpected, m);

    }

    @Test
    public final void testRemoveTwoToEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef();

        m.remove("a");
        m.remove("c");
        assertEquals(mExpected, m);

    }

    /**
     * Tests for Remove-any.
     */
    @Test
    public final void removeAnyTestSizeOne() {
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef();
        Pair<String, String> s = m.removeAny();
        assertTrue(!mExpected.hasKey(s.key()));

        assertEquals(m, mExpected);
    }

    @Test
    public final void removeAnyTestSizeTwo() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");

        Pair<String, String> s = m.removeAny();
        assertTrue(mExpected.hasKey(s.key()));
        mExpected.remove(s.key());
        assertEquals(m, mExpected);
    }

    /**
     * Tests for Value.
     */
    @Test
    public final void testValueSizeMoreThanOne() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");

        String testA = m.value("a");
        assertEquals(testA, "b");
        String testC = m.value("c");
        assertEquals(testC, "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");
        assertEquals(m, mExpected);
    }

    @Test
    public final void testValueSizeOne() {
        Map<String, String> m = this.createFromArgsTest("a", "b");

        String test = m.value("a");
        assertEquals(test, "b");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");
        assertEquals(m, mExpected);
    }

    /**
     *
     * Tests for Has-key.
     */
    @Test
    public final void testHasKeySizeMoreThanOne() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");
        assertEquals(m.hasKey("a"), true);
        assertEquals(m.hasKey("c"), true);
        assertEquals(m, mExpected);
    }

    @Test
    public final void testHasKeySizeOne() {
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");
        assertEquals(m.hasKey("a"), true);
        assertEquals(m.hasKey("c"), false);
        assertEquals(m, mExpected);
    }

    @Test
    public final void testHasKeySizeZero() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        assertEquals(m.hasKey("a"), false);
        assertEquals(m, mExpected);
    }

    /**
     * Tests for Size.
     */
    @Test
    public final void testSizeZero() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        int sizem = m.size();
        assertEquals(sizem, 0);
        assertEquals(m, mExpected);
    }

    @Test
    public final void testSizeOne() {
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");
        int sizem = m.size();
        assertEquals(sizem, 1);
        assertEquals(m, mExpected);
    }

    @Test
    public final void testSizeMoreThanOne() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");
        int sizem = m.size();
        assertEquals(sizem, 3);
        assertEquals(m, mExpected);
    }
}
