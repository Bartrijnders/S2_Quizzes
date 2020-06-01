package Obeserver;

public interface Observable<T> {
    void register(T t);

    void unRegister(T t);

    void notifyRegister();
}
