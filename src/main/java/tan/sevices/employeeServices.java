package tan.sevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Statement;

import setting.JdbcUtils;
import tan.pojo.employee;

public class employeeServices {

    public List<employee> getEmployees() throws SQLException {
        List<employee> ds = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                employee b = new employee(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("idbr"),
                        rs.getInt("active"));
                ds.add(b);
                
            }
        }
        return ds;
    }

    public boolean addEmployee(employee b) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {

            conn.setAutoCommit(false);
            String sql = "INSERT INTO employee(id,name,phone,active,idbr) VALUES(?,?,?,?,?)";
            PreparedStatement stm =conn.prepareCall(sql);
            stm.setString(1, b.getId());
            stm.setString(2, b.getName());
            stm.setString(3, b.getPhone());
            stm.setInt(4, b.getActive());
            stm.setString(5, b.getIdbr());
           
            stm.executeUpdate();

            
            try {
                conn.commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
