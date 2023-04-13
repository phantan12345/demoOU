/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tan.pojo.customer;
import tan.services.CustomerServices;

/**
 *
 * @author admin
 */
public class CustomerTester {

    CustomerServices cS = new CustomerServices();
    customer c;

    @BeforeAll
    public static void beforeAll() {

    }

    @AfterAll
    public static void afterAll() {

    }

    @Test
    public void testGetCus() {
        String phone= "0377264480";
        try {
            c=cS.getCus(phone);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assertions.assertEquals(phone, c.getPhoneNumber());
    }
}
