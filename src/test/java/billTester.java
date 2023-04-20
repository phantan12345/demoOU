import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import tan.services.BillServices;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author admin
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import setting.JdbcUtils;
import tan.pojo.bill;

public class billTester {
    
    private static BillServices b = new BillServices();
    private static Connection conn;
    private static bill billTester;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(billTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void afterAll() throws Exception {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(billTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test

    public void saveBillTest() throws SQLException {
        billTester = new bill(10000);
        String id = null;
        b.saveBill(billTester, id);
        String sql = "Select * from bill where id=?";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, billTester.getId());
        ResultSet rs = prepare.executeQuery();
        Assertions.assertTrue(rs.next());
    }
    
}