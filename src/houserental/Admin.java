package houserental;
import static java.awt.PageAttributes.MediaType.C;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.A;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class Admin extends User implements Serializable
{
//    public String addIcon = "file:C:\\Users\\noor amgad\\OneDrive\\Desktop\\House-Rental-System\\add icon.png\";
    public Admin(){}
    
    public Admin(String newfirstName, String newlastName, String newemail, String newphone, int age, String newuserName, String newpassword){ //i am adding temporary paremeters for my signup functions feel free to change but take signup into consideration if possible
        super(newfirstName,newlastName, newemail,newphone, age,newuserName,newpassword);
    }
    
    public void addHouse(House obj,ArrayList<House> houseList){
        
       houseList.add(obj);
    }
    
    
    
    public void removeHouse(String houseID, ArrayList<House> houseList){
        
        houseList.removeIf(house->house.getHouseID().equals(houseID));
    }
    public Category viewHouseCategory(String houseID, ArrayList<House> houseList){
        
        int index = houseList.indexOf(houseID);
        return houseList.get(index).getCategory();
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
   public void removeCustumer(Renter obj){
        Renters.remove(obj);
        obj=null;
             
   }
   public void addCustomer (Renter obj){
       Renters.add(obj);
   }
    

    @Override
    public boolean login(String username, String Password){
        if(Admins!=null){
        for(Admin e: Admins)
        {
            if(e.userName.equals(username) && e.password.equals(Password))
            {
                return true;
            }
        }
               return false;
       }
              return false;
    }

    @Override
    public void signUp(){
//        for(Admin r :Admins){
//            if(r.AdminID().equals(r.AdminID()))
//            {
//                System.out.println("User Already exists!");
//            }
//            
//        }
        Admins.add(this);
    }
private void showTableViewWindow(House obj, ArrayList<House> houseList) {
    
    ObservableList<House> observableHouseList = FXCollections.observableArrayList(houseList);

    Stage tableViewStage = new Stage();
    tableViewStage.setTitle("Table View Window");

    TableView<House> tableView = new TableView<>();
    TableColumn<House, Category.EnumDesc> catColumn = new TableColumn<>("Category");
    TableColumn<House, View.EnumDesc> viewColumn = new TableColumn<>("View");
    

//    catColumn.setCellValueFactory(cellData -> cellData.getValue().getCategory());
//    viewColumn.setCellValueFactory(cellData -> cellData.getValue().getView());
    catColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
    viewColumn.setCellValueFactory(new PropertyValueFactory<>("view"));
   
    tableView.setEditable(true);
//    catColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//    viewColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//    catColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    tableView.getColumns().addAll(catColumn, viewColumn);
    tableView.setItems(observableHouseList);

   
    Button saveChangesBtn = new Button("Save Changes");
    saveChangesBtn.setOnAction(e -> saveChanges(houseList, observableHouseList));
    saveChangesBtn.setStyle("-fx-background-color: transparent;");

    GridPane gridPane = new GridPane();
    gridPane.add(tableView, 0, 0);
    gridPane.add(saveChangesBtn, 0, 1);

    Scene tableViewScene = new Scene(gridPane, 400, 300);
    tableViewStage.setScene(tableViewScene);
    tableViewStage.show();
}

private void saveChanges(ArrayList<House> houseList, ObservableList<House> observableHouseList) {
 
    houseList.clear();
    houseList.addAll(observableHouseList);
    System.out.println("Changes saved!");
}

   
public  void showAdminMainScrn(Stage primaryStage){
        
        Image image = new Image("file:///C:/Users/yahia/Documents/GitHub/House-Rental-System/add icon new.png");
        ImageView addimage = new ImageView(image);
        
        Image image2 = new Image("file:///C:/Users/yahia/Documents/GitHub/House-Rental-System/cancel button attempt 47389204567.png");
        ImageView cnclimage = new ImageView(image2);
        
        Image image3 = new Image("file:///C:/Users/yahia/Documents/GitHub/House-Rental-System/list icon new.png");
        ImageView listimage = new ImageView(image3);
       
        Button addBtn = new Button();
        addBtn.setGraphic(addimage);
        addimage.setFitWidth(150);  
        addimage.setFitHeight(150);
        addBtn.setStyle("-fx-background-color: transparent;");
        
        Button cnclBtn = new Button();
        cnclBtn.setGraphic(cnclimage);
        cnclBtn.setPrefSize(150, 150);
        cnclimage.setFitWidth(150);  
        cnclimage.setFitHeight(150);
        cnclBtn.setStyle("-fx-background-color: transparent;");
        
        Button listBtn = new Button();
        listBtn.setGraphic(listimage);
        listBtn.setPrefSize(150, 150);
        listimage.setFitWidth(150);  
        listimage.setFitHeight(150);
        listBtn.setStyle("-fx-background-color: transparent;");
        listBtn.setOnAction(e ->{
        House obj = new House();
        obj.setCategory(Category.duplex);  
        obj.setView(View.farm);        
        ArrayList<House> houseList = new ArrayList<>();
        houseList.add(obj);

        showTableViewWindow(obj, houseList);
        });

        
        
        
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Image backgroundImage = new Image("file:///C:/Users/yahia/Documents/GitHub/House-Rental-System/Untitled design (1).png");
        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
               
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        Background background = new Background(backgroundImg);
        gridPane.setBackground(background);

        
      
        
        gridPane.add(addBtn, 2, 0);
        gridPane.add(cnclBtn,3,0);
        gridPane.add(listBtn, 2, 1);
        Scene sc = new Scene(gridPane,250,70);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Admin MainScreen");
        primaryStage.show();
        
        
    }
}