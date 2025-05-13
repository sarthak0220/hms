package utils;

import models.Booking;
import models.Guest;
import models.Room;
import models.ServiceRequest;
import models.User;

import java.io.*;
// import java.util.ArrayList;
// import java.util.List;

import data_structures.GuestLinkedList;
import data_structures.RoomLinkedList;
import data_structures.BookingLinkedList;
import data_structures.ServiceLinkedList;

public class FileManager {

    private static final String HEADER = "+----+----------------------+-------------------------+\n"
                                       + "| ID | Name                 | Email                   |\n"
                                       + "+----+----------------------+-------------------------+";

    // Save list of guests in a formatted table
    public static void saveGuestsToFile(GuestLinkedList guestList, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(HEADER);
            GuestLinkedList.Node current = guestList.head;
            while (current != null) {
                writer.printf("| %-2d | %-20s | %-23s |\n", current.data.getId(), current.data.getName(), current.data.getEmail());
                current = current.next;
            }
            writer.println("+----+----------------------+-------------------------+");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    // Load guest data (not suitable for tabular parsing, just a placeholder)
    public static GuestLinkedList loadGuestsFromFile(String filename) {
        GuestLinkedList guestList = new GuestLinkedList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // boolean skip = true;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("|") && !line.contains("Name")) {
                    String[] parts = line.split("\\|");
                
                    String name = parts[2].trim();
                    String email = parts[3].trim();
                    guestList.addGuest(new Guest(name, email));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return guestList;
    }



    // Save Rooms
public static void saveRoomsToFile(RoomLinkedList rooms, String filename) {
    String header = "+--------+------------+---------+\n"
                  + "| Number | Type       | Price   |\n"
                  + "+--------+------------+---------+";
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        writer.println(header);
        RoomLinkedList.Node current = rooms.head;
        while (current != null) {
            writer.printf("| %-6d | %-10s | %-7.2f |\n", current.data.getRoomNumber(), current.data.getRoomType(), current.data.getPrice());
            current = current.next;
        }
        writer.println("+--------+------------+---------+");
    } catch (IOException e) {
        System.out.println("Error writing rooms to file.");
    }
}


public static RoomLinkedList loadRoomsFromFile(String filename) {
    RoomLinkedList roomList = new RoomLinkedList();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("|") && !line.contains("Type")) {
                String[] parts = line.split("\\|");
                int number = Integer.parseInt(parts[1].trim());
                String type = parts[2].trim();
                double price = Double.parseDouble(parts[3].trim());
                roomList.addRoom(new Room(number, type, price));
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + filename);
    }
    return roomList;
}



public static BookingLinkedList loadBookingsFromFile(String filename) {
    BookingLinkedList bookings = new BookingLinkedList();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("|") && !line.contains("BookingID")) {
                String[] parts = line.split("\\|");
                
                int guestId = Integer.parseInt(parts[2].trim());
                int roomNumber = Integer.parseInt(parts[3].trim());
                String checkIn = parts[4].trim();
                String checkOut = parts[5].trim();
                bookings.addBooking(new Booking(guestId, roomNumber, checkIn, checkOut));
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + filename);
    }
    return bookings;
}


// Save Bookings
public static void saveBookingsToFile(BookingLinkedList bookings, String filename) {
    String header = "+-----------+---------+------------+-------------+-------------+\n"
                  + "| BookingID | GuestID | RoomNumber | Check-In    | Check-Out   |\n"
                  + "+-----------+---------+------------+-------------+-------------+";
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        writer.println(header);
        BookingLinkedList.Node current = bookings.head;
        while (current != null) {
            writer.printf("| %-9d | %-7d | %-10d | %-11s | %-11s |\n",
                current.data.getBookingId(), current.data.getGuestId(), current.data.getRoomNumber(), current.data.getCheckInDate(), current.data.getCheckOutDate());
            current = current.next;
        }
        writer.println("+-----------+---------+------------+-------------+-------------+");
    } catch (IOException e) {
        System.out.println("Error writing bookings to file.");
    }
}

public static ServiceLinkedList loadServiceRequestsFromFile(String filename) {
    ServiceLinkedList requests = new ServiceLinkedList();
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("File does not exist: " + filename);
    } else {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("|") && !line.contains("RequestID")) {
                    String[] parts = line.split("\\|");
                   
                    String requestType = parts[2].trim();
                    int roomNumber = Integer.parseInt(parts[3].trim());
                    String requestTime = parts[4].trim();
                    ServiceRequest request = new ServiceRequest(requestType, roomNumber, requestTime);
                    requests.addServiceRequest(request);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            e.printStackTrace();
        }
    }
    return requests;
}

// Save Service Requests to file
public static void saveServiceRequestsToFile(ServiceLinkedList requests, String filename) {
    String header = "+-----------+-------------------+------------+----------------+\n"
                  + "| RequestID | Request Type      | RoomNumber | Request Time   |\n"
                  + "+-----------+-------------------+------------+----------------+";
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        writer.println(header);
        ServiceLinkedList.Node current = requests.head;
        while (current != null) {
            writer.printf("| %-9d | %-17s | %-10d | %-14s |\n",
                current.data.getRequestId(), current.data.getRequestType(), current.data.getRoomNumber(), current.data.getRequestTime());
            current = current.next;
        }
        writer.println("+-----------+-------------------+------------+----------------+");
        System.out.println("Service requests saved to file.");
    } catch (IOException e) {
        System.out.println("Error writing service requests to file.");
        e.printStackTrace();
    }
}

public static boolean saveUser(User user, String filename) {
    try {
        File file = new File(filename);
        if (!file.exists()) file.createNewFile();

        // Check if email already exists
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && parts[0].equalsIgnoreCase(user.getEmail())) {
                reader.close();
                return false; // email already exists
            }
        }
        reader.close();

        // Save new user
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(user.getEmail() + "," + user.getPassword());
        writer.newLine();
        writer.close();
        return true;
    } catch (IOException e) {
        System.out.println("Error saving user.");
        return false;
    }
}

public static boolean validateUser(String email, String password, String filename) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].equalsIgnoreCase(email) && parts[1].equals(password)) {
                return true;
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading user file.");
    }
    return false;
}




}


