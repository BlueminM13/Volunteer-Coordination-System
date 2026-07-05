public class CustomQueue<T> {
    private Node<T> head, tail;

    public void enqueue(T item) {
        Node<T> n = new Node<>(item);
        if (tail == null) head = tail = n;
        else { tail.next = n; tail = n; }
    }

    public T dequeue() {
        if (head == null) return null;
        T d = head.data;
        head = head.next;
        if (head == null) tail = null;
        return d;
    }

    public T peek() {
        return (head != null) ? head.data : null;
    }

    public void dequeueIfMatch(T item) {
        if (head != null && head.data.equals(item)) {
            dequeue();
        }
    }

    public void remove(T item) {    // Removes a specific item from any position in the queue
        if (head == null) return;

        // Remove head
        if (head.data.equals(item)) {
            dequeue();
            return;
        }

        // Remove from middle or tail
        Node<T> prev = head;
        Node<T> cur = head.next;
        while (cur != null) {
            if (cur.data.equals(item)) {
                prev.next = cur.next;
                if (cur == tail) tail = prev;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    public boolean isEmpty() { return head == null; }
}

