
  import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
  import java.sql.SQLException;
 import java.sql.Statement;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
  import java.util.logging.Level;
  import java.util.logging.Logger;
 import javafx.collections.ObservableList;
  import org.junit.jupiter.api.AfterAll;
  import org.junit.jupiter.api.BeforeAll;        
  import org.junit.jupiter.api.Assertions;
  import org.junit.jupiter.api.Test;
  import setting.JdbcUtils;
 import tan.pojo.product;
 import tan.pojo.product_bill;
 import tan.services.ProductServices;
 import tan.services.Product_billServices;
  public class ProductTester {
      private static Connection conn;
      private static ProductServices p =new ProductServices();
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
       //ma co ds sp
     @Test
     public void checkgetProduct() {
         List<product> ds = new ArrayList<>();
         try {
             ds = p.getProducts();
                 Assertions.assertEquals(2,ds.size());
         } catch (SQLException e) {
         }
     }
     //ma k co sp
     @Test
     public void checkgetProduct2() {
           List<product> ds = new ArrayList<>();
         try {
             ds = p.getProducts();
             Assertions.assertEquals(0,ds.size());
         } catch (SQLException e) {
         }
     }
     //Co sp
     @Test
     public void checkgetProductBarcode() {
         String bar = "123456781";
         try {
             product pr = p.getProduct(bar);
             Assertions.assertNotNull(pr);
         } catch (SQLException e) {
         }
     }
     //K co sp
     @Test
     public void checkgetProductBarcode2() {
        String bar = "1234000000";
         try {
             product pr = p.getProduct(bar);
             Assertions.assertNotNull(pr);
         } catch (SQLException e) {
         }
     }
     //Co ma ve de xuat
     @Test
     public void checkUpdateProduct() {
         String bar = "123456781";
         try {
             product pr  = p.getProduct(bar);
             p.updateProduct(pr);
             product pr1 = new product();
             PreparedStatement stm = conn.prepareCall("SELECT * FROM product WHERE barcode=? ");
             stm.setString(1, "123456781");
             ResultSet rs = stm.executeQuery();
             Assertions.assertEquals("1", rs.getString("barcode"));
         } catch (SQLException e) {
         }
     }    
        //Co ma ve de xuat
     @Test
     public void checkUpdateProduct2() {
         String bar = "12345678";
         try {
             product pr  = p.getProduct(bar);
             p.updateProduct(pr);
             product pr1 = new product();
             PreparedStatement stm = conn.prepareCall("SELECT * FROM product WHERE barcode=? ");
             stm.setString(1, "123456781");
             ResultSet rs = stm.executeQuery();
             Assertions.assertEquals("1", rs.getString("barcode"));
         } catch (SQLException e) {
         }
     }
       @Test
      public void testUniqueBarcode() throws SQLException {
          try (Connection conn = JdbcUtils.getConn()) {
              Statement stm = conn.createStatement();
              ResultSet rs = stm.executeQuery("SELECT * FROM product");
              List<String> kq = new ArrayList<>();
              while (rs.next()) {
                  String bar = rs.getString("barcode");
                  kq.add(bar);
              }
              Set<String> kq2 = new HashSet<>(kq);
              System.out.println(kq2);
              Assertions.assertEquals(kq.size(), kq2.size());
          }
      }   
     //Xoa 
     @Test
     public void checkDeleteProduct() {
        String id="7c0553d3-eb4e-40cd-b0e7-c606cf1097d4";
         try {       
           p.deleteProduct((p.getProductByID(id)).getId());
            product p1 =p.getProductByID(id);
             Assertions.assertNull(p1);           
         } catch (SQLException e) {          
         }
     }
  //Xoa ko thanh cong
     @Test
     public void checkDeleteProduct1() {
        String id="7c0553d3-eb4e-40cd-b0e7-c606cf1097d4";
         try {       
           p.deleteProduct((p.getProductByID(id)).getId());
            product p1 =p.getProductByID(id);
             Assertions.assertNull(p1);         
         } catch (SQLException e) {           
         }
     }
    
    
   

    
  }
