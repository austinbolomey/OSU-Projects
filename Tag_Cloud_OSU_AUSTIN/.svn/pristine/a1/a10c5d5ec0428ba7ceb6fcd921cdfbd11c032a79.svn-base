import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplewriter.SimpleWriter;

/**
 * Program to test static methods {@code generateElements} and
 * {@code nextWordOrSeparator}.
 *
 * @author Put your name here
 *
 */
public final class NextWordOrSeparatorTest {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NextWordOrSeparatorTest() {
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *            poopie butts
     * @replaces strSet
     * @ensures strSet = entries(fart)
     */
    public static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";
        int index = 0;
        while (index < str.length()) {
            char element = str.charAt(index);
            if (!strSet.contains(element)) {
                strSet.add(element);
            }
            index++;
        }

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        int separatorIndex = position;
        int otherIndex = position;
        char beginning = text.charAt(position);
        boolean looper = true;
        String word = "";
        if (separators.contains(beginning)) {
            while (otherIndex < text.length()
                    && separators.contains(text.charAt(otherIndex))) {
                word += text.charAt(otherIndex);
                otherIndex++;
            }
        } else {
            while (looper && separatorIndex < text.length()) {
                if (separators.contains(text.charAt(separatorIndex))) {
                    looper = false;
                } else {
                    separatorIndex++;
                }
            }
            word = text.substring(position, separatorIndex);
        }
        return word;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static Map<String, Integer> TagCloudGenerator(SimpleReader in,
            SimpleWriter out) {
        /*
         * Define separator characters for test
         */
        final String separatorStr = " \t, !?;:";
        Set<Character> separatorSet = new Set1L<>();
        generateElements(separatorStr, separatorSet);

        /*
         * Create Map to store words
         */
        Map<String, Integer> tag = new Map1L<String, Integer>();

        /*
         * Process test case
         */
        int position = 0;
        String testStr = in.nextLine();
        while (position < testStr.length()) {
            String token = nextWordOrSeparator(testStr, position, separatorSet);
            if (separatorSet.contains(token.charAt(0))) {
                if (tag.hasKey(token)) {
                    Map.Pair<String, Integer> pair = tag.remove(token);
                    tag.add(pair.key(), pair.value() + 1);
                } else {
                    tag.add(token, 1);
                }
            }
            position += token.length();
        }
        return tag;
    }
    /*
     * Close input and output streams
     */
}
