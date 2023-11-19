//Edited 
package houserental;

import java.io.Serializable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class User implements Serializable{
   
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;
    protected String userName;
    protected String password;
    protected UserType type;
    protected String userID;
    static protected int userCounter;
    
    User(String firstName, String lastName, String email, String phone, String userName, String password, UserType type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.type = type;
    }
    
    public static String generateUserId(String FirstName, String LastName, int age, String phone, String email, Enum type) {
        // Extract initials from the first and last name
        char firstInitial = FirstName.isEmpty() ? '?' : FirstName.charAt(0);
        char lastInitial = LastName.isEmpty() ? '?' : LastName.charAt(0);

        // Use the age as part of the ID
        String ageString = String.valueOf(age);

        // Generate a unique counter for each user
        String counterString = String.valueOf(userCounter++);

        // Combine the elements
        String userId = type.toString() + String.valueOf(firstInitial) + String.valueOf(lastInitial) + ageString + counterString;

        return userId;
    } 
    public boolean login(String userName,String Password){
        try{
        FileInputStream i = new FileInputStream("Users.txt");
        ObjectInputStream in = new ObjectInputStream(i);
        while(in.readLine() != userName){
        }
        if(in.readLine() == password){ /////Comparing strings using == instead of str.equal()
            return true;
        }
        else{
            return false;
        }
        }catch (IOException e) {
            System.out.println(e);
         return false;
         //Fixed Error in line 69 Illegal start of expression due to un-closed curly braces.
         //Added return statement at end of method body
    }
    }
    public void signUp(String NewUserName, String NewPassword, String NewEmail, int NewAge, String NewFirstName, String NewLastName,UserType type,String userID, String newPhone, String newemail){
        switch(type.ordinal()){
            case 0:
                Admin x = new Admin(NewUserName, NewPassword, NewEmail, NewAge, NewFirstName,NewLastName, userID, newphone, newemail);/// what is newphone?? ///
                try{
                    FileOutputStream f = new FileOutputStream(new File("Users.txt"));
	            ObjectOutputStream o = new ObjectOutputStream(f);
                    o.writeObject(x);
                    o.close();
                    f.close();
                }catch (FileNotFoundException e) {
			System.out.println("File not found");
                }catch (IOException e) {
			System.out.println("Error initializing stream");
                }
        
            case 1:
                Receptionist y = new Receptionist(NewUserName, NewPassword, NewEmail, NewAge, NewFirstName,NewLastName, userID,newphone, newemail);/// what is newphone?? ///
                try{
                    FileOutputStream f = new FileOutputStream(new File("Users.txt"));
	            ObjectOutputStream o = new ObjectOutputStream(f);
                    o.writeObject(y);
                    o.close();
                    f.close();
                }catch (FileNotFoundException e) {
			System.out.println("File not found");
                }catch (IOException e) {
			System.out.println("Error initializing stream");
                }
        
            case 2: 
                Renter z = new Renter(NewUserName, NewPassword, NewEmail, NewAge, NewFirstName,NewLastName, userID,newphone, newemail);/// what is newphone?? ///
                try{
                    FileOutputStream f = new FileOutputStream(new File("Users.txt"));
	            ObjectOutputStream o = new ObjectOutputStream(f);
                    o.writeObject(z);
                    o.close();
                    f.close();
                }catch (FileNotFoundException e) {
			System.out.println("File not found");
                }catch (IOException e) {
			System.out.println("Error initializing stream");
                }
        }
    }
}