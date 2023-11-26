///Restored from local backup 
//Daniel 20/11 @11:00pm /// ///
///KAKAKAKAKAKAA
//ff to match all beanches 

package houserental;

import java.util.ArrayList;

public class HouseRental { 
    public static void main(String[] args) {
        UserType renterType = UserType.Renter;
        UserType AdminType = UserType.Admin;
        UserType ReceptionistType = UserType.Receptionist;
        
      Renter customer = new Renter("Daniel", "Michel", "D@gmail.com", "01115566999", 22,"Danioooll", "wawa",renterType);
      System.out.println("New Customer Added to list: " + customer.getFirstName()+ " UserName: "+ customer.getUserName()+ " User ID: " + customer.getRenterID());
        Renter customer1 = new Renter("Youssef", "Michel", "Y@gmail.com", "01115566420", 31,"joeee", "wawa55",renterType);
      customer.writeBin();
        System.out.println(customer.getAllUsers().size());
      
    }
    
}


///Last Edit 21/11 @9:42 ... /// 