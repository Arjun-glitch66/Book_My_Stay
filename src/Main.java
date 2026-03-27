
import java.util.*;
class roomInventory {
  HashMap<String, Integer> roomAvailablity;

  roomInventory() {
    roomAvailablity = new HashMap<>();
    roomAvailablity.put("Single", 5);
    roomAvailablity.put("Double", 3);
    roomAvailablity.put("Suite", 2);
  }
  HashMap<String, Integer> getRoomAvailablity () {
      return roomAvailablity;
  }
}
//USE CASE 5
class roomSearchService{
  void searchAvailableRooms(){
    roomInventory ob1 = new roomInventory();
    HashMap<String,Integer> availablity=ob1.getRoomAvailablity(); //Duplicate of main HashMap
    for(String room:availablity.keySet()){ //Runs from 0th till end, keyset return key
      if(availablity.get(room)>0) { // availablity.get("Single") returns the value =5 because availablity only has the key-value pair
        if (room.equals("Single")) {
          System.out.println("Single Room");
          System.out.println("Beds: 1");
          System.out.println("Size: 250 sqt");
          System.out.println("Availble rooms: " + availablity.get(room));
        }
        else if (room.equals("Double")) {
          System.out.println("Double Room");
          System.out.println("Beds: 2");
          System.out.println("Size: 500 sqt");
          System.out.println("Availble rooms: " + availablity.get(room));
        }
        else if (room.equals("Suite")) {
          System.out.println("Suite Room");
          System.out.println("Beds: 3");
          System.out.println("Size: 1000 sqt");
          System.out.println("Availble rooms: " + availablity.get(room));
        }
      }

    }
  }
}
public class Main {
  public static void main(String[] args) {
    roomSearchService ob2= new roomSearchService();
    ob2.searchAvailableRooms();
  }
}


