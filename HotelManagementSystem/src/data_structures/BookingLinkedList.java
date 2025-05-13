package data_structures;


import models.Booking;

public class BookingLinkedList {
    public Node head;

    // Node class for the linked list
    public static class Node {
        public Booking data;
        public Node next;

        public Node(Booking data) {
            this.data = data;
            this.next = null;
        }

        // Getter method to access the booking
        public Booking getBooking() {
            return data;
        }
    }
    public BookingLinkedList toList() {
        BookingLinkedList list = new BookingLinkedList();
        Node current = head;
        while (current != null) {
            list.addBooking(current.data);
            current = current.next;
        }
        return list;
    }

public void loadFromList(BookingLinkedList bookings) {
    Node current = bookings.head;
    while (current != null) {
        addBooking(current.data);
        current = current.next;
    }
}


    // Constructor for BookingLinkedList
    public BookingLinkedList() {
        this.head = null;
    }

    // Add a booking to the linked list
    public void addBooking(Booking booking) {
        Node newNode = new Node(booking);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;  // Append to the end of the list
        }
    }

    // Display all bookings
    public void displayBookings() {
        Node current = head;
        while (current != null) {
            System.out.println(current.getBooking());  // Access booking via the getter
            current = current.next;
        }
    }

    public boolean deleteBookingById(int bookingId) {
        if (head == null) return false;
    
        if (head.data.getBookingId() == bookingId) {
            head = head.next;
            return true;
        }
    
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getBookingId() == bookingId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    

    public Booking searchBookingById(int bookingId) {
        Node current = head;
        while (current != null) {
            if (current.data.getBookingId() == bookingId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    
}
