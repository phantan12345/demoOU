package tan.sevices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import setting.JdbcUtils;
import tan.pojo.promotion;

public class promotionServices {
    
    public List<promotion> getPromotion() throws SQLException {
        List<promotion> ds = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM promotion");
            while (rs.next()) {
                
                promotion p = new promotion(rs.getString("id"),rs.getInt("discount"));
                ds.add(p);
                
            }
        }
        return ds;
}
}
