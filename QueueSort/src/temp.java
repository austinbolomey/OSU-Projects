import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class temp {

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    public void flip() {

    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        // TODO Auto-generated method stub
        //        Stack<Integer> si = new Stack1L<>();
        //        Stack<Integer> temp = new Stack1L<>();
        //        for (int i = 0; i < 10; i++) {
        //            si.push(i);
        //        }
        //        int length = si.length();
        //        temp.transferFrom(si);
        //
        //        for (int j = 0; j < length; j++) {
        //            si.push(temp.pop());
        //        }
        //
        //        out.println(temp.toString());
        //        out.println(si.toString());

        Sequence<Integer> si = new Sequence1L<>();
        for (int i = 0; i < 10; i++) {
            si.add(i, i);
        }
        int length = si.length();
        for (int j = 0; j < length; j++) {
            int temp = si.remove(length - 1);
            si.add(j, temp);
        }

        out.println(si.toString());
        out.close();
    }

}
