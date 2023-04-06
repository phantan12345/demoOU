/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import setting.JdbcUtils;
import tan.pojo.customer;
import tan.pojo.product;

/**
 *
 * @author admin
 */
public class CustomerServices {

    public CustomerServices() {
    }

    
    public customer getCus(int phone) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM customer WHERE phoneNumber = ?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, phone);
            ResultSet rs = prepare.executeQuery();
            customer c = new customer();
            if (rs.next()) {
                customer p = new customer(
                        rs.getString("fullName"),
                        rs.getString("birthDay"),
                        rs.getInt("phoneNumber")
                );
                return p;
            };
            return c;
        }
    }
    public List<customer> loadCus() throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM customer";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            List<customer> cusList=new ArrayList<>();
            while (rs.next()) {
                customer p = new customer(
                        rs.getString("fullName"),
                        rs.getString("birthDay"),
                        rs.getInt("phoneNumber")
                );
                cusList.add(p);
            };
            return cusList;
        }
    }

    public void saveCus(customer c) throws SQLException {
        String sql = "INSERT INTO customer VALUES(?,?, ?, ?)";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, c.getId());
        prepare.setString(2, c.getFullName());
        prepare.setString(3, c.getBirthDay());
        prepare.setInt(4, c.getPhoneNumber());
        prepare.executeUpdate();
    }

}
