import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {

        assert exp != null : "Violation of: exp is not null";

        NaturalNumber answer;
        //checks if tag, and has attribute of value
        //if value exists, sets answer to the first number value
        if (exp.label().equals("number")) {
            answer = new NaturalNumber2(
                    Integer.parseInt(exp.attributeValue("value")));
        } else {
            //this section deals with the expression tags
            //sets the answer to lowest height first child
            answer = evaluate(exp.child(0));
            //adds the second number if plus
            if (exp.label().equals("plus")) {
                answer.add(evaluate(exp.child(1)));
                //multiply second num if times
            } else if (exp.label().equals("times")) {
                answer.multiply(evaluate(exp.child(1)));
                //subtract second num if minus
            } else if (exp.label().equals("minus")) {
                answer.subtract(evaluate(exp.child(1)));
                //divide second num if divide
            } else if (exp.label().equals("divide")) {
                NaturalNumber divider = evaluate(exp.child(1));
                //error trap if the divisor is zero
                if (divider.isZero()) {
                    Reporter.fatalErrorToConsole(
                            "violation of: division by 0 error");
                }
                //otherwise, divide the result by the second number
                answer.divide(evaluate(exp.child(1)));
            } else {
                //error trap if an operator that is not supported is used
                Reporter.fatalErrorToConsole(
                        "At least one function used in the expression is not supported by the program");
            }

        }

        return answer;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
