public class CustomStack<T> {
    private Node<T> top;
    public void push(T item) {
        Node<T> n = new Node<>(item);
        n.next = top;
        top = n;
    }
    public T pop() {
        if (top == null) return null;
        T d = top.data;
        top = top.next;
        return d;
    }
}

