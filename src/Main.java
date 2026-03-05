
import java.util.*;
abstract class Room{
  protected int numberOfBeds;
  protected int squareFeet;
  protected double pricePerNight;

  Room(int numberOfBeds,int squareFeet,double pricePerNight){
    this.numberOfBeds=numberOfBeds;
    this.squareFeet=squareFeet;
    this.pricePerNight=pricePerNight;
  }
  abstract void displayRoomDetails();
}


class Single extends Room{
  Single() {
    super(1, 250, 1500.0);
  }

  void displayRoomDetails(){
    System.out.println("Beds: "+numberOfBeds);
    System.out.println("Square Feet: " +squareFeet);
    System.out.println("Price per night: " +pricePerNight);
    System.out.println("Available: 5");
  }
}
class Double extends Room{
  Double() {
    super(2, 400, 2500.0);
  }

  void displayRoomDetails(){
    System.out.println("Beds: "+numberOfBeds);
    System.out.println("Square Feet: " +squareFeet);
    System.out.println("Price per night: " +pricePerNight);
    System.out.println("Available: 3");
  }
}

class Suite extends Room{
  Suite() {
    super(3, 600, 3500.0);
  }

  void displayRoomDetails(){
    System.out.println("Beds: "+numberOfBeds);
    System.out.println("Square Feet: " +squareFeet);
    System.out.println("Price per night: " +pricePerNight);
    System.out.println("Available: 2");
  }
}
public class Main{
  public static void main(String[] args){
    Single ob1=new Single();
    ob1.displayRoomDetails();

    Double ob2 =new Double();
    ob2.displayRoomDetails();

    Suite ob3 =new Suite();
    ob3.displayRoomDetails();
  }
}
