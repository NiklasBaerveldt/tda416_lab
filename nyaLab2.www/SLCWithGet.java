/**
 * Created by Niklas on 2018-02-02.
 */
public class SLCWithGet<E extends Comparable<? super E>>
        extends LinkedCollection<E>
        implements CollectionWithGet<E>  {

    SLCWithGet()
    {
        super();
    }

    /**
     * Adds an element of type E to the linkedlist according to the natural ordering,
     * where the first element in the list is the least.
     * @param element an element of type E
     * @return always returns true
     */


    @Override
    public boolean add( E element ) {
        if ( element == null )
            throw new NullPointerException();
        else {
            if(head == null)
            {
                head = new Entry( element, head );
                return true;
            }
            else {
                Entry current = head;
                boolean correctPlaceFound = false;
                while (!correctPlaceFound) {
                    int compareAns = element.compareTo(current.element);
                    if (compareAns <= 0 || current.next == null) {
                        current.next = new Entry(element, current.next);
                        correctPlaceFound = true;
                    } else {
                        current = current.next;
                    }
                }
                return true;
            }
        }
    } // add



    /**
     * Get the element provided as the parameter from the linkedlist if it exists, otherwise null is returned.
     * This method does not change the list, i.e. it does not remove the element that it returns.
     * @param element an element of type E
     * @return element of type E or null.
     */

    public E get(E element)
    {
        if(element == null)
        {
            throw new NullPointerException();
        }
        if(head==null)
        {
            return null;
        }
        Entry current = head;
        while(true)
        {
            int compareAns;
            compareAns = element.compareTo(current.element);
            if(compareAns == 0)
            {
                return current.element;
            }
            else {
                if(current.next == null) {
                    return null;
                }
                current = current.next;
            }
        }
    }
}
