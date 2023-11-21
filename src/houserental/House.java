package House;
import java.util.Date;


public class House {

 public enum Category{
        
        apartment, house, villa, penthouse, cottage, mansion, duplex, farmHouse, split_level
    }
    
    public enum View{
        mountain, garden, rooftop, pool, beach, pond, plain, farm, city
    }
    
    //calss object variables
    private Enum category; 
    private Enum view;
    private int numberOfRentals;
    private String houseID;
    private boolean rented;
    private Date dateOfRental;
    private Date endOfRental;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    //private Location location;
    private String Description;
    private int costPerNight;
    public int Rate;
    
    
    //Constructor

    public House(Enum category, Enum view, int numberOfRentals, String houseID, boolean rented, Date dateOfRental, Date endOfRental, int numberOfBedrooms, int numberOfBathrooms, String Description, int costPerNight, int Rate) {
        this.category = category;
        this.view = view;
        this.numberOfRentals = numberOfRentals;
        this.houseID = houseID;
        this.rented = rented;
        this.dateOfRental = dateOfRental;
        this.endOfRental = endOfRental;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.Description = Description;
        this.costPerNight = costPerNight;
        this.Rate = Rate;
    }

    public Enum getCategory() {
        return category;
    }

    public void setCategory(Enum category) {
        this.category = category;
    }

    public Enum getView() {
        return view;
    }

    public void setView(Enum view) {
        this.view = view;
    }

    public int getNumberOfRentals() {
        return numberOfRentals;
    }

    public void setNumberOfRentals(int numberOfRentals) {
        this.numberOfRentals = numberOfRentals;
    }

    public String getHouseID() {
        return houseID;
    }

    public void setHouseID(String houseID) {
        this.houseID = houseID;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Date getDateOfRental() {
        return dateOfRental;
    }

    public void setDateOfRental(Date dateOfRental) {
        this.dateOfRental = dateOfRental;
    }

    public Date getEndOfRental() {
        return endOfRental;
    }

    public void setEndOfRental(Date endOfRental) {
        this.endOfRental = endOfRental;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(int costPerNight) {
        this.costPerNight = costPerNight;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }
    
    public boolean rented(){
        return rented;
    }
}
