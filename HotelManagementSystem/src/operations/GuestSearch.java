package operations;

import data_structures.GuestLinkedList;
import models.Guest;

public class GuestSearch {
    // Search for a guest by ID
    public static Guest searchById(GuestLinkedList guestList, int id) {
        GuestLinkedList.Node current = guestList.head;
        while (current != null) {
            if (current.data.getId() == id) {
                return current.data;
            }
            current = current.next;
        }
        return null;  // Not found
    }

    // Search for a guest by Email
    public static Guest searchByEmail(GuestLinkedList guestList, String email) {
        GuestLinkedList.Node current = guestList.head;
        while (current != null) {
            if (current.data.getEmail().equalsIgnoreCase(email)) {
                return current.data;
            }
            current = current.next;
        }
        return null;  // Not found
    }
}
