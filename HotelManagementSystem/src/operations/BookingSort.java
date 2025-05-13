package operations;

import data_structures.BookingLinkedList;
import models.Booking;

public class BookingSort {
    // Sort bookings by check-in date using Bubble Sort
    public static void sortBookingsByCheckInDate(BookingLinkedList bookingList) {
        if (bookingList.head == null) return;

        boolean swapped;
        do {
            swapped = false;
            BookingLinkedList.Node current = bookingList.head;
            while (current != null && current.next != null) {
                if (current.data.getCheckInDate().compareTo(current.next.data.getCheckInDate()) > 0) {
                    Booking temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}
