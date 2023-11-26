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
      System.out.println("New Customer Added to list: " + customer.getFirstName()+ " UserName: "+ customer.getUserName()+ " USer ID: " + customer.getUserID());
        
      customer.writeBin();
      
    }
    
}


///Last Edit 21/11 @9:42 ... /// 