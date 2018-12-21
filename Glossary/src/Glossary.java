import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program gets a list of terms and definitions, and creates html pages linking
 * the terms and a master index page.
 *
 *
 * @author Austin Bolomey.1
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
        //no code needed
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
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
        if (q.length() > 0) {
            smallest = q.front();

            for (int i = 0; i < length; i++) {
                String temp1 = q.dequeue();

                if (order.compare(temp1, smallest) < 0) {
                    smallest = temp1;
                }

                q.enqueue(temp1);

            }

            //removes smallest
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
     * Creates the html page for each word and definition.
     *
     * @param out
     *            simplewriter for output
     * @param word
     *            glossary word
     * @param definitions
     *            word key and matching definitions
     * @param allwords
     *            set of all words
     * @requires out is open, word is not null
     *
     */
    public static void createPage(SimpleWriter out, String word,
            Map<String, String> definitions, Set<String> allwords) {
        assert out.isOpen() : "Violation of: out is not open";
        assert word != null : "Violation of: word is not null";
        //this is just a random comment
        out.print("<html>\n" + "<head>\n" + "<title>" + word + "</title>\n"
                + "</head>\n" + "<body>\n" + "<h2><b><i><font color=\"red\">"
                + word + "</font></i></b></h2>");
        String definition = definitions.value(word);
        int checkwords = allwords.size();

        String[] tempa = new String[checkwords];
        //looks through all words to find if hyperlink needs to be created
        for (int i = 0; i < checkwords; i++) {
            String temp = allwords.removeAny();
            tempa[i] = temp;

            /*
             * search through all words in definition, if it contains a glossary
             * word replace the word in the definition with a hyperlink
             */
            for (String search : definition.split("\\W+")) {

                if (search.equals(temp)) {
                    definition = definition.replaceFirst(temp,
                            "<a href=\"" + temp + ".html\">" + temp + "</a>");
                }
            }

        }
        for (int j = 0; j < checkwords; j++) {
            allwords.add(tempa[j]);
        }

        out.print("<blockquote>" + definition + "</blockquote>\n" + "<hr />\n"
                + "<p>Return to <a href=\"index.html\">index</a>.</p>\n"
                + "</body>\n" + "</html>");

    }

    /**
     * Creates the index html page for each word and definition.
     *
     * @param out
     *            simplewriter for output
     * @param q
     *            queue of alphabetical words
     *
     * @requires out is open, q is lexiconographically sorted
     *
     */
    public static void indexPage(SimpleWriter out, Queue<String> q) {
        assert out.isOpen() : "Violation of: out is not open";
        assert q != null : "Violation of: word is not null";
        out.print("<html>\n" + "<head>\n" + "<title>Glossary</title>\n"
                + "</head>\n" + "<body>\n" + "<h2>Glossary</h2>\n" + "<hr />\n"
                + "<h3>Index</h3>\n" + "<ul>");
        int length = q.length();

        for (int i = 0; i < length; i++) {
            String name = q.dequeue();
            out.println(
                    "<li><a href=\"" + name + ".html\">" + name + "</a></li>");
        }
        out.print("</ul>\n" + "</body>\n" + "</html>");
    }

    /**
     * Checks if string is one word.
     *
     * @param s
     *            input string
     *
     * @requires s is not null
     * @return true if string is just one word, false if not
     */
    public static boolean isword(String s) {
        assert s != null : "Violation of: s is not null";
        return (s.length() > 0 && s.split("\\s+").length == 1);
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

        out.println("Name of input file?");
        String inputfile = in.nextLine();
        SimpleReader inFile = null;
        //reads in file
        inFile = new SimpleReader1L(inputfile);

        //destination folder
        out.println("Name of destination folder?");
        String inputfolder = in.nextLine();

        //instantiate map and set for words/definitions
        Map<String, String> worddefs = new Map1L<String, String>();
        Set<String> allwords = new Set1L<String>();
        Queue<String> allwordsq = new Queue1L<String>();

        //value for map
        String value = "";
        //queue of words
        Queue<String> w = new Queue1L<String>();
        //queue of definitions
        Queue<String> d = new Queue1L<String>();

        //loops through file until end
        while (!inFile.atEOS()) {
            String temp = inFile.nextLine();
            //if the line is a single word
            if (isword(temp)) {
                //enqueues word to queue w
                w.enqueue(temp);
                //adds the words to set and queue for sorting and searching
                allwords.add(temp);
                allwordsq.enqueue(temp);

            } else if (temp.equals("")) {
                /*
                 * if the line is a blank space, add the concated string to
                 * definition queue
                 */
                d.enqueue(value);
                value = "";
            } else {
                //deals with definitions on multiple lines
                value = value.concat(" " + temp);
            }

        }
        //adds words and defs to the map, with each word as a key and definition as value
        int numwords = w.length();
        for (int z = 0; z < numwords; z++) {
            worddefs.add(w.dequeue(), d.dequeue());
        }

        //create a new comparator for alphabetizing
        Comparator<String> cs = new StringLT();

        //sort the queue of words
        sort(allwordsq, cs);

        //duplicates the queue to avoid destroying original when making index
        Queue<String> copyq = allwordsq.newInstance();
        int copynum = allwordsq.length();
        for (int j = 0; j < copynum; j++) {
            String temps = allwordsq.dequeue();
            copyq.enqueue(temps);
            allwordsq.enqueue(temps);
        }

        //creates an index page
        String index = "./" + inputfolder + "/index.html";
        SimpleWriter indexfile = new SimpleWriter1L(index);
        indexPage(indexfile, allwordsq);

        //loops through all words and creates html pages for each word
        int numberwords = copyq.length();

        for (int i = 0; i < numberwords; i++) {
            String word = copyq.dequeue();
            String page = "./" + inputfolder + "/" + word + ".html";

            SimpleWriter outpage = new SimpleWriter1L(page);
            createPage(outpage, word, worddefs, allwords);
        }

        //close all streams
        in.close();
        out.close();
        inFile.close();
    }

}
