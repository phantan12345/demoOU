

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import setting.Info;
import setting.JdbcUtils;
import tan.pojo.employee;
import tan.services.branchServices;
import tan.services.employeeServices;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 84377
 */
public class EmployeeTest {
    
    private final employeeServices employeeServices = new employeeServices();

    @Test
    public void testAddEmployee() throws SQLException {
        // create an employee object
        employee emp = new employee("Son", "123111222", 0, null);

        // add the employee to the database
        boolean added = employeeServices.addEmployee(emp);

        // verify that the employee was added successfully
        Assertions.assertTrue(added);
    }
    
    @Test
    public void testDeleteEmployee() throws SQLException {
    // Thêm một nhân viên vào CSDL để xóa sau đó
    employee emp = new employee("Son", "123111222", 0, null);
    employeeServices.addEmployee(emp);

    // Lấy id của nhân viên mới thêm để xóa
    String idToDelete = emp.getId();

    // Gọi phương thức xóa nhân viên theo id
    employeeServices.deleteEmployee(idToDelete);

    // Kiểm tra xem nhân viên có còn trong danh sách nhân viên hay không
    List<employee> empList = employeeServices.getEmployees();
    boolean isDeleted = true;
    for(employee e : empList) {
        if(e.getId().equals(idToDelete)) {
            isDeleted = false;
            break;
        }
    }

    // Kiểm tra kết quả
    Assertions.assertTrue(isDeleted);
    }
    
    @Test
    public void testUpdateEmployee() throws SQLException{
        employee emp = new employee("Son", "123111222", 0, null);
        employeeServices.addEmployee(emp);
        
        emp.setName("Hoan");
        emp.setPhone("09221112");
        
        boolean updated = employeeServices.updateEmployee(emp);
        
        Assertions.assertTrue(updated);
        
    }
    
    @Test
    public void testPhone()throws SQLException {
        String existingPhone = "123456789";
        
        // Act
        boolean result = employeeServices.checkPhone(existingPhone);
        
        // Assert
        assertTrue(result);
    
    }
    @Test
    public void testNoPhone()throws SQLException {
        String nonexistingPhone = "123456789";
        
        // Act
        boolean result = employeeServices.checkPhone(nonexistingPhone);
        
        // Assert
        Assertions.assertFalse(result);
    
    }
}       
    
