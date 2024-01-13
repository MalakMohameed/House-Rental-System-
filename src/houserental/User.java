///DMS 10/1 @12:06am

package houserental;
        
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

//JavaFX Imports 

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


 abstract public class User implements Serializable{

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email + ", phone=" + phone + ", userName=" + userName + ", password=" + password + ", type=" + ", userID=" + userID + '}';
    }
    
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String email;
    protected String phone;
    protected String userName;
    protected String password;
    protected String userID;
    static public int userCounter;
    static protected ArrayList<Receptionist> Receptionists = new ArrayList<Receptionist>();   ///Moved From Receptionist Class and changed to static 
    static protected ArrayList<Admin> Admins = new ArrayList<Admin>();
    static protected ArrayList<Renter> Renters = new ArrayList<Renter>();
    
    
    User(){}
    
    User(String firstName, String lastName, String email, String phone, int age, String userName, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.userID=generateUserId(firstName, lastName, age);
    }
    
    public static String generateUserId(String FirstName, String LastName, int age) {
        // Extract initials from the first and last name
        char firstInitial = FirstName.isEmpty() ? '?' : FirstName.charAt(0);
        char lastInitial = LastName.isEmpty() ? '?' : LastName.charAt(0);
        // Use the age as part of the ID
        String ageString = String.valueOf(age);
        // Generate a unique counter for each user
        String counterString = String.valueOf(userCounter++);
        // Combine the elements
        String userId = String.valueOf(firstInitial) + String.valueOf(lastInitial) + ageString + counterString;
        return userId;
    } 
    
    
    public static ArrayList<User> getAllUsers(){
        ArrayList<User> usersArray = new ArrayList();
        for(short i = 0; i < Admins.size(); i++){
            usersArray.add(Admins.get(i));
        }
         for(short i = 0; i < Renters.size(); i++){
            usersArray.add(Renters.get(i));
        }
          for(short i = 0; i < Receptionists.size(); i++){
            usersArray.add(Receptionists.get(i));
        }
        
        
        return usersArray;
    }
    
//    public User getUserByID(String UserID){ ///Error Handling
//        
//        for (User e : Users){
//            if(e.userID.equals(UserID)){
//                int index = Users.indexOf(e);
//                return Users.get(index);
//            }
//        }
//        return null;
//    }

    public static boolean isValidEmail(String email) {

//        String emailRegex = "^[a-zA-Z0-9+&*-]+(?:\.[a-zA-Z0-9+&-]+)@(gmail\.com|yahoo\.com|outlook\.com)$";
            String emailRegex ="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean isValidAge(int age){
        return age >= 18;
    } 
    public static void SerializeBinary(){
        
        try{
        FileOutputStream i = new FileOutputStream("Renters.dat");
        ObjectOutputStream in = new ObjectOutputStream(i);
          System.out.println( Renters.toString());
        in.writeObject(Renters);
        in.close();
        i.close();
        }
        catch (IOException e) {
            System.out.println(e);
    }
            System.out.println("houserental.Renter.writeBin()");
 
                    
        
        try{
        FileOutputStream i = new FileOutputStream("Admins.dat");
        ObjectOutputStream in = new ObjectOutputStream(i);
        in.writeObject(Admins);
        }catch (IOException e) {
            System.out.println(e);
    }
            System.out.println("houserental.Admin.writeBin()");
        
        
        ////
        try{
        FileOutputStream i = new FileOutputStream("Receptionists.dat");
        ObjectOutputStream in = new ObjectOutputStream(i);
        in.writeObject(Receptionists);
        }catch (IOException e) {
            System.out.println(e);
    }
            System.out.println("houserental.Receptionist.writeBin()");
            
    }
    
    public static void DeserializeBinary(){
        
        try{
        FileInputStream i = new FileInputStream("Renters.dat");
        ObjectInputStream in = new ObjectInputStream(i);
            try {
               Renters = (ArrayList<Renter>) in.readObject();        
                in.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Renter.class.getName()).log(Level.SEVERE, null, ex);
                in.close();
            }
             
        }catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("houserental.Renter.readBin()");
        System.out.println("houserental.Renter.readBin()" + Renters.size());
        
        /////
        
        try{
        FileInputStream i = new FileInputStream("Receptionists.dat");
        ObjectInputStream in = new ObjectInputStream(i);
            try {
                Receptionists = (ArrayList<Receptionist>) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);}
        }catch (IOException e) {
            System.out.println(e);}
        
        /////
        try{
        FileInputStream i = new FileInputStream("Admins.dat");
        ObjectInputStream in = new ObjectInputStream(i);
            try {
                Admins = (ArrayList<Admin>) in.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);}
        }catch (IOException e) {
            System.out.println(e);}
        
                        
        for(short k= 0; k < Renters.size();k++){
                   System.out.println(Renters.get(k).getUserName());
               }
       
    }
    
    public static void updateLocalObj(User usrObj){//////JUST REWRITE THE ENTIRE FUNCTIONNNN 
        
        if(usrObj instanceof Renter){
            for(Renter rntObj : Renters)
            {
                if(rntObj.equals(usrObj)){
                    
                }
            }
        }
        else if(usrObj instanceof Admin){
            
        }
        else {
            //Receptionist
        }
        
    }
    
    
     private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(firstName);
        out.writeObject(lastName);
        out.writeInt(age);
        out.writeObject(email);
        out.writeObject(phone);
        out.writeObject(userName);
        out.writeObject(password);
        out.writeObject(userID);
        // Custom serialization logic if needed
    }
      private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        firstName = (String) in.readObject();
         lastName = (String) in.readObject();
         age =   in.readInt();
         email = (String) in.readObject();
         phone = (String) in.readObject();
         userName = (String) in.readObject();
         password = (String) in.readObject();
         userID = (String) in.readObject();
       // in.defaultReadObject();
        // Custom deserialization logic if needed
    }
    
   
    abstract protected void signUp();
    abstract protected boolean login(String username, String password);
    // all users have unique ArrayLists making it impossible to generalize in this class so it will be overridden in each subclass
    
    
     public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}