
import java.util.*;
class roomInventory{
  HashMap<String,Integer> roomAvailablity;

  roomInventory(){
    roomAvailablity = new HashMap<>();
    roomAvailablity.put("Single", 5);
    roomAvailablity.put("Double",3);
    roomAvailablity.put("Suite",2);
  }

  HashMap<String,Integer> getRoomAvailablity(){
    return roomAvailablity;
  }
}
public class Main {
  public static void main(String[] args) {
    roomInventory ob= new roomInventory();
    HashMap<String,Integer> store=ob.getRoomAvailablity();
    for (String room : store.keySet(){
      if (room.equals("Single")) {
        System.out.println("Single Room");
        System.out.println("Beds: 1");
        System.out.println("Size: 250 sqt");
        System.out.println("Availble rooms: " + store.get(room));
      }
    if (room.equals("Double")) {
      System.out.println("Double Room");
      System.out.println("Beds: 2");
      System.out.println("Size: 500 sqt");
      System.out.println("Availble rooms: " + store.get(room));
    }
    if (room.equals("Suite")) {
      System.out.println("Suite Room");
      System.out.println("Beds: 1");
      System.out.println("Size: 1000 sqt");
      System.out.println("Availble rooms: " + store.get(room));
    }
  }
}
}

