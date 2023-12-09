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
    public String getAdminID(){
        return this.AdminID;
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
<<<<<<< HEAD

    @Override
    public boolean login(String username, String Password){
=======
    @Override
    public void writeBin(){   //This writes the ArrayList of Admins for later
        try{
        FileOutputStream i = new FileOutputStream("Admins.bin");
        ObjectOutputStream in = new ObjectOutputStream(i);
        in.writeObject(Admins);
        }catch (IOException e) {
            System.out.println(e);
    }
            System.out.println("houserental.Admin.writeBin()");
    }

    @Override
    public void readBin(){  //This reads the ArrayList of Admins
    
        try{
        FileInputStream i = new FileInputStream("Admins.bin");
        ObjectInputStream in = new ObjectInputStream(i);
            try {
                Admins = (ArrayList<Admin>) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);}
        }catch (IOException e) {
            System.out.println(e);}
    }
    @Override
    public void login(String username, String Password){
>>>>>>> 57805290eda2d072a87beea37e473715098ad7d2
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
<<<<<<< HEAD
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
=======
    }

    public void signUp(Admin admin){
        readBin();
        for(Admin r :Admins){
            if(r.getAdminID().equals(r.getAdminID()))
            {
                System.out.println("User Already exists!");
            }
        }
        System.out.println(admin.AdminID);
        Admins.add(admin);
>>>>>>> 57805290eda2d072a87beea37e473715098ad7d2
        writeBin();
    }
}