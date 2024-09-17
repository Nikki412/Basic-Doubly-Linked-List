import java.util.NoSuchElementException;
/**
 * Description: This class is the implementation of a basic doubly linked list without a header or trailer node. This method extends the list interface. 
 * @author OpenDSA Data Structures and Algorithms Modules Collection, CHAPTER 9 LINEAR STRUCTURES: https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/ListDouble.html
 * @author Nikki Johnson 
 * @version 3.1.15
 */

// Linked list implementation
public class BasicDLList<E> implements List<E> {
    private Link<E> head;      // Pointer to list header
    private Link<E> tail;      // Pointer to last element
    private Link<E> curr;      // Access to current element
    private int listSize;      // Size of list

    // Constructors
    BasicDLList(int size) {      // Constructor -- Ignore size
        this();
    }

    BasicDLList() {
        clear();
    }

    /**
     * This method removes all elements from the list and sets the list size to 0
     */
    public void clear() {
        head = curr = tail = null; 
        listSize = 0;
    }

    /**
     * This method inserts an element into the list
     * @param it the element to insert
     */
    public boolean insert(E it){
      if (listSize == 0){ // empty list
            head = curr = tail = new Link<E>(null, null);
            head.setElement(it);
            listSize++;
            return true;
      } else if (head == curr){ // before the beginning
            Link<E> newLink = new Link<E>(it, null, curr);
            curr.setPrev(newLink);
            head = curr = newLink;
            listSize++;
            return true;
      } else if (curr == null){ // after the last element in the list
            Link<E> newLink = new Link<E>(it, tail, null);
            tail.setNext(newLink);
            tail = curr = newLink;
            listSize++;
            return true;
      } else{
            // create the new link to be inserted into the list 
            Link<E> newLink = new Link<E>( it, curr.prev(), curr );
            curr.prev().setNext( newLink );
            curr.setPrev( newLink );
            curr = newLink;
            listSize++;
            return true;
      }
    }

    /**
     * This method adds an element to the end of the list and does not update the position of curr
     * @param it the element to be added to the end of the list
     */
    public boolean append(E it){
      if (listSize == 0){
            head = tail = curr = new Link<E>(null, null);
            head.setElement(it);
            listSize++;
      } else{
            Link<E> newLink = new Link<E>(it, tail, null);
            tail.setNext( newLink );
            tail = newLink;
            listSize++;
      }
      return true;
    }

    /**
     * This method removes the element at the current position from the list and returns what it removed
     * @return the element that was removed from the list
     */
    public E remove () {
        if( listSize == 0 ){
            // Nothing to remove
            return null;
        }
        E it;
        if (listSize == 1){ // if there is only one element, remove that element (head)
            it = head.element(); // remeber value
            clear(); // remove element 
            return it; // return value removed
        } else if (curr == null){ // if curr is null there is nothing to remove
            return null; // nothing to remove
        } else { // current is not null
            if (curr.prev() == null){   // removing the head element
                  it = curr.element();  // Remember value
                  head = curr.next();    // update the head
                  curr.next().setPrev( null );  // set the next element's previous to curr's previous element 
                  curr = curr.next(); // update curr
                  listSize--;  // Decrement node count
                  return it;  // Return value removed
            }
            if (curr.next() == null){  // removing the tail element 
                  it = curr.element();  // Remember value
                  tail = curr.prev(); // update the tail
                  curr.prev().setNext( null );  // set the previous element's next to curr's next element 
                  curr = curr.prev(); // update curr
                  listSize--;  // Decrement node count
                  return it;  // Return value removed
            } else { // removing an element in the middle of the list
                  it = curr.element();  // Remember value
                  curr.prev().setNext( curr.next() );  // set the previous element's next to curr's next element 
                  curr.next().setPrev( curr.prev() );  // set the next element's previous to curr's previous element 
                  curr = curr.next(); // update curr
                  listSize--;  // Decrement node count
                  return it;  // Return value removed
            }
        }
    }

    /**
     * This method moves the current position to the start of the list
     */
    public void moveToStart() {
      if (curr == head){ return;}
      curr = head; // Set curr at list start
    }

    /**
     * This method moves the current position to the end of the list after tail
     */
    public void moveToEnd() {
        curr = null; // Set curr at list end
    }

    /**
     * This method moves the current postion backwards by one element 
     */
    public void prev() {
        if (curr == head){ return;}
        if (curr == null) {
            curr = tail;
        } else {
            curr = curr.prev();
        }
        
    }

    /**
     * This method moves the current postion forwards by one element
     */
    public void next() {
      if (curr != null) { curr = curr.next(); } 
    }

    /**
     * This method returns the size of the list
     */
    public int length() { return listSize; } // Return list length


    /**
     * This method returns the numerical value current position
     * @return the numerical value of the current position
     */
    public int currPos() {
      Link<E> temp = head;
      int i;
      for (i=0; curr != temp && temp != null; i++) {
            temp = temp.next();
      }
      return i;
    }

    /**
     * This method moves the current position to pos. If the method cannot move the position because it is invalid then it returns false
     * @param pos the position to move the current position to
     * @return whether or not the current position was moved
     */
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) {
            return false;
        }
        if (listSize == 0 && pos != 0){
            System.out.println("List is empty");
            return false;
        }
        if (pos == 0){
            curr = head;
            return true;
        }
        curr = head;
        for(int i=0; i<pos && curr != null; i++) { curr = curr.next(); }
        return true;
    }

    /** 
     * Return true if current position is at end of the list
     * @return if current is at the end of the list
     */
    public boolean isAtEnd() { return curr == tail; }

    /**
     * This method returns the current element value. Note that null gets returned if curr is null
     */
    public E getValue() throws NoSuchElementException {
      if (listSize == 1 && curr == null){return head.element();}
      return curr.element();
    }

    /**
     * This method returns true if the list is empty
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return listSize == 0;
    }

    /**
     * This method returns a string representation of the list
     * @return the string representation of the list
     */
    public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("(");
      
      Link<E> temp = head; 
      while(temp != null) {
            if(temp == curr) sb.append("| ");
            sb.append(temp.element()); 
            if(temp != tail) sb.append(", "); 
            temp = temp.next();
      }

      if (curr == null){sb.append("| ");}

      sb.append(")");
      return sb.toString();
    }
}