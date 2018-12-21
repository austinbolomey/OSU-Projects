import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Bolomey.1
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
     * Test for add non-Empty.
     */
    @Test
    public final void testAdd() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> mExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");

        m.add("e", "f");

        assertEquals(m, mExpected);
    }

    /**
     * Test for add Remove.
     */
    @Test
    public final void testRemove() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> sExpected = this.createFromArgsRef("c", "d");

        m.remove("a");
        assertEquals(sExpected, m);

    }

    /**
     * Test for Remove-any.
     */
    @Test
    public final void testRemoveAny() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");

        m.removeAny();
        int size = m.size();
        assertEquals(size, 3);
    }

    /**
     * Test for Value.
     */
    @Test
    public final void testValue() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        String test = m.value("a");

        assertEquals(test, "b");
    }

    /**
     *
     * Test for Has-key.
     */
    @Test
    public final void testHasKey() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d");
        assertEquals(m.hasKey("a"), true);
    }

    /**
     * Test for Size.
     */
    @Test
    public final void testSize() {
        Map<String, String> m = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");
        int sizem = m.size();
        assertEquals(sizem, 6);
    }
}
