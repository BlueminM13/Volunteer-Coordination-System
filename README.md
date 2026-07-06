# 🎯 Smart Event Volunteer Coordination System

A console-based volunteer management system built entirely in **Java** using **custom-implemented data structures** — no built-in Java collections used. Designed for managing volunteers at large-scale events such as marathons, conferences, and exhibitions.

---

## 📌 Overview

This system handles the full lifecycle of volunteer coordination:
- Registering volunteers with unique IDs and priority levels
- Assigning tasks by specific volunteer or highest available priority
- Removing volunteers from the registry
- Undoing the most recent assignment or removal
- Searching and viewing volunteers by various criteria

All core data structures - **Singly Linked List**, **Queue**, and **Stack** were implemented from scratch to demonstrate a deep understanding of how these structures work internally.

---

## 🏗️ System Architecture

```
Smart-Event-Volunteer-Coordination-System/
│
├── Node.java                 # Generic node used by all data structures
├── SinglyLinkedList.java     # Master volunteer registry
├── CustomQueue.java          # Priority-based FIFO queues
├── CustomStack.java          # LIFO stack for undo operations
├── Volunteer.java            # Volunteer data model
├── VolunteerManager.java     # Core coordination logic
└── Main.java                 # Console UI and entry point
```

---

## 🧠 Data Structures Used

| Structure | Implementation | Purpose | Time Complexity |
|---|---|---|---|
| Singly Linked List | `SinglyLinkedList.java` | Master registry of all volunteers | Append O(1), Search O(n) |
| Custom Queue × 3 | `CustomQueue.java` | Separate FIFO queues for HIGH / MEDIUM / LOW priority | Enqueue O(1), Dequeue O(1) |
| Custom Stack | `CustomStack.java` | Undo history for assignments and removals | Push O(1), Pop O(1) |
| Generic Node | `Node.java` | Shared building block for all three structures | — |

---

## ⚙️ Algorithms Implemented

**Linear Search** — used in `findVolunteerById()`, `findVolunteerByName()`, and `removeById()`. A linked list has no index-based access, so linear traversal is the only valid approach. O(n) worst case.

**Priority-Based Greedy Selection** — `getHighestPriorityCandidate()` checks the HIGH queue first, then MEDIUM, then LOW. Always selects the most urgent volunteer available in O(1). Includes a stale-entry cleanup loop for robustness.

**FIFO Queue Assignment** — within each priority tier, volunteers are processed in the order they registered. Ensures fairness. Enqueue and dequeue both O(1).

**LIFO Stack Undo** — every assignment or removal pushes an `Operation` object onto the stack before the change is made. Undoing pops the stack and restores the previous state — covering both assignment and removal operations.

**Queue Traversal Removal** — a custom `remove()` method was added to support removing a volunteer from any position in the queue (not just the head), enabling correct behaviour when assigning by ID or name.

---

## 🖥️ Features

| # | Feature | Description |
|---|---|---|
| 1 | Register Volunteer | Add a volunteer with a unique ID, name, and priority level |
| 2 | Assign Task by ID/Name | Assign a task to a specific volunteer regardless of queue position |
| 3 | Assign Task by Priority | Auto-select and assign the highest available priority volunteer |
| 4 | Remove Volunteer by ID | Remove a volunteer from the system with full undo support |
| 5 | Undo Last Action | Reverse the most recent assignment or removal |
| 6 | Search Volunteer | Find a volunteer by ID or by name |
| 7 | View by Specific Priority | Display all volunteers of a given priority level |
| 8 | View All by Registration Order | Display all volunteers in the order they registered |
| 9 | View All by Priority | Display all volunteers grouped HIGH → MEDIUM → LOW |
| 10 | Exit | Close the application |

---

## 🚀 How to Run

**Prerequisites:** Java JDK 17 or later

**Step 1 — Clone the repository**
```bash
git clone https://github.com/your-username/smart-volunteer-system.git
cd smart-volunteer-system
```

**Step 2 — Compile**
```bash
javac *.java
```

**Step 3 — Run**
```bash
java Main
```

## 📊 Complexity Summary

| Operation | Best Case | Worst Case |
|---|---|---|
| Register volunteer | O(1) | O(n) — duplicate ID check |
| Assign by priority | O(1) | O(1) |
| Assign by ID/Name | O(1) | O(n) — search + queue removal |
| Remove by ID | O(1) | O(n) — search + list removal |
| Undo last action | O(1) | O(1) |
| Search by ID/Name | O(1) | O(n) |

---

## 🛠️ Tech Stack

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Principles-blue?style=for-the-badge)
![Data Structures](https://img.shields.io/badge/Data%20Structures-Custom%20Built-green?style=for-the-badge)
![Algorithms](https://img.shields.io/badge/Algorithms-Greedy%20%7C%20Linear%20Search-orange?style=for-the-badge)

- **Language:** Java 17+
- **Paradigm:** Object-Oriented Programming
- **Data Structures:** Custom Singly Linked List, Custom Queue, Custom Stack, Generic Nodes
- **Algorithms:** Linear Search, Greedy Priority Selection, FIFO Queue Assignment, LIFO Stack Undo, Queue Traversal Removal
- **IDE:** IntelliJ IDEA / Eclipse / VS Code

---
