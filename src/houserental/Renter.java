//Daniel @19/11 @ 12:55pm
//check notes 
package houserental;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Renter extends User {
    private String RenterID;
    private int numberOfBooking;
    private ArrayList<Renter> Renters;
    private List<House> houseList = new ArrayList<House>();
     
    Renter(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, UserType type){
        //super = constructor to the user calss
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword,type); //2 here is the enum corresponding to renter in enum type ///check constructor calling super class 
                                                                                                                        /// constructor using diff param
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
                   
                
            }
        }
    }
        @Override
    public void writeBin(){   //This writes the ArrayList of Renters for later
        try{
        FileOutputStream i = new FileOutputStream("Renters.dat");
        ObjectOutputStream in = new ObjectOutputStream(i);
        in.writeObject(Renters);
        }catch (IOException e) {
            System.out.println(e);
    }
   }

    @Override
    public void readBin(){  //This reads the ArrayList of Renters
    
        try{
        FileInputStream i = new FileInputStream("Renters.dat");
        ObjectInputStream in = new ObjectInputStream(i);
            try {
                Renters = (ArrayList<Renter>) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Renter.class.getName()).log(Level.SEVERE, null, ex);}
        }catch (IOException e) {
            System.out.println(e);}
    }
    @Override
    public void login(String username, String Password){
        readBin(); //reading the ArrayList of Renters
        for(int i = 0; i < Renters.size(); i++){
            if(Renters.get(i).getUserName().equals(username) && Renters.get(i).getPassword().equals(Password)){
                System.out.println("logged in");
            }
        }
    }
    @Override
    public void signUp(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, String userID){
        readBin();
        for(int i = 0; i < Renters.size(); i++){
            if(Renters.get(i).getUserName().equals(newuserName) || Renters.get(i).getPassword().equals(newpassword)){ //making sure that the account doesn't already exist
                System.out.println("account already exists");
                return;
            }
        }
        Renters.add(new Renter(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword, getType())); //creating new account and adding it to the ArrayList
        writeBin();
    }   
}
