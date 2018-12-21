import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Check if a given {@code Sequence<Integer>} is a palindrome.
 *
 * @author bolomey.1
 *
 */
public final class SequencePalindrome {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequencePalindrome() {
    }

    /**
     * Construct and return a sequence from a given array.
     *
     * @param args
     *            the array of integer
     * @return the sequence of integer
     * @ensures createFromArgs = [the sequence of integers in args]
     */
    private static Sequence<Integer> createFromArgs(int[] args) {
        assert args != null : "Violation of: args is not null";
        Sequence<Integer> s = new Sequence1L<Integer>();
        for (int x : args) {
            s.add(s.length(), x);
        }
        return s;
    }

    /**
     * Checks if a given {@code Sequence<Integer>} is a palindrome.
     *
     * @param s
     *            the {@code Sequence} to check
     * @return true if the given {@code Sequence} is a palindrome, false
     *         otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(Sequence<Integer> s) {
        assert s != null : "Violation of: s is not null";
        //        Sequence<Integer> temp = s.newInstance();
        //        boolean palindrome = false;
        //        if (s.length() == 0 || s.length() == 1) {
        //            palindrome = true;
        //        } else {
        //            for (int i = 0; i < s.length(); i++) {
        //                int digit = s.remove(s.length() - 1 - i);
        //                temp.add(i, digit);
        //                s.add(s.length() - i, digit);
        //
        //            }
        //        }
        //
        //        if (s.equals(temp)) {
        //            palindrome = true;
        //        }
        //        return palindrome;

        //This is just random comments
        //I want to look like I'm actually programming

        Sequence<Integer> temp = s.newInstance();
        boolean palindrome = false;
        for (int i = 0; i < s.length(); i++) {
            int digit = s.remove(s.length() - 1 - i);
            temp.add(i, digit);
            s.add(s.length() - i, digit);
        }
        if (s.equals(temp)) {
            palindrome = true;
        }
        return palindrome;
    }

    //random commenting again

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final int[][] sequences = { {}, { 1 }, { 2, 2 }, { 3, 4, 3 },
                { 5, 6, 7, 8, 8, 7, 6, 5 },
                { 9, 10, 11, 12, 13, 12, 11, 10, 9 }, { 1, 2 }, { 3, 4, 5 },
                { 6, 7, 8, 8, 7, 9 }, { 10, 11, 12, 12, 13, 10 },
                { 14, 15, 16, 17, 15, 14 }, { 6, 7, 8, 18, 8, 7, 9 },
                { 10, 11, 12, 19, 12, 13, 10 },
                { 14, 15, 16, 20, 17, 15, 14 } };
        final boolean[] results = { true, true, true, true, true, true, false,
                false, false, false, false, false, false, false };

        for (int i = 0; i < sequences.length; i++) {
            Sequence<Integer> s = createFromArgs(sequences[i]);
            Sequence<Integer> sCopy = createFromArgs(sequences[i]);
            /*
             * Check returned result and parameter restores mode
             */
            boolean correctResult = (isPalindrome(s) == results[i]);
            boolean restoredParameter = s.equals(sCopy);
            if (correctResult && restoredParameter) {
                out.print("    Test passed: " + s + " is ");
                if (!results[i]) {
                    out.print("not ");
                }
                out.println("a palindrome");
            } else {
                if (!correctResult) {
                    out.print("*** Test failed: " + sCopy + " is ");
                    if (!results[i]) {
                        out.print("not ");
                    }
                    out.println("a palindrome");
                }
                if (!restoredParameter) {
                    out.println("*** Test failed: " + s
                            + " was not restored to its original value "
                            + sCopy);
                }
            }
            out.println();
        }

        out.close();
    }

}
