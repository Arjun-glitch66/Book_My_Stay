import java.util.*;

// -------------------- Custom Exception --------------------
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

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

// -------------------- Room Inventory --------------------
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrement(String roomType) throws InvalidBookingException {
        int count = inventory.getOrDefault(roomType, 0);

        if (count <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }

        inventory.put(roomType, count - 1);
    }
}

// -------------------- Booking Validator --------------------
class BookingValidator {

    private static final Set<String> VALID_ROOM_TYPES =
            new HashSet<>(Arrays.asList("Single", "Double", "Suite"));

    public static void validate(Reservation reservation, RoomInventory inventory)
            throws InvalidBookingException {

        // Validate guest name
        if (reservation.getGuestName() == null || reservation.getGuestName().trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate room type (case-sensitive as per requirement)
        if (!VALID_ROOM_TYPES.contains(reservation.getRoomType())) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Validate availability
        if (!inventory.isAvailable(reservation.getRoomType())) {
            throw new InvalidBookingException("Room not available.");
        }
    }
}

// -------------------- Main Class --------------------
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();

        try {
            // Input
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            Reservation reservation = new Reservation(name, roomType);

            // Validation (Fail-Fast)
            BookingValidator.validate(reservation, inventory);

            // If valid, update inventory
            inventory.decrement(roomType);

            System.out.println("Booking successful for " + name);

        } catch (InvalidBookingException e) {
            // Graceful failure handling
            System.out.println("Booking failed: " + e.getMessage());
        }

        scanner.close();
    }
}