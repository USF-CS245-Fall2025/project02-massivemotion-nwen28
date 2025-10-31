//Doubly LinkedList class that implements the List interface
public class DoublyLinkedList<T> implements List<T>{
    
    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            data = value;
            next = null;
            prev = null;
        }
    }

    int size;
    Node<T> head;
    Node<T> tail;

    public DoublyLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    /** 
     * add method
     * Takes in an item and adds it to the arraylist.
     * @param T item data that is being added to arraylist
     */
    @Override
    public boolean add(T item) {

        Node<T> newNode = new Node(item);

        if(size == 0){
            head = newNode;
            tail = newNode;
        } else{
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    /** 
     * add method
     * Takes in an item and an index of where the item should be added to.
     * @param integer pos for the index of where the item should be added
     * @param T item, data that's being added
     * @throws out of bounds exception
     */
    public void add(int pos, T item){
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }

        Node<T> newNode = new Node<>(item);

        if(pos == 0){

            if (head == null) {

                head = newNode;
                tail = newNode;
            } else {

                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }

        } else if(pos == size){

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else{

            Node<T> current = head;
            for (int i=0; i < pos- 1; i++){
                current = current.next;
            }

            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    /** 
     * remove method
     * Takes in an index of which the item at that index will be removed.
     * @param integer pos, index of the element that will be removed
     * @throws out of bounds exception
     */
    public T remove(int pos){
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }

        Node<T> current = head;

        if(pos == 0){
            T data = head.data;
            head = head.next;

            if(head != null){
                head.prev = null;
            } else{
                tail = null;
            }

            size--;
            return data;

        }

        if(pos == size - 1){
            T data = tail.data;

            tail = tail.prev;
            tail.next = null;
            size--;
            return data;
        }

        for (int i = 0; i < pos; i++) {
            current = current.next;
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return current.data;
    }

    /** 
     * get method
     * Takes in an index and returns the element at that index.
     * @param integer pos, index of the element
     * @throws out of bounds exception
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("List index out of bounds");
        }

        Node<T> current = head;

        for (int i = 0; i < pos; i++) {
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

        /** 
         * hasNext method
         * Checks if the next node is null or not.
         */
        @Override
        public boolean hasNext() {
            return node != null;
        }

        /** 
         * next method
         * Moves to the next node.
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
