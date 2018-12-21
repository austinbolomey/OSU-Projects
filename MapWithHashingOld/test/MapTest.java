import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author bolomey.1 and xu.3052
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

    /**
     * Test no argument constructor.
     */
    @Test
    public final void testConstructor() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        assertEquals(m, mExpected);

    }

    /**
     * Test add to an empty map
     */
    @Test
    public final void testAddEmpty() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");
        m.add("a", "b");
        assertEquals(m, mExpected);

    }

    /**
     * Test add to map with existing elements
     */
    @Test
    public final void testAddNonEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");
        m.add("c", "d");
        assertEquals(m, mExpected);

    }

    /**
     * Test add to map with existing elements multiple times
     */
    @Test
    public final void testAddNonEmptyMultiple() {
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");
        m.add("c", "d");
        m.add("e", "f");
        assertEquals(m, mExpected);

    }

    /**
     * Test remove on map with existing elements
     */
    @Test
    public final void testRemoveOne() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b");
        m.remove("c");
        assertEquals(m, mExpected);

    }

    /**
     * Test remove to make map empty
     */
    @Test
    public final void testRemoveOneToEmpty() {
        Map<String, String> m = this.createFromArgsTest("a", "b");
        Map<String, String> mExpected = this.createFromArgsRef();
        m.remove("a");
        assertEquals(m, mExpected);
    }

    /**
     * Test remove from end
     */
    @Test
    public final void testRemoveEnd() {
        Map<String, String> m = this.createFromArgsTest("1", "a", "2", "b", "3",
                "c", "4", "d", "5", "e");
        Map<String, String> mExpected = this.createFromArgsRef("1", "a", "2",
                "b", "3", "c", "4", "d");
        Map.Pair<String, String> removed = m.remove("5");
        String key = removed.key();
        String val = removed.value();
        //makes sure the pair was removed
        assertEquals(m, mExpected);

        //checks that the key and value are correct
        assertEquals(key, "5");
        assertEquals(val, "e");

        //makes sure size is correct
        assertEquals(m.size(), 4);
    }

    /**
     * Test remove from middle
     */
    @Test
    public final void testRemoveMid() {
        Map<String, String> m = this.createFromArgsTest("1", "a", "2", "b", "3",
                "c", "4", "d", "5", "e");
        Map<String, String> mExpected = this.createFromArgsRef("1", "a", "2",
                "b", "4", "d", "5", "e");
        Map.Pair<String, String> removed = m.remove("3");
        String key = removed.key();
        String val = removed.value();
        //makes sure the pair was removed
        assertEquals(m, mExpected);

        //checks that the key and value are correct
        assertEquals(key, "3");
        assertEquals(val, "c");

        //makes sure size is correct
        assertEquals(m.size(), 4);
    }

    /**
     * Test removeany
     */
    @Test
    public final void testRemoveany() {
        //the setup
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d");
        Map.Pair<String, String> removed = m.removeAny();
        //tests that the pair is really removed from m
        boolean contains = m.hasKey(removed.key());
        assertEquals(contains, false);
        //makes sure the size has decreased by 1 for m
        assertEquals(m.size(), 1);

        //adds the removed pair back into m, makes sure it is the same as beginning
        m.add(removed.key(), removed.value());
        assertEquals(m, mExpected);

    }

    /**
     * Test hasvalue
     */
    @Test
    public final void testHasValue() {

        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");

        assertEquals(m.hasValue("b"), true);
        assertEquals(m.hasValue("false"), false);

    }

    /**
     * Test value
     */
    @Test
    public final void testValue() {

        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");
        String b = m.value("a");
        String d = m.value("c");
        String f = m.value("e");
        //tests that all the values in m return the right key values
        assertEquals(b, "b");
        assertEquals(d, "d");
        assertEquals(f, "f");

        //tests that m is unchanged after the method value
        assertEquals(m, mExpected);

    }

    /**
     * Test hasKey
     */
    @Test
    public final void testHasKey() {

        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");

        //tests that all the values in m return the right key values
        assertEquals(true, m.hasKey("a"));
        assertEquals(true, m.hasKey("c"));
        assertEquals(true, m.hasKey("e"));
        assertEquals(false, m.hasKey("false"));
        //tests that m is unchanged after the method value
        assertEquals(m, mExpected);

    }

    /**
     * Test size with empty map
     */
    @Test
    public final void testSizeEmpty() {

        Map<String, String> m = this.createFromArgsTest();

        assertEquals(m.size(), 0);

    }

    /**
     * Test size of filled map
     */
    @Test
    public final void testSize() {

        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");
        assertEquals(m.size(), 3);
        //makes sure m was not changed by size method
        assertEquals(m, mExpected);

    }
}
