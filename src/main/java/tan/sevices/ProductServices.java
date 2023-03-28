/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.sevices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import setting.JdbcUtils;
import tan.pojo.product;

/**
 *
 * @author ADMIN
 */
public class ProductServices {
    public List<product> getProducts() throws SQLException {
        List<product> ds = new ArrayList<>();
        try ( Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM product");
            while (rs.next()) {
                product p = new product(
                        rs.getInt("id"), 
                        rs.getString("name")
                        , rs.getString("type"),
                        rs.getInt("price"),
                        rs.getString("status"));
                ds.add(p);
            }
        }
        return ds;
    }
    
}
