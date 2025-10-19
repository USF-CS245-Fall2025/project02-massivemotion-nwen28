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

    @Override
    public void add (int pos, T item) {
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index OOB");
        }

        Node<T> newNode = new Node<>(item);
        Node<T> prev = head;

        for (int i=0; i < pos; i++){
            prev = prev.next;
        }
        
        newNode.next = prev.next;
        prev.next = newNode;
        size++;

    }
        

    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException("List index OOB");
        }
            
        Node<T> prev = head;


        for (int i = 0; i < pos; i++){
            prev = prev.next;
        }
        
        Node<T> current = prev.next;
        prev.next = current.next;
        --size;
        return current.data;
    }

    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException("List index OOB");
        }

        Node<T> current = head.next;
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
    
        @Override
        public boolean hasNext() {
            return node != null;
        }
    
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
