/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.Date;
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
import tan.pojo.promotion;
import tan.services.branchServices;
import tan.services.promotionServices;

/**
 *
 * @author 84377
 */
public class PromotionTest {
    private final promotionServices promotionServices = new promotionServices();
    
    @Test
    public void addPromotion() throws SQLException{
        promotion newPromo = new promotion(20, Date.valueOf("2023-05-01"), Date.valueOf("2023-06-30"), 1);
        
        boolean isAdded = promotionServices.addPromotion(newPromo);
        
        assertTrue(isAdded);
    }
    @Test
    public void deletePromotion() throws SQLException{
        promotion newPromo = new promotion(20,Date.valueOf("2023-05-01"), Date.valueOf("2023-05-30"), 1);
        boolean isAdded = promotionServices.addPromotion(newPromo);
        
        String idPro = newPromo.getId();
        promotionServices.deletePromotion(idPro);
        
        List<promotion> proList = promotionServices.getPromotion();
    boolean isDeleted = true;
    for(promotion e : proList) {
        if(e.getId().equals(idPro)) {
            isDeleted = false;
            break;
        }
    }
    // Kiểm tra kết quả
    Assertions.assertTrue(isDeleted);
    }
    
    
    @Test
    public void testUpdateEmployee() throws SQLException{
        promotion newPromo = new promotion(20,Date.valueOf("2023-05-01"), Date.valueOf("2023-05-30"), 1);
        boolean isAdded = promotionServices.addPromotion(newPromo);
        
        newPromo.setDiscount(70);
        newPromo.setEnd(Date.valueOf("2023-05-28"));
        
        boolean updated = promotionServices.updatePromotion(newPromo);
        
        Assertions.assertTrue(updated);
        
    }
}
