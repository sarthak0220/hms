package operations;

import data_structures.BookingLinkedList;
import models.Booking;

public class BookingSearch {

    // Method to search for a booking by Email
    public static Booking searchByBookingEmail(BookingLinkedList bookingList, String email) {
        BookingLinkedList.Node current = bookingList.head;  // Access the head of the linked list

        // Traverse the linked list
        while (current != null) {
            if (current.getBooking().getEmail().equalsIgnoreCase(email)) {
                return current.getBooking();  // Access booking through the getter
            }
            current = current.next;  // Move to the next node
        }
        return null;  // Return null if booking not found
    }
}
