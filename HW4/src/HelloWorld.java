import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber temp = new NaturalNumber2();
        temp.copyFrom(n1);
        n1.copyFrom(n2);
        n2.copyFrom(temp);
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void tswapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber temp = new NaturalNumber2();
        temp.transferFrom(n1);
        n1.transferFrom(n2);
        n2.transferFrom(temp);
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {
        NaturalNumber temp = new NaturalNumber2();
        temp.copyFrom(n);
        n.multiply(temp);
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static int root(int n, int r) {
        int low = 0, high = n + 1;
        int g = 0;
        while ((high - low) > 1) {
            g = (high + low) / 2;
            if (Math.pow(g, r) > n) {
                high = g;
            } else {
                low = g;
            }

        }
        return low;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits(NaturalNumber n) {
        NaturalNumber sum = new NaturalNumber2();
        int remainder = 0;

        if (!n.isZero()) {
            remainder = n.divideBy10();
            NaturalNumber temp = new NaturalNumber2(remainder);
            sum.add(temp);
            sum.add(sumOfDigits(n));
        }
        n.multiplyBy10(remainder);
        return sum;

    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        int count = 0;
        int temp = 0;
        if (!n.isZero()) {
            n.divideBy10();
            count++;
            count += (numberOfDigits(n));
        }
        n.multiplyBy10(temp);
        return count;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        int z = n.divideBy10();
        int y = n.divideBy10();
        if (n.isZero()) {
            // Base case.
            int result = (y * 10 + z) / 2;
            n.multiplyBy10(result / 10);
            n.multiplyBy10(result % 10);
        } else if (y % 2 == 0) {
            n.multiplyBy10(y);
            divideBy2(n);
            n.multiplyBy10(z / 2);
        } else {
            n.multiplyBy10(y - 1);
            divideBy2(n);
            n.multiplyBy10((10 + z) / 2);
        }
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    private static boolean isPalindrome(String s) {

        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {

            return isPalindrome(s.substring(1, s.length() - 1));
        }

        return false;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    //    private static int sumOfDigits(NaturalNumber n) {
    //        int sum = 0;
    //        int remainder = 0;
    //        if (!n.isZero()) {
    //            remainder = n.divideBy10();
    //            sum = sum + remainder + sumOfDigits(n);
    //        }
    //        n.multiplyBy10(remainder);
    //        return sum;
    //    }

    public static int average(int j, int k) {

        int avg = 0;
        boolean diffOfOne = false;
        if (Math.abs(j - k) == 1) {
            diffOfOne = true;
        }

        //if the two integers differ by magnitude of 1
        if (diffOfOne) {
            if (j <= 0 && k <= 0) {
                if (j < k) {
                    avg = k;

                } else {
                    avg = j;
                }
            } else {
                if (j < k) {
                    avg = j;

                } else {
                    avg = k;
                }
            }
        }
        //if both are equal
        else if (k == j) {
            avg = j;
        }
        //otherwise average like usual
        else {
            avg = (j + k) / 2;
        }
        return avg;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        NaturalNumber one = new NaturalNumber2(124232);
        //        out.println(sumOfDigits(one));
        //        out.println(numberOfDigits(one));
        //        divideBy2(one);
        //        out.println(one);
        //        out.println(124232 / 2);
        //
        //        String t = "hel";
        //        out.println(isPalindrome(t));
        //        out.println(average(Integer.MIN_VALUE, Integer.MIN_VALUE));
        //        out.println(average(3, 5));
        //        out.println(Integer.MIN_VALUE);
        out.println(-5 % 3);
        out.close();

    }

}
