import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author bolomey.1 fraser.116
 *
 * 
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    @Test
    public final void testEmptyConstructor() {
        Set<String> setTest = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef();

        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testOneElementConstructor() {
        Set<String> setTest = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a");

        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testManyElementsConstructor() {
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");
        Set<String> setExpected = this.createFromArgsRef("a", "b", "c");

        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testAddToEmpty() {
        Set<String> setTest = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef("a");
        setTest.add("a");
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testAddNonEmpty() {
        Set<String> setTest = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a", "b");
        setTest.add("b");
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testAddNonEmptyMany() {
        Set<String> setTest = this.createFromArgsTest("a", "b", "c", "d", "e");
        Set<String> setExpected = this.createFromArgsRef("a", "b", "c", "d",
                "e", "f");
        setTest.add("f");
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testAddEmptyStringToEmpty() {
        Set<String> setTest = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef();
        setTest.add("");
        setExpected.add("");
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testAddToEmpty2() {
        Set<String> setTest = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef("a", "b");
        setTest.add("a");
        setTest.add("b");
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testRemoveToEmpty() {
        Set<String> setTest = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a");
        String removedTest = setTest.remove("a");
        String removedExpected = setExpected.remove("a");
        assertEquals(setTest, setExpected);
        assertEquals(removedTest, removedExpected);
    }

    @Test
    public final void testRemove2ToEmpty() {
        Set<String> setTest = this.createFromArgsTest("a", "b");
        Set<String> setExpected = this.createFromArgsRef();
        setTest.remove("a");
        setTest.remove("b");
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testRemoveToNonEmpty() {
        Set<String> setTest = this.createFromArgsTest("a", "b");
        Set<String> setExpected = this.createFromArgsRef("a", "b");
        String removedTest = setTest.remove("b");
        String removedExpected = setExpected.remove("b");
        assertEquals(setTest, setExpected);
        assertEquals(removedTest, removedExpected);
    }

    @Test
    public final void testRemove2ToNonEmpty() {
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");
        Set<String> setExpected = this.createFromArgsRef("a");
        setTest.remove("b");
        setTest.remove("c");
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testRemoveOneFromManyToNonEmpty() {
        Set<String> setTest = this.createFromArgsTest("a", "b", "c", "d", "e");
        Set<String> setExpected = this.createFromArgsRef("a", "b", "c", "d",
                "e");
        String removedTest = setTest.remove("b");
        String removedExpected = setExpected.remove("b");
        assertEquals(setTest, setExpected);
        assertEquals(removedTest, removedExpected);
    }

    @Test
    public final void testContainsFalse() {
        Set<String> setTest = this.createFromArgsTest("a", "b");
        Set<String> setExpected = this.createFromArgsRef("a", "b");
        String x = "Jupiter";
        assertTrue(!setExpected.contains(x));
        assertTrue(!setTest.contains(x));
    }

    @Test
    public final void testContainsSize1() {
        Set<String> setTest = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a");
        String x = "a";
        assertTrue(setExpected.contains(x));
        assertTrue(setTest.contains(x));
    }

    @Test
    public final void testContainsSize2() {
        Set<String> setTest = this.createFromArgsTest("a", "b");
        Set<String> setExpected = this.createFromArgsRef("a", "b");
        String x = "a";
        String y = "b";

        assertTrue(setExpected.contains(x));
        assertTrue(setTest.contains(x));
        assertTrue(setExpected.contains(y));
        assertTrue(setTest.contains(y));
    }

    @Test
    public final void testSize0() {
        Set<String> setTest = this.createFromArgsTest();
        Set<String> setExpected = this.createFromArgsRef();
        int s = setTest.size();
        int s1 = setExpected.size();
        assertTrue(s == s1);
        assertTrue(s == 0);
        assertEquals(setTest, setExpected);

    }

    @Test
    public final void testSize1Same() {
        Set<String> setTest = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a");
        int s = setTest.size();
        int s1 = setExpected.size();
        assertTrue(s == s1);
        assertTrue(s == 1);
        assertEquals(setTest, setExpected);

    }

    @Test
    public final void testSize2() {
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");
        Set<String> setExpected = this.createFromArgsRef("a", "b", "c");
        int s = setTest.size();
        int s1 = setExpected.size();
        assertTrue(s == s1);
        assertTrue(s == 3);
        assertEquals(setTest, setExpected);

    }

    @Test
    public final void testRemoveAnySize1() {
        Set<String> setTest = this.createFromArgsTest("a", "b");
        Set<String> setExpected = this.createFromArgsRef("a", "b");
        String s = setTest.removeAny();
        assertEquals(setExpected.contains(s), true);
        setExpected.remove(s);
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testRemoveAnySize2() {
        Set<String> setTest = this.createFromArgsTest("a", "b");
        Set<String> setExpected = this.createFromArgsRef("a", "b");
        String s = setTest.removeAny();
        assertEquals(setExpected.contains(s), true);
        setExpected.remove(s);
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testRemoveAnySize3() {
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");
        Set<String> setExpected = this.createFromArgsRef("a", "b", "c");
        String s = setTest.removeAny();
        assertEquals(setExpected.contains(s), true);
        setExpected.remove(s);
        assertEquals(setTest, setExpected);
    }

    @Test
    public final void testRemoveAnyToEmpty() {
        Set<String> setTest = this.createFromArgsTest("a");
        Set<String> setExpected = this.createFromArgsRef("a");
        String removedTest = setTest.removeAny();
        String removedExpected = setExpected.removeAny();

        assertEquals(setTest, setExpected);
        assertEquals(removedTest, removedExpected);
    }

}
