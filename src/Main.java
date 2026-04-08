
import java.util.*;
class Reservation {
    private String guestName;
    private String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
    String getGuestName() {
        return guestName;
    }
    String getRoomType() {
        return roomType;
    }
}
class BookingRequestQueue{
    private Queue<Reservation> requestQueue;

    BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }
    void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }
    Reservation getNextRequest() {
        return requestQueue.poll();
    }
    boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}
//USE CASE 5
public class Main {
  public static void main(String[] args) {
    System.out.println("Booking request queue");
    BookingRequestQueue bookingQueue = new BookingRequestQueue();
    Reservation r1=new Reservation("Abhi","Single");
    Reservation r2=new Reservation("Ram","Double");
    Reservation r3=new Reservation("Dany","Suite");
    bookingQueue.addRequest(r1);
    bookingQueue.addRequest(r2);
    bookingQueue.addRequest(r3);
    while (bookingQueue.hasPendingRequests()) {
        Reservation r = bookingQueue.getNextRequest();
        System.out.println("Guest" +r.getGuestName());
        System.out.println("Room" +r.getRoomType());
    }
  }
}


