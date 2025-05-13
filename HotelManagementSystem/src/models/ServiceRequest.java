package models;

public class ServiceRequest {
    private int requestId;
    private int roomNumber;
    private String requestType;
    private String requestTime;
    private static int nextId = 1;

    // Constructor
    public ServiceRequest(String requestType, int roomNumber, String requestTime) {
        this.requestId = nextId++;
        this.requestType = requestType;
        this.roomNumber = roomNumber;
        this.requestTime = requestTime;
    }

    // Getters
    public int getRequestId() {
        return requestId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getRequestTime() {
        return requestTime;
    }

    // To String method for better display
    @Override
    public String toString() {
        return "ServiceRequest{" +
               "requestId=" + requestId +
               ", requestType='" + requestType + '\'' +
               ", roomNumber=" + roomNumber +
               ", requestTime='" + requestTime + '\'' +
               '}';
    }
}
