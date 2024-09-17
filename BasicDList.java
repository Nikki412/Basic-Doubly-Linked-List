public class BasicDList<E> implements List<E> {
      private Link<E> head;      // Pointer to list header
    private Link<E> tail;      // Pointer to last element
    private Link<E> curr;      // Access to current element
    private int listSize;      // Size of list

    // Constructors
    DLList(int size) {      // Constructor -- Ignore size
        this();
    }

    DLList() {
        clear();
    }

    // Remove all elements
    public void clear() {
        curr = tail = new Link<E>(null, null); // Create trailer
        head = new Link<E>(null, tail);        // Create header
        tail.setPrev( head );
        listSize = 0;
    }

    public boolean insert(E it){
        curr = new Link( it, curr.prev(), curr );
        curr.prev().setNext( curr );
        curr.next().setPrev( curr );
        listSize++;
        return true;
    }

    // Append "it" to list
    // Add "it" to the end of the list
    public boolean append(E it){
        tail.setPrev( new Link( it, tail.prev(), tail ) );
        tail.prev().prev().setNext( tail.prev() );
        if( curr == tail ){
            curr = tail.prev();
        }
        listSize++;
        return true;
    }

    // Remove and return current element
    public E remove () throws NoSuchElementException {
        if( curr == tail ){
            // Nothing to remove
            return null;
        }
        E it = curr.element();  // Remember value
        curr.prev().setNext( curr.next() );
        curr.next().setPrev( curr.prev() );
        curr = curr.next();
        listSize--;  // Decrement node count
        return it;  // Return value removed
    }

    public void moveToStart() {
        curr = head.next(); // Set curr at list start
    }

    public void moveToEnd() {
        curr = tail; // Set curr at list end
    }

    // Move curr one step left; no change if now at front
    public void prev() {
        if (head.next() == curr) {
            return; // No previous element
        }
        curr = curr.prev();
    }

    // Move curr one step right; no change if now at end
    public void next() { if (curr != tail) { curr = curr.next(); } }

    public int length() { return listSize; } // Return list length


    // Return the position of the current element
    public int currPos() {
        Link<E> temp = head.next();
        int i;
        for (i=0; curr != temp; i++) {
            temp = temp.next();
        }
        return i;
    }

    // Move down list to "pos" position
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) {
            return false;
        }
        curr = head.next();
        for(int i=0; i<pos; i++) { curr = curr.next(); }
        return true;
    }

    // Return true if current position is at end of the list
    public boolean isAtEnd() { return curr == tail; }

    // Return current element value. Note that null gets returned if curr is at the tail
    public E getValue() throws NoSuchElementException {
        if (curr == tail) // No current element
        {
            throw new NoSuchElementException("getValue() in DLList has current of " + curr + " and size of "
                                             + listSize + " that is not a a valid element");
        }
        return curr.element();
    }

    //Tell if the list is empty or not
    public boolean isEmpty() {
        return listSize == 0;
    }

    public String toString() {}
}