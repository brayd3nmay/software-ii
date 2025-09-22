import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Brayden May and Micheal Sidoti
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
            assert !map.hasKey(args[i])
                    : "" + "Violation of: the 'key' entries in args are unique";
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
            assert !map.hasKey(args[i])
                    : "" + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /*
     * Test cases for constructor
     */

    @Test
    public final void testConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test cases for add
     */

    @Test
    public final void testAddEmptyMap() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1");
        /*
         * Call method under test
         */
        m.add("Red", "1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddNonEmptyMap() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        m.add("Green", "4");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test cases for remove
     */

    @Test
    public final void testRemoveOnlyElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Pair<String, String> removed = m.remove("Red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("Red", removed.key());
        assertEquals("1", removed.value());
    }

    @Test
    public final void testRemoveFrontElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Orange", "2", "Yellow",
                "3", "Green", "4");
        /*
         * Call method under test
         */
        Pair<String, String> removed = m.remove("Red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("Red", removed.key());
        assertEquals("1", removed.value());
    }

    @Test
    public final void testRemoveMiddleElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Green", "4");
        /*
         * Call method under test
         */
        Pair<String, String> removed = m.remove("Yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("Yellow", removed.key());
        assertEquals("3", removed.value());
    }

    @Test
    public final void testRemoveLastElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3");
        /*
         * Call method under test
         */
        Pair<String, String> removed = m.remove("Green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("Green", removed.key());
        assertEquals("4", removed.value());
    }

    /*
     * Test case for removeAny
     */

    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        m.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(m.size() == mExpected.size() - 1);
    }

    /*
     * Test cases for value
     */

    @Test
    public final void testValueOnlyElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1");
        /*
         * Call method under test
         */
        String value = m.value("Red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("1", value);
    }

    @Test
    public final void testValueFrontElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        String value = m.value("Red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("1", value);
    }

    @Test
    public final void testValueMiddleElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        String value = m.value("Yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("3", value);
    }

    @Test
    public final void testValueLastElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        String value = m.value("Green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("4", value);
    }

    /*
     * Test cases for hasKey
     */

    @Test
    public final void testHasKeyOnlyElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1");
        /*
         * Call method under test
         */
        boolean key = m.hasKey("Red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, key);
    }

    @Test
    public final void testHasKeyFrontElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        boolean key = m.hasKey("Red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, key);
    }

    @Test
    public final void testHasKeyMiddleElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        boolean key = m.hasKey("Yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, key);
    }

    @Test
    public final void testHasKeyLastElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        boolean key = m.hasKey("Green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, key);
    }

    @Test
    public final void testHasKeyNoElement() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        boolean key = m.hasKey("Blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, key);
    }

    /*
     * Test cases for size
     */

    @Test
    public final void testSize() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        Map<String, String> mExpected = this.createFromArgsRef("Red", "1", "Orange", "2",
                "Yellow", "3", "Green", "4");
        /*
         * Call method under test
         */
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(4, size);
    }
}
