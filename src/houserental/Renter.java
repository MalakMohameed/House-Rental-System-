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
    
    Renter(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, UserType type){
        //super = constructor to the user calss
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword,type); //2 here is the enum corresponding to renter in enum type ///check constructor calling super class 
         this.setRenterID( User.generateUserId(newfirstName, newlastName, age, newphone, newemail, type));                                                                                                               /// constructor using diff param
         System.out.println("houserental.Renter.<init>()" + this.getRenterID());
//         Users.add();
//         Renters.add();
         //System.out.println("Renter Const-->>" + getUserByID(this.getRenterID()).getUserName());
    }
    public void setRenterID(String RenterID){
        this.RenterID = RenterID;
    }
    
    public String getRenterID(){
        return this.RenterID;
    }
    
    public void rateRent(int Rate){ ////Change this to have an enum of Rating and basically refactor into cleaner code.
        int index = -1;
        for(int i = 0 ; i <houseList.size(); i++){
           if(houseList.get(i).getUserID().equals(this.getRenterID()) && Rate >= 5){ //five stars or less
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
                if (booking.getRenter().getRenterID().equals(this.getRenterID())) {
                    bookingHistory.add(booking);
                    System.out.println("House ID: " + house.getHouseID());
                }
                   
                
            }
        }
    }
    
    
     public User getUserByID(String RenterID){ ///Error Handling
        
        for (Renter e : Renters){
            if(e.userID.equals(RenterID)){
                int index = Renters.indexOf(e);
                return Renters.get(index);
            }
        }
        return null;
        
    }
     public static Renter getUserByUserName(String UserName)
     {
         for (Renter e : Renters){
            if(e.getUserName().equals(UserName)){
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
    public void writeBin(){   //This writes the ArrayList of Renters for later
        try{
        FileOutputStream i = new FileOutputStream("Renters.dat");
        ObjectOutputStream in = new ObjectOutputStream(i);
        in.writeObject(Renters);
        }catch (IOException e) {
            System.out.println(e);
    }
            System.out.println("houserental.Renter.writeBin()");
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
            System.out.println(e);
        }
        System.out.println("houserental.Renter.readBin()");
    }
    
    @Override
    public void login(String username, String Password){
        readBin(); //reading the ArrayList of Renters
       if(Renters!=null){
        for(Renter e: Renters)
        {
            System.out.println(e.getRenterID() + " + " + e.getUserName());
            if(e.getUserName().equals(username) && e.getPassword().equals(Password))
            {
                System.out.println("logged in");
            }
        }
       }
    }

    
    
    @Override
    public void signUp(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, String userID){
        readBin();
        for(int i = 0; i < Renters.size(); i++){
            if(Renters.get(i).getUserName().equals(newuserName) || Renters.get(i).getPassword().equals(newpassword)){ //making sure that the account doesn't already exist
                System.out.println("account already exists");
               // return;
            }
        }
        System.out.println("******");
        Renters.add(new Renter(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword, getType())); //creating new account and adding it to the ArrayList
        writeBin();
    }   
    public void signUp(Renter renter){
        readBin();
        for(Renter r :Renters){
            if(r.getRenterID().equals(renter.getRenterID()))
            {
                System.out.println("User Already exists!");
            }
            
        }
        Renters.add(renter);
        writeBin();
    }
}
