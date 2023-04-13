package tan.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Statement;
import setting.Info;

import setting.JdbcUtils;
import tan.pojo.branch;
import tan.pojo.employee;
import tan.services.branchServices;

public class employeeServices {

    public branchServices branch = new branchServices();
    Info in = new Info();

    public List<employee> getEmployees() throws SQLException {
        List<employee> ds = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                employee b = new employee(
                        rs.getString("id"),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber"),
                        rs.getString("idBr"),
                        rs.getInt("Active"));
                ds.add(b);

                b.setNamebr(branch.getBranch(b.getIdbr()).getAddress());

            }
        }

        return ds;
    }

    public boolean addEmployee(employee b) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {

            conn.setAutoCommit(false);
            String sql = "INSERT INTO employee(id,fullName,password,phoneNumber,active,idBr) VALUES(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, b.getId());
            stm.setString(2, b.getName());
            stm.setString(3, b.getPassword());
            stm.setString(4, b.getPhone());
            stm.setInt(5, b.getActive());
            stm.setString(6, b.getIdbr());

            stm.executeUpdate();

            try {
                conn.commit();
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
    }

    public boolean deleteEmployee(String p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM employee where id=?";
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, p);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateEmployee(employee p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "UPDATE branch set fullName=?,phoneNumber=?,idBr=? where id=?";

            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, p.getName());
            stm.setString(2, p.getPhone());
            stm.setString(3, p.getIdbr());
            stm.setString(4, p.getId());

            return stm.executeUpdate() > 0;
        }
    }

    public employee checkEmployee(String phone, String password) throws SQLException {
        employee c = null;
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM employee WHERE phoneNumber = ? and password = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, phone);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                employee e = new employee(rs.getString("id"),
                        rs.getString("fullName"),
                        rs.getString("phoneNumber"),
                        rs.getString("idBr"),
                        rs.getInt("active"));
                return e;
            };
            return c;
        }

    }
}
