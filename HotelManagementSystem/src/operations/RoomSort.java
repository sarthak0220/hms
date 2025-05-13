package operations;

import data_structures.RoomLinkedList;
import models.Room;

public class RoomSort {
    // Sort rooms by room number using Bubble Sort
    public static void sortRoomsByNumber(RoomLinkedList roomList) {
        if (roomList.head == null) return;

        boolean swapped;
        do {
            swapped = false;
            RoomLinkedList.Node current = roomList.head;
            while (current != null && current.next != null) {
                if (current.data.getRoomNumber() > current.next.data.getRoomNumber()) {
                    Room temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    // Sort rooms by price using Bubble Sort
    public static void sortRoomsByPrice(RoomLinkedList roomList) {
        if (roomList.head == null) return;

        boolean swapped;
        do {
            swapped = false;
            RoomLinkedList.Node current = roomList.head;
            while (current != null && current.next != null) {
                if (current.data.getPrice() > current.next.data.getPrice()) {
                    Room temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}
