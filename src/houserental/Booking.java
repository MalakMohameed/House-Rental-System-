package houserental;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


///Removed Imports from same package. houserental.House and houserental.Receptionist ar both part of house rental package imported above 
/**
 *
 * @author noor amgad
 */
public class Booking implements Serializable{
    private String bookingID;
    private Receptionist receptionist;
    private Renter renter;
    private House rentedHouse;
    private Date startDate;
    private Date endDate;
    private int numberOfNights;
    private double totalCost;
    private static int count;
    private static ArrayList<Booking> Bookings = new ArrayList<>();
    public Booking(Receptionist receptionist, Renter renter, House rentedHouse,
                        Date startDate, Date endDate) {
        this.bookingID = generateBookingID(renter,rentedHouse);
        this.receptionist = receptionist;
        this.renter = renter;
        this.rentedHouse = rentedHouse;
        this.startDate = startDate;
        this.endDate = endDate;
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        this.numberOfNights = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        this.totalCost = calculateCost(numberOfNights);  ///calculate cost is overridable, check function.
}

    public String generateBookingID(Renter renter, House house){
        return renter.getRenterID() + house.getHouseID() + String.valueOf(count++) ;
    }
    public static ArrayList<Booking> getBookings(){
    return Bookings;
}
    public static void SerializeBooking(){
        try{
        FileOutputStream i = new FileOutputStream("Bookings.dat");
        ObjectOutputStream in = new ObjectOutputStream(i);
        in.writeObject(Bookings);
        in.close();
        i.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void DeserializeBooking(){
        try{
        FileInputStream i = new FileInputStream("Bookings.dat");
        ObjectInputStream in = new ObjectInputStream(i);
            try {
                Bookings = (ArrayList<Booking>) in.readObject();        
                in.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
                in.close();
            }
             
        }catch (IOException e) {
            System.out.println(e);
        }
    }
    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    
    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

   
    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    
    public House getRentedHouse() {
        return rentedHouse;
    }

    public void setRentedHouse(House rentedHouse) {
        this.rentedHouse = rentedHouse;
    }

   
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    
    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

 public double calculateCost(int numberOfNights) { //changed from int to long
      
        return numberOfNights * rentedHouse.getCostPerNight();  ///Definition of `costPerNight`////This is an attribute of the rented house Object.
    
 }
}