import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Statement}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class StatementTest {

    /**
     * The name of a file containing a sequence of BL statements.
     */
    private static final String FILE_NAME_1 = "test/statement1.bl",
            FILE_NAME_2 = "test/statement2.bl";

    private static final String invalid1 = "test/invalidStatement1.bl",
            invalid2 = "test/invalidStatement2.bl",
            invalid3 = "test/invalidStatement3.bl",
            invalid4 = "test/invalidStatement4.bl",
            invalid5 = "test/invalidStatement5.bl",
            invalid6 = "test/invalidStatement6.bl",
            invalid7 = "test/invalidStatement7.bl",
            invalid8 = "test/invalidStatement8.bl",
            invalid9 = "test/invalidStatement9.bl",
            invalid10 = "test/invalidStatement10.bl",
            invalid11 = "test/invalidStatement11.bl",
            invalid12 = "test/invalidStatement12.bl",
            invalid13 = "test/invalidStatement13.bl",
            invalid14 = "test/invalidStatement14.bl",
            invalid15 = "test/invalidStatement15.bl",
            invalid16 = "test/invalidStatement16.bl",
            invalid17 = "test/invalidStatement17.bl";

    /**
     * Invokes the {@code Statement} constructor for the implementation under
     * test and returns the result.
     *
     * @return the new statement
     * @ensures constructorTest = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorTest();

    /**
     * Invokes the {@code Statement} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new statement
     * @ensures constructorRef = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    // TODO - add more test cases for valid inputs for both parse and parseBlock
    // TODO - add more test cases for as many distinct syntax errors as possible
    //        for both parse and parseBlock

    /**
     * Test of parse on syntactically invalid input. invalid while condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError2() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing DO for while
     * statement
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError3() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid3);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing ending while word
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError4() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid4);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing first IF
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError5() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid5);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing first then
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError6() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid6);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing ending if for first
     * if statement
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError7() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid7);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing END
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError8() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid8);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing second IF
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError9() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid9);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. invalid if condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError10() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid10);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. invalid if condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError11() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid11);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. invalid while condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError12() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid12);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing ending IF
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError14() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid14);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing if ending END
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError15() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid15);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing second if statement
     * "THEN"
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError16() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid16);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input. missing while condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError17() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(invalid17);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }
}
