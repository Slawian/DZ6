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

    public boolean remove(T item) {
        return cacheList.remove(item);
    }
    public boolean exists(T item) {
        return cacheList.contains(item);
    }
    public T getFirst() {
        if (cacheList.isEmpty()) {
            return null;
        }
        return cacheList.getFirst();
    }


}