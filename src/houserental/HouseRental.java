///Restored from local backup 
//Daniel 20/11 @11:00pm /// ///
///KAKAKAKAKAKAA
//ff to match all beanches 

package houserental;

import java.util.ArrayList;
import java.util.Scanner;

public class HouseRental{ 
    
    
        static UserType renterType = UserType.Renter;
        static UserType AdminType = UserType.Admin;
        static UserType ReceptionistType = UserType.Receptionist;
    public static void adminScreen(Scanner sc){
        System.out.println("what would you like to do\n1. add a house\n2. remove a house\n3. view a house category\n4. add a customer\n5. remove a customer\n6. view all users ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch(choice){
            case 1:
                System.out.println("House added");

            case 2:
                System.out.println("house removed");

            case 3:
                System.out.println("house viewed");

            case 4:
                System.out.println("customer added");

            case 5:
                System.out.println("customer removed");

            case 6:
                System.out.println("users viewed");
        }


    }
    public static void resetScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    public static void mainMenu()
    {
<<<<<<< HEAD
        
        System.out.println("1.Create Account");
        System.out.println("2.Login ");   
    }
    public static void RenterMenu(Renter renter){
        System.out.println("========Rneter Screen========");
        System.out.println("What would you like to do?\n1.Rate an old experience\n2.View bookings history\n Enter:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
         switch(choice){
            case 1:
                int rating = input.nextInt();
                //instead of renter should be the users object
                renter.rateRent(rating);
                System.out.println("Thank you for your feedback :)!!");
                break;
            case 2:
                //instead of renter should be the users object
                renter.viewBookingHistory();
                break;
            default:
                System.out.println("Invalid input.\n");
        }
    }
    public static void ReceptionistMenu( Receptionist receptionist)
    {

        System.out.println( "========Receptionist screen========" );
        System.out.println("what do you want to do?\n1.Create booking\n2.Cancel booking\n3.Calculate payment");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
         switch(choice){
            case 1:
                House house = new House();
                Renter renter = new Renter();
                //Receptionist receptionist = new Receptionist();
                house.getCategory();
                System.out.println("what's your choice");
                int catChoice =input.nextInt();
                house.getView();
                System.out.println("what's your choice");
                int viewChoice =input.nextInt();
                System.out.println("How many nights?");
                int numOfNights=input.nextInt();
                System.out.println("Date?");
                String DateOfRental=input.next();
                System.out.println("End of rental? ");
                String EndOfRental=input.next();
                 System.out.println("Number of Rooms? "); 
                int NumberOfRooms=input.nextInt();
               // Receptionist.createBooking(renter,house.getHouseID(),numOfNights,catChoice,viewChoice,DateOfRental,EndOfRental,NumberOfRooms);



                break;
            case 2:
                //Booking booking= new Booking();
                //Receptionist.cancelBooking(booking.getBookingID);
                break;
            case 3 :

            default:
                System.out.println("Invalid input.\n");
    }
    }
    public static void signUp(Scanner sc, int user )
=======

        System.out.println("1.Create Account");
        System.out.println("2.Login ");   
    }
    
    public static void signUp(Scanner sc,int user)
>>>>>>> 57805290eda2d072a87beea37e473715098ad7d2
    {
        
         resetScreen();
        System.out.println("========Welcome To the House Rental System========");
        System.out.println("Enter First Name: ");
        String FirstName = sc.next();
        System.out.println("Enter Last Name: ");
        String LastName = sc.next();
        System.out.println("Enter Your Age: ");
        int userAge = sc.nextByte();
        System.out.println("Enter A Valid E-mail Address: ");
        String EmailAddr = sc.next();
        ///Verify Email
        if(!User.isValidEmail(EmailAddr)){
            System.out.println("Invalid Email Address");
            return;
        }
        else{
            System.out.println("Enter a Phone Number:");
            String phoneNO = sc.next();
            System.out.println("Create a Username for your Account: ");
            String UserName = sc.next();
            System.out.println("Now Create a Strong PassWord for your Account:");
            String usrPassword = sc.next();
            switch(user){
                case 1:
                    Renter signUpUSR1 = new Renter(FirstName, LastName, EmailAddr, phoneNO,userAge, UserName, usrPassword, renterType);
//                  signUpUSR.signUp(FirstName, LastName, UserName, phoneNO, userAge, UserName, usrPassword, signUpUSR.getUserID());
<<<<<<< HEAD
                    signUpUSR1.signUp();
                    User.writeBin();
                   
=======
                    signUpUSR1.signUp(signUpUSR1);
>>>>>>> 57805290eda2d072a87beea37e473715098ad7d2
                    break;
                case 2:
                    Receptionist signUpUSR2 = new Receptionist(FirstName, LastName, EmailAddr, phoneNO,userAge, UserName, usrPassword, ReceptionistType);
//                  signUpUSR.signUp(FirstName, LastName, UserName, phoneNO, userAge, UserName, usrPassword, signUpUSR.getUserID());
<<<<<<< HEAD
                    signUpUSR2.signUp();
                    User.writeBin();
=======
                    signUpUSR2.signUp(signUpUSR2);
>>>>>>> 57805290eda2d072a87beea37e473715098ad7d2
                    break;
                case 3:
                    Admin signUpUSR3 = new Admin(FirstName, LastName, EmailAddr, phoneNO,userAge, UserName, usrPassword, AdminType);
                  //signUpUSR.signUp(FirstName, LastName, UserName, phoneNO, userAge, UserName, usrPassword, signUpUSR.getUserID());
                    System.out.println(FirstName + LastName + EmailAddr + phoneNO + userAge + UserName + usrPassword);
<<<<<<< HEAD
                    signUpUSR3.signUp();
                    User.writeBin();
=======
                    signUpUSR3.signUp(signUpUSR3);
>>>>>>> 57805290eda2d072a87beea37e473715098ad7d2
            }
        }
    }
    public static void login(Scanner sc,int userTypeIndex)
    {
        System.out.println("========Welcome To the House Rental System========");
        System.out.println("Enter Username: ");
        String Username = sc.next();
        System.out.println("Enter your password: ");
        String usrPassword = sc.next();
        Scanner cin = new Scanner(System.in);
        ///
        
<<<<<<< HEAD
        switch (userTypeIndex)
        {
            case 3:
                Admin adminUsr = new Admin();
                if(adminUsr.login(Username, usrPassword))
                {
                    adminScreen(cin);
                }
                
=======
        int menuChoice,user;   
        Scanner SCin = new Scanner(System.in);
        System.out.println("========Welcome To the House Rental System========");       
        System.out.println("Logging in as\n1.Renter\n2.Receptionist\n3.Admin");
        user = SCin.nextInt();       
        switch(user){
            case 1:
                mainMenu();
                break;
            case 2:
                mainMenu();
                break;
            case 3:
                mainMenu();
                break;
        }
        menuChoice = SCin.nextInt();  
        switch (menuChoice)
        {
            case 1:
                signUp(SCin,user);
>>>>>>> 57805290eda2d072a87beea37e473715098ad7d2
                break;
            case 2:
                
                Receptionist receptionistUsr = new Receptionist();
                if(receptionistUsr.login(Username, usrPassword))
                {
                    ReceptionistMenu(receptionistUsr);
                }
                
                break;
            case 1:
                Renter renterUsr = new Renter();
                if(renterUsr.login(Username, usrPassword)){
                    RenterMenu(renterUsr);
                }
                
                break;
                
            default:
                //error handle 
                break;
<<<<<<< HEAD
        }
        
        
    }
    
    public static void main(String[] args) {
        User.writeBin();
        
        User.readBin();
        
        
        int menuChoice,user;   
        Scanner SCin = new Scanner(System.in);
        System.out.println("========Welcome To the House Rental System========");       
        System.out.println("Logging in as\n1.Renter\n2.Receptionist\n3.Admin");
        user = SCin.nextInt();       
        mainMenu();
        menuChoice = SCin.nextInt();  
        switch (menuChoice)
        {
            case 1:
                signUp(SCin,user);
                break;
            case 2:
                login(SCin,user );
                break;
            default:
                System.out.println("Invalid choice, Please Select from Above options");
        }
=======
            default:
                System.out.println("Invalid choice, Please Select from Above options");
        }
>>>>>>> 57805290eda2d072a87beea37e473715098ad7d2

            
      
    }
    
}


///Last Edit 21/11 @9:42 ... /// 