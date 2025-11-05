import java.util.LinkedList;


public class Cache<T> {
    private final int capacity;
    private final LinkedList<T> cacheList;

    public Cache(int capacity) {
        this.capacity = capacity;
        this.cacheList = new LinkedList<>();
    }


    public void add(T item) {
        if (cacheList.size() >= capacity) {
            cacheList.removeFirst();
        }
        cacheList.addLast(item);
    }


}