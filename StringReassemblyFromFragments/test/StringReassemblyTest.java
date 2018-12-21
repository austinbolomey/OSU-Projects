import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/* Austin Bolomey.1
 *
 */
public class StringReassemblyTest {

    @Test
    public void bigwhitest() {
        String combination = StringReassembly.combination("bigwhite", "whitest",
                5);
        assertEquals(combination, "bigwhitest");

    }

    @Test
    public void hamburger() {
        String combination = StringReassembly.combination("hamburger",
                "burgerrecipie", 6);
        assertEquals(combination, "hamburgerrecipie");

    }

    @Test
    public void linestest() {

        SimpleWriter out = new SimpleWriter1L();
        String test = "one space~two space~~three space~~~.";
        String result = "one space\n" + "two space\n" + "\n" + "three space\n"
                + "\n" + "\n" + ".";
        StringReassembly.printWithLineSeparators(test, out);
        out.close();
        assertEquals(test, result);
    }

    @Test
    public void buckspass() {
        Set<String> tester = new Set1L<>();
        tester.add("Buck");
        tester.add("eye Na");
        tester.add("tion");

        Set<String> result = new Set1L<>();
        result.add("Buck");
        result.add("eye Na");
        result.add("tion");
        StringReassembly.addToSetAvoidingSubstrings(tester, "Buc");
        assertEquals(tester, result);
    }

    @Test
    public void bucksadd() {
        Set<String> tester = new Set1L<>();
        tester.add("Buck");
        tester.add("eye Na");

        Set<String> result = new Set1L<>();
        result.add("Buck");
        result.add("ey" + "e Na");
        result.add("tion");
        StringReassembly.addToSetAvoidingSubstrings(tester, "tion");
        assertEquals(tester, result);
    }

    @Test
    public void thenaysh() {
        Set<String> tester = new Set1L<>();
        tester.add("Buck");
        tester.add("Nation");

        Set<String> result = new Set1L<>();
        result.add("Buckeye");
        result.add("Nation");

        StringReassembly.addToSetAvoidingSubstrings(tester, "Buckeye");
        assertEquals(tester, result);
    }

}
