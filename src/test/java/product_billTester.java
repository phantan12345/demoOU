/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import setting.JdbcUtils;
import tan.pojo.product_bill;
import tan.services.Product_billServices;

public class product_billTester {

    private static Connection conn;
    private static Product_billServices product_billServices = new Product_billServices();
    private static product_bill pro;
    ObservableList<product_bill> dspb = FXCollections.observableArrayList();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(product_billTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll() throws Exception {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(product_billTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void saveProduct_billTester() throws SQLException {
        pro = new product_bill("coca", "chai", 10000, 10);
        pro.setIdBill(null);
        pro.setIdProduct(null);
        dspb.add(pro);

        product_billServices.saveProduct_bill(dspb);
        String sql = "Select * from billdetail where idBill IS NULL";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
//        prepare.setString(1, "NULL");
        ResultSet rs = prepare.executeQuery();
        int countpb = 0;
        while (rs.next()) {
            ++countpb;
        }
        Assertions.assertEquals(countpb, 1);
    }
}