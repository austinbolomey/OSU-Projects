import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class MonteCarlo {

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        double pos = Math.sqrt(1 - Math.pow(xCoord - 1, 2)) + 1;
        double neg = (-1) * (Math.sqrt(1 - Math.pow(xCoord - 1, 2))) + 1;

        return (yCoord >= neg && yCoord <= pos);
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        SimpleWriter out = new SimpleWriter1L();
        Random rnd = new Random1L();

        int counter = 0;
        int temp = 0;
        while (temp < n) {
            double x = rnd.nextDouble() * 2.0;
            double y = rnd.nextDouble() * 2.0;
            if (pointIsInCircle(x, y)) {
                counter++;
            }
            temp++;
        }
        return counter;
    }

    public static void main(String[] args) {

        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        output.println("How many points");
        int n = input.nextInteger();

        int temp = numberOfPointsInCircle(n);

        double percent = temp / (double) n;
        double pie = percent * 4.0;
        output.println(pie);
    }
}
