/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import setting.JdbcUtils;
import setting.Singleton;
import tan.pojo.bill;
import tan.pojo.customer;
import tan.pojo.product_bill;

/**
 *
 * @author admin
 */
public class BillServices {

    public void saveBill(bill b, String id) throws SQLException {
        Singleton singleton = Singleton.getInstance();
        String sql = "INSERT INTO bill values(?,?,?,?,?)";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, b.getId());
        prepare.setString(2, b.getPayDate());
        prepare.setInt(3, b.getTotal());
        //       prepare.setString(4, null);
        prepare.setString(4, singleton.getUserID());
        prepare.setString(5, id);

        prepare.executeUpdate();
    }
}
