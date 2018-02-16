/**
 * Created by Niklas on 2018-02-06.
 */
public class SplayWithGet<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E> {

    public E get(E e) {
        Entry t = find(e, root);
        if(t==null)
        {
            return null;
        }
        boolean notCorrectElemFound = t.element != e;
        Entry parent;
        Entry grandparent;
        while(t.parent != null)
        {
            parent = t.parent;
            grandparent = parent.parent;
            boolean shouldZigZag;
            boolean shouldZagZig;
            boolean shouldZagZag;
            boolean shouldZigZig;
            boolean shouldZig;
            boolean shouldZag;
            shouldZag = t.parent.left == t;
            shouldZig = t.parent.right == t;

            //System.out.println(t.parent.parent != null);
            if(t.parent.parent != null)
            {
            shouldZigZag = t.parent.left == t && t.parent.parent.right == t.parent;
            shouldZagZig = t.parent.right == t && t.parent.parent.left == t.parent;
            shouldZagZag = t.parent.right == t && t.parent.parent.right == t.parent;
            shouldZigZig = t.parent.left == t && t.parent.parent.left == t.parent;
                if(shouldZigZig)
                {
                    zigzig(t.parent.parent);
                    System.out.println(("zigzig"));
                }
                else if(shouldZagZag)
                {
                    zagzag(t.parent.parent);
                    System.out.println(("zagzag"));
                }
                else if(shouldZagZig)
                {
                    doubleRotateRight(t.parent.parent);
                    t = t.parent;
                    System.out.println(("zagzig"));
                }
                else if(shouldZigZag)
                {
                    doubleRotateLeft(t.parent.parent);
                    t = t.parent;
                }
            }
            else if(shouldZag)
            {
                rotateRight(t.parent);
                t = t.parent;
                System.out.println(("zag"));
            }
            else if(shouldZig)
            {
                rotateLeft(t.parent);
                t = t.parent;
                System.out.println(("zig"));
            }

        }

        if(t != null && e == t.element)
        {
            System.out.println("rootVal = element");
            int i = 5;
        }
        System.out.println( t == null);
        return t == null || notCorrectElemFound ? null : t.element;
    }  // get
    // ========== ========== ========== ==========
    @Override
    protected Entry find( E elem, Entry t ) {
        if ( t == null )
            return null;
        else {
            int jfr = elem.compareTo( t.element );
            if ( jfr  < 0 )
                if(t.left == null)
                    return t;
                else
                    return find( elem, t.left );
            else if ( jfr > 0 )
                if(t.right == null)
                    return t;
                else
                    return find( elem, t.right );
            else
                return t;
        }
    }  //   find

    //private int height(Entry t) {
    //    if (t == null)
    //        return 0;
    //    else
    //        return ((Entry) t);
    //} //  height

    // ========== ========== ========== ==========
    //private void checkHeight(Entry t) {
    //    ((Entry) t).height = 1 + Math.max(height(t.left),
    //            height(t.right));
    //}  //  CheckHeight
    // ========== ========== ========== ==========

    /**
     * The constructor creates the empty tree
     */
    public SplayWithGet() {
        super();
    }  // constructor AVL_Tree
    // ========== ========== ========== ==========

    /**
     * Add the element to its first proper empty place.
     * After the element is inserted the height balance
     * is checked and if nescessary restored.
     *
     * @param elem the element to be included
     */
    public boolean add(E elem) {
        if (root == null)
            root = new Entry(elem, null);
        else
            addInAVL(elem, root);
        size++;
        return true;
    } // add
    // ========== ========== ========== ==========

    /**
     * Remove the first occurance of an element with the same key
     * as the argument element.
     * If no element is removed false is returned,
     * otherwise true is returned.
     * After the element is removed the height balance
     * is checked and if nescessary restored.
     *
     * @param elem any element with the searched key
     * @return true if an element has disapeared from the tree,
     * false otherwise
     */
    public boolean remove(E elem) {
        if (root == null)
            return false;
        else if (root.element.compareTo(elem) == 0 &&
                (root.left == null || root.right == null)) {
            root = root.left == null ? root.right : root.left;
            size--;
            return true;
        } else {
            int oldSize = size;
            remove(elem, root);
            return size != oldSize;
        }
    } // remove
    // ========== ========== ========== ==========

    //  In order to make the iterator in
    //  BinarySearchTree to work properly !!
    protected void removeThis(Entry t) {
        remove(t.element, root);
    }  //  removeThis

    // ========== ========== ========== ==========
    private boolean addInAVL(E newElem, Entry t) {
        if (newElem.compareTo(t.element) < 0) {
            if (t.left == null) {
                t.left = new Entry(newElem, t);
                //checkHeight(t);
            } else {
                boolean left = addInAVL(newElem, t.left);
                //if (height(t.left) - height(t.right) > 1) {
                //    if (left)
                //        rotateRight(t);
                //    else
                //        doubleRotateRight(t);
                //} else
                    //checkHeight(t);
            }
            return true;
        } else {
            if (t.right == null) {
                t.right = new Entry(newElem, t);
                //checkHeight(t);
            } else {
                boolean left = addInAVL(newElem, t.right);
                //if (height(t.right) - height(t.left) > 1) {
                //    if (left)
                //        doubleRotateLeft(t);
                //    else
                //        rotateLeft(t);
                //} else
                    //checkHeight(t);
            }
        }
        return false;
    }  //   addInAVL
    // ========== ========== ========== ==========

    private void remove(E elem, Entry x) {
        if (elem.compareTo(x.element) == 0)
            if (x.left == null || x.right == null) {
                Entry newX = x.left == null ? x.right : x.left;
                if (newX != null)
                    newX.parent = x.parent;
                if (x.parent.left == x)
                    x.parent.left = newX;
                else
                    x.parent.right = newX;
                size--;
                return;
            } else {
                Entry t = x.left; //x.element = findRefToMostRight( x.left ).element;
                while (t.right != null)//
                    t = t.right;       //
                x.element = t.element; //
                remove(x.element, x.left);
                //if (height(x.right) - height(x.left) > 1)
                //    if (height(x.right.right) <
                //            height(x.right.left))
                //        doubleRotateLeft(x);
                //    else
                //        rotateLeft(x);
                //else
                    //checkHeight(x);
            }
        else if (elem.compareTo(x.element) < 0) {
            if (x.left != null) {
                remove(elem, x.left);
                //if (height(x.right) - height(x.left) > 1)
                //    if (height(x.right.right) <
                //            height(x.right.left))
                //        doubleRotateLeft(x);
                //    else
                //        rotateLeft(x);
                //else
                //    //checkHeight(x);
            }
        } else if (x.right != null) {
            remove(elem, x.right);
            //if (height(x.left) - height(x.right) > 1)
            //    if (height(x.left.left) <
            //            height(x.left.right))
            //        doubleRotateRight(x);
            //    else
            //        rotateRight(x);
            //else
            //    //checkHeight(x);
        }
    }  // remove private version
    // ========== ========== ========== ==========

    /* Rotera 1 steg i hogervarv, dvs
              x'                 y'
             / \                / \
            y'  C   -->        A   x'
           / \                    / \
          A   B                  B   C
    */
    private void rotateRight(Entry x) {
        Entry y = x.left;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.left = y.left;
        if (x.left != null)
            x.left.parent = x;
        y.left = y.right;
        y.right = x.right;
        if (y.right != null)
            y.right.parent = y;
        x.right = y;
        //checkHeight(y);
        //checkHeight(x);
    } //   rotateRight
    // ========== ========== ========== ==========

    private void updateRoot()
    {
        while(root.parent != null)
        {
            root = root.parent;
        }
    }

    public void zigzig(Entry z){
        if(z == null || z.left == null || z.left.left == null) {
            throw new NullPointerException("Fel indata");
        }
        Entry y = z.left;
        Entry x = y.left;
        Entry tempRoot = z.parent;

        z.left = y.right;
        if(y.right != null)
        {
            y.right.parent = z;
        }
        z.parent = y;

        y.right = z;
        y.left = x.right;
        if(x.right != null)
        {
            x.right.parent = y;
        }
        y.parent = x;

        x.right = y;
        x.parent = tempRoot;
        if(tempRoot != null && x.parent.right == z)
            x.parent.right = x;
        else if(tempRoot != null && x.parent.left == z)
            x.parent.left = x;
        updateRoot();
    }

    // ys barn

    public void zagzag(Entry z){
        if(z == null || z.right == null || z.right.right == null) {
            throw new NullPointerException("Fel indata");
        }
        Entry y = z.right;
        Entry x = y.right;
        Entry tempRoot = z.parent;

        z.right = y.left;
        if(y.left != null)
        {
            y.left.parent = z;
        }
        z.parent = y;

        y.left = z;
        y.right = x.left;
        if(x.left != null)
        {
            x.left.parent = y;
        }
        y.parent = x;

        x.left = y;
        x.parent = tempRoot;
        if(tempRoot != null && x.parent.right == z)
            x.parent.right = x;
        else if(tempRoot != null && x.parent.left == z)
            x.parent.left = x;
        updateRoot();
    }

    /* Rotera 1 steg i vanstervarv, dvs
              x'                 y'
             / \                / \
            A   y'  -->        x'  C
               / \            / \
              B   C          A   B
    */
    public void rotateLeft(Entry x) {
        Entry y = x.right;
        E temp = x.element;
        x.element = y.element;
        y.element = temp;
        x.right = y.right;
        if (x.right != null)
            x.right.parent = x;
        y.right = y.left;
        y.left = x.left;
        if (y.left != null)
            y.left.parent = y;
        x.left = y;
        //checkHeight(y);
        //checkHeight(x);
    } //   rotateLeft
    // ========== ========== ========== ==========

    /* Rotera 2 steg i hogervarv, dvs
              x'                  z'
             / \                /   \
            y'  D   -->        y'    x'
           / \                / \   / \
          A   z'             A   B C   D
             / \
            B   C
    */
    private void doubleRotateRight(Entry x) {
        Entry y = x.left,
                z = x.left.right;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.right = z.left;
        if (y.right != null)
            y.right.parent = y;
        z.left = z.right;
        z.right = x.right;
        if (z.right != null)
            z.right.parent = z;
        x.right = z;
        z.parent = x;
        //checkHeight(z);
        //checkHeight(y);
        //checkHeight(x);
    }  //  doubleRotateRight
    // ========== ========== ========== ==========

    /* Rotera 2 steg i vanstervarv, dvs 
               x'                  z'
              / \                /   \
             A   y'   -->       x'    y'
                / \            / \   / \
               z   D          A   B C   D
              / \  
             B   C  
     */
    private void doubleRotateLeft(Entry x) {
        Entry y = x.right,
                z = x.right.left;
        E e = x.element;
        x.element = z.element;
        z.element = e;
        y.left = z.right;
        if (y.left != null)
            y.left.parent = y;
        z.right = z.left;
        z.left = x.left;
        if (z.left != null)
            z.left.parent = z;
        x.left = z;
        z.parent = x;
        //checkHeight(z);
        //checkHeight(y);
        //checkHeight(x);
    } //  doubleRotateLeft
    // ========== ========== ========== ==========
}  //  class AVL_Tree

