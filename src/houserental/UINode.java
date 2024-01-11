package houserental;

import javafx.geometry.Insets;
import javafx.stage.StageStyle;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class UINode 
{
    private Rectangle RectangleContainer;
    private Label titleLabel = new Label();
    private Label descriptionLabel = new Label();
    private Label priceLabel = new Label();
    private Label extraDetailsLabel1 = new Label();
    private Label extraDetailsLabel2 = new Label();
    private Button detailsBtn = new Button("Details");
    private boolean isDetailsWinOpen = false;
    
    
    private Pane container = new Pane();
    House house = new House();
    
    
    
    public UINode(House houseObj){
        
        this.house = houseObj;
        RectangleContainer = new Rectangle(350,100, Color.GRAY);
        RectangleContainer.setArcHeight(15);
        RectangleContainer.setArcWidth(15);
        
        descriptionLabel.setText(houseObj.getDescription());
        descriptionLabel.setLayoutX(container.getLayoutX()+10);
        descriptionLabel.setLayoutY(container.getLayoutY()+40);
        priceLabel.setText(String.valueOf(houseObj.getCostPerNight()) + "/Night");
        priceLabel.setFont(new Font(18));
        priceLabel.setLayoutX(container.getLayoutX()+10);
        priceLabel.setLayoutY(container.getLayoutY()+5);
        ///String.valueOf( houseObj.getCostPerNight())
        //Add rest of dataLabels
        
        detailsBtn.setLayoutX(container.getLayoutX()+285);
        detailsBtn.setLayoutY(container.getLayoutY()+65);
        
        detailsBtn.setOnAction(e->{
        
             Pane detailsPane = new Pane();
             detailsPane.setMaxWidth(100);
            if(!isDetailsWinOpen){
                isDetailsWinOpen = true;
            Stage detailsStage = new Stage();
            detailsStage.initStyle(StageStyle.UNDECORATED);
            detailsStage.setResizable(false);
            
            ///add labels to show maore info 
            
            Button closeBtn = new Button("Close");
            closeBtn.setLayoutX(235);
            closeBtn.setLayoutY(365);
            closeBtn.setOnAction(evntClose->{
                detailsStage.close();
                isDetailsWinOpen = false;
            });
            
            
            Button rentBtn = new Button("Rent it Now");
            rentBtn.setLayoutX(150);
            rentBtn.setLayoutY(365);
            
            
            Label priceLabelDet = new Label(priceLabel.getText());
            priceLabelDet.setFont(priceLabel.getFont());        
            priceLabelDet.setLayoutX(20);
            priceLabelDet.setLayoutY(20);
            
            Label isPopularLabel = new Label("Popular!");
            isPopularLabel.setFont( Font.font("Arial",FontWeight.BOLD ,11));
            isPopularLabel.setStyle("-fx-text-fill: red;");
            isPopularLabel.setLayoutX(20);
            isPopularLabel.setLayoutY(40);
            if(houseObj.getNumberOfRentals()>=10){
                 detailsPane.getChildren().add(isPopularLabel);
            }
            
            
            Label propTypeLabel = new Label(""+ houseObj.getCategory());
            propTypeLabel.setLayoutX(20);
            propTypeLabel.setLayoutY(60);
            
            
            Label viewLabel = new Label("Property View: " + houseObj.getView());
            viewLabel.setLayoutX(20);
            viewLabel.setLayoutY(90);
            
            Label noBedroomLabel = new Label("Number Of Bedrooms: " + houseObj.getNumberOfRooms());
            noBedroomLabel.setLayoutX(20);
            noBedroomLabel.setLayoutY(90);
            
           
            Label descriptionLabelDet = new Label(houseObj.generateDescription());
            descriptionLabelDet.setLayoutX(20);
            descriptionLabelDet.setLayoutY(120);
            descriptionLabelDet.setWrapText(true);
            descriptionLabelDet.setMaxWidth(250);
            
            
           
            detailsPane.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), javafx.geometry.Insets.EMPTY)));
            detailsPane.getChildren().addAll(closeBtn,rentBtn,priceLabelDet,viewLabel,propTypeLabel,descriptionLabelDet);
            
            Screen primaryScreen = Screen.getPrimary();
            Rectangle2D bounds = primaryScreen.getVisualBounds();
            
            detailsStage.setX(bounds.getMinX() + (bounds.getWidth() - detailsStage.getWidth()) / 2);
            detailsStage.setY(bounds.getMinY() + (bounds.getHeight() - detailsStage.getHeight()) / 2);
            
            
            
            
             Scene popupScene = new Scene(detailsPane, 300, 400);
             detailsStage.setScene(popupScene);
             detailsStage.showAndWait();
            
            }
            
             
          
        });
        
        container.setPadding(new Insets(2));
        
        container.getChildren().addAll(RectangleContainer,priceLabel,titleLabel,descriptionLabel,extraDetailsLabel1,extraDetailsLabel2,detailsBtn);
        
    }
    public Pane getNode(){
        return container;
    }
    
}
