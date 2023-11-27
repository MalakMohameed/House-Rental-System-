///Restored from local backup 
//Daniel 20/11 @11:00pm /// ///
///KAKAKAKAKAKAA
//ff to match all beanches 

package houserental;

import java.util.ArrayList;
import java.util.Scanner;

public class HouseRental  { 
    
    
        static UserType renterType = UserType.Renter;
        static UserType AdminType = UserType.Admin;
        static UserType ReceptionistType = UserType.Receptionist;
    
    public static void resetScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    public static void mainMenu()
    {

        System.out.println("1.Create Account");
        System.out.println("2.Login ");   
    }
    
    public static void signUp(Scanner sc,int user)
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
                    signUpUSR1.signUp(signUpUSR1);
                    break;
                case 2:
                    Receptionist signUpUSR2 = new Receptionist(FirstName, LastName, EmailAddr, phoneNO,userAge, UserName, usrPassword, ReceptionistType);
//                  signUpUSR.signUp(FirstName, LastName, UserName, phoneNO, userAge, UserName, usrPassword, signUpUSR.getUserID());
                    signUpUSR2.signUp(signUpUSR2);
                    break;
                case 3:
                    Admin signUpUSR3 = new Admin(FirstName, LastName, EmailAddr, phoneNO,userAge, UserName, usrPassword, AdminType);
                  //signUpUSR.signUp(FirstName, LastName, UserName, phoneNO, userAge, UserName, usrPassword, signUpUSR.getUserID());
                    System.out.println(FirstName + LastName + EmailAddr + phoneNO + userAge + UserName + usrPassword);
                    signUpUSR3.signUp(signUpUSR3);
            }
        }
    }
    public static void login(Scanner sc)
    {
        System.out.println("========Welcome To the House Rental System========");
        System.out.println("Enter Username: ");
        String Username = sc.next();
        System.out.println("Enter your password: ");
        String usrPassword = sc.next();
        Renter renter = new Renter();///<-Problem is Here
        System.out.println("houserental.HouseRental.login()");
        //renter = Renter.getUserByUserName(Username);
        System.out.println("Logging in using UserName -> " + renter.getUserName());
        renter.login(Username, usrPassword);
    }
    
    public static void main(String[] args) {
        
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
                break;
            case 2:
                login(SCin);
                break;
            default:
                System.out.println("Invalid choice, Please Select from Above options");
        }

            
      
    }
    
}


///Last Edit 21/11 @9:42 ... /// 