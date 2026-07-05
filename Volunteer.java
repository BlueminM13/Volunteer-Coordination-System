public class Volunteer {
    public final String volunteerId;
    public String name;
    public String taskAssigned;
    public Priority priority;
    public boolean assigned;
    public enum Priority { HIGH, MEDIUM, LOW }  // Enum to define fixed priority levels

    // To create a new volunteer record
    public Volunteer(String id, String name, Priority priority) {
        this.volunteerId = id; this.name = name; this.priority = priority;
        this.taskAssigned = "None"; this.assigned = false;
    }

    // Returns the volunteer's information
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Priority: %s | Task: %s",
                volunteerId, name, priority, taskAssigned);
    }
}
