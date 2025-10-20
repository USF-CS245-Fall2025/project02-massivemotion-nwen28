
// ArrayList class which implements the List interface.
//Has grow_array. add, add at position, and remove
public class ArrayList<T> implements List<T> {
    private class ListIterator implements Iterator<T> {
        protected int pos = 0;

        public boolean hasNext() {
        if (pos < size){
            return true;
        }
            
        return false;
        }

        public T next() {
            return (T) arr[pos++];
        }
    }

    T [] arr;
    int size;

    public ArrayList () {
        size = 0;
        arr = (T[]) new Object[10];
    }

    @Override
    public int size() {
        return size;
    }

    /** grow_array method doubles size of arraylist
     * 
     */
    protected void grow_array() {
    T[] new_arr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++){
            new_arr[i] = arr[i];
        }
        
        arr = new_arr;
    }

    /** add method, takes in an item and adds it to the arraylist
     * @param T item data that is being added to arraylist
     */
    @Override
    public boolean add(T item) {
        if (size == arr.length - 1){
            grow_array();
        }
            
        arr[size++] = item;
        return true;
    }

    /** add method, takes in an item and an index of where the item should be added to
     * @param integer pos for the index of where the item should be added
     * @param T item, data that's being added
     */
    @Override
    public void add(int pos, T item) {
        if (pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }
            
        if (size == arr.length - 1){
            grow_array();
        }
            
        for (int i = size; i > pos; i--) {
            arr[i] = arr[i-1];
        }
        arr[pos] = item;
        ++size;
    }

    /** remove method, takes in an index of which the item at that index will be removed
     *  @param integer pos, index of the element that will be removed
     */
    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }
            
        T copy = arr[pos];
        for (int i = pos; i < size - 1; i++){
            arr[i] = arr[i+1];
        }
        --size;
        return copy;
    }

    /** get method, takes in an index and returns the element at that index
     * @param integer pos, index of the element
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size){
            throw new IndexOutOfBoundsException("List index out of bounds");
        }
        
        return arr[pos];
    }

    public Iterator<T> iterator() {
        return new ListIterator();
    }
}