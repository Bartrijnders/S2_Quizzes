package sevices;

public interface ValueChecker<T, S> {
    boolean checkForName(T name, S collection);
}
