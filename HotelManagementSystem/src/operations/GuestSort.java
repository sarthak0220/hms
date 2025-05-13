package operations;

import data_structures.GuestLinkedList;
import models.Guest;

public class GuestSort {
    // Sort guests by name using Bubble Sort
    public static void sortGuestsByName(GuestLinkedList guestList) {
        if (guestList.head == null) return;

        boolean swapped;
        do {
            swapped = false;
            GuestLinkedList.Node current = guestList.head;
            while (current != null && current.next != null) {
                if (current.data.getName().compareTo(current.next.data.getName()) > 0) {
                    // Swap the data of the two guests
                    Guest temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}
