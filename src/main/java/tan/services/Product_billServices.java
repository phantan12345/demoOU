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
import javax.crypto.interfaces.PBEKey;
import setting.JdbcUtils;
import tan.pojo.product_bill;
/**
 *
 * @author admin
 */
public class Product_billServices {
    
    public void saveProduct_bill(ObservableList<product_bill> dspb ) throws SQLException{
        String sql = "INSERT INTO billdetail values(?,?,?,?,?);";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        for(product_bill pb:dspb){
                    prepare.setString(1, pb.getId());
                    prepare.setDouble(2, pb.getAmount());
                    prepare.setInt(3, pb.getProPrice());
                    prepare.setString(4, pb.getIdProduct());
                    prepare.setString(5, pb.getIdBill());
                    prepare.executeUpdate();
        }
    }
    
    public void loadProduct_bill(String idPro) throws SQLException{
        String sql = "UPDATE billdetail SET idPro = ? WHERE idPro = ?;";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, null);
        prepare.setString(2, idPro);
        prepare.executeUpdate();
    }
}
