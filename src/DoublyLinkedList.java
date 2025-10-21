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

    /** add method, takes in an item and adds it to the arraylist
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

    /** add method, takes in an item and an index of where the item should be added to
     * @param integer pos for the index of where the item should be added
     * @param T item, data that's being added
     * @throws out of bounds exception
     */
    public void add(int pos, T item){
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }

        Node<T> newNode = new Node<>(item);

        //Adding to the front
        if(pos == 0){

            //Checks if head is null (if the list is empty)
            if (head == null) {

                //Newly created node becomes both the head and tail
                head = newNode;
                tail = newNode;
            } else {

                //Newly created node becomes the head and all the pointers get moved
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }

        //Adding to the end of the list
        } else if(pos == size){

            //Newly created node becomes the tail
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else{

            //Iterates through the linkedlist to the desired position
            Node<T> current = head;
            for (int i=0; i < pos- 1; i++){
                current = current.next;
            }

            //Connects pointers to and from the newly created node
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    /** remove method, takes in an index of which the item at that index will be removed
     * @param integer pos, index of the element that will be removed
     * @throws out of bounds exception
     */
    public T remove(int pos){
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }

        Node<T> current = head;

        //Checks if the element to be removed is the first element
        if(pos == 0){
            T data = head.data;
            head = head.next;

            if(head != null){
                head.prev = null;
            } else{
                tail = null;
            }
            //Decrease size by 1
            size--;
            return data;

        }

        //Checks if removing the last element
        if(pos == size - 1){
            T data = tail.data;

            //Tail.prev becomes the new tail
            tail = tail.prev;
            tail.next = null;
            size--;
            return data;
        }

        //Traverses linkedlist to find the position of the element
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }

        //Connects current.prev with current.next
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return current.data;
    }

    /** get method, takes in an index and returns the element at that index
     * @param integer pos, index of the element
     * @throws out of bounds exception
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("List index out of bounds");
        }

        Node<T> current = head;

        //Moves current node to the next node until it reaches the desired index/position
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
