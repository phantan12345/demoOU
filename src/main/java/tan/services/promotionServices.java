package tan.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import setting.Info;

import setting.JdbcUtils;
import tan.pojo.product;
import tan.pojo.promotion;

public class promotionServices {

    Info in = new Info();

    public List<promotion> getPromotion() throws SQLException {
        List<promotion> ds = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM promotion");
            while (rs.next()) {
                promotion p = new promotion(rs.getString("id"),
                        rs.getInt("discount"),
                         rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getInt("active")
                );
                ds.add(p);

            }
        }
        return ds;
    }

    public promotion getPromotion(String id) throws SQLException {
        promotion b = new promotion();
        String sql = "SELECT * FROM promotion where id=?";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, id);
        ResultSet rs = prepare.executeQuery();
        if (rs.next()) {
            promotion c = new promotion(rs.getString("id"),
                    rs.getInt("discount"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    rs.getInt("active")
            );
            return c;
        }
        return b;
    }

    public boolean addPromotion(promotion p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {

            conn.setAutoCommit(false);
            String sql = "INSERT INTO promotion(id,discount,startDate,endDate,active) VALUES(?, ?, ?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, p.getId());
            stm.setInt(2, p.getDiscount());
            stm.setDate(3, p.getStar());
            stm.setDate(4, p.getEnd());
            stm.setInt(5, p.getAative());

            stm.executeUpdate();

            try {
                conn.commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean deletePromotion(String p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM promotion where id=?";
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, p);
            return stm.executeUpdate() > 0;
        }

    }

    public boolean updatePromotion(promotion p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "UPDATE promotion set discount=?,startDate=?,endDate=? where id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, p.getDiscount());
            stm.setDate(2, p.getStar());
            stm.setDate(3, p.getEnd());
            stm.setString(4, p.getId());

            return stm.executeUpdate() > 0;
        }

    }

    public boolean checkPromotion(promotion p) throws SQLException {

        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM promotion WHERE discount=? and startDate=? and endDate=?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);

            prepare.setInt(1, p.getDiscount());
            prepare.setDate(2, p.getStar());
            prepare.setDate(3, p.getEnd());

            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
                in.infoBox("Registered phone number", "Check Member", "");
                return true;

            };
            return false;
        }
    }

    public boolean checkday(String p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "UPDATE promotion set active=? where startDate<? && endDate<?";

            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, 0);
            stm.setString(2, p);
            stm.setString(3, p);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean checkActive(promotion pr) {
        if (pr.getAative() == 0) {
            return true;
        }
        return false;
    }
}