import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author Cory Bianco
 */
public final class TagCloud {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private TagCloud() {
        // no code needed here
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *
     * @replaces strSet
     * @ensures strSet = entries()
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
    public static Map<String, Integer> TagCloudGenerator(SimpleReader in) {
        /*
         * Define separator characters for test
         */
        final String separatorStr = "\\t\\n\\r,-.!?[]';:/()";
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

        while (!in.atEOS()) {
            String testStr = in.nextLine();
            position = 0;
            while (position < testStr.length()) {
                String token = nextWordOrSeparator(testStr, position,
                        separatorSet);
                if (!separatorSet.contains(token.charAt(0))) {
                    if (tag.hasKey(token)) {
                        Map.Pair<String, Integer> pair = tag.remove(token);
                        tag.add(pair.key(), pair.value() + 1);
                    } else {
                        tag.add(token, 1);
                    }
                }
                position += token.length();
            }
        }
        return tag;
    }

    private static void printHeader(SimpleWriter out, Map<String, Integer> tags,
            String fileName, int words, Sequence<String> ordered) {

        out.println("<html><head><title>Glossary</title>");
        out.println("</head><body>");
        out.println("<h1>");
        out.println(fileName);
        out.println("</h1>");
        out.println("<p>");
        for (int i = 0; i < words; i++) {

            Map.Pair<String, Integer> pair = tags.remove(ordered.entry(i));
            out.println("<font size = \"" + (1 + pair.value()) + "\"> "
                    + pair.key() + "</font>     ");
        }
        out.println("</p>");
        out.println("</body></html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter output = new SimpleWriter1L("tagCloud.html");

        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.print("Enter the file name: ");
        String fileName = in.nextLine();
        out.print("Number of words in cloud: ");
        String words = in.nextLine();
        int cloudSize = Integer.parseInt(words);
        Map<String, Integer> map = new Map1L<>();
        SimpleReader input = new SimpleReader1L(fileName);
        map = TagCloudGenerator(input);
        Sequence<String> orderedMap = new Sequence1L<String>();
        printHeader(output, map, fileName, cloudSize, orderedMap);

    }

}
