import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Brayden May
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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
            assert !set.contains(s) : "Violation of: every entry in args is unique";
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
            assert !set.contains(s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size
    /*
     * Test cases for constructor
     */

    @Test
    public final void testConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for add
     */

    @Test
    public final void testAddEmptySet() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        s.add("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddNonEmptySet() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        s.add("4");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for remove
     */

    @Test
    public final void testRemoveOnlyElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String removed = s.remove("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("1", removed);
    }

    @Test
    public final void testRemoveFrontElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("2", "3", "4");
        /*
         * Call method under test
         */
        String removed = s.remove("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("1", removed);
    }

    @Test
    public final void testRemoveMiddleElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "4");
        /*
         * Call method under test
         */
        String removed = s.remove("3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("3", removed);
    }

    @Test
    public final void testRemoveLastElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3");
        /*
         * Call method under test
         */
        String removed = s.remove("4");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("4", removed);
    }

    /*
     * Test case for removeAny
     */

    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        String digit = s.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(sExpected.contains(digit) && s.size() == sExpected.size() - 1);
    }

    /*
     * Test cases for contains
     */

    @Test
    public final void testContainsOnlyElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1");
        Set<String> sExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        boolean contains = s.contains("1");
        boolean containsExpected = sExpected.contains("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(containsExpected, contains);
    }

    @Test
    public final void testContainsFrontElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        boolean contains = s.contains("1");
        boolean containsExpected = sExpected.contains("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(containsExpected, contains);
    }

    @Test
    public final void testContainsMiddleElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        boolean contains = s.contains("3");
        boolean containsExpected = sExpected.contains("3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(containsExpected, contains);
    }

    @Test
    public final void testContainsLastElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        boolean contains = s.contains("4");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contains);
    }

    @Test
    public final void testContainsNoElement() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        boolean contains = s.contains("5");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, contains);
    }

    /*
     * Test cases for size
     */

    @Test
    public final void testSize() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Set<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        int size = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(4, size);
    }
}
