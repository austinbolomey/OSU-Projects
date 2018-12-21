import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort1() {
        super();
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * (q * <removeMin>) is permutation of #q  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";
        SimpleWriter out = new SimpleWriter1L();
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

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";
        int length = this.length();
        Queue<String> temp = new Queue1LSort1();
        String min;
        for (int i = 0; i < length; i++) {
            min = removeMin(this, order);
            temp.enqueue(min);
        }
        for (int i = 0; i < length; i++) {
            min = temp.dequeue();
            this.enqueue(min);

        }

    }

}
