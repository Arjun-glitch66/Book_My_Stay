import java.util.*;

// -------------------- Reservation Class --------------------
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// -------------------- Booking History --------------------
class BookingHistory {

    // List to maintain confirmed bookings in order
    private List<Reservation> bookingList;

    public BookingHistory() {
        bookingList = new ArrayList<>();
    }

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        bookingList.add(reservation);
    }

    // Retrieve all bookings
    public List<Reservation> getAllBookings() {
        return bookingList;
    }
}

// -------------------- Booking Report Service --------------------
class BookingReportService {

    // Generate and display report
    public void generateReport(List<Reservation> reservations) {

        System.out.println("Booking History Report");

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName()
                    + ", Room Type: " + r.getRoomType());
        }
    }
}

// -------------------- Main Class --------------------
public class Main {
    public static void main(String[] args) {

        // Initialize booking history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getAllBookings());
    }
}