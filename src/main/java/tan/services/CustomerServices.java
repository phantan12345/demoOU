/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.services;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import static java.lang.System.in;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import setting.Info;
import setting.JdbcUtils;
import tan.pojo.customer;
import tan.pojo.product;

/**
 *
 * @author admin
 */
public class CustomerServices {
   Info in = new Info();
    public CustomerServices() {
    }
    

    public customer getCus(String phone) throws SQLException {
           Info in = new Info();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM customer WHERE phoneNumber = ?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, phone);
            ResultSet rs = prepare.executeQuery();
            customer c = new customer();
            if (rs.next()) {
                customer p = new customer(
                        rs.getString("id"),
                        rs.getString("fullName"),
                        rs.getString("birthDay"),
                        rs.getString("phoneNumber")
                );
//                in.infoBox("Promotion applied", "Check Member", "1");
                return p;
            };
//            in.infoBox("Not registered as a member", "Check Member", "");
            return c;
        }
    }

    public boolean checkCus(String phone) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM customer WHERE phoneNumber = ?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, phone);
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
//                in.infoBox("Registered phone number", "Check Member", "");
                return true;

            };
            return false;
        }
    }

    public List<customer> loadCus() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM customer";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            List<customer> cusList = new ArrayList<>();
            while (rs.next()) {
                customer p = new customer(
                        rs.getString("fullName"),
                        rs.getString("birthDay"),
                        rs.getString("phoneNumber")
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
        prepare.setString(4, c.getPhoneNumber());
        prepare.executeUpdate();
    }

}
