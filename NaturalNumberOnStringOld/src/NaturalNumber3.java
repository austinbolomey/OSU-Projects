import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * {@code NaturalNumber} represented as a {@code String} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 * </pre>
 * @correspondence <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 */
public class NaturalNumber3 extends NaturalNumberSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.rep = "";

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumber3() {

        this.createNewRep();

    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber3(int i) {
        assert i >= 0 : "Violation of: i >= 0";

        this.rep = Integer.toString(i);

    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber3(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";

        this.rep = s;

    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber3(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";

        this.rep = n.toString();

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber3 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber3 localSource = (NaturalNumber3) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    /**
     * multiplyBy10 overrided message
     *
     *
     * @param k
     *            number to be added to this
     * @ensures this = 10 * #this + k
     */
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < 10 : "Violation of: k < 10";

        if (this.rep.equals("0")) {
            this.rep = Integer.toString(k);
        } else {
            String digit = Integer.toString(k);
            this.rep = this.rep.concat(digit);
        }
    }

    @Override
    /**
     * overrided divideBy10 method
     * 
     * @returns the remainder
     * @updates this
     * @ensures #this = 10 * this + divideBy10 and 0 <= divideBy10 < 10
     */
    public final int divideBy10() {

        int remainder = 0;

        if (this.rep.length() == 1) {
            remainder = Integer.parseInt(this.rep.substring(0, 1));
            this.clear();
        } else {

            String lastdigit = this.rep.substring(this.rep.length() - 1,
                    this.rep.length());

            remainder = Integer.parseInt(lastdigit);

            this.rep = this.rep.substring(0, this.rep.length() - 1);

        }
        return remainder;

    }

    @Override
    /**
     * overrided isZero method
     * 
     * @return true if this = zero
     * @ensures isZero = (this = 0)
     */
    public final boolean isZero() {
        boolean zero = false;
        if (this.rep.equals("") || this.rep.equals("0")) {
            zero = true;
        }
        return zero;
    }

}
