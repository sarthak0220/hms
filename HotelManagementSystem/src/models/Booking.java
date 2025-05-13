package models;

// import java.util.List;

public class Booking {
    private int bookingId;
    private int guestId;
    private int roomNumber;
    private String checkInDate;
    private String checkOutDate;
    private static int nextId = 1;
    private String guestemail;
    // private Guest guest;
    // private Room room;

    // Constructor
    // public Booking(int bookingId, Guest guest, Room room, String checkInDate, String checkOutDate) {
    //     this.bookingId = bookingId;
    //     this.guest = guest;
    //     this.room = room;
    //     this.guestId = (guest != null) ? guest.getId() : -1;
    //     this.roomNumber = (room != null) ? room.getRoomNumber() : -1;
    //     this.checkInDate = checkInDate;
    //     this.checkOutDate = checkOutDate;
    // }


    public Booking(int guestId, int roomNumber, String checkInDate, String checkOutDate) {
        this.bookingId = nextId++;
        this.guestId = guestId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    
    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestEmail(String guestemail) {
        this.guestemail = guestemail;
    }
    public String getEmail() {
        return guestemail;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }


    // Setters
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    

    // toString for displaying booking info
    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Guest ID: " + guestId + ", Room Number: " + roomNumber + ", Check-in: " + checkInDate + ", Check-out: " + checkOutDate;
    }
}
