package tan.oumarket;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import setting.JdbcUtils;
import setting.data;
import tan.pojo.product;
import tan.sevices.ProductServices;



/**
 *
 * @author WMarcoMan
 *
 */
public class adminController implements Initializable {

    ProductServices product = new ProductServices();


    @FXML
    private AnchorPane branchFD_form;

    @FXML
    private AnchorPane employeeFD_form;

    @FXML
    private Button branch_btn;

    @FXML
    private Button close;

   
    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private ComboBox<String> productStatus;
   
    @FXML
    private ComboBox<String> productType;

    

    @FXML
    private AnchorPane productFD_form;

    @FXML
    private TableView<product> productFD_tableView;

    @FXML
    private TableColumn<?, ?> productFD_col_price;

    @FXML
    private TableColumn<?, ?> productFD_col_productID;

    @FXML
    private TableColumn<?, ?> productFD_col_productName;

    @FXML
    private TableColumn<?, ?> productFD_col_status;

    @FXML
    private TableColumn<?, ?> productFD_col_type;

    @FXML
    private TextField txtproductID;

    @FXML
    private TextField txtproductName;

    @FXML
    private TextField txtproductprice;

    @FXML
    private Button product_btn;

    @FXML
    private Button employee_btn;

    @FXML
    private Label username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    

      
    public void switchForm(ActionEvent event) {

        if (event.getSource() == product_btn) {
            productFD_form.setVisible(true);
            branchFD_form.setVisible(false);
            employeeFD_form.setVisible(false);

            product_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            branch_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            employee_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

        } else if (event.getSource() == branch_btn) {
            productFD_form.setVisible(false);
            branchFD_form.setVisible(true);
            employeeFD_form.setVisible(false);

            branch_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            product_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            employee_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

        } else if (event.getSource() == employee_btn) {
            productFD_form.setVisible(false);
            branchFD_form.setVisible(false);
            employeeFD_form.setVisible(true);

            employee_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            branch_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            product_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8f);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }

        } catch (IOException e) {
        }

    }

    public void displayUsername() {
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void loadProduct(String kw) throws SQLException {
        List<product> ds = product.getProducts();
        this.productFD_tableView.getItems().clear();
        this.productFD_tableView.setItems(FXCollections.observableList(ds));

    }

    public void loadCol() {
    
        productFD_col_productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productFD_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productFD_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        productFD_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        productFD_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

      

    }

    private  String[] status = {"Available", "Not Available"};

    public void availableFDStatus() {
        List<String> listStatus = new ArrayList<>();

        for (String data : status) {
            listStatus.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listStatus);
        productStatus.setItems(listData);

    }

    private  String[] categories = {"Meals", "Drinks"};

    public void availableFDType() {
        List<String> listCat = new ArrayList<>();

        for (String data : categories) {
            listCat.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listCat);
        productType.setItems(listData);

    }

    public void addProduct(ActionEvent evt) throws SQLException{
        product p=new product(Integer.parseInt(txtproductID.getText()),txtproductName.getText()
        ,productType.getSelectionModel().getSelectedItem(),
        Integer.parseInt(txtproductprice.getText()),productStatus.getSelectionModel().getSelectedItem());
       if( product.addProduct(p)){
            this.loadProduct(null);
            Alert  alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("successfully add");
        alert.showAndWait();
       }
       else{
        
        Alert  alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
       }
      
    }

    public void deleteProduct(ActionEvent evt){
        
    }
    

    public void select(){
        product p=productFD_tableView.getSelectionModel().getSelectedItem();
        int num=productFD_tableView.getSelectionModel().getSelectedIndex();
        if((num-1)<-1){
            return;
        }

        txtproductID.setText(String.valueOf(p.getId()));
        txtproductName.setText(p.getName());
        txtproductprice.setText(String.valueOf(p.getPrice()));



        
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.availableFDStatus();
        this.availableFDType();
        
        try {
           
            this.loadProduct(null);
            this.loadCol();

        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
