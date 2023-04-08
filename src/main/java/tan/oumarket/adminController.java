package tan.oumarket;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import setting.setting;
import tan.pojo.branch;
import tan.pojo.employee;
import tan.pojo.product;
import tan.pojo.promotion;
import tan.services.ProductServices;
import tan.services.branchServices;
import tan.services.employeeServices;
import tan.services.promotionServices;
import tan.services.branchServices;

/**
 *
 * @author WMarcoMan
 *
 */
public class adminController implements Initializable {

    ProductServices product = new ProductServices();

    branchServices branch = new branchServices();
    employeeServices employee = new employeeServices();
    promotionServices promo = new promotionServices();

    @FXML
    private AnchorPane branchFD_form;

    @FXML
    private TableColumn<?, ?> branchFD_col_productAddress;

    @FXML
    private TableColumn<?, ?> branchFD_col_productID;

    @FXML
    private TableColumn<?, ?> branchFD_col_productName;

    @FXML
    private AnchorPane employeeFD_form;

    @FXML
    private TableView<employee> employeeFD_tableview;

    @FXML
    private Button employee_btn;

    @FXML
    private Button employee_btnadd;

    @FXML
    private Button employee_btndelete;

    @FXML
    private Button employee_btnuodate;

    @FXML
    private CheckBox employee_cbactive;

    @FXML
    private ComboBox<branch> employee_cbbranch;

    @FXML
    private TableColumn<?, ?> employee_col_active;

    @FXML
    private TableColumn<?, ?> employee_col_date;

    @FXML
    private TableColumn<?, ?> employee_col_branch;

    @FXML
    private TableColumn<?, ?> employee_col_id;

    @FXML
    private TableColumn<?, ?> employee_col_name;

    @FXML
    private TableColumn<?, ?> employee_col_phone;

    @FXML
    private TextField employee_txtname;

    @FXML
    private TextField employee_txtphone;

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
    private TextField txtproductStatus;

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
    private ComboBox<promotion> cbPromotion;

    @FXML
    private TextField txtproductID;

    @FXML
    private TextField txtproductName;

    @FXML
    private TextField txtproductprice;

    @FXML
    private Button product_btn;

    @FXML
    private Button branchFD_btnadd;

    @FXML
    private Button branchFD_btnclear;

    @FXML
    private Button branchFD_btndelete;

    @FXML
    private Button branchFD_btnupdate;

    @FXML
    private TableView<branch> branchFD_tableView;

    @FXML
    private TextField branchFD_txtbranchAddress;

    @FXML
    private TextField branchFD_txtbranchID;

    @FXML
    private TextField branchFD_txtbranchName;

    @FXML
    private AnchorPane promosionFD;

    @FXML
    private Button promosion_btn;

    @FXML
    private Label username;

    @FXML
    private Button promotionFD_btnAdd;

    @FXML
    private Button promotionFD_btnClear;

    @FXML
    private Button promotionFD_btnDelete;

    @FXML
    private Button promotionFD_btnUpdate;

    @FXML
    private TableColumn<?, ?> promotionFD_col_enddate;

    @FXML
    private TableColumn<?, ?> promotionFD_col_id;

    @FXML
    private TableColumn<?, ?> promotionFD_col_name;

    @FXML
    private TableColumn<?, ?> promotionFD_col_startdate;

    @FXML
    private DatePicker promotionFD_dtEnddate;

    @FXML
    private DatePicker promotionFD_dtStartdate;

    @FXML
    private TableView<promotion> promotionFD_tableview;

    @FXML
    private TextField promotionFD_txtDiscount;

    @FXML
    private TextField promotionFD_txtName;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void switchForm(ActionEvent event) {

        if (event.getSource() == product_btn) {
            productFD_form.setVisible(true);
            branchFD_form.setVisible(false);
            employeeFD_form.setVisible(false);
            promosionFD.setVisible(false);

            product_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            branch_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            employee_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            promosion_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

        } else if (event.getSource() == branch_btn) {
            productFD_form.setVisible(false);
            branchFD_form.setVisible(true);
            employeeFD_form.setVisible(false);
            promosionFD.setVisible(false);

            branch_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            product_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            employee_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            promosion_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

        } else if (event.getSource() == employee_btn) {
            productFD_form.setVisible(false);
            branchFD_form.setVisible(false);
            employeeFD_form.setVisible(true);
            promosionFD.setVisible(false);

            employee_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            branch_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            product_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            promosion_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

        } else if (event.getSource() == promosion_btn) {
            productFD_form.setVisible(false);
            branchFD_form.setVisible(false);
            employeeFD_form.setVisible(false);
            promosionFD.setVisible(true);

            promosion_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            branch_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            product_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            employee_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

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

    public void loadBranch(String kw) throws SQLException {
        List<branch> ds = branch.getBranchs();
        this.branchFD_tableView.getItems().clear();
        this.branchFD_tableView.setItems(FXCollections.observableList(ds));

        branchFD_col_productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        branchFD_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        branchFD_col_productAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

    }

    public void loadEmoploye(String kw) throws SQLException {
        List<employee> ds = employee.getEmployees();
        this.employeeFD_tableview.getItems().clear();
        this.employeeFD_tableview.setItems(FXCollections.observableList(ds));

        employee_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        employee_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        employee_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        employee_col_active.setCellValueFactory(new PropertyValueFactory<>("active"));
        employee_col_branch.setCellValueFactory(new PropertyValueFactory<>("namebr"));

    }

    public void loadPromotion(String kw) throws SQLException {
        List<promotion> listpromottion = promo.getPromotion();
        this.productFD_tableView.getItems().clear();
        this.promotionFD_tableview.setItems(FXCollections.observableList(listpromottion));

    }

    public void loadCol() {

        productFD_col_productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productFD_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productFD_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        productFD_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        productFD_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));


    }

    public void loadCol_promotion() {

        promotionFD_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        promotionFD_col_name.setCellValueFactory(new PropertyValueFactory<>("discount"));
        promotionFD_col_startdate.setCellValueFactory(new PropertyValueFactory<>("star"));
        promotionFD_col_enddate.setCellValueFactory(new PropertyValueFactory<>("end"));

    }

    public void addProduct(ActionEvent evt) throws SQLException {
        product p = new product(txtproductName.getText(),
                productType.getSelectionModel().getSelectedItem(),
                Integer.parseInt(txtproductprice.getText()), Integer.parseInt(txtproductStatus.getText()),
                cbPromotion.getSelectionModel().getSelectedItem().getId(),txtproductName.getText());
        if (product.addProduct(p)) {
            this.loadProduct(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully add");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void deleteProduct(ActionEvent evt) throws SQLException {
        if (product.deleteProduct(productFD_tableView.getSelectionModel().getSelectedItem().getId())) {
            this.loadProduct(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully delete");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void updateProduct(ActionEvent evt) throws SQLException {
        String km = "";
        if (cbPromotion.getSelectionModel().getSelectedIndex() < 0) {
            km = null;
        } else {
            km = cbPromotion.getSelectionModel().getSelectedItem().getId();
        }

        try {

            product p = new product(productFD_tableView.getSelectionModel().getSelectedItem().getId(),
                    txtproductName.getText(),
                    productType.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(txtproductprice.getText()), Integer.parseInt(txtproductStatus.getText()), km);

            if (product.updateProduct(p)) {
                this.loadProduct(null);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("successfully update");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void clearProduct(ActionEvent evt) {
        txtproductID.clear();
        txtproductName.clear();
        txtproductStatus.clear();
        txtproductprice.clear();

    }

    public void select() throws SQLException {
        product p = productFD_tableView.getSelectionModel().getSelectedItem();
        int num = productFD_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        txtproductName.setText(p.getName());
        txtproductprice.setText(String.valueOf(p.getPrice()));
        txtproductStatus.setText(String.valueOf(p.getStatus()));
        this.productType.setPromptText(p.getType());
        this.cbPromotion.setPromptText(String.valueOf(promo.getPromotion(p.getIdKM()).getDiscount()));

    }

    // public void selectEmploye() throws SQLException {
    // employee p = employeeFD_tableview.getSelectionModel().getSelectedItem();
    // int num = productFD_tableView.getSelectionModel().getSelectedIndex();
    // if ((num - 1) < -1) {
    // return;
    // }

    // txtproductName.setText(p.getName());
    // txtproductprice.setText(String.valueOf(p.getPhone()));
    // txtproductStatus.setText(String.valueOf(p.getStatus()));
    // this.productType.setPromptText(p.getType());
    // this.cbPromotion.setPromptText(p.getKm_id());

    // }

    private String[] categories = { "Meals", "Drinks" };

    public void availableFDType() {
        List<String> listCat = new ArrayList<>();

        for (String data : categories) {
            listCat.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listCat);

        productType.setItems(listData);

    }

    // branch

    public void addBranch(ActionEvent evt) throws SQLException {
        branch b = new branch(branchFD_txtbranchName.getText(), branchFD_txtbranchAddress.getText());
        if (branch.addBranch(b)) {
            this.loadBranch(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully add");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void deleteBranch(ActionEvent evt) throws SQLException {
        if (branch.deleteBranch((branchFD_tableView.getSelectionModel().getSelectedItem()).getId())) {
            this.loadBranch(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully delete");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void selectBranch() {
        branch p = branchFD_tableView.getSelectionModel().getSelectedItem();
        branchFD_txtbranchName.setText(p.getName());
        branchFD_txtbranchAddress.setText(p.getAddress());
    }

    public void updateBranch(ActionEvent evt) throws SQLException {

        try {
            branch p = branchFD_tableView.getSelectionModel().getSelectedItem();
            branch b = new branch(p.getId(), branchFD_txtbranchName.getText(), branchFD_txtbranchAddress.getText());

            if (branch.updateBranch(b)) {
                this.loadBranch(null);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("successfully update");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    // employee

    public void addEmployee(ActionEvent evt) throws SQLException {
        int ac = 0;
        String br = employee_cbbranch.getSelectionModel().getSelectedItem().getId();
        if (employee_cbactive.isSelected()) {
            ac = 1;
        }
        employee b = new employee(employee_txtname.getText(),
                employee_txtphone.getText(), ac, br);

        if (employee.addEmployee(b)) {
            this.loadEmoploye(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully add");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void deleteEmployee(ActionEvent evt) throws SQLException {
        if (employee.deleteEmployee((employeeFD_tableview.getSelectionModel().getSelectedItem()).getId())) {
            this.loadEmoploye(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully delete");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void updateEmployee(ActionEvent evt) throws SQLException {

        try {
            employee p = employeeFD_tableview.getSelectionModel().getSelectedItem();

            if (employee.updateEmployee(p)) {
                this.loadBranch(null);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("successfully update");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    // promotion

    public void addPromotion(ActionEvent evt) throws SQLException {
        Date start = Date.valueOf(this.promotionFD_dtStartdate.getValue());
        Date end = Date.valueOf(this.promotionFD_dtEnddate.getValue());
        promotion b = new promotion(Integer.parseInt(promotionFD_txtDiscount.getText()),start, end);

        if (promo.addPromotion(b)) {
            this.loadPromotion(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully add");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void deletePromotion(ActionEvent evt) throws SQLException {
        if (promo.deletePromotion((promotionFD_tableview.getSelectionModel().getSelectedItem()).getId())) {
            this.loadPromotion(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("successfully delete");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    public void updatePromotion(ActionEvent evt) throws SQLException {

        try {
            promotion p = promotionFD_tableview.getSelectionModel().getSelectedItem();

            if (promo.updatePromotion(p)) {
                this.loadPromotion(null);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("successfully update");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.availableFDType();

        try {
            List<promotion> listpromottion = promo.getPromotion();
            this.cbPromotion.setItems(FXCollections.observableList(listpromottion));

            List<branch> ds = branch.getBranchs();
            this.employee_cbbranch.setItems(FXCollections.observableList(ds));

            // promotion
            loadCol_promotion();
            loadPromotion(null);

            this.loadProduct(null);
            this.loadBranch(null);
            loadEmoploye(null);
            this.loadCol();

        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
