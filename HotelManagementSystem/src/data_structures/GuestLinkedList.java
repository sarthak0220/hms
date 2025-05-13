package data_structures;




import models.Guest;

public class GuestLinkedList {
    public static class Node {
        public Guest data;
        public Node next;

        public Node(Guest data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;

    public GuestLinkedList() {
        this.head = null;
    }

    public void addGuest(Guest guest) {
        Node newNode = new Node(guest);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }



    public GuestLinkedList toList() {
        GuestLinkedList list = new GuestLinkedList();
        Node current = head;
        while (current != null) {
            list.addGuest(current.data);
            current = current.next;
        }
        return list;
    }

public void loadFromList(GuestLinkedList guests) {
    Node current = guests.head;
    while (current != null) {
        addGuest(current.data);
        current = current.next;
    }
}



    // Display all Guests
    public void displayGuests() {
        if (head == null) {
            System.out.println("No guests in the system.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // ✅ Update Guest by Email
    public boolean updateGuestByEmail(String email, Guest updatedGuest) {
        Node current = head;
        while (current != null) {
            if (current.data.getEmail().equalsIgnoreCase(email)) {
                current.data = updatedGuest;
                System.out.println("Guest updated: " + updatedGuest);
                return true;
            }
            current = current.next;
        }
        System.out.println("Guest with email " + email + " not found.");
        return false;
    }

    // ✅ Delete Guest by Email
    public boolean deleteGuestByEmail(String email) {
        if (head == null) return false;

        if (head.data.getEmail().equalsIgnoreCase(email)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.getEmail().equalsIgnoreCase(email)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
}
