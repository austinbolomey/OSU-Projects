import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @Jake Alvord
 *
 */
public final class WordCounter2 {

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {

            String s1 = o1.toLowerCase();
            String s2 = o2.toLowerCase();

            return s1.compareTo(s2);
        }
    }

    /**
     * Reads the file and generates a Sequence of Strings containing the various
     * sentences.
     *
     * @param file
     *            the input file from user
     * @param sentences
     *            the sentences from the file
     * @updates sentences
     * @requires <pre>
     * file != empty
     * </pre>
     * @ensures <pre>
     * sentences contains a list of sentences from the file
     * </pre>
     */
    public static void reader(SimpleReader file, Sequence<String> sentences) {
        assert file != null : "Violation of: file is not null";
        assert sentences != null : "Violation of: sentences is not null";

        while (!file.atEOS()) {
            sentences.add(sentences.length(), file.nextLine());
        }

    }

    /**
     * Compresses the sequence of Strings into one long String
     *
     * @param sentences
     *            the sentences from the file
     * @updates sentences
     * @returns the compressed String
     * @requires <pre>
     * sentences != empty
     * </pre>
     * @ensures <pre>
     * a compressed String from the Sequence of String will be returned
     * </pre>
     */
    public static String compressor(Sequence<String> sentences) {
        assert sentences != null : "Violation of: sentences is not null";

        String sent = sentences.remove(0);

        while (sentences.length() > 0) {
            sent += " " + sentences.remove(0) + " ";
        }

        return sent;
    }

    /**
     * Takes apart the individual words from a String.
     *
     * @param sent
     *            the String to be taken apart
     * @param terms
     *            the Sequence of individual words from the String
     * @updates terms
     * @requires <pre>
     * sent != empty
     * </pre>
     * @ensures <pre>
     * individual words with no white space will be assembeled in the Sequence
     * </pre>
     */
    public static void deconstructor(String sent, Sequence<String> terms) {
        assert sent != null : "Violation of: sent is not null";
        assert terms != null : "Violation of: terms is not null";

        sent = sent.replaceAll("[?!.,-]", " ");

        String word = "";

        for (int i = 0; i < sent.length() - 1; i++) {
            String letter = sent.substring(i, i + 1);
            if (!letter.equals(" ")) {
                word += letter;
            } else {
                terms.add(terms.length(), word);
                word = "";
            }
        }

        word = " ";

        Sequence<Integer> blank = new Sequence1L<>();

        for (int j = 0; j < terms.length(); j++) {
            if (terms.entry(j).equals(word) || terms.entry(j).isEmpty()) {
                blank.add(0, j);
            }
        }

        int length = blank.length();

        for (int k = 0; k < length; k++) {
            terms.remove(blank.entry(k));
        }

    }

    /**
     * Arranges the Strings from the Sequence in lexicographical order.
     *
     * @param terms
     *            sequence of individual words
     * @updates terms
     * @requires <pre>
     * terms != empty
     * </pre>
     * @ensures <pre>
     * the Strings in the Sequence will be arranged in lexicographical order
     * </pre>
     */
    public static void order(Sequence<String> terms) {
        assert terms != null : "Violation of: terms is not null";

        Comparator<String> comp = new StringLT();

        Queue<String> temp = new Queue1L<>();

        while (terms.length() > 0) {
            temp.enqueue(terms.remove(0));
        }

        temp.sort(comp);

        while (temp.length() > 0) {
            terms.add(terms.length(), temp.dequeue());
        }

    }

    /**
     * Counts the similar string in the Sequence and eliminates Strings that are
     * redundant.
     *
     * @param terms
     *            Strings in the Sequence
     * @param counter
     *            number of individual and reoccurring Strings in terms
     * @updates terms, counter
     * @requires <pre>
     * terms != empty
     * </pre>
     * @ensures <pre>
     * redundant words in terms will be erased, and the number of Strings will
     * be counted
     * </pre>
     */
    public static void counter(Sequence<String> terms,
            Sequence<Integer> counter) {
        assert terms != null : "Violation of: terms is not null";
        assert counter != null : "Violation of: counter is not null";

        for (int i = 0; i < terms.length(); i++) {
            counter.add(0, 1);
        }

        for (int j = 0; j < terms.length() - 1; j++) {
            for (int k = j + 1; k < terms.length() - 2; k++) {
                if (terms.entry(j).equals(terms.entry(k))) {
                    terms.remove(k);
                    k--;
                    int first = counter.remove(j);
                    int second = counter.remove(j + 1);

                    counter.add(j, first + second);
                }
            }
        }

    }

    /**
     * Generates an HTML page.
     *
     * @param terms
     *            Strings in the Sequence
     * @param counter
     *            numbers of the Strings on the Sequence
     * @param file
     *            the file location to output to
     * @param name
     *            the name of the original file
     * @requires <pre>
     * terms != empty
     * </pre>
     * @ensures <pre>
     * a functioning HTML page will be generated
     * </pre>
     */
    public static void generator(Sequence<String> terms,
            Sequence<Integer> counter, SimpleWriter file, String name) {
        assert terms != null : "Violation of: terms is not null";
        assert counter != null : "Violation of: counter is not null";
        assert file != null : "Violation of: file is not null";
        assert name != null : "Violation of: fileName is not null";

        file.println("<html>");
        file.println("<head>");
        file.println("<title>Words Counted in " + name + ".txt </title>");
        file.println("</head>");
        file.println("<body>");
        file.println("<h2>Words Counted in " + name + ".txt </h2>");
        file.println("<hr>");
        file.println("<table border=\"1\">");
        file.println("<tbody>");
        file.println("<tr>");
        file.print("<th>");
        file.print("Words");
        file.println("</th>");
        file.print("<th>");
        file.print("Counts");
        file.println("</th>");
        file.println("</tr>");
        // prints HTML code

        for (int i = 0; i < terms.length(); i++) {

            file.println("<tr>");
            file.print("<td>");
            file.print(terms.entry(i));
            file.println("</td>");
            file.print("<td>");
            file.print(counter.entry(i));
            file.println("</td>");
            file.println("</tr>");

        }

        file.println("</tbody>");
        file.println("</table>");
        file.println("</body>");
        file.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //        out.print(
        //                "Please input the name of the text file in data to aggregate from: ");
        //        String name = in.nextLine();
        //
        //        SimpleReader fin = new SimpleReader1L("data/" + name);
        SimpleReader fin = new SimpleReader1L(
                "http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/count-words/data/gettysburg.txt");
        out.println();

        out.print("Please input the name of the file in doc to store to: ");
        String fname = in.nextLine();

        SimpleWriter fout = new SimpleWriter1L("doc/" + fname + ".html");

        Sequence<String> sentences = new Sequence1L<>();
        Sequence<String> terms = new Sequence1L<>();
        Sequence<Integer> counter = new Sequence1L<>();

        reader(fin, sentences);

        deconstructor(compressor(sentences), terms);

        order(terms);

        counter(terms, counter);

        generator(terms, counter, fout, "name");

        fin.close();
        fout.close();
        in.close();
        out.close();
    }
}
