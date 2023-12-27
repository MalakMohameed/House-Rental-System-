//Daniel @19/11 @ 12:55pm
//check notes 
package houserental;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Renter extends User implements Serializable{
    private String RenterID;
    private int numberOfBooking; 
    private List<House> houseList = new ArrayList<House>();
   
    
    Renter(){}
    
    Renter(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword){
        //super = constructor to the user calss
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword); //2 here is the enum corresponding to renter in enum type ///check constructor calling super class 
        // this.setRenterID( User.generateUserId(newfirstName, newlastName, age, newphone, newemail, type));                                                                                                               /// constructor using diff param    
//         Users.add();
//         Renters.add();
        // System.out.println("Renter Const-->>" + getUserByID(this.getRenterID()).getUserName());
    }
    public void setRenterID(String RenterID){
        this.RenterID = RenterID;
    }
    
    public String getRenterID(){
        return this.RenterID;
    }
    
//    public void rateRent(int Rate){ ////Change this to have an enum of Rating and basically refactor into cleaner code.
//        int index = -1;
//        for(int i = 0 ; i <houseList.size(); i++){
//           if(houseList.get(i).getUserID().equals(this.getRenterID()) && Rate >= 5){ //five stars or less
//               index = i;
//               break;
//           }else{
//               System.out.println("");
//           }
//       }
//    }
//    public void viewBookingHistory() {
//        
//        ArrayList<Booking> bookingHistory = new ArrayList<>();
//        for (House house : houseList) {
//            ArrayList<Booking> houseBookings = house.getBookings();
//            for (Booking booking : houseBookings) {
//                if (booking.getRenter().getRenterID().equals(this.getRenterID())) {
//                    bookingHistory.add(booking);
//                    System.out.println("House ID: " + house.getHouseID());
//                }
//                   
//                
//            }
//        }
//    }
    
    
     public Renter getUserByID(String RenterID){ ///Error Handling
        
        for (Renter e : Renters){
            if(e.userID.equals(RenterID)){
                int index = Renters.indexOf(e);
                return Renters.get(index);
            }
        }
        return null;
        
    }
     public  Renter getUserByUserName(String UserName)
     {
         for (Renter e : Renters){
            if(e.userName.equals(UserName)){
                int index = Renters.indexOf(e);
                        System.out.println("User with UserName: "+ UserName + "Found!");
                return Renters.get(index);
            }
        }
          System.out.println("User with UserName: "+ UserName + " WAS NOT Found!");
        return null;
     }
    
     public ArrayList<Renter> getAllUsers(){
         if(Renters.isEmpty()) { System.out.println("Empty List");return null;}
         else {
             return this.Renters;
         }
     }
     
    
    @Override
    public boolean login(String username, String Password){
       if(Renters!=null){
        for(Renter e: Renters)
        {
            if(e.userName.equals(username) && e.password.equals(Password))
            {
                return true;
            }
        }
               return false;
       }
              return false;
    }
    
    @Override
    public void signUp(){
       // readBin();
        for(Renter r :Renters){
            System.out.println(r.toString() + userID);
            if(r.getUserID().equals(userID))
            {
                System.out.println("User Already exists!");
            }       
        }
        Renters.add(this);
        //writeBin();
    }

   
    
}
