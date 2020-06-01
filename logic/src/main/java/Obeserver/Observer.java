package Obeserver;

public interface Observer {
    void register(Observable observable);

    void update();
}
