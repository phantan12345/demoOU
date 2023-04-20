
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import setting.JdbcUtils;
import tan.pojo.branch;
import tan.pojo.customer;
import tan.services.CustomerServices;
import tan.services.branchServices;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class BranchTester {
     private static Connection conn;
     private static branchServices b = new branchServices();
     @BeforeAll
     public static void beforeAll() {
         try {
             conn = JdbcUtils.getConn();
         } catch (SQLException ex) {
             Logger.getLogger(BranchTester.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     @AfterAll
     public static void afterAll() throws Exception {
         if (conn != null) {
             try {
                 conn.close();
             } catch (SQLException ex) {
                 Logger.getLogger(BranchTester.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
     
     
     @Test
     public void testUniqueName() throws SQLException {
         try (Connection conn = JdbcUtils.getConn()) {
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM branch");
             List<String> kq = new ArrayList<>();
             while (rs.next()) {
                 String phone = rs.getString("name");
                 kq.add(phone);
             }

             Set<String> kq2 = new HashSet<>(kq);
             System.out.println(kq2);
             Assertions.assertEquals(kq.size(), kq2.size());
         }
     }
     
     @Test
     public void checkUpdateBranch(){
         String id="1";
         String name="tan";
         try {    
             branch br=b.getBranch(id);
             br.setName("tan");
              Assertions.assertEquals(name, br.getName());

         } catch (SQLException ex) {
             Logger.getLogger(BranchTester.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
}
