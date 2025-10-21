//Dummy Head LinkedList class implementation that implements List interface
public class DummyHeadLinkedList<T> implements List<T> {
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

    public DummyHeadLinkedList() {
        size = 0;
        head = new Node<T>(null);
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
        
        Node<T> current = head;
        Node<T> newNode = new Node<>(item);

        while(current.next != null){
            current = current.next;
        }

        current.next = newNode;
        size++;
        return true;
    }

    /** add method, takes in an item and an index of where the item should be added to
     * @param integer pos for the index of where the item should be added
     * @param T item, data that's being added
     * @throws out of bounds exception
     */
    @Override
    public void add (int pos, T item) {
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }

        Node<T> newNode = new Node<>(item);
        Node<T> prev = head;

        //Traverses through the list
        for (int i=0; i < pos; i++){
            prev = prev.next;
        }
        
        //Connects prev.next to newNode and changes the other pointer
        newNode.next = prev.next;
        prev.next = newNode;
        size++;

    }
        
    /** remove method, takes in an index of which the item at that index will be removed
     * @param integer pos, index of the element that will be removed
     * @throws out of bounds exception
     */
    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }
            
        Node<T> prev = head;


        //Traverses through list
        for (int i = 0; i < pos; i++){
            prev = prev.next;
        }
        
        //Connects prev.next to current.next so current can be removed safely
        Node<T> current = prev.next;
        prev.next = current.next;
        --size;
        return current.data;
    }

    /** get method, takes in an index and returns the element at that index
     * @param integer pos, index of the element
     * @throws out of bounds exception
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }

        Node<T> current = head.next;

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
        private Node<T> node = head.next;
    
        /** hasNext method, checks if the next node
         *  is null or not
         */
        @Override
        public boolean hasNext() {
            return node != null;
        }
    
        /** next method, moves to the next node
         * @return T data
         * @throws out of bounds exception
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
