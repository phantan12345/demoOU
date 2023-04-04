/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import setting.JdbcUtils;
import tan.pojo.product;

/**
 *
 * @author ADMIN
 */
public class ProductServices {

    public List<product> getProducts() throws SQLException {
        List<product> ds = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM product");
            while (rs.next()) {
                
                product p = new product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getInt("status"));
                ds.add(p);
                
            }
        }
        return ds;
    }


    public boolean addProduct(product p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {

            conn.setAutoCommit(false);
            String sql = "INSERT INTO product(id,name,type,price,status,km_id) VALUES(?, ?, ?,?,?,?)";
            PreparedStatement stm =conn.prepareCall(sql);

            stm.setString(1, p.getId());
            stm.setString(2, p.getName());
            stm.setString(3, p.getType());
            stm.setInt(4, p.getPrice());
            stm.setInt(5, p.getStatus());
            stm.setString(6, p.getKm_id());

            stm.executeUpdate();
           
            
            try {
                conn.commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        
       
       
    }

    public boolean deleteProduct(String p) throws SQLException{
        try(Connection conn=JdbcUtils.getConn()){
            String sql="DELETE FROM product where id=?";
            PreparedStatement stm=conn.prepareCall(sql);

            stm.setString(1, p);
            return stm.executeUpdate() > 0;
        }

    }

    public boolean updateProduct(product p) throws SQLException{
        try(Connection conn=JdbcUtils.getConn()){
            String sql="UPDATE product set id=?,name=?,type=?,price=?,status=?,km_id=? where id=?";

            PreparedStatement stm=conn.prepareCall(sql);

            stm.setString(1, p.getId());
            stm.setString(2, p.getName());
            stm.setString(3, p.getType());
            stm.setInt(4, p.getPrice());
            stm.setInt(5, p.getStatus());
            stm.setString(6, p.getKm_id());
            stm.setString(7, p.getId());


            return stm.executeUpdate() > 0;
        }

    }
}

