import java.io.*;
import java.util.*;

// -------------------- Room Inventory (Serializable) --------------------
class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void printInventory() {
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.getOrDefault("Single", 0));
        System.out.println("Double: " + inventory.getOrDefault("Double", 0));
        System.out.println("Suite: " + inventory.getOrDefault("Suite", 0));
    }
}

// -------------------- Persistence Service --------------------
class PersistenceService {

    private static final String FILE_NAME = "inventory.dat";

    // Save inventory to file
    public void save(RoomInventory inventory) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    // Load inventory from file
    public RoomInventory load() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return new RoomInventory();
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (RoomInventory) ois.readObject();

        } catch (Exception e) {
            System.out.println("Corrupted data. Starting fresh.");
            return new RoomInventory();
        }
    }
}

// -------------------- Main Class --------------------
public class Main {
    public static void main(String[] args) {

        PersistenceService persistence = new PersistenceService();

        // Load previous state (Recovery)
        RoomInventory inventory = persistence.load();

        // Display recovered or default inventory
        inventory.printInventory();

        // Simulate shutdown → save state
        persistence.save(inventory);
    }
}