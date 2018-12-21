import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIO {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        String bellow = "whitest";
        String sub = bellow.substring(0, 5);
        String end = bellow.substring(5);
        out.println(sub);
        out.println(end);

        String finalstring;
        String str1 = "   asdf  asdf  a.";
        String str2 = "___asdf__asdf__a.";
        int overlap = 5;
        String pre1 = str1.substring(0, overlap);
        String pre2 = str2.substring(0, overlap);
        String end1 = str1.substring(str1.length() - overlap);
        String end2 = str2.substring(str2.length() - overlap);
        //if beginning of str2 matches end of str1
        //ex bellow --> lower
        //        if (pre2.equals(end1)) {
        //            finalstring = (str1).concat(str2.substring(overlap));
        //
        //        }
        //        //if beginning of str1 matches end of str2
        //        //ex whitest --> bigwhite
        //        else {
        //            out.println("here");
        //            finalstring = (str2).concat(str1.substring(overlap));
        //        }
        //        out.println(finalstring);
        //
        //        String temp1 = "Bucks -- Beat";
        //        String temp2 = "Bucks";
        //        out.println(temp1.indexOf(temp2));
        out.println(str1.substring(3, 4));
        out.close();

    }

}
