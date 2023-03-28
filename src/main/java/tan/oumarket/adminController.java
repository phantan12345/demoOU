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
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author WMarcoMan
 *
 */
public class adminController implements Initializable {

    @FXML
    private TextField availableFD_search;

    @FXML
    private AnchorPane branchFD_form;

    @FXML
    private Button branch_btn;

    @FXML
    private Button close;

    @FXML
    private Button employeeFD_addBtn;

    @FXML
    private Button employeeFD_clearBtn;

    @FXML
    private TableColumn<?, ?> employeeFD_col_price;

    @FXML
    private TableColumn<?, ?> employeeFD_col_productID;

    @FXML
    private TableColumn<?, ?> employeeFD_col_productName;

    @FXML
    private TableColumn<?, ?> employeeFD_col_status;

    @FXML
    private TableColumn<?, ?> employeeFD_col_type;

    @FXML
    private Button employeeFD_deleteBtn;

    @FXML
    private AnchorPane employeeFD_form;

    @FXML
    private TextField employeeFD_productID;

    @FXML
    private TextField employeeFD_productName;

    @FXML
    private TextField employeeFD_productPrice;

    @FXML
    private ComboBox<String> employeeFD_productStatus;

    @FXML
    private ComboBox<String> employeeFD_productType;

    @FXML
    private TableView<?> employeeFD_tableView;

    @FXML
    private Button employeeFD_updateBtn;

    @FXML
    private Button employee_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button order_addBtn;

    @FXML
    private Label order_balance;

    @FXML
    private Button order_payBtn;

    @FXML
    private Button order_receiptBtn;

    @FXML
    private Button order_removeBtn;

    @FXML
    private Label order_total;

    @FXML
    private TableColumn<product, Integer> productFD_col_price;

    @FXML
    private TableColumn<product, Integer> productFD_col_productID;

    @FXML
    private TableColumn<product, String> productFD_col_productName;

    @FXML
    private TableColumn<product, String> productFD_col_status;

    @FXML
    private TableColumn<product, String> productFD_col_type;

    @FXML
    private AnchorPane productFD_form;

    @FXML
    private TableView<product> productFD_tableView;

    @FXML
    private Button product_btn;

    @FXML
    private Label username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private final String[] status = {"Available", "Not Available"};

    public void availableFDStatus() {
        List<String> listStatus = new ArrayList<>();

        for (String data : status) {
            listStatus.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listStatus);
        employeeFD_productStatus.setItems(listData);

    }

    private final String[] categories = {"Meals", "Drinks"};

    public void availableFDType() {
        List<String> listCat = new ArrayList<>();

        for (String data : categories) {
            listCat.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listCat);
        employeeFD_productType.setItems(listData);

    }

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
        ProductServices product = new ProductServices();
        List<product> ds = product.getProducts();
        this.productFD_tableView.getItems().clear();
        this.productFD_tableView.setItems(FXCollections.observableList(ds));

    }

    public void loadCol() {
        productFD_col_productID.setCellValueFactory(new PropertyValueFactory<product, Integer>("id"));
        productFD_col_productName.setCellValueFactory(new PropertyValueFactory<product, String>("name"));
        productFD_col_type.setCellValueFactory(new PropertyValueFactory<product, String>("type"));
        productFD_col_price.setCellValueFactory(new PropertyValueFactory<product, Integer>("price"));
        productFD_col_status.setCellValueFactory(new PropertyValueFactory<product,String>("status"));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
 
        loadCol();
        try {

            this.loadProduct(null);
        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
