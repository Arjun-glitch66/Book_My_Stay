import java.util.*;

// -------------------- Reservation --------------------
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

// -------------------- Thread-Safe Booking Queue --------------------
class BookingQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public synchronized void addRequest(Reservation r) {
        queue.add(r);
    }

    public synchronized Reservation getRequest() {
        return queue.poll();
    }
}

// -------------------- Room Inventory (Thread-Safe) --------------------
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public synchronized boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public synchronized void decrement(String roomType) {
        int count = inventory.get(roomType);
        inventory.put(roomType, count - 1);
    }

    public synchronized int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void printInventory() {
        System.out.println("Remaining Inventory:");
        System.out.println("Single: " + getAvailability("Single"));
        System.out.println("Double: " + getAvailability("Double"));
        System.out.println("Suite: " + getAvailability("Suite"));
    }
}

// -------------------- Room Allocation Service --------------------
class RoomAllocationService {

    private Set<String> allocatedRoomIds = new HashSet<>();

    public synchronized String allocateRoom(Reservation r, RoomInventory inventory) {

        String roomType = r.getRoomType();

        if (!inventory.isAvailable(roomType)) {
            return null;
        }

        // Generate unique room ID
        int count = 1;
        String roomId;

        do {
            roomId = roomType + "-" + count;
            count++;
        } while (allocatedRoomIds.contains(roomId));

        // Critical section
        allocatedRoomIds.add(roomId);
        inventory.decrement(roomType);

        return roomId;
    }
}

// -------------------- Booking Processor (Thread) --------------------
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomAllocationService service;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue,
                            RoomAllocationService service,
                            RoomInventory inventory) {
        this.queue = queue;
        this.service = service;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            Reservation r;

            synchronized (queue) {
                r = queue.getRequest();
            }

            if (r == null) break;

            String roomId = service.allocateRoom(r, inventory);

            if (roomId != null) {
                System.out.println("Booking confirmed for Guest: "
                        + r.getGuestName()
                        + ", Room ID: " + roomId);
            } else {
                System.out.println("Booking failed for Guest: "
                        + r.getGuestName());
            }
        }
    }
}

// -------------------- Main Class --------------------
public class Main {
    public static void main(String[] args) {

        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        // Add booking requests (simulating concurrent users)
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Double"));
        queue.addRequest(new Reservation("Kural", "Suite"));
        queue.addRequest(new Reservation("Subha", "Single"));

        // Create multiple threads
        Thread t1 = new BookingProcessor(queue, service, inventory);
        Thread t2 = new BookingProcessor(queue, service, inventory);

        // Start threads
        t1.start();
        t2.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final inventory
        inventory.printInventory();
    }
}