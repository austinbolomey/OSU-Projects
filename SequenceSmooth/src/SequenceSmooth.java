import components.sequence.Sequence;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1.length() >= 1 : "|s1| >= 1";

        s2.clear();
        if (s1.length() == 1 || s1.length() == 0) {
            s2 = s1;
        } else {
            for (int i = 0; i < s1.length() - 1; i++) {

                int first = s1.remove(i);
                int second = s1.remove(i);

                s2.add(i, (first + second) / 2);

                s1.add(i, first);
                s1.add(i + 1, second);
            }
        }
        //        if (s1.length() > 1) {
        //
        //            int first = s1.remove(0);
        //            int second = s1.remove(0);
        //
        //            int avg = (first + second) / 2;
        //
        //            s1.add(0, second);
        //
        //            if (s1.length() > 0) {
        //                smooth(s1, s2);
        //            }
        //
        //            s1.add(0, first);
        //            s2.add(0, avg);
        //
        //        }

    }

}
