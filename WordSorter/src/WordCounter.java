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
 * creates an html page with table of words from a document in lexiconographic
 * order, with the number of instances of the word in the file displayed as well
 *
 * @author Austin Bolomey.1
 *
 */
public final class WordCounter {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCounter() {
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }

    /**
     * combines all sentences in the sequence into one long string
     *
     * @param input:
     *            input sequence of strings
     *
     * @updates input
     * @requires input != empty
     * @returns a combined long string from input
     */
    public static String longstring(Sequence<String> input) {
        assert input != null : "Violation of: input is not null";

        //create return string
        String output = "";

        //concats all sentences in seq into one long string
        while (input.length() > 0) {
            output = output.concat(" " + input.remove(0) + " ");
        }
        return output;
    }

    /**
     * removes separator characters and puts individual words into a sequence
     *
     * @param inputseq:
     *            input sequence of strings
     *
     * @updates inputseq
     * @requires inputseq != empty
     * @returns a combined long string from input
     */
    public static void removeandsep(String inputstring,
            Sequence<String> inputseq) {
        assert inputseq != null : "Violation of: inputseq is not null";
        assert inputstring != null : "Violation of: inputstring is not null";

        String temp = inputstring;
        //removes all separator chars from the long string
        temp = temp.replaceAll("[?!.,-]", " ");

        //scans through the long string and separates the words into a sequence
        String word = "";
        for (int i = 0; i < temp.length() - 1; i++) {
            String letter = temp.substring(i, i + 1);
            //if not a space, add the letter to the blank string
            if (!letter.equals(" ")) {
                word += letter;
            } else {
                inputseq.add(inputseq.length(), word);

                word = "";
            }
        }
    }

    /**
     * sorts the words in seq into lexicographical order
     *
     * @param seq:
     *            sequence of individual words
     * @updates seq
     * @requires seq != empty
     * @ensures the strings in the seq will be arranged in lexicographical order
     */
    public static void sort(Sequence<String> seq) {
        assert seq != null : "Violation of: terms is not null";

        //declare comparator and make a temporary queue
        Comparator<String> comp = new StringLT();
        Queue<String> temp = new Queue1L<>();

        //add all values from seq into temp queue
        while (seq.length() > 0) {
            temp.enqueue(seq.remove(0));
        }
        //sort the queue and add the values back into the seq
        temp.sort(comp);
        while (temp.length() > 0) {
            seq.add(seq.length(), temp.dequeue());
        }

    }

    /**
     * sorts the words in seq into lexicographical order
     *
     * @param seq:
     *            sequence of individual words
     * @updates seq
     * @requires seq != empty
     * @ensures the strings in the seq will be arranged in lexicographical order
     */
    public static Sequence<String> removespaces(Sequence<String> seq) {
        assert seq != null : "Violation of: terms is not null";

        Sequence<String> temp = seq.newInstance();
        for (int i = 0; i < seq.length(); i++) {
            if (!seq.entry(i).equals("")) {
                temp.add(temp.length(), seq.entry(i));
            }
        }

        return temp;

    }

    /**
     * creates an html page.
     *
     * @param s:
     *            words in the Sequence
     * @param count:
     *            numbers of the words in the sequence
     * @param out:
     *            where to write the html file to
     * @param title:
     *            name of the file
     * @requires s!=null, count!=null
     * @ensures a functioning HTML page will be generated
     */
    public static void htmlfile(SimpleWriter out, Sequence<String> s,
            Sequence<Integer> count, String title) {
        assert out.isOpen() : "Violation of: out is not open";
        assert s != null : "Violation of: Sequence is not null";
        assert count != null : "Violation of: Sequence is not null";
        out.print("<html>\n" + "<head>\n" + "<title>Words Counted in " + title
                + " </title>\n" + "</head>\n" + "<body>\n"
                + "<h2>Words Counted in " + title + "</h2>\n" + "<hr />\n"
                + "<table border=\"1\">\n" + "<tr>\n" + "<th>Words</th>\n"
                + "<th>Counts</th>\n" + "</tr>");

        int length = s.length();
        for (int i = 0; i < length; i++) {

            //adds each value into table with number of instances

            out.print("<tr>\n" + "<td>" + s.entry(i) + "</td>\n" + "<td>"
                    + count.entry(i) + "</td>\n" + "</tr>");

        }
        out.print("</table>\n" + "</body>\n" + "</html>");

    }

    /**
     * creates a sequence of the number of instances of words in the sequence of
     * words and puts the individual words in a squnce
     *
     * @param s:
     *            sequence of strings
     * @requires s!=null
     * @return a sequence of number of instances of the words
     */
    public static Sequence<Integer> counter(Sequence<String> s,
            Sequence<String> individual) {

        assert s != null : "Violation of: Sequence is not null";
        Sequence<Integer> count = new Sequence1L<Integer>();

        int wordcount = 1;
        //loops through all words
        for (int i = 0; i < s.length() - 1; i++) {
            String temp = s.entry(i);
            //checks if the first and second words are the same, adds 1 to counter if same
            if (temp.equals(s.entry(i + 1))) {
                wordcount++;
            } else {
                //if next word is different, add the counter to seq and reset counter
                //also add the first word to the individual word sequence
                count.add(count.length(), wordcount);
                individual.add(individual.length(), s.entry(i));
                wordcount = 1;
                //edge case, if the last word is not the same as the second last word, add
                //it to the end of the sequence with a count of 1
                if (i == s.length() - 2) {
                    individual.add(individual.length(), s.entry(i + 1));
                    count.add(count.length(), 1);

                }
            }

        }
        return count;

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

        out.print("Please enter the name of the input data file: ");
        String inname = in.nextLine();
        SimpleReader filein = new SimpleReader1L("data/" + inname);

        out.print("Please input the name of the file to store the data: ");
        String filename = in.nextLine();
        SimpleWriter fileout = new SimpleWriter1L("doc/" + filename + ".html");

        //declare the data storage elements
        Sequence<String> sentences = new Sequence1L<String>();
        Sequence<String> words = sentences.newInstance();
        Sequence<String> words2 = sentences.newInstance();
        Sequence<String> individual = sentences.newInstance();
        Sequence<Integer> count = new Sequence1L<Integer>();

        //adds all the lines into a sequence
        while (!filein.atEOS()) {
            sentences.add(sentences.length(), filein.nextLine());
        }
        //combine the sentences into one long sentence
        String combinedstring = longstring(sentences);
        //remove punctuation and separate the words into a sequence
        removeandsep(combinedstring, words);
        //remove all the blank values from the sequence
        words2 = removespaces(words);
        sort(words2);

        count = counter(words2, individual);
        htmlfile(fileout, individual, count, filename);
        filein.close();
        //fileout.close();
        in.close();
        out.close();
    }
}
