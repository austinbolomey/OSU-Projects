import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author bolomey.1 fraser.116
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        boolean found = false;
        if (t.height() > 0) {
            T root = t.disassemble(left, right);
            int comp = x.compareTo(root);
            if (comp > 0) {
                found = isInTree(right, x);
            } else if (comp < 0) {
                found = isInTree(left, x);
            } else {
                found = true;
            }
            t.assemble(root, left, right);
        }
        return found;

    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        if (t.height() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);
            //if x is smaller than root and the left tree has at least 1 element
            if (x.compareTo(root) < 0 && left.height() > 0) {
                insertInTree(left, x);
            }
            //if x is smaller than root and the left tree has no height, reassemble the left tree

            else if (x.compareTo(root) < 0 && left.height() == 0) {
                BinaryTree<T> newleft = t.newInstance();
                BinaryTree<T> newright = t.newInstance();
                left.assemble(root, newleft, newright);
                root = left.replaceRoot(x);
            }
            //if x is larger than root and the right tree has at least 1 element

            else if (x.compareTo(root) > 0 && right.height() > 0) {
                insertInTree(right, x);
            }
            //if x is larger than root and the right tree has no height, reassemble the right tree

            else if (x.compareTo(root) > 0 && right.height() == 0) {
                BinaryTree<T> newleft = t.newInstance();
                BinaryTree<T> newright = t.newInstance();
                right.assemble(root, newleft, newright);
                root = right.replaceRoot(x);
            }
            //reassemble tree
            t.assemble(root, left, right);
        }
        //if tree has no elements, insert x as root
        else {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            t.assemble(x, left, right);
        }

    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";

        BinaryTree<T> left = t.newInstance();
        BinaryTree<T> right = t.newInstance();
        T smallest;
        T root = t.disassemble(left, right);
        if (left.height() > 0) {

            smallest = removeSmallest(left);
            t.assemble(root, left, right);

        } else {
            smallest = root;
            t.transferFrom(right);
        }

        return smallest;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        T removed;
        //if more than just root exists
        if (t.height() > 1) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);

            removed = root;
            //if x is smaller than root, traverse to left tree
            if (x.compareTo(root) < 0) {
                removed = removeFromTree(left, x);
                t.assemble(root, left, right);
            }
            //if x is larger than root, traverse to right tree
            else if (x.compareTo(root) > 0) {
                removed = removeFromTree(right, x);
                t.assemble(root, left, right);
            }
            //when element x node is found in tree
            else {
                //if both right and left branches have elements
                if (left.height() > 0 && right.height() > 0) {
                    //move up the smallest right elements into the left branch
                    while (right.size() > 0) {
                        insertInTree(left, removeSmallest(right));
                    }
                    //reassemble the left branch
                    BinaryTree<T> templeft = t.newInstance();
                    BinaryTree<T> tempright = t.newInstance();
                    T node = left.disassemble(templeft, tempright);
                    t.assemble(node, templeft, tempright);
                }
                //if only left branch exists
                else if (left.height() > 0) {
                    //reassemble the left branch
                    BinaryTree<T> port = t.newInstance();
                    BinaryTree<T> starboard = t.newInstance();
                    T node = left.disassemble(port, starboard);
                    t.assemble(node, port, starboard);
                }
                //if only right branch exists
                else if (right.height() > 0) {
                    //reassemble the right branch
                    BinaryTree<T> port = t.newInstance();
                    BinaryTree<T> starboard = t.newInstance();
                    T node = right.disassemble(port, starboard);
                    t.assemble(node, port, starboard);
                }
            }
        }
        //if only root exists, remove root
        else {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            removed = t.disassemble(left, right);
        }

        return removed;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.tree = new BinaryTree1<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
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
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";
        insertInTree(this.tree, x);

    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        return removeFromTree(this.tree, x);
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        return removeSmallest(this.tree);
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {

        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
