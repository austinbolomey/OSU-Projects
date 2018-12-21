import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Cycles through combinations of de Jaeger formula until as close as
     * possible, then output closest approximation value
     *
     * @param u
     *            constant for approximation
     * @param w
     *            personal number 1
     * @param x
     *            personal number 2
     * @param y
     *            personal number 3
     * @param z
     *            personal number 4
     * @return the double closest to the value of the approximation constant
     */

    private static double guesser(double u, double w, double x, double y,
            double z) {
        SimpleWriter out = new SimpleWriter1L();
        final int threei = 3;
        final double three = 3.0;
        final double four = 4.0;
        final double five = 5.0;
        final int hundo = 100;
        double[] user = { w, x, y, z };
        //array of exponents
        double[] constant = { (-1.0 * five), -1 * four, -1 * three, -1 * 2.0,
                -1, (-1.0 / 2.0), (-1.0 / three), (-1.0 / four), 0, (1 / four),
                (1.0 / three), (1.0 / 2.0), 1, 2, three, four, five };
        double[] wvals = new double[constant.length];
        double[] xvals = new double[constant.length];
        double[] yvals = new double[constant.length];
        double[] zvals = new double[constant.length];

        double approximate = 0.0, finalval = 0, finalerror = 0;

        //makes 4 arrays for the powers at each exponent for the four user inputs
        int i = 0;
        while (i < constant.length) {
            wvals[i] = Math.pow(user[0], constant[i]);

            i++;
        }
        i = 0;
        while (i < constant.length) {
            xvals[i] = Math.pow(user[1], constant[i]);
            i++;
        }
        i = 0;
        while (i < constant.length) {
            yvals[i] = Math.pow(user[2], constant[i]);
            i++;
        }
        i = 0;
        while (i < constant.length) {
            zvals[i] = Math.pow(user[threei], constant[i]);

            i++;
        }

        int q = 0, g = 0, e = 0, r = 0;
        int outq = 0, outw = 0, oute = 0, outr = 0;
        double temp2 = 2;
        /*
         * loops for each product in the 4 arrays and stores the smallest error
         * with index values
         */ for (q = 0; q < constant.length; q++) {
            for (g = 0; g < constant.length; g++) {
                for (e = 0; e < constant.length; e++) {
                    for (r = 0; r < constant.length; r++) {

                        approximate = wvals[q] * xvals[g] * yvals[e] * zvals[r];

                        double temp1 = (approximate - u) / u;
                        temp1 = Math.abs(temp1);

                        if (temp1 < temp2) {
                            outq = q;
                            outw = g;
                            oute = e;
                            outr = r;

                            finalval = approximate;
                            finalerror = temp1;
                            temp2 = temp1;
                        }

                    }

                }

            }

        }
        out.print("Relative error: ");
        out.print(finalerror * hundo, 2, false);
        out.println("%");

        out.println("Exponent a: " + constant[outq]);
        out.println("Exponent b: " + constant[outw]);
        out.println("Exponent c: " + constant[oute]);
        out.println("Exponent d: " + constant[outr]);

        out.close();
        return finalval;

    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        boolean state = true;
        double input = 0;
        String tempstring;
        while (state) {
            out.print("Please enter a positive double:  ");
            tempstring = in.nextLine();
            if (FormatChecker.canParseDouble(tempstring)) {
                if (Double.parseDouble(tempstring) > 0) {
                    input = Double.parseDouble(tempstring);
                    state = false;
                }
            }

        }

        return input;

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        boolean state = true;
        double input = 0;
        String tempstring;
        while (state) {
            out.print("Please enter a positive double not one:  ");
            tempstring = in.nextLine();
            if (FormatChecker.canParseDouble(tempstring)) {
                if (Double.parseDouble(tempstring) > 1) {
                    input = Double.parseDouble(tempstring);
                    state = false;
                }
            }

        }

        return input;
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
        //prompts user for values
        double u = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        //        double u = 238900.0;
        //        double w = 14.0;
        //        double x = 102329.0;
        //        double y = 1936.0;
        //        double z = 13.0;
        out.println("Closest approximation: " + guesser(u, w, x, y, z));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
