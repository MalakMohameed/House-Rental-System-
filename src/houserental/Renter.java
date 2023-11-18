package houserental;
import java.util.ArrayList;
import java.util.List;

public class Renter extends User {
    private String RenterID;
    private int numberOfBooking;
     private List<House> houseList = new ArrayList<House>();
    Renter(String FirstName, String LastName, int Age, String Phone, String Email){
        //super = constructor to the user calss
        super(FirstName, LastName, Age , Phone , Email , 2 ); //2 here is the enum corresponding to renter in enum type
    }
    public void setRenterID(String RenterID){
        this.RenterID = RenterID;
    }
    public String getRenterID(){
        return this.RenterID;
    }
    public void rateRent(int Rate){
        int index = -1;
        for(int i = 0 ; i <houseList.size(); i++){
           if(houseList.get(i).getRenterID().equals(this.RenterID) && Rate >= 5){ //five stars or less
               index = i;
               break;
           }else{
               System.out.println("");
           }
       }
    }
    public void viewBookingHistory() {
        ArrayList<Booking> bookingHistory = new ArrayList<>();
        for (House house : houseList) {
            ArrayList<Booking> houseBookings = house.getBookings();
            for (Booking booking : houseBookings) {
                if (booking.getRenter().getRenterID().equals(this.RenterID)) {
                    bookingHistory.add(booking);
                    System.out.println("House ID: " + house.getHouseID());
                }
                else{
                    continue;
                }
            }
        }
    }
}
