package tan.oumarket;

import java.io.IOException;
import java.net.URL;
import java.security.interfaces.DSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import setting.Info;
import setting.Singleton;
import setting.SwitchPage;
import setting.data;
import setting.JdbcUtils;
import tan.pojo.customer;
import tan.pojo.product;
import tan.pojo.product_bill;
import tan.pojo.promotion;
import tan.services.CustomerServices;
import tan.services.ProductServices;
import tan.services.Product_billServices;
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
public class employeeController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button avaialbeFD_btn;

    @FXML
    private Button order_btn;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_NC;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Label dashboard_TIncome;

    @FXML
    private BarChart<?, ?> dashboard_NOCChart;

    @FXML
    private AreaChart<?, ?> dashboard_ICChart;

    @FXML
    private AnchorPane availableFD_form;

    @FXML
    private TextField txtProductID;

    @FXML
    private Button availableFD_clearBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField availableFD_search;

    @FXML
    private AnchorPane order_form;

    @FXML
    private ComboBox<?> order_productID;

    @FXML
    private ComboBox<?> order_productName;

    @FXML
    private Spinner<Integer> order_quantity;

    @FXML
    private Button order_addBtn;

    @FXML
    private Label order_total;

    @FXML
    private TextField order_amount;

    @FXML
    private Label order_balance;

    @FXML
    private Button order_payBtn;

    @FXML
    private Button order_receiptBtn;

    @FXML
    private Button order_removeBtn;

    @FXML
    private TableColumn<?, ?> col_amount;

    @FXML
    private TableColumn<?, ?> col_price;

    @FXML
    private TableColumn<?, ?> col_type;

    @FXML
    private TableColumn<?, ?> col_productName;

    @FXML
    private TableView<product_bill> tbv_Product;

    @FXML
    private TableColumn<?, ?> col_promotionPrice;

    @FXML
    private Spinner<Integer> orderQuantity;

    @FXML
    private TextField txtFundsTotal;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtBillAbate;

    @FXML
    private Button checkBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private SwitchPage sw;
    private int ft = 0;
    private int abe;
    private double percent = 1;

    private ObservableList<product_bill> dspb = FXCollections.observableArrayList();

    public void addBill() throws SQLException {

        ProductServices pds = new ProductServices();
        product p = pds.getProduct(txtProductID.getText());
        System.out.print(p.getName());
        product_bill pb = new product_bill(p.getName(), p.getType(), p.getPrice(), orderQuantity.getValue());
        pb.setIdProduct(p.getId());
        promotion pro = getPromotion(p.getIdKM());
        pb.setProPrice((pb.getPrice() - pb.getPrice() * pro.getDiscount() / 100) * pb.getAmount());
        dspb.add(pb);
        loadTableView();
        orderQuantity.getValueFactory().setValue(1);
        txtProductID.setText("");
        fundsTotal();

    }

    public void deleteBill() throws SQLException {
        product_bill pb = tbv_Product.getSelectionModel().getSelectedItem();
        dspb.remove(pb);
        loadTableView();
    }

    public void ClearBill() throws SQLException {
        txtProductID.setText("");
        orderQuantity.getValueFactory().setValue(1);
        dspb.clear();
        tbv_Product.getItems().clear();
    }

    public void completeBill() throws SQLException {
        Product_billServices pdServices = new Product_billServices();
        pdServices.saveProduct_bill(dspb);
        ClearBill();
    }

    public void fundsTotal() {
        ft = 0;
        for (product_bill p : dspb) {
            ft += p.getProPrice();
        }
        txtFundsTotal.setText(ft + " VND");
        txtBillAbate.setText(ft * percent + " VND");
    }

    public void checkPhone() throws SQLException {
        CustomerServices cs = new CustomerServices();
        System.err.println(txtPhone.getText());
        customer c = cs.getCus(Integer.parseInt(txtPhone.getText()));
        System.out.println(c.getBirthDay());
        //month and day of birth
        Calendar cal = Calendar.getInstance();
        cal.setTime(c.getBirthDay());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        //current time
        LocalDate myLocalDate = LocalDate.now();
        int dayOfMonth = myLocalDate.getDayOfMonth();
        int monthValue = myLocalDate.getMonthValue();
        System.out.println(day);

        if (ft > 1000 && day==dayOfMonth && month==monthValue) {
            percent = 0.9;
        }
        fundsTotal();
    }

// 
//    public boolean updateAmount() throws SQLException {
//        for (product_bill p : dspb) {
//            if (p.getIdProduct().equals(txtProductID.getText())) {
//                p.setAmount(p.getAmount() + orderQuantity.getValue());
//                return false;
//            }
//        }
//        return true;
//    }
    public void loadCol() {
        col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_promotionPrice.setCellValueFactory(new PropertyValueFactory<>("proPrice"));

    }

    public void loadTableView() {
        if (dspb.size() > 0) {
            this.tbv_Product.setItems(dspb);
        }
        //System.out.println(dspb.get(0).getAmount());

    }

    //Get promotion
    public promotion getPromotion(String id) throws SQLException {
        connect = JdbcUtils.getConn();
        String sql = "SELECT * FROM promotion WHERE id = ?";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, id);
        ResultSet rs = prepare.executeQuery();
        promotion c = new promotion();
        if (rs.next()) {
            promotion p = new promotion(
                    rs.getString("id"),
                    rs.getInt("discount")
            );
            return p;
        };
        return c;
    }

    //    Set spinner
    private SpinnerValueFactory<Integer> spinn;

    public void orderSpinner() {
        spinn = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 1);
        orderQuantity.setValueFactory(spinn);
    }
    private int qty;

    public void orderQuantity() {
        qty = orderQuantity.getValue();
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void logout() throws IOException {
        logoutBtn.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        sw = new SwitchPage(root);
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            availableFD_form.setVisible(false);
            order_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            avaialbeFD_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            order_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

        } else if (event.getSource() == avaialbeFD_btn) {
            dashboard_form.setVisible(false);
            availableFD_form.setVisible(true);
            order_form.setVisible(false);

            avaialbeFD_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            dashboard_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            order_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

        } else if (event.getSource() == order_btn) {
            dashboard_form.setVisible(false);
            availableFD_form.setVisible(false);
            order_form.setVisible(true);

            order_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            avaialbeFD_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            dashboard_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Singleton singleton = Singleton.getInstance();
        username.setText(" " + singleton.getName());
        orderSpinner();
        loadCol();

    }

}
