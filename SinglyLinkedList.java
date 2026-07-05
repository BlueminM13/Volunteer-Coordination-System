public class SinglyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public void append(T item) {    // Adds a new item to the end of the list
        Node<T> n = new Node<>(item);
        if (head == null) head = tail = n;
        else { tail.next = n; tail = n; }
    }

    public Node<T> getHead() { return head; }   // Returns the starting node of the list

    public void displayAll() {
        Node<T> cur = head;
        if (cur == null) { System.out.println("Registry is empty."); return; }
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }

    //Linear search algorithm for findVolunteerById, findVolunteerByName and removeById
    public Volunteer findVolunteerById(String id) {
        Node<T> cur = head;
        while (cur != null) {
            if (cur.data instanceof Volunteer v && v.volunteerId.equalsIgnoreCase(id)) return v;
            cur = cur.next;
        }
        return null;
    }

    public Volunteer findVolunteerByName(String name) {
        Node<T> cur = head;
        while (cur != null) {
            if (cur.data instanceof Volunteer v && v.name.equalsIgnoreCase(name)) return v;
            cur = cur.next;
        }
        return null;
    }

    public boolean removeById(String id) {
        Node<T> prev = null;
        Node<T> cur = head;
        while (cur != null) {
            if (cur.data instanceof Volunteer v && v.volunteerId.equalsIgnoreCase(id)) {
                if (prev == null) head = cur.next;
                else prev.next = cur.next;
                if (cur == tail) tail = prev;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }
}

