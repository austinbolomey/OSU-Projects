import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * creates an html page with table of words in a text file and number of
 * instances of each word
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
     * returns the first alphabetical string in queue q.
     *
     * @param q
     *            queue of strings
     * @param order
     *            comparator for string comparison
     * @updates q
     * @requires q is not null, order is not null
     * @ensures q=q#
     * @return alphabetically smallest item in queue q
     */
    public static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        //finds smallest object
        int length = q.length();
        String smallest = "";
        //loops to find smallest value
        if (length > 0) {
            smallest = q.front();

            for (int i = 0; i < length; i++) {
                String temp = q.dequeue();

                if (order.compare(temp, smallest) <= 0) {
                    smallest = temp;
                }

                q.enqueue(temp);

            }

            //removes smallest from queue
            for (int j = 0; j < length; j++) {
                String temp1 = q.dequeue();
                if (order.compare(temp1, smallest) != 0) {
                    q.enqueue(temp1);

                }
            }
        }
        return smallest;

    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        int length = q.length();
        Queue<String> temp = new Queue1L<String>();
        String min;
        //adds the smallest value into the queue
        for (int i = 0; i < length; i++) {
            min = removeMin(q, order);
            temp.enqueue(min);
        }
        for (int i = 0; i < length; i++) {
            min = temp.dequeue();
            q.enqueue(min);

        }

    }

    /**
     * separates string into individual words and adds them to a queue
     *
     * @return
     */
    public static Map<String, Integer> counter(Queue<String> q) {
        SimpleWriter out = new SimpleWriter1L();
        Map<String, Integer> mapcounter = new Map1L<String, Integer>();

        int length = q.length();

        for (int i = 0; i < length; i++) {
            String temp = q.dequeue();
            //add to map if word is not present
            if (!mapcounter.hasKey(temp)) {
                mapcounter.add(temp, 1);
            } else if (mapcounter.hasKey(temp)) {
                //if the map already contains word, add one to the value number
                int number = mapcounter.value(temp);
                number++;
                mapcounter.remove(temp);
                mapcounter.add(temp, number);

            }
            q.enqueue(temp);
        }
        out.close();
        return mapcounter;
    }

    /**
     * separates string into individual words and adds them to a queue
     *
     * @param s
     *            input string
     * @return queue of words
     */
    public static Queue<String> separator(String s) {

        String temp = "";

        Queue<String> words = new Queue1L<String>();
        for (int i = 0; i < s.length(); i++) {
            if (!s.substring(i, i + 1).equals(" ")
                    && !s.substring(i, i + 1).equals(".")
                    && !s.substring(i, i + 1).equals(",")
                    && !s.substring(i, i + 1).equals("?")
                    && !s.substring(i, i + 1).equals("-")
                    && !s.substring(i, i + 1).equals("")) {
                //if substring is a letter, concat to a temp string
                temp = temp.concat(s.substring(i, i + 1));

            } else {
                //enqueue temp string when the word ends
                if (temp.length() > 0) {
                    words.enqueue(temp);
                }
                temp = "";
            }
        }
        //adds the last word
        if (temp.length() > 0) {
            words.enqueue(temp);
        }

        return words;

    }

    /**
     * generate html page
     *
     *
     */
    public static void htmlfile(SimpleWriter out, Queue<String> q,
            Map<String, Integer> m, String title) {
        assert out.isOpen() : "Violation of: out is not open";
        assert q != null : "Violation of: queue is not null";
        assert m != null : "Violation of: map is not null";
        out.print("<html>\n" + "<head>\n" + "<title>Words Counted in " + title
                + " </title>\n" + "</head>\n" + "<body>\n"
                + "<h2>Words Counted in " + title + "</h2>\n" + "<hr />\n"
                + "<table border=\"1\">\n" + "<tr>\n" + "<th>Words</th>\n"
                + "<th>Counts</th>\n" + "</tr>");

        int length = q.length();
        for (int i = 0; i < length; i++) {
            String temp = q.dequeue();
            //adds each value into table with number of instances
            if (m.hasKey(temp) && !temp.equals("")) {
                out.print("<tr>\n" + "<td>" + temp + "</td>\n" + "<td>"
                        + m.value(temp) + "</td>\n" + "</tr>");
            }
            temp = null;
        }
        out.print("</table>\n" + "</body>\n" + "</html>");
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

        Map<String, Integer> map = new Map1L<String, Integer>();
        Queue<String> allwords = new Queue1L<String>();
        out.println("Name of input file?");
        String inputfile = in.nextLine();

        SimpleReader intext = new SimpleReader1L(inputfile);
        while (!intext.atEOS()) {
            allwords.append(separator(intext.nextLine()));
        }
        //create a new comparator for alphabetizing
        Comparator<String> cs = new StringLT();
        Queue<String> copy = new Queue1L<String>();
        int length = allwords.length();

        //make a copy of the queue
        for (int i = 0; i < length; i++) {
            String temp = allwords.dequeue();
            copy.enqueue(temp);
            allwords.enqueue(temp);

        }
        out.println("\n");
        map = counter(copy);

        //sort the queue of words
        sort(allwords, cs);

        out.println("Output file name?");
        String outputfile = in.nextLine();

        SimpleWriter table = new SimpleWriter1L(outputfile);
        htmlfile(table, allwords, map, "Words Counted in " + inputfile);

        /*
         * Close input and output streams
         */
        in.close();
        intext.close();
        out.close();
    }

}
