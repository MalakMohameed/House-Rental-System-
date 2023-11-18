package houserental;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receptionist extends User{
    
    private String ReceptionistID;
     private List<Booking> bookingList = new ArrayList<Booking>();
     private List<House> houseList = new ArrayList<House>();
     
     Receptionist(String FirstName, String LastName, int Age, String UserName, String Password, String Email){
        //super = constructor to the user calss
        super(FirstName, LastName, Age, UserName, Password , Email , 1 ); //1 here is the enum corresponding to renter in enum type   
     }
     
    public void setReceptionistID(String ReceptionistID){
        this.ReceptionistID = ReceptionistID;
    }
    public String getReceptionistID(){
        return this.ReceptionistID;
    }
    
      private static int bookingCounter = 0;
      public static String generateBookingID(House house, String renterID, Date startDate, Date endDate) {
        char renterInitial = renterID.isEmpty() ? '?' : renterID.charAt(0);
        String houseIDString = String.valueOf(house.houseId);
        String numberOfRoomsString = String.valueOf(house.numberOfRooms);
        String counterString = String.valueOf(bookingCounter++);
        char categoryInitial = house.category.toString().charAt(0);
        char viewInitial = house.view.toString().charAt(0);
        String datePart = String.valueOf(startDate.getTime() % 100000) + String.valueOf(endDate.getTime() % 100000);
        
        String bookingID = String.valueOf(renterInitial) + houseIDString + numberOfRoomsString + counterString +categoryInitial + viewInitial + datePart;
        return bookingID;
    }
     public void createBooking(String RenterID, int numberOfRooms, Enum category, Enum view, Date dateOfRental, Date endOfRental) {
         //i wanna check if a certain house is empty of not
         int index = -1;
         for(int i = 0; i < houseList.size(); i++){
             if(houseList.get(i).getnumberOfRooms.equals(numberOfRooms) && 
                     houseList.get(i).category.equals(category) && 
                     houseList.get(i).getview.equals(view) &&
                     houseList.get(i).empty = 0)
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
        newHouse.setCategory = Category;
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
}
