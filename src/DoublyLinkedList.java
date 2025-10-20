
/** DoublyLinkedList class which implements the List interface
 * functions similar to a linkedlist but now keeps track of the previous 
 * node as well. 
 */
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
     */
    public void add(int pos, T item){
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index OOB");
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

    /** remove method, takes in an index of which the item at that index will be removed
     *  @param integer pos, index of the element that will be removed
     */
    public T remove(int pos){
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index OOB");
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

    /** get method, takes in an index and returns the element at that index
     * @param integer pos, index of the element
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("List index OOB");
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
