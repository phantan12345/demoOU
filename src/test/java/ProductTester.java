
// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import setting.JdbcUtils;

// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */

// /**
//  *
//  * @author ADMIN
//  */
// public class ProductTester {
//     private static Connection conn;
    
    
//     @BeforeAll
//     public static void beforeAll() {
//         try {
//             conn = JdbcUtils.getConn();

//         } catch (SQLException ex) {
//             Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
//         }

//     }

//     @AfterAll
//     public static void afterAll() throws Exception {
//         if (conn != null) {
//             try {
//                 conn.close();
//             } catch (SQLException ex) {
//                 Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
//             }
//         }
//     }
    
    
//     @Test
//     public void testGetProduct(){
        
        
//     }
    
// }
