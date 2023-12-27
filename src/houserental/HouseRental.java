///Restored from local backup 
//Daniel 20/11 @11:00pm /// ///
///KAKAKAKAKAKAA
//ff to match all beanches 

package houserental;

import java.util.ArrayList;
import java.util.Scanner;

public class HouseRental    { 
    
    
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
                    Renter signUpUSR1 = new Renter(FirstName, LastName, EmailAddr, phoneNO,userAge, UserName, usrPassword);
                    signUpUSR1.signUp();
                    User.SerializeBinary();
                   
                    break;
                case 2:
                    Receptionist signUpUSR2 = new Receptionist(FirstName, LastName, EmailAddr, phoneNO,userAge, UserName, usrPassword);
                    signUpUSR2.signUp();
                    User.SerializeBinary();
                    break;
                case 3:
                    Admin signUpUSR3 = new Admin(FirstName, LastName, EmailAddr, phoneNO,userAge, UserName, usrPassword);
                    signUpUSR3.signUp();
                    User.SerializeBinary();
            }
        }
    }
    public static void login(Scanner sc, int userTypeIndex)
    {
        System.out.println("Enter Username: ");
        String Username = sc.next();
        System.out.println("Enter your password: ");
        String usrPassword = sc.next();
        ///
        switch (userTypeIndex)
        {
            case 3:
                Admin adminUsr = new Admin();
                if(adminUsr.login(Username, usrPassword))
                {
                    System.out.println("logged in as admin");
                    //Call Admin Screen Function
                }
                
                break;
            case 2:
                
                Receptionist receptionistUsr = new Receptionist();
                if(receptionistUsr.login(Username, usrPassword))
                {
                    System.out.println("logged in as receptionist");
                    ///Call Receptionist Screen
                }
                
                break;
            case 1:
                Renter renterUsr = new Renter();
                if(renterUsr.login(Username, usrPassword))
                {               
                    System.out.println("logged in as renter");
                    //Call Renter Screen 
                }
                break;
                
            default:
                //error handle 
                break;
        }
        
        
    }
    
    public static void main(String[] args) {
        
        User.DeserializeBinary();
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
    }
}


///Last Edit 21/11 @9:42 ... /// 