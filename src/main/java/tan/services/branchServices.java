package tan.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import setting.JdbcUtils;
import tan.pojo.branch;

public class branchServices {


    
    public List<branch> getBranchs() throws SQLException {
        List<branch> ds = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM branch");
            while (rs.next()) {
                
                branch b = new branch(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"));
                ds.add(b);
                
            }
        }
        return ds;
    }


    public branch getBranch(String id) throws SQLException {
        branch b=null;
        String sql="SELECT * FROM branch where id=?";
        Connection connect = JdbcUtils.getConn();
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setString(1, id);
        ResultSet rs = prepare.executeQuery();
        if (rs.next()) {
                
            branch c = new branch(
                   rs.getString("id"),
                   rs.getString("name"),
                   rs.getString("address"));

           return c;
       }

        return b;
    }


    public boolean addBranch(branch b) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {

            conn.setAutoCommit(false);
            String sql = "INSERT INTO branch(id,name,address) VALUES(?, ?, ?)";
            PreparedStatement stm =conn.prepareCall(sql);
            stm.setString(1, b.getId());
            stm.setString(2, b.getName());
            stm.setString(3, b.getAddress());
            stm.executeUpdate();
           
            
            try {
                conn.commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean deleteBranch(String p) throws SQLException{
        try(Connection conn=JdbcUtils.getConn()){
            String sql="DELETE FROM branch where id=?";
            PreparedStatement stm=conn.prepareCall(sql);

            stm.setString(1, p);
            return stm.executeUpdate() > 0;
        }

    }

    public boolean updateBranch(branch p) throws SQLException{
        try(Connection conn=JdbcUtils.getConn()){
            String sql="UPDATE branch set   name=?,address=? where id=?";

            PreparedStatement stm=conn.prepareCall(sql);

            stm.setString(1, p.getName());
            stm.setString(2, p.getAddress());
            stm.setString(3, p.getId());
            return stm.executeUpdate() > 0;
        }

    }
    
}
