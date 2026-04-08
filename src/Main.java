import java.util.*;

// -------------------- Reservation Class --------------------
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private boolean isActive;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.isActive = true;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void cancel() {
        this.isActive = false;
    }
}

// -------------------- Room Inventory --------------------
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRooms(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void increment(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// -------------------- Booking History --------------------
class BookingHistory {
    private Map<String, Reservation> reservationMap;

    public BookingHistory() {
        reservationMap = new HashMap<>();
    }

    public void addReservation(Reservation reservation) {
        reservationMap.put(reservation.getReservationId(), reservation);
    }

    public Reservation getReservation(String reservationId) {
        return reservationMap.get(reservationId);
    }
}

// -------------------- Cancellation Service --------------------
class CancellationService {

    // Stack to track rollback (LIFO)
    private Stack<String> rollbackStack;

    public CancellationService() {
        rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId,
                              BookingHistory history,
                              RoomInventory inventory) {

        // Validate reservation existence
        Reservation reservation = history.getReservation(reservationId);

        if (reservation == null) {
            System.out.println("Cancellation failed: Reservation not found.");
            return;
        }

        // Check if already cancelled
        if (!reservation.isActive()) {
            System.out.println("Cancellation failed: Already cancelled.");
            return;
        }

        // Perform cancellation
        reservation.cancel();

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        inventory.increment(reservation.getRoomType());

        // Output
        System.out.println("Booking cancelled successfully. Inventory restored for room type: "
                + reservation.getRoomType());

        // Display rollback history
        System.out.println("Rollback History (Most Recent First):");
        for (int i = rollbackStack.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + rollbackStack.get(i));
        }

        System.out.println("Updated " + reservation.getRoomType()
                + " Room Availability: "
                + inventory.getAvailability(reservation.getRoomType()));
    }
}

// -------------------- Main Class --------------------
public class Main {
    public static void main(String[] args) {

        // Setup inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRooms("Single", 5);

        // Setup booking history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed booking
        Reservation r1 = new Reservation("Single-1", "Abhi", "Single");
        history.addReservation(r1);

        // Setup cancellation service
        CancellationService service = new CancellationService();

        // Perform cancellation
        service.cancelBooking("Single-1", history, inventory);
    }
}