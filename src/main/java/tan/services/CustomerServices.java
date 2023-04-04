/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import setting.JdbcUtils;
import tan.pojo.customer;
import tan.pojo.product;

/**
 *
 * @author admin
 */
public class CustomerServices {
    public customer getCus(int phone) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM customer WHERE phoneNumber = ?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, phone);
            ResultSet rs = prepare.executeQuery();
            customer c = new customer();
            if(rs.next()){
                customer p = new customer(
                    rs.getInt("id"),
                    rs.getString("fullName"),
                    rs.getDate("birthDay"),
                    rs.getInt("phoneNumber")
                    );
                return p;
            };
            return c;
        }
    }
    
}
