package houserental;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

////Split enums and classes to seperate files Category.java , View.java and Location.java....Refer to UML 
public class House implements Serializable {


    //calss object variables
    private static ArrayList<House> houseList = new ArrayList<House>();
    private Category category; 
    private View view;
    private int numberOfRentals;
    private String houseID;
    private boolean rented;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private Location location;
    private String Description;
    private int costPerNight;
    private int Rate;
    
    
    //Constructor
    public House(){
        //Default Constructior
    }

    public House(Category category, View view, int numberOfRentals, boolean rented, int numberOfBedrooms, int numberOfBathrooms, String Description, int costPerNight, int Rate, String country, String city, String streetName, int zipCode, int buildingNumber, int floorNumber)
    {
        this.houseID = generateHouseID(country,city,zipCode);
        this.category = category;
        this.view = view;
        this.numberOfRentals = numberOfRentals;
        this.rented = rented;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.Description = Description;
        this.costPerNight = costPerNight;
        this.Rate = Rate;
        location = new Location(country,city,streetName,zipCode,buildingNumber,floorNumber);
    }
    
    public String generateDescription(){
        String description = this.getDescription();
        if(this.getDescription().isEmpty()){
            description = "Treat yourself to this " + this.getNumberOfRooms() + " Bedroom " + this.getCategory() + " that comes with an amazing " + this.getView() + " View!" + "\n Customer Stisfaction guaranteed.";
        }
        
        return description;
    }
    public static void SerializeHouse(){
        try{
        FileOutputStream i = new FileOutputStream("Houses.dat");
        ObjectOutputStream in = new ObjectOutputStream(i);
        System.out.println( houseList.toString());
        in.writeObject(houseList);
        in.close();
        i.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void DeserializeHouse(){
        try{
        FileInputStream i = new FileInputStream("Houses.dat");
        ObjectInputStream in = new ObjectInputStream(i);
            try {
               houseList = (ArrayList<House>) in.readObject();        
                in.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(House.class.getName()).log(Level.SEVERE, null, ex);
                in.close();
            }
             
        }catch (IOException e) {
            System.out.println(e);
        }
    }
    public String generateHouseID(String country,String city, int zipcode) {
        char initialCountry = country.charAt(0);
        char initialCity = city.charAt(0);
        String zip = String.valueOf(zipcode);
        return String.valueOf(initialCountry) + String.valueOf(initialCity) + zip;       
}
    public static House getHouseByID(String HouseID){
        for (House e : houseList){
            if(e.houseID.equals(HouseID)){
                int index = houseList.indexOf(e);
                return houseList.get(index);
            }
        }
        return null;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
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

    public int getNumberOfRooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfRooms(int numberOfBedrooms) {
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
