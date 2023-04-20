package tan.oumarket;

import java.io.IOException;
import java.net.URL;
import java.security.interfaces.DSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import setting.CheckText;
import setting.Info;
import setting.Singleton;
import setting.SwitchPage;
import setting.data;
import setting.JdbcUtils;
import tan.pojo.bill;
import tan.pojo.customer;
import tan.pojo.product;
import tan.pojo.product_bill;
import tan.pojo.promotion;
import tan.services.BillServices;
import tan.services.CustomerServices;
import tan.services.ProductServices;
import tan.services.Product_billServices;
import tan.services.promotionServices;
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
    private TextField txtFundsTotal;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtBillAbate;

    @FXML
    private Button checkBtn;

    @FXML
    private Button addCusBtn;

    @FXML
    private TextField txtFullName;

    @FXML
    private DatePicker dpBirthDay;

    @FXML
    private TextField txtPhoneCus;

    @FXML
    private TableColumn<?, ?> col_FullName;

    @FXML
    private TableColumn<?, ?> col_BirthDay;

    @FXML
    private TableColumn<?, ?> col_PhoneNumber;

    @FXML
    private TableView<customer> tbv_Cus;

    @FXML
    private TextField txtCash;

    @FXML
    private TextField txtQuantity;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private SwitchPage sw;
    private int ft = 0;
    private int abe;
    private double percent = 1;
    private CustomerServices customerServices = new CustomerServices();
    private ObservableList<customer> cusList = FXCollections.observableArrayList();

    ObservableList<product_bill> dspb = FXCollections.observableArrayList();
    ProductServices pds;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("#,###₫");
    CheckText checkText = new CheckText();
    Info info = new Info();
    product_bill pb;
    String idcus = null;
    promotionServices pS = new promotionServices();
    Singleton singleton = Singleton.getInstance();

    public void addBill() throws SQLException {
        String Quantity=checkText.removeExtraSpaces(txtQuantity.getText());
        String code = txtProductID.getText();
        ProductServices bProductServices = new ProductServices();
        if (checkText.checkEmpty(code) || bProductServices.checkBarcode(code)||checkText.checkEmpty(Quantity)) {
            return;
        }
        pds = new ProductServices();
        product p = pds.getProduct(txtProductID.getText());
        if(checkText.checkQuantity(Quantity, p.getType())){
            return;
        }
        Double quan=Double.parseDouble(Quantity);
        if(p.getStatus()<quan){
            info.infoBox("Wrong", "Quantity", "-1");
            return;
        }

        pb = new product_bill(p.getName(), p.getType(), p.getPrice(), quan);
        pb.setIdProduct(p.getId());
        promotion pro = pS.getPromotion(p.getIdKM());
        System.out.println( pS.getPromotion(p.getIdKM()).getDiscount());
        int discount = 0;
        System.out.print(pro.getId());
        System.out.print(pro.getAative());
        System.out.print(pro.getDiscount());
        if (pS.checkActive(pro)) {
            pro.setDiscount(0);
        }
        if (updateAmount(p.getId(), pro.getDiscount(),quan) ) {
            dspb.add(pb);
        }
        System.out.print(pro.getAative());
        System.out.print(pro.getDiscount());
        int price = (int) Math.round((pb.getPrice() - pb.getPrice() * pro.getDiscount() / 100) * pb.getAmount());
        pb.setProPrice(price);
        this.tbv_Product.refresh();
        loadTableView();
        txtQuantity.setText("");
        txtProductID.setText("");
        fundsTotal();
        info.mess();
    }

    public void deleteBill() throws SQLException {
        info.conFir();
        product_bill pb = tbv_Product.getSelectionModel().getSelectedItem();
        dspb.remove(pb);
        loadTableView();
        fundsTotal();
    }

    public void ClearBill() throws SQLException {
        if (info.conFir()) {
            return;
        }
        Clear();
    }

    public void Clear() {
        txtPhone.setText("");
        txtProductID.setText("");
        txtCash.setText("");
        txtQuantity.setText("");
        dspb.clear();
        tbv_Product.getItems().clear();
        ft = 0;
        txtPhone.setText("");
        fundsTotal();
    }

    public void completeBill() throws SQLException, IOException {
        int funds = (int) (ft * percent);
        if (dspb.size() == 0) {
            info.infoBox("Bill Empty", "Bill", "-1");
            return;
        }
        if (info.conFir() || checkText.checkTotal(txtCash.getText(), funds)) {
            return;
        }
        Product_billServices pdServices = new Product_billServices();
        bill b = new bill((int) (ft * percent));
        BillServices bs = new BillServices();
        bs.saveBill(b, idcus);
        for (product_bill p : dspb) {
            p.setIdBill(b.getId());
            singleton.getPdsList().add(p);
        }
        b.setCash(Integer.parseInt(txtCash.getText()));
        singleton.setB(b);
       singleton.setPdsList(dspb);
        pdServices.saveProduct_bill(dspb);
        
        pds.updateAmount(dspb);
        Clear();
        Parent root = FXMLLoader.load(getClass().getResource("bill.fxml"));
        sw = new SwitchPage(root);
    }

    public void fundsTotal() {
        ft = 0;
        for (product_bill p : dspb) {
            ft += p.getProPrice();
        }
        txtFundsTotal.setText(decimalFormat.format(ft));
        txtBillAbate.setText(decimalFormat.format(Math.round(ft * percent)));
    }

    public void checkPhone() throws SQLException {
        String phone = txtPhone.getText();
        if (checkText.checkEmpty(phone) || checkText.checkPhone(phone)) {
            return;
        }
        CustomerServices cs = new CustomerServices();
        customer c = cs.getCus(phone);
        if (c.getPhoneNumber() != null) {
            idcus = c.getId();
        }
        LocalDate myLocalDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateTime = myLocalDate.format(formatter);
        if (c != null && ft > 1000000 && formattedDateTime.contains(c.getBirthDay().substring(0, 5))) {
            percent = 0.9;
        }
        fundsTotal();
    }

    public boolean old(DatePicker dp) {
        int selectedYear = dp.getValue().getYear();
        int currentYear = LocalDate.now().getYear();
        if (currentYear - selectedYear <= 10) {
            info.infoBox("Invalid birthDay", "birthDay", "-1");
            return true;
        }
        return false;
    }

    public void addCus() throws SQLException {
        String name = checkText.removeExtraSpaces(txtFullName.getText());
        String birthDay;
        try {
            birthDay = dpBirthDay.getValue().format(formatter);
        } catch (Exception e) {
            checkText.checkEmpty(-1);
            return;
        }
        String phone = txtPhoneCus.getText();
        if (checkText.checkEmpty(birthDay) || checkText.checkEmpty(name)
                || checkText.checkEmpty(phone) || checkText.checkPhone(phone) || customerServices.checkCus(phone)
                || old(dpBirthDay) || checkText.containsSpecialCharacter(name)) {
            return;
        }
        customer cus = new customer(name, birthDay, phone);
        customerServices.saveCus(cus);
        cusList.add(cus);
        info.infoBox("ADD SUCCES", "Check Member", "1");
        loadTableCus();
    }

    public boolean updateAmount(String kw, int discount,double quantity) throws SQLException {
        if (dspb.size() > 0) {
            for (product_bill p : dspb) {
                if (p.getIdProduct().equals(kw)) {
                    p.setAmount(quantity + p.getAmount());
                    p.setProPrice((int) ((p.getPrice() - p.getPrice() * discount / 100) * p.getAmount()));
                    pb.setAmount(p.getAmount());
                    return false;
                }
            }
            return true;
        }
        return true;

    }

    public void loadColCus() {
        col_FullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        col_BirthDay.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        col_PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    public void loadTableCus() throws SQLException {
        this.tbv_Cus.setItems(cusList);
    }

    public void loadCol() {
        col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_promotionPrice.setCellValueFactory(new PropertyValueFactory<>("proPrice"));
    }

    public void loadTableView() {
        if (dspb.size() > 0) {
//            tbv_Product.getItems().clear();
            tbv_Product.setItems(dspb);

        }
    }



    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void logout() throws IOException {
        if (info.conFir()) {
            return;
        }
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
        loadCol();
        loadColCus();
        try {
            cusList = FXCollections.observableList(customerServices.loadCus());
            loadTableCus();
        } catch (SQLException ex) {
            Logger.getLogger(employeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtBillAbate.setEditable(false);
        txtBillAbate.setFocusTraversable(false);
        txtFundsTotal.setEditable(false);
        txtFundsTotal.setFocusTraversable(false);
        dpBirthDay.setFocusTraversable(false);
        dpBirthDay.setEditable(false);
    }

}
