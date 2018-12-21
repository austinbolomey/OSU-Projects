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
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */

    private static double sqrt(double x) {

        final double error = 0.00001;
        boolean state = true;
        double r = x;
        //loops until the square root satisfies the error percentage
        if (x == 0) {
            return 0.0;
        }
        while (state) {
            //checks if the approximation is within the accepted error
            if (Math.abs((r * r) - x) / x >= (error * error)) {
                //updates estimation with averaged estimation
                r = (r + (x / r)) / 2;
            } else {
                //if error threshold is passed, exit loop
                state = false;

            }

        }
        return r;
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
        boolean state = true;
        //loops until user enters something other than y
        while (state) {
            out.println("Would you like to compute a square root?");
            out.print("Input y for yes, any other key for no : ");
            String input = in.nextLine();
            //if user inputs y, prompts for number and displays root using method
            if (input.equals("y")) {
                out.println("");
                out.print("Enter number to take the square root of: ");
                out.println("");
                double root = in.nextDouble();
                //this should look like im actually programming
                //dccumentation is an easy way to look lieke im actually programming

                out.println("Square root: " + sqrt(root));
                out.println("");
            } else {
                //if user exits, says bye and terminates.
                out.println("Bye, have a nice day!");
                state = false;
            }
        }

        /*
         * Close input and output streams
         */

        in.close();
        out.close();
    }

}
