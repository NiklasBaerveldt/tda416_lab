/**
 * Created by Niklas on 2018-02-06.
 */
public class SplayWithGet<E extends Comparable<? super E>> extends BinarySearchTree<E> implements CollectionWithGet<E> {

    boolean notCorrectElemFound = false;
    public E get(E e) {
        Entry t = find(e, root);
        if(t==null)
        {
            return null;
        }
        while(t.parent != null)
        {
            boolean shouldZigZag;
            boolean shouldZagZig;
            boolean shouldZagZag;
            boolean shouldZigZig;
            boolean shouldZig;
            boolean shouldZag;
            shouldZag = t.parent.left == t;
            shouldZig = t.parent.right == t;
            if(t.parent.parent != null)
            {
            shouldZigZag = t.parent.left == t && t.parent.parent.right == t.parent;
            shouldZagZig = t.parent.right == t && t.parent.parent.left == t.parent;
            shouldZagZag = t.parent.right == t && t.parent.parent.right == t.parent;
            shouldZigZig = t.parent.left == t && t.parent.parent.left == t.parent;

                if(shouldZigZig)
                {
                    zigzig(t.parent.parent);
                    t=t.parent.parent;
                }
                else if(shouldZagZag)
                {
                    zagzag(t.parent.parent);
                    t=t.parent.parent;
                }
                else if(shouldZagZig)
                {
                    zagzig(t.parent.parent);
                    t = t.parent;
                }
                else if(shouldZigZag)
                {
                    zigzag(t.parent.parent);
                    t = t.parent;
                }
            }
            else if(shouldZag)
            {
                zag(t.parent);
                t = t.parent;
            }
            else if(shouldZig)
            {
                zig(t.parent);
                t = t.parent;
            }
        }

        if(t==null || notCorrectElemFound)
        {
            notCorrectElemFound = false;
            return null;
        }
        else
            return t.element;
    }  // get
    // ========== ========== ========== ==========
    
    @Override
    protected Entry find( E elem, Entry t ) {
        if ( t == null )
            return null;
        else {
            int jfr = elem.compareTo( t.element );
            if ( jfr  < 0 )
                if(t.left == null) {
                    notCorrectElemFound = true;
                    return t;
                }
                else
                    return find( elem, t.left );
            else if ( jfr > 0 )
                if(t.right == null) {
                    notCorrectElemFound = true;
                    return t;
                }
                else
                    return find( elem, t.right );
            else
                return t;
        }
    }  //   find

    // ========== ========== ========== ==========

    /**
     * The constructor creates the empty tree
     */
    public SplayWithGet() {
        super();
    }  // constructor 
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
            addInSplay(elem, root);
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
    private boolean addInSplay(E newElem, Entry t) {
        if (newElem.compareTo(t.element) < 0) {
            if (t.left == null) {
                t.left = new Entry(newElem, t);
            } else {
                boolean left = addInSplay(newElem, t.left);
            }
            return true;
        } else {
            if (t.right == null) {
                t.right = new Entry(newElem, t);
            } else {
                boolean left = addInSplay(newElem, t.right);
            }
        }
        return false;
    }  //   addInSplay
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
                Entry t = x.left;
                while (t.right != null)
                    t = t.right;
                x.element = t.element;
                remove(x.element, x.left);
            }
        else if (elem.compareTo(x.element) < 0) {
            if (x.left != null) {
                remove(elem, x.left);
            }
        } else if (x.right != null) {
            remove(elem, x.right);
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
    private void zag(Entry x) {
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
    } //   zag
    // ========== ========== ========== ==========


    /* Rotera 1 steg i vanstervarv, dvs
              x'                 y'
             / \                / \
            A   y'  -->        x'  C
               / \            / \
              B   C          A   B
    */
    public void zig(Entry x) {
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
    } //   zig
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
    private void zagzig(Entry x) {
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
    }  //  zagzig
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
    private void zigzag(Entry x) {
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
    } //  zigzag



    /* zigzigga , dvs
              z              x
             / \            / \
            y  D   -->     A   y
           / \                / \
          x   C              B   z
         / \                    / \
        A   B                  C   D
    */

    public void zigzig(Entry z)
    {
        Entry y = z.left;
        Entry x = y.left;
        Entry a = x.left;
        Entry b = x.right;
        Entry c = y.right;
        Entry d = z.right;

        E tempE = z.element;
        z.element = x.element;
        x.element = tempE;

        z.right = y;
        z.left = a;
        if(a!=null)
            a.parent = z;

        y.right = x;
        y.left = b;
        if(b!=null)
            b.parent = y;

        x.right = d;
        if(d!=null)
            d.parent = x;
        x.left = c;
        if(c!=null)
            c.parent = x;
    }

    /* zagzagga, dvs
          x                  z
         / \                / \
        A   y   -->        y  D
           / \            / \
          B   z          x   C
             / \        / \
            C   D      A   B
     */

    public void zagzag(Entry x)
    {
        Entry y = x.right;
        Entry z = y.right;
        Entry a = x.left;
        Entry b = y.left;
        Entry c = z.left;
        Entry d = z.right;

        E tempE = x.element;
        x.element = z.element;
        z.element = tempE;

        x.left = y;
        x.right = d;
        if(d!=null)
            d.parent = x;

        y.left = z;
        y.right = c;
        if(c!=null)
            c.parent = y;

        z.left = a;
        if(a!=null)
            a.parent = z;
        z.right = b;
        if(b!=null)
            b.parent = z;
    }
    // ========== ========== ========== ==========
}  //  

