import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /*
     * Test cases for constructor
     */
    @Test
    public final void testConstructor() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.constructorTest();
        Stack<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for push
     */

    @Test
    public final void testPushEmptySet() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        s.push("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testPushNonEmptySet() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("2", "3", "4");
        Stack<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        s.push("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for pop
     */

    @Test
    public final void testPopEmptyStack() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("1");
        Stack<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String removed = s.pop();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("1", removed);
    }

    @Test
    public final void testPopNonEmptyStack() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Stack<String> sExpected = this.createFromArgsRef("2", "3", "4");
        /*
         * Call method under test
         */
        String removed = s.pop();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("1", removed);
    }

    /*
     * Test cases for length
     */
    @Test
    public final void testLengthEmptyStack() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int size = s.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(0, size);
    }

    @Test
    public final void testLengthNonEmptyStack() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("1", "2", "3", "4");
        Stack<String> sExpected = this.createFromArgsRef("1", "2", "3", "4");
        /*
         * Call method under test
         */
        int size = s.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(4, size);
    }
}
