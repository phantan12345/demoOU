/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import setting.JdbcUtils;
import tan.pojo.product_bill;
/**
 *
 * @author admin
 */
public class Product_billServices {
    public void saveProduct_bill(ObservableList<product_bill> dspb ) throws SQLException{
        Connection conn = JdbcUtils.getConn();
        String sql = "INSERT INTO product_bill(amount,proPrice,idPD) values(?,?,?);";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        for(product_bill pb:dspb){
                    System.out.println(pb.getName());
                    prepare.setInt(1, pb.getAmount());
                    prepare.setInt(2, pb.getProPrice());
                    prepare.setString(3, pb.getIdProduct());
                    prepare.executeUpdate();
        }
    }
}
