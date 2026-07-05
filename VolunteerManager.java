public class VolunteerManager {
    private final SinglyLinkedList<Volunteer> masterList = new SinglyLinkedList<>();    // Master record of all volunteers
    private final CustomQueue<Volunteer> highQ = new CustomQueue<>();   // Separate queues for each priority tier
    private final CustomQueue<Volunteer> medQ = new CustomQueue<>();
    private final CustomQueue<Volunteer> lowQ = new CustomQueue<>();
    private final CustomStack<Operation> undoStack = new CustomStack<>();   // Stack to store operation history for undoing

    private static class Operation {
        enum Type { ASSIGN, REMOVE }
        Type type;
        Volunteer volunteer;
        String prevTask;
        boolean prevAssigned;
        Operation(Type t, Volunteer v, String pt, boolean pa) {
            this.type = t; this.volunteer = v; this.prevTask = pt; this.prevAssigned = pa;
        }
    }

    public boolean registerVolunteer(Volunteer v) {
        if (masterList.findVolunteerById(v.volunteerId) != null) return false;
        masterList.append(v);
        reQueue(v);
        return true;
    }

    private void reQueue(Volunteer v) {
        if (!v.assigned) {
            if (v.priority == Volunteer.Priority.HIGH) highQ.enqueue(v);
            else if (v.priority == Volunteer.Priority.MEDIUM) medQ.enqueue(v);
            else lowQ.enqueue(v);
        }
    }

    public Volunteer getHighestPriorityCandidate() {
        // Clean high queue
        while (!highQ.isEmpty() && highQ.peek().assigned) {
            highQ.dequeue();
        }
        if (!highQ.isEmpty()) return highQ.peek();

        // Clean medium queue
        while (!medQ.isEmpty() && medQ.peek().assigned) {
            medQ.dequeue();
        }
        if (!medQ.isEmpty()) return medQ.peek();

        // Clean low queue
        while (!lowQ.isEmpty() && lowQ.peek().assigned) {
            lowQ.dequeue();
        }
        if (!lowQ.isEmpty()) return lowQ.peek();

        return null;
    }

    public void assignSpecific(Volunteer v, String task) {
        // If assigning from a queue remove it from that
        if (!v.assigned) {
            removeFromCorrectQueue(v);
        }
        undoStack.push(new Operation(Operation.Type.ASSIGN, v, v.taskAssigned, v.assigned));
        v.taskAssigned = task;
        v.assigned = true;
    }

    private void removeFromCorrectQueue(Volunteer v) {
        if (v.priority == Volunteer.Priority.HIGH) highQ.remove(v);
        else if (v.priority == Volunteer.Priority.MEDIUM) medQ.remove(v);
        else lowQ.remove(v);
    }

    public boolean removeVolunteerById(String id) {
        Volunteer v = masterList.findVolunteerById(id);
        if (v == null) return false;
        if (!v.assigned) removeFromCorrectQueue(v);
        undoStack.push(new Operation(Operation.Type.REMOVE, v, v.taskAssigned, v.assigned));
        return masterList.removeById(id);
    }

    public String undoLast() {
        Operation op = undoStack.pop();
        if (op == null) return null;
        if (op.type == Operation.Type.ASSIGN) {
            op.volunteer.taskAssigned = op.prevTask;
            op.volunteer.assigned = op.prevAssigned;
            if (!op.prevAssigned) reQueue(op.volunteer);
            return "Assignment";
        } else {
            masterList.append(op.volunteer);
            if (!op.prevAssigned) reQueue(op.volunteer);
            return "Removal";
        }
    }

    // Search and display wrapper methods
    public Volunteer searchById(String id) { return masterList.findVolunteerById(id); }
    public Volunteer searchByName(String name) { return masterList.findVolunteerByName(name); }
    public void displayAllInOrder() { masterList.displayAll(); }

    public void displayByPriority(Volunteer.Priority p) {
        Node<Volunteer> cur = masterList.getHead();
        boolean found = false;
        while (cur != null) {
            if (cur.data.priority == p) {
                System.out.println(cur.data);
                found = true;
            }
            cur = cur.next;
        }
        if (!found) System.out.println("No volunteers found.");
    }
}

