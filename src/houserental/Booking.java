package houserental;
import java.util.Date;
import houserental.House;
import houserental.reciptionist;
/**
 *
 * @author noor amgad
 */
public class Booking {
    private String bookingID;
    private Receptionist receptionist;
    private Renter renter;
    private House rentedHouse;
    private Date startDate;
    private Date endDate;
    private int numberOfNights;
    private double totalCost;
  
    
     public Booking(String bookingID, Receptionist receptionist, Renter renter, House rentedHouse,
                         Date startDate, Date endDate, int numberOfNights,double totalCost) {
        this.bookingID = bookingID;
        this.receptionist = receptionist;
        this.renter = renter;
        this.rentedHouse = rentedHouse;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfNights = numberOfNights;
        this.totalCost = calculateCost(numberOfNights);
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

 public double calculateCost(int numberOfNights) {
      
        return numberOfNights * costPerNight; 
    }