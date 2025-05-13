package data_structures;



import models.Room;

public class RoomLinkedList {
    public Node head;

    // Define the Node class inside RoomLinkedList
    public static class Node {
        public Room data;
        public Node next;

        public Node(Room data) {
            this.data = data;
            this.next = null;
        }

       
    }

       public RoomLinkedList toList() {
        RoomLinkedList list = new RoomLinkedList();
        Node current = head;
        while (current != null) {
            list.addRoom(current.data);
            current = current.next;
        }
        return list;
    }

public void loadFromList(RoomLinkedList rooms) {
    Node current = rooms.head;
    while (current != null) {
        addRoom(current.data);
        current = current.next;
    }
}


    // Add Room
    public void addRoom(Room room) {
        Node newNode = new Node(room);
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

    // Display all Rooms
    public void displayRooms() {
        if (head == null) {
            System.out.println("No rooms available.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // Update Room by Room Number
    public boolean updateRoom(int roomNumber, Room updatedRoom) {
        Node current = head;
        while (current != null) {
            if (current.data.getRoomNumber() == roomNumber) {
                current.data = updatedRoom;
                System.out.println("Room updated: " + updatedRoom);
                return true;
            }
            current = current.next;
        }
        System.out.println("Room with number " + roomNumber + " not found.");
        return false;
    }

    // Delete Room by Room Number
    public boolean deleteRoomByNumber(int roomNumber) {
        if (head == null) return false;
    
        if (head.data.getRoomNumber() == roomNumber) {
            head = head.next;
            return true;
        }
    
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getRoomNumber() == roomNumber) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
}
