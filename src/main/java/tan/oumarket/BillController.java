package tan.oumarket;

/**
 *
 * @author admin
 */
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import setting.Singleton;
import tan.pojo.branch;
import tan.pojo.product_bill;
import tan.services.branchServices;

public class BillController implements Initializable {

    @FXML
    private TableColumn<?, ?> tb;

    @FXML
    private TableView<product_bill> tbv_pb;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> quantity;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private Label txtBranch;

    @FXML
    private Label txtCash;

    @FXML
    private Label txtEM;

    @FXML
    private Label txtEN;

    @FXML
    private Label txtTotal;

    @FXML
    private Label txtbillID;

    @FXML
    private AnchorPane main_form;

    branchServices b = new branchServices();
    Singleton singleton = Singleton.getInstance();
    DecimalFormat decimalFormat = new DecimalFormat("#,###₫");

    public void showbill() throws SQLException {
        int total = singleton.getB().getTotal();
        int cash = singleton.getB().getCash();
        int em = cash - total;
        txtBranch.setText(b.getBranch(singleton.getIDbr()).getAddress());
        System.out.println(b.getBranch(singleton.getIDbr()).getAddress());
        txtEN.setText(singleton.getName());
        txtTotal.setText(decimalFormat.format(total));
        txtbillID.setText(singleton.getB().getId());
        txtCash.setText(decimalFormat.format(cash));
        txtEM.setText(decimalFormat.format(em));
        loadProduct_bill();
    }

    public void loadProduct_bill() throws SQLException {
        System.out.print(singleton.getPdsList());
        tbv_pb.setItems(singleton.getPdsList());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("amount"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        price.setCellValueFactory(new PropertyValueFactory<>("proPrice"));
    }

    public void autoClose() {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000); // Ngủ trong 10 giây
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Stage stage = (Stage) main_form.getScene().getWindow();
                stage.close();
            }
        });

        new Thread(sleeper).start();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadProduct_bill();
            showbill();
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<product_bill> pdsList = FXCollections.observableArrayList();
        singleton.setPdsList(pdsList);
//        autoClose();
    }

}