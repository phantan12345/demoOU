
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tan.pojo.customer;
import tan.services.CustomerServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import setting.Info;
import setting.JdbcUtils;

public class CustomerTester {

    private static Connection conn;
    private static final CustomerServices s = new CustomerServices();
    private static customer c = new customer();

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void afterAll() throws Exception {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testGetCus() {
        String phone = "0";
        try {
            c = s.getCus(phone);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assertions.assertEquals(phone, c.getPhoneNumber());
    }

    @Test
    public void testNameNotNull() {
        try {
            List<customer> cates = s.loadCus();
            long r = cates.stream().filter(c -> c.getPhoneNumber() == null).count();
            Assertions.assertTrue(r == 0);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetCus1() {
        String phone = "1110000";
        try {
            c = s.getCus(phone);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assertions.assertEquals(phone, c.getPhoneNumber());
    }

    @Test
    public void testUniquePhone() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM customer");
            List<String> kq = new ArrayList<>();
            while (rs.next()) {
                String phone = rs.getString("phoneNumber");
                kq.add(phone);
            }
            Set<String> kq2 = new HashSet<>(kq);
            System.out.println(kq2);
            Assertions.assertEquals(kq.size(), kq2.size());
        }
    }
}
