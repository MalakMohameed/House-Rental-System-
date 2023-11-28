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

 abstract public class User implements Serializable{

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", email=" + email + ", phone=" + phone + ", userName=" + userName + ", password=" + password + ", type=" + type + ", userID=" + userID + ", userType=" + userType + '}';
    }

   
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
    static public ArrayList<Renter> Renters = new ArrayList<Renter>();
    
    
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
        this.userID=generateUserId(firstName, lastName, age, type );
        System.out.println("houserental.User.<init>()" + userName);
    }
    
    public static String generateUserId(String FirstName, String LastName, int age, Enum type) {
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
        
         System.out.println("houserental.Renter.readBin()<----");
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
        for(int i =0; i< Renters.size(); i++)
        {
            System.out.println("-->"+Renters.get(i).firstName);
        }
        
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
        
       
    }
    
    abstract protected void signUp(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword, String userID);
    abstract protected void signUp();
    abstract protected boolean login(String username, String password);
    abstract protected void writeBin();
    abstract protected void readBin();
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