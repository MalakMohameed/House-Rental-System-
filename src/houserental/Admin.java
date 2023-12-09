package houserental;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends User
{
    
    private String AdminID;
    public Admin(){};
    
    public Admin(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, UserType type){ //i am adding temporary paremeters for my signup functions feel free to change but take signup into consideration if possible
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword,type);
    }
    
    public void addHouse(House obj,ArrayList<House> houseList){
       houseList.add(obj);
    }
    
    public void removeHouse(String houseID, ArrayList<House> houseList){
        houseList.removeIf(house->house.getHouseID().equals(houseID));
        
    }
    public House.Category viewHouseCategory(String houseID, ArrayList<House> houseList){
        
        int index = houseList.indexOf(houseID);
        return (House.Category) houseList.get(index).getCategory();
    }
    
    public void addCustomer(String RenterID){ /////Add Customer by ID ???
        
        Renter cutomer = new Renter();////Param?
        
    }
    
    
    
    public ArrayList<Booking> viewBookingPerReceptionist(String ReceptionistID){
        
        Receptionist receptionist = null;
        receptionist = receptionist.getUserByID(ReceptionistID);
        return receptionist.getBookingList();
        
    }
    public User getUserByID(String UserID){ ///Error Handling
        
        for (Admin e : Admins){
            if(e.userID.equals(UserID)){
                int index = Admins.indexOf(e);
                return Admins.get(index);
            }
        }
        return null;
    }
   public void removeCustumer(User obj){
           Users.remove(obj);
           obj=null;
             
   }
   public void addCustomer (User obj){
       Users.add(obj);
   }
    
    
    ////User Class Methods Overriding

    @Override
    public boolean login(String username, String Password){
        readBin(); //reading the ArrayList of Admins
        for(int i = 0; i < Admins.size(); i++){
            if(Admins.get(i).getUserName().equals(username) && Admins.get(i).getPassword().equals(Password)){
                System.out.println("logged in");
                return true;
            }
        }
        return false;
    }

    @Override
    public void signUp(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, String userID){
        readBin();
        for(int i = 0; i < Admins.size(); i++){
            if(Admins.get(i).getUserName().equals(newuserName) || Admins.get(i).getPassword().equals(newpassword)){ //making sure that the account doesn't already exist
                System.out.println("account already exists");
                return;
            }
        }
        Admins.add(new Admin(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword, getType())); //creating new account and adding it to the ArrayList
        writeBin();
    }   
    public void signUp(){
        readBin();
        for(Admin r :Admins){
            if(r.AdminID.equals(AdminID))
            {
                System.out.println("User Already exists!");
            }
            
        }
        Admins.add(this);
        writeBin();
    }
}