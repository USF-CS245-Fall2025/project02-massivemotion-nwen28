
//LinkedList implementation that implements the List interface
public class LinkedList<T> implements List<T> {

    private class Node<T> {
        T data;
        Node<T> next;
        public Node(T value) {
            data = value;
            next = null;
        }
    }

    int size;
    Node<T> head;

    public LinkedList() {
        size = 0;
        head = null;
    }

    @Override
    public int size() {
        return size;
    }

    /** add method, takes in an item and adds it to the arraylist
     * @param T item data that is being added to arraylist
     */
    @Override
    public boolean add(T item) {
        if (size == 0) {
            head = new Node(item);
            ++size;
            return true;
        }

        // else
        Node<T> node = head;
        while (node.next != null){
            node = node.next;
        }
        Node<T> newlast = new Node(item);
        newlast.next = null;
        node.next = newlast;
        ++size;
        return true;
    }

    /** add method, takes in an item and an index of where the item should be added to
     * @param integer pos for the index of where the item should be added
     * @param T item, data that's being added
     */
    @Override
    public void add (int pos, T item) {
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index OOB");
        }

        if (pos == 0) {
            Node<T> node = new Node(item);
            node.next = head;
            head = node;
        } else {
            Node<T> node = new Node(item);
            Node<T> prev = head;
            for (int i=0; i < pos- 1; i++){
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
        }
        ++size;
    }
        
    /** remove method, takes in an index of which the item at that index will be removed
     *  @param integer pos, index of the element that will be removed
     */
    @Override
    public T remove(int pos) {
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index OOB");
        }
            
        if (pos == 0) {
            Node<T> node = head;
            head = head.next;
            --size;
            return node.data;
        }

        Node<T> prev = head;
        for (int i = 0; i < pos-1; i++)
        prev = prev.next;
        Node<T> current = prev.next;
        prev.next = current.next;
        --size;
        return current.data;
    }

    /** get method, takes in an index and returns the element at that index
     * @param integer pos, index of the element
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException("List index OOB");
        }

        Node<T> current = head;

        //Moves current node to the next node until it reaches the desired index/position
        for(int i = 0; i < pos; i++){
            current = current.next;
        }

        return current.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> node = head;
    
        /** hasNext method, checks if the next node
         *  is null or not
         */
        @Override
        public boolean hasNext() {
            return node != null;
        }
    
        /** next method, moves to the next node
         * @return T data
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements");
            }
            T data = node.data;
            node = node.next;
            return data;
        }
    }
    



}