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
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param error
     *            error percentage allowed for square root estimate
     *
     * @return estimate of square root, returns 0.0 if user inputs 0
     */

    private static double sqrt(double x, double error) {

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
        final double hundo = 100.0;
        //loops until user enters something other than y
        while (state) {
            out.print("Enter number to take the square root of: ");
            out.println("");
            double root = in.nextDouble();
            //if user inputs y, prompts for number and displays root using method
            //checks for positive, if negative then exits
            if (root >= 0) {

                out.print(
                        "Enter percent allowed for relative error (0.0%-100%): ");
                out.println("");
                double error = in.nextDouble();

                out.println("Square root: " + sqrt(root, (error / hundo)));
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
