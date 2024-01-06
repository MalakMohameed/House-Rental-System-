package houserental;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin extends User implements Serializable
{
    public Admin(){}
    
    public Admin(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword){ //i am adding temporary paremeters for my signup functions feel free to change but take signup into consideration if possible
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword);
    }
    
    public void addHouse(House obj,ArrayList<House> houseList){
        
       houseList.add(obj);
    }
    
    
    
    public void removeHouse(String houseID, ArrayList<House> houseList){
        
        houseList.removeIf(house->house.getHouseID().equals(houseID));
    }
    public Category viewHouseCategory(String houseID, ArrayList<House> houseList){
        
        int index = houseList.indexOf(houseID);
        return houseList.get(index).getCategory();
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
   public void removeCustumer(Renter obj){
        Renters.remove(obj);
        obj=null;
             
   }
   public void addCustomer (Renter obj){
       Renters.add(obj);
   }
    

    @Override
    public boolean login(String username, String Password){
        if(Admins!=null){
        for(Admin e: Admins)
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
//        for(Admin r :Admins){
//            if(r.AdminID().equals(r.AdminID()))
//            {
//                System.out.println("User Already exists!");
//            }
//            
//        }
        Admins.add(this);
    }
}