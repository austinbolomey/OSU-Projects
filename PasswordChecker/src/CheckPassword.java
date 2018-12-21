import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Checks whether the given String satisfies the CSE department criteria for
     *   a valid password. Prints an appropriate message to the given output
     * stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream  
     */
    private static void checkPassword(String s, SimpleWriter out) {
        int specialcounter = 0;
        String special = "~!@#$%^&*()_+=-`|}{[]\":<>?';,./";
        for (int i = 0; i < special.length(); i++) {
            Integer.parseInt(special);
        }
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        //checkPassword();

        String n = "Hi!";
        out.println(n.contains("!"));
        String integer1 = "112131";
        out.println(Integer.parseInt(integer1));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
