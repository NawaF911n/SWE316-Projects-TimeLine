
public interface BasicCollection<T> {

    int size();
    boolean isEmpty();
    T get(int i);
    T[] getAll();
    void add(T o);
}