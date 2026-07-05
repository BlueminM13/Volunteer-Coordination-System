import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VolunteerManager vm = new VolunteerManager();   // Initialize core manager
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Smart Event Volunteer Coordination System ---");
            System.out.println("1. Register Volunteer\n2. Assign Task by ID/Name\n3. Assign Task by Priority\n4. Remove Volunteer by ID\n5. Undo Last Action\n6. Search Volunteer\n7. View Volunteers by Specific Priority\n8. View All by Registration Order\n9. View All Volunteers by Priority\n10. Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    String id = readValidString(scanner, "Volunteer ID");
                    String name = readValidString(scanner, "Volunteer Name");
                    Volunteer.Priority p = readPriority(scanner);
                    if (vm.registerVolunteer(new Volunteer(id, name, p)))
                        System.out.println("SUCCESS: " + name + " Registered.");
                    else
                        System.out.println("ERROR: Duplicate ID.");
                }
                case "2" -> {
                    System.out.print("Enter volunteer id or name: ");
                    String query = scanner.nextLine().trim();
                    Volunteer v = vm.searchById(query);
                    if (v == null) v = vm.searchByName(query);

                    if (v != null) {
                        System.out.print("Enter task: ");
                        String task = scanner.nextLine().trim();
                        vm.assignSpecific(v, task);
                        System.out.println("SUCCESS: Task assigned to " + v.name);
                    } else {
                        System.out.println("ERROR: Volunteer not found.");
                    }
                }
                case "3" -> {
                    Volunteer candidate = vm.getHighestPriorityCandidate();
                    if (candidate != null) {
                        System.out.println("Available Volunteer: " + candidate.name + " - " + candidate.priority + " priority");
                        System.out.print("Enter task: ");
                        String task = scanner.nextLine().trim();
                        vm.assignSpecific(candidate, task);
                        System.out.println("Task successfully assigned to highest priority volunteer");
                    } else {
                        System.out.println("ERROR: No unassigned volunteers available.");
                    }
                }
                case "4" -> {
                    String id = readValidString(scanner, "ID to remove");
                    if (vm.removeVolunteerById(id)) System.out.println("SUCCESS: Removed.");
                    else System.out.println("ERROR: ID not found.");
                }
                case "5" -> {
                    String opDone = vm.undoLast();
                    if (opDone != null) System.out.println(opDone + " undone successfully.");
                    else System.out.println("Nothing to undo.");
                }
                case "6" -> {
                    System.out.println("press 1 to search by Volunteer ID or press 2 to search by name");
                    System.out.print("Choice: ");
                    String sub = scanner.nextLine().trim();
                    if (sub.equals("1") || sub.equals("2")) {
                        String term = readValidString(scanner, "Search Term");
                        Volunteer found = sub.equals("1") ? vm.searchById(term) : vm.searchByName(term);
                        System.out.println(found != null ? "FOUND: " + found : "NOT FOUND.");
                    } else System.out.println("Invalid choice.");
                }
                case "7" -> {   // Filtered view
                    Volunteer.Priority p = readPriority(scanner);
                    vm.displayByPriority(p);
                }
                case "8" -> {   // FIFO order view
                    System.out.println("\n--- Volunteers by Registration Order ---");
                    vm.displayAllInOrder();
                }
                case "9" -> {   // Priority grouped view
                    for (Volunteer.Priority p : Volunteer.Priority.values()) {
                        System.out.println("\n--- " + p + " Priority ---");
                        vm.displayByPriority(p);
                    }
                }
                case "10" -> running = false;
                default -> System.out.println("Error: Invalid input. Please enter a number from 1 to 10.");
            }
        }
        scanner.close();
    }

    // To ensure user input is not blank
    private static String readValidString(Scanner sc, String field) {
        while (true) {
            System.out.print("Enter " + field + ": ");
            String in = sc.nextLine().trim();
            if (!in.isEmpty()) return in;
            System.out.println("Error: Cannot be empty.");
        }
    }

    // To ensure valid priority input
    private static Volunteer.Priority readPriority(Scanner sc) {
        while (true) {
            System.out.print("Enter Priority (HIGH, MEDIUM, LOW): ");
            try {
                return Volunteer.Priority.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (Exception e) {
                System.out.println("Invalid priority.");
            }
        }
    }
}

