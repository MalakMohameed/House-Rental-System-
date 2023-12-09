package houserental;
        
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

 abstract public class User implements Serializable{

   
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String email;
    protected String phone;
    protected String userName;
    protected String password;
    protected UserType type;
    protected String userID;
    protected UserType userType;
    static protected int userCounter;
    static protected ArrayList<User> Users = new ArrayList<User>();
    static protected ArrayList<Receptionist> Receptionists =new ArrayList<Receptionist>();   ///Moved From Receptionist Class and changed to static 
    static protected ArrayList<Admin> Admins = new ArrayList<Admin>();
    static protected ArrayList<Renter> Renters = new ArrayList<Renter>();
    
    User(){}
    
    User(String firstName, String lastName, String email, String phone, int age, String userName, String password, UserType type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.type = type;
    }
    
    public static String generateUserId(String FirstName, String LastName, int age, String phone, String email, Enum type) {
        // Extract initials from the first and last name
        System.out.println("houserental.User.generateUserId()");
        char firstInitial = FirstName.isEmpty() ? '?' : FirstName.charAt(0);
        char lastInitial = LastName.isEmpty() ? '?' : LastName.charAt(0);

        // Use the age as part of the ID
        String ageString = String.valueOf(age);

        // Generate a unique counter for each user
        String counterString = String.valueOf(userCounter++);

        // Combine the elements
        String userId = type.toString() + String.valueOf(firstInitial) + String.valueOf(lastInitial) + ageString + counterString;

        System.out.println("ID GENERATED: " + userId);
        return userId;
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
    public ArrayList<User> getUsersByType(UserType userType){
        
        ArrayList<User> Users = new ArrayList();
        for(User e: Users)
        {
            if(e.userType == userType){
                Users.add(e);
            }
        }
        return Users;
        
    }
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
    
    
    
    abstract protected void signUp(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, String userID);
    abstract protected boolean login(String username, String password);
    static protected void writeBin(){
         try {
            FileOutputStream renterOut = new FileOutputStream("Renters.bin");
            ObjectOutputStream renterOutOS  = new ObjectOutputStream(renterOut);
            renterOutOS.writeObject(Renters);
            FileOutputStream receptionistOut = new FileOutputStream("Receptionists.dat");
            ObjectOutputStream receptionistOutOS = new ObjectOutputStream(receptionistOut);
            receptionistOutOS.writeObject(Receptionists);
            receptionistOutOS.writeObject(Renters);
            FileOutputStream adminOut = new FileOutputStream("Admins.dat");
            ObjectOutputStream adminOutOS = new ObjectOutputStream(adminOut);
            adminOutOS.writeObject(Admins);
         }
         catch (IOException e){
             System.out.println(e);
         }
    }
    static protected void readBin(){
        //System.out.println("houserental.Renter.readBin()<----");
        try{
        FileInputStream renterIn = new FileInputStream("Renters.bin");
        ObjectInputStream renterInOs = new ObjectInputStream(renterIn);
        FileInputStream receptionistIn = new FileInputStream("Receptionists.dat");
        ObjectInputStream receptionistInOs = new ObjectInputStream(receptionistIn);
        FileInputStream adminIn = new FileInputStream("Admins.dat");
        ObjectInputStream adminInOs = new ObjectInputStream(adminIn);
            try {
                Renters = (ArrayList<Renter>) renterInOs.readObject();
                Receptionists = (ArrayList<Receptionist>) receptionistInOs.readObject();
                Admins = (ArrayList<Admin>) adminInOs.readObject();

            } catch (ClassNotFoundException ex) {
               
                
        }catch (IOException e) {
            System.out.println(e);
        }


    }   catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // all users have unique ArrayLists making it impossible to generalize in this class so it will be overridden in each subclass

    public String getFirstName(){
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}