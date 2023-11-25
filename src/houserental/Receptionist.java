package houserental;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receptionist extends User{
    
    private static int bookingCounter = 0;
    
    private String ReceptionistID;
    private List<Booking> bookingList = new ArrayList<Booking>();
    private List<House> houseList = new ArrayList<House>();
   
     
     Receptionist(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, UserType type){
        //super = constructor to the user calss
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword,type);; //1 here is the enum corresponding to renter in enum type   
        //Same Note as in Renter class regarding the use of super class constructor
     }
     
    public void setReceptionistID(String ReceptionistID){
        this.ReceptionistID = ReceptionistID;
    }
    
    public String getReceptionistID(){
        return this.ReceptionistID;
    }
    
      
        public static String generateBookingID(House house, String renterID, Date startDate, Date endDate) { //House ID Creation creiteria 
                                                                                                           ////Why is this function static?     
        char renterInitial = renterID.isEmpty() ? '?' : renterID.charAt(0);
        
        String numberOfRoomsString = String.valueOf(House.getNumberOfRooms());
        String houseIDString = String.valueOf(House.getHouseID());
        
        String counterString = String.valueOf(bookingCounter++);
        char categoryInitial = house.getCategory().toString().charAt(0);
        char viewInitial = house.getView().toString().charAt(0);
        String datePart = String.valueOf(startDate.getTime() % 100000) + String.valueOf(endDate.getTime() % 100000);
        
        String bookingID = String.valueOf(renterInitial) + houseIDString + numberOfRoomsString + counterString +categoryInitial + viewInitial + datePart;
        return bookingID;
    }
     public void createBooking(String RenterID, int numberOfRooms, Enum category, Enum view, Date dateOfRental, Date endOfRental) {
         
        //i wanna check if a certain house is empty of not.
        
         int index = -1;
         for(int i = 0; i < houseList.size(); i++){
             if(houseList.get(i).getNumberOfRooms().equals(numberOfRooms) && ////Shows Error 'int can't be dereferenced`....
                     houseList.get(i).getCategory().equals(category) && 
                     houseList.get(i).getView().equals(view) &&
                     houseList.get(i).isRented() = 0)
             {
                 index = i;
                 break;
             }
             else{
                 System.out.println("House Category isn't found.");
             }
         }
        String bookingID = generateBookingID(houseList.get(i).houseID,RenterID,dateOfRental,endOfRental);
        Booking newBooking = new Booking(RenterID,numberOfRooms,category, view, dateOfRental, endOfRental);
        bookingList.add(newBooking);
        //saveBookingToFile(newBooking);
    }
    
    public void specifyRentalDetails(String BookingID){
        
          int index = -1 ;
       for(int i = 0 ; i <bookingList.size(); i++){
           if(bookingList.get(i).getBookingID().equals(BookingID)){
               index = i;
               break;
           }
       }
       if (index != -1) {
            System.out.println("Booking ID: " + bookingList.get(index).getBookingID());
        System.out.println("Renter: " + bookingList.get(index).getRenter().getName()); 
        System.out.println("House: " + bookingList.get(index).getHouse().getHouseID());
       }
       else{
             System.out.println("Booking not found.");
       }
    }
    
    public void selecteHouseCategoty(Enum Category){
        House newHouse = new House();
        newHouse.setCategory(Category); //changed line to use setters 
    }
    
   public void cancelBooking(String bookingID) {
       
        bookingList.removeIf(booking -> booking.getBookingID().equals(bookingID));
       // removeBookingFromFile(bookingID);
    }

    public double calculatePayment(String BookingID,Date dateOfRental,Date endOfRental){
        
        int index = -1 ;
       for(int i = 0 ; i <bookingList.size(); i++){
           if(bookingList.get(i).getBookingID().equals(BookingID)){
               index = i;
               break;
           }
       }
       if (index != -1) {
       long diffInMillies = Math.abs(endOfRental.getTime() - dateOfRental.getTime());
       long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
       return bookingList.get(index).claculateCost(diffInDays);
       }
       else {
            System.out.println("Booking not found.");
            return 0.0; 
        }
    }
    
      
    
      
      
    
    
        @Override
    public void writeBin(){   //This writes the ArrayList of Receptionists for later
        try{
        FileOutputStream i = new FileOutputStream("Receptionists.dat");
        ObjectOutputStream in = new ObjectOutputStream(i);
        in.writeObject(Receptionists);
        }catch (IOException e) {
            System.out.println(e);
    }
   }

    @Override
    public void readBin(){  //This reads the ArrayList of Receptionists
    
        try{
        FileInputStream i = new FileInputStream("Receptionists.dat");
        ObjectInputStream in = new ObjectInputStream(i);
            try {
                Receptionists = (ArrayList<Receptionist>) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);}
        }catch (IOException e) {
            System.out.println(e);}
    }
    @Override
    public void login(String username, String Password){
        readBin(); //reading the ArrayList of Receptionist
        for(int i = 0; i < Receptionists.size(); i++){
            if(Receptionists.get(i).getUserName().equals(username) && Receptionists.get(i).getPassword().equals(Password)){
                System.out.println("logged in");
            }
        }
    }
    @Override
    public void signUp(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, String userID){
        readBin();
        for(int i = 0; i < Receptionists.size(); i++){
            if(Receptionists.get(i).getUserName().equals(newuserName) || Receptionists.get(i).getPassword().equals(newpassword)){ //making sure that the account doesn't already exist
                System.out.println("account already exists");
                return;
            }
        }
        Receptionists.add(new Receptionist(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword, getType())); //creating new account and adding it to the ArrayList
        writeBin();
    }
}
