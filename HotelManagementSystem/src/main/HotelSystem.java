
package main;

import models.Guest;
import models.Room;
import models.Booking;
import data_structures.GuestLinkedList;
import data_structures.RoomLinkedList;
import data_structures.BookingLinkedList;
import data_structures.ServiceLinkedList;
import operations.GuestSearch;
import operations.RoomSearch;
import utils.FileManager;
import models.ServiceRequest;
import models.User;


import java.util.Scanner;

public class HotelSystem {

    private static GuestLinkedList guestList = new GuestLinkedList();
    private static RoomLinkedList roomList = new RoomLinkedList();
    private static BookingLinkedList bookingList = new BookingLinkedList();
    private static ServiceLinkedList ServiceLinkedList = new ServiceLinkedList();

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(emailRegex);
    }
    // User session management
    // This class is used to manage the user session
    public class UserSession {
        private static String loggedInEmail;
        private static String role;

        public static void setSession(String email, String userRole) {
            loggedInEmail = email;
            role = userRole;
        }

        public static String getLoggedInEmail() {
            return loggedInEmail;
        }

        public static String getRole() {
            return role;
        }

        public static void clearSession() {
            loggedInEmail = null;
            role = null;
        }
    }

    public static void main(String[] args) {

        // Load data from files
        guestList.loadFromList(FileManager.loadGuestsFromFile("guests.txt"));
        roomList.loadFromList(FileManager.loadRoomsFromFile("rooms.txt"));
        bookingList.loadFromList(FileManager.loadBookingsFromFile("bookings.txt"));
        ServiceLinkedList.loadFromList(FileManager.loadServiceRequestsFromFile("services.txt"));

        Scanner scanner = new Scanner(System.in);

        String userRole = "";
        while (true) {
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
            System.out.println("\n                                                     +-------------------------------Greetings, from Cervello!-----------------------------+");
            System.out.println("                                                     |                                                                                     |");

            System.out.println(
                    "                                                     |                                   Select your role:                                 |");
            System.out.println(
                    "                                                     |                                                                                     |");
            System.out.println(
                    "                                                     |                                       1. User                                       |");
            System.out.println(
                    "                                                     |                                       2. Staff                                      |");
            System.out.println(
                    "                                                     |                                       3. Exit                                       |");
            System.out.println(
                    "                                                     |                                                                                     |");
            System.out.println(
                    "                                                     +----------------------___________________________________________--------------------+");

            System.out.print("Enter choice: ");

            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            

            if (roleChoice == 1) {
                userRole = "user";
                break;
            } else if (roleChoice == 2) {
                userRole = "staff";
                break;
            } else if (roleChoice == 3) {
                System.out.println("Exiting system.");
                return;
            } else {
                System.out.println("Invalid choice. Try again.");
            }

        }

        boolean authenticated = false;
        while (!authenticated) {
           
            System.out.println(
                    "\n                                                     +-------------------------------Greetings, from Cervello!-----------------------------+");
            System.out.println(
"                                                     |                                                                                     |");

            System.out.println(
                    "                                                     |                                   Select SignUp/Login:                              |");
            System.out.println(
                    "                                                     |                                                                                     |");
            System.out.println(
                    "                                                     |                                       1. Login                                      |");
            System.out.println(
                    "                                                     |                                       2. Signup                                     |");
            System.out.println(
                    "                                                     |                                       3. Exit                                       |");
            System.out.println(
                    "                                                     |                                                                                     |");
            System.out.println(
                    "                                                     +----------------------___________________________________________--------------------+");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("\nEnter email: ");
            String email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Try again.\n");
                continue;
            }

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String filePath = userRole.equals("user") ? "users.txt" : "staff.txt";

            switch (choice) {
                case 1:
                    if (FileManager.validateUser(email, password, filePath)) {
                        authenticated = true;
                        UserSession.setSession(email, userRole);
                        // System.out.print("\033[H\033[2J");
                        //     System.out.flush();
                        System.out.println("\nLogin successful as " + UserSession.getLoggedInEmail() + "!\n");
                    } else {
                        System.out.println("Invalid credentials.\n");
                    }
                    break;
                case 2:
                    if (FileManager.saveUser(new User(email, password), filePath)) {
                        System.out.println("Signup successful! You are being logged in.\n");

                        if (FileManager.validateUser(email, password, filePath)) {
                            authenticated = true;
                            UserSession.setSession(email, userRole);
                            // System.out.print("\033[H\033[2J");
                            // System.out.flush();
                            System.out.println("Login successful as " + UserSession.getLoggedInEmail() + "!\n");
                        } else {
                            System.out.println("Invalid credentials.\n");
                        }
                        break;
                    } else {
                        System.out.println("Email already exists. Choose another.\n");
                    }
                    break;
                case 3:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        // Continue with the hospitality system based on role
       
        runHospitalitySystem(scanner, userRole);

    }

    public static void runHospitalitySystem(Scanner scanner, String role) {
        if (role.equals("staff")) {
            // Load staff dashboard or features
            int choice;

            do {
               System.out.println("\n"
        + "                                               +--------------------------------------------------+\n"
        + "                                               |             Hospitality Management System        |\n"
        + "                                               +--------------------------------------------------+\n"
        + "                                               |                                                  |\n"
        + "                                               |               1. Manage Guests                   |\n"
        + "                                               |               2. Manage Rooms                    |\n"
        + "                                               |               3. Manage Bookings                 |\n"
        + "                                               |               4. Manage Service Requests         |\n"
        + "                                               |               5. Exit                            |\n"
        + "                                               |__________________________________________________|\n");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        manageGuests(scanner);
                        break;
                    case 2:
                        manageRooms(scanner);
                        break;
                    case 3:
                        manageBookings(scanner);
                        break;
                    case 4:
                        manageServiceRequests(scanner);
                        break;
                    case 5:
                        System.out.println("Exiting system...");
                        break;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } while (choice != 5);

            scanner.close();
        } else if (role.equals("user")) {
            // Load user dashboard or features

            int choice;

            do {

                 
          System.out.println("\n"
        + "                                                                       +--------------------Welcome !------------------------+\n"
        + "                                                                       |                                                     |\n"
        + "                                                                       |               1. Make Booking                       |\n"
        + "                                                                       |               2. Make Service Requests              |\n"
        + "                                                                       |               3. Exit                               |\n"
        + "                                                                       |_____________________________________________________|\n");


                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        manageBookings(scanner);
                        break;
                    case 2:
                        manageServiceRequests(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting system...");
                        break;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } while (choice != 3);

            scanner.close();
        }
    }

    private static void manageGuests(Scanner scanner) {

        int choice;
        do {
    //         System.out.print("\033[H\033[2J");
    // System.out.flush();
           System.out.println("\n"
        + "                                               +------------------------+\n"
        + "                                               |      Manage Guests     |\n"
        + "                                               +------------------------+\n"
        + "                                               |  1. Add Guest          |\n"
        + "                                               |  2. Update Guest       |\n"
        + "                                               |  3. Delete Guest       |\n"
        + "                                               |  4. Search by Email    |\n"
        + "                                               |  5. Go Back            |\n"
        + "                                               +------------------------+");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addGuest(scanner);
                    break;
                case 2:
                    updateGuest(scanner);
                    break;
                case 3:
                    deleteGuest(scanner);
                    break;
                case 4:
                    searchGuest(scanner);
                    break;
                case 5:
                    System.out.println("Going back to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 5);
    }

    // Manage Rooms submenu
    private static void manageRooms(Scanner scanner) {

        int choice;
        do {
    //         System.out.print("\033[H\033[2J");
    // System.out.flush();
    
    System.out.println("\n"
        + "                                               +------------------------+\n"
        + "                                               |       Manage Rooms     |\n"
        + "                                               +------------------------+\n"
        + "                                               |  1. Add Room           |\n"
        + "                                               |  2. Update Room        |\n"
        + "                                               |  3. Delete Room        |\n"
        + "                                               |  4. Search by Number   |\n"
        + "                                               |  5. Go Back            |\n"
        + "                                               +------------------------+");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addRoom(scanner);
                    break;
                case 2:
                    updateRoom(scanner);
                    break;
                case 3:
                    deleteRoom(scanner);
                    break;
                case 4:
                    searchRoom(scanner);
                    break;
                case 5:
                    System.out.println("Going back to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 5);
    }

    // Manage Bookings submenu
    private static void manageBookings(Scanner scanner) {

        int choice;
        do {
    //         System.out.print("\033[H\033[2J");
    // System.out.flush();
    
    System.out.println("\n"
        + "                                               +---------------------------+\n"
        + "                                               |      Manage Bookings      |\n"
        + "                                               +---------------------------+\n"
        + "                                               |  1. Add Booking           |\n"
        + "                                               |  2. Update Booking        |\n"
        + "                                               |  3. Delete Booking        |\n"
        + "                                               |  4. Search by Booking ID  |\n"
        + "                                               |  5. Go Back               |\n"
        + "                                               +---------------------------+");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBooking(scanner);
                    break;
                case 2:
                    updateBooking(scanner);
                    break;
                case 3:
                    deleteBooking(scanner);
                    break;
                case 4:
                    searchBooking(scanner);
                    break;
                case 5:
                    System.out.println("Going back to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 5);
    }

    // Manage Service Requests submenu
    private static void manageServiceRequests(Scanner scanner) {

        int choice;
        do {
    //          System.out.print("\033[H\033[2J");
    // System.out.flush();
    
    System.out.println("\n"
        + "                                               +--------------------------------+\n"
        + "                                               |   Manage Service Requests      |\n"
        + "                                               +--------------------------------+\n"
        + "                                               |  1. Add Service Request        |\n"
        + "                                               |  2. View Service Requests      |\n"
        + "                                               |  3. Go Back                    |\n"
        + "                                               +--------------------------------+");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addServiceRequest(scanner);
                    break;
                case 2:
                    viewServiceRequests();
                    break;
                case 3:
                    System.out.println("Going back to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 3);
    }

    // Add Guest method
    private static void addGuest(Scanner scanner) {

        scanner.nextLine(); // Consume newline
        System.out.print("Enter Guest Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Guest Email: ");
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format. Try again.\n");
            return;
        }

        Guest newGuest = new Guest(name, email);
        guestList.addGuest(newGuest);
        FileManager.saveGuestsToFile(guestList.toList(), "guests.txt");

        System.out.println("Guest added successfully.");
    }

    // Update Guest method
    private static void updateGuest(Scanner scanner) {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Guest email to update: ");

        String email = scanner.nextLine();
        Guest guest = GuestSearch.searchByEmail(guestList, email);
        if (guest != null) {

            System.out.print("Enter new Name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new Email: ");
            String newEmail = scanner.nextLine();
            guest.setName(newName);
            guest.setEmail(newEmail);
            FileManager.saveGuestsToFile(guestList.toList(), "guests.txt");

            System.out.println("Guest updated successfully.");
        } else {
            System.out.println("Guest not found.");
        }
    }

    // Delete Guest method
    private static void deleteGuest(Scanner scanner) {
        System.out.print("Enter Guest email to delete: ");
        scanner.nextLine(); // Consume newline
        String email = scanner.nextLine();
        guestList.deleteGuestByEmail(email);
        FileManager.saveGuestsToFile(guestList.toList(), "guests.txt");

        System.out.println("Guest deleted successfully.");
    }

    // Search Guest by Email method
    private static void searchGuest(Scanner scanner) {
        System.out.print("Enter Guest email to search: ");
        scanner.nextLine(); // Consume newline
        String email = scanner.nextLine();
        Guest guest = GuestSearch.searchByEmail(guestList, email);
        // Guest guest = GuestLinkedList.displayGuests();
        if (guest != null) {
            System.out.println("Guest found: " + guest);
        } else {
            System.out.println("Guest not found.");
        }
    }

    // Add Room method
    private static void addRoom(Scanner scanner) {
        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Room Type: ");
        String roomType = scanner.nextLine();
        System.out.print("Enter Room Price: ");
        double price = scanner.nextDouble();

        Room newRoom = new Room(roomNumber, roomType, price);
        roomList.addRoom(newRoom);
        FileManager.saveRoomsToFile(roomList.toList(), "rooms.txt");
        System.out.println("Room added successfully.");
    }

    // Update Room method
    private static void updateRoom(Scanner scanner) {
        System.out.print("Enter Room Number to update: ");
        int roomNumber = scanner.nextInt();
        Room room = RoomSearch.searchByRoomNumber(roomList, roomNumber);
        if (room != null) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Room Type: ");
            String newRoomType = scanner.nextLine();
            System.out.print("Enter new Room Price: ");
            double newPrice = scanner.nextDouble();
            room.setRoomType(newRoomType);
            room.setPrice(newPrice);
            FileManager.saveRoomsToFile(roomList.toList(), "rooms.txt");
            System.out.println("Room updated successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }

    // Delete Room method
    private static void deleteRoom(Scanner scanner) {
        System.out.print("Enter Room Number to delete: ");
        int roomNumber = scanner.nextInt();
        roomList.deleteRoomByNumber(roomNumber);
        FileManager.saveRoomsToFile(roomList.toList(), "rooms.txt");
        System.out.println("Room deleted successfully.");
    }

    // Search Room method
    private static void searchRoom(Scanner scanner) {
        System.out.print("Enter Room Number to search: ");
        int roomNumber = scanner.nextInt();
        Room room = RoomSearch.searchByRoomNumber(roomList, roomNumber);
        if (room != null) {
            System.out.println("Room found: " + room);
        } else {
            System.out.println("Room not found.");
        }
    }
    public static boolean isValidDate(String date) {
    // Match pattern dd-MM-yyyy (with zero-padding)
    if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) return false;

    try {
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Check valid ranges
        if (month < 1 || month > 12) return false;

        // Days in each month
        int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // Handle leap year for February
        if (month == 2 && isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        return day >= 1 && day <= daysInMonth[month - 1];
    } catch (Exception e) {
        return false;
    }
}

private static boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
}

public static boolean isCheckOutAfterCheckIn(String checkIn, String checkOut) {
    if (!isValidDate(checkIn) || !isValidDate(checkOut)) {
        return false; // One or both dates are invalid
    }

    String[] inParts = checkIn.split("-");
    String[] outParts = checkOut.split("-");

    int inDay = Integer.parseInt(inParts[0]);
    int inMonth = Integer.parseInt(inParts[1]);
    int inYear = Integer.parseInt(inParts[2]);

    int outDay = Integer.parseInt(outParts[0]);
    int outMonth = Integer.parseInt(outParts[1]);
    int outYear = Integer.parseInt(outParts[2]);

    

    // Same month - compare days
    return outDay >= inDay && outMonth >= inMonth && outYear >= inYear;
}



    // Add Booking method
    private static void addBooking(Scanner scanner) {

        System.out.print("Enter email for the booking: ");
        scanner.nextLine(); // Consume newline
        String email = scanner.nextLine();
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format. Try again.\n");
            return;
        }

        System.out.print("Enter Room Number for the booking: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Check-in Date: ");
        String checkInDate = scanner.nextLine();
        
        System.out.print("Enter Check-out Date: ");
        String checkOutDate = scanner.nextLine();
       

        if (!isValidDate(checkInDate) || !isValidDate(checkOutDate)) {
            System.out.println("Invalid date format. Try again.\n");
            return;
        }
        if (!isCheckOutAfterCheckIn(checkInDate, checkOutDate)) {
            System.out.println("Check-out date should be after Check-in date. Try again.\n");
            return;
        }

        Guest guest = GuestSearch.searchByEmail(guestList, email);
        Room room = RoomSearch.searchByRoomNumber(roomList, roomNumber);

        if (guest != null && room != null) {
            Booking newBooking = new Booking(guest.getId(), room.getRoomNumber(), checkInDate, checkOutDate);
            newBooking.setGuestEmail(email);
            bookingList.addBooking(newBooking);
            FileManager.saveBookingsToFile(bookingList.toList(), "bookings.txt");
            System.out.println("Booking added successfully.");
        } else {
            System.out.println("Invalid Guest email or Room Number.");
        }
    }

    // Update Booking method
    private static void updateBooking(Scanner scanner) {
        System.out.print("Enter Booking ID to update: ");
        int bookingId = scanner.nextInt();
        Booking booking = bookingList.searchBookingById(bookingId);
        if (booking != null) {
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Room Number: ");
            int newRoomNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new Check-in Date: ");
            String newCheckInDate = scanner.nextLine();
            System.out.print("Enter new Check-out Date: ");
            String newCheckOutDate = scanner.nextLine();
            if (!isValidDate(newCheckInDate) || !isValidDate(newCheckOutDate)) {
                System.out.println("Invalid date format. Try again.\n");
                return;
            }
            if (!isCheckOutAfterCheckIn(newCheckInDate, newCheckOutDate)) {
                System.out.println("Check-out date should be after Check-in date. Try again.\n");
                return;
            }
            booking.setRoomNumber(newRoomNumber);
            booking.setCheckInDate(newCheckInDate);
            booking.setCheckOutDate(newCheckOutDate);
            FileManager.saveBookingsToFile(bookingList.toList(), "bookings.txt");
            System.out.println("Booking updated successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    // Delete Booking method
    private static void deleteBooking(Scanner scanner) {
        System.out.print("Enter Booking ID to delete: ");
        int bookingId = scanner.nextInt();
        bookingList.deleteBookingById(bookingId);
        FileManager.saveBookingsToFile(bookingList.toList(), "bookings.txt");
        System.out.println("Booking deleted successfully.");
    }

    // Search Booking method
    private static void searchBooking(Scanner scanner) {
        System.out.print("Enter Booking ID to search: ");
        int bookingId = scanner.nextInt();
        Booking booking = bookingList.searchBookingById(bookingId);
        if (booking != null) {
            System.out.println("Booking found: " + booking);
        } else {
            System.out.println("Booking not found.");
        }
    }

    // Add Service Request method
    private static void addServiceRequest(Scanner scanner) {

        System.out.print("Enter request: ");
        scanner.nextLine(); // Consume newline
        String requestType = scanner.nextLine();

        System.out.print("Enter Room Number: ");
        int roomNumber = scanner.nextInt();

        scanner.nextLine(); // Consume newline
        System.out.print("Enter Request Time (00-23 Hrs): ");
        String requestTime = scanner.nextLine();
        if(Integer.parseInt(requestTime)>=24){
            System.out.println("Invalid time format. Try again.\n");
            return;
        }

        // Assuming RoomSearch is implemented properly
        Room room = RoomSearch.searchByRoomNumber(roomList, roomNumber);

        if (room != null) {
            // Create new service request
            ServiceRequest serviceRequest = new ServiceRequest(requestType, roomNumber, requestTime);
            ServiceLinkedList.addServiceRequest(serviceRequest);

            // Debugging: Print the service request added
            System.out.println("Added Service Request: " + serviceRequest);

            // Save the updated service queue to file
            ServiceLinkedList serviceRequests = ServiceLinkedList.toList();
            System.out.println("Saving updated service requests to file...");
            FileManager.saveServiceRequestsToFile(serviceRequests, "services.txt");
            System.out.println("Service request added successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }

    // View Service Requests method
    private static void viewServiceRequests() {
        System.out.println("All Service Requests: ");
        ServiceLinkedList.viewAllServiceRequests();
    }
}

