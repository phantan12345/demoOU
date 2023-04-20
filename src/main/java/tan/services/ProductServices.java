package tan.services;

/**
 *
 * @author admin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import setting.Info;
import setting.JdbcUtils;
import tan.pojo.product;
import tan.pojo.product_bill;

/**
 *
 * @author ADMIN
 */
public class ProductServices {

    Info in = new Info();
    Product_billServices pb = new Product_billServices();

    public List<product> getProducts() throws SQLException {
        List<product> ds = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery("SELECT * FROM product");
            while (rs.next()) {
                product p = new product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getDouble("status"),
                        rs.getString("idPr"),
                        rs.getString("barcode")
                );
                ds.add(p);
            }
        }
        return ds;
    }

    public boolean addProduct(product p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO product(id,barcode,name,type,price,status,idPr) VALUES( ?,?, ?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, p.getId());
            stm.setString(2, p.getBarcode());
            stm.setString(3, p.getName());
            stm.setString(4, p.getType());
            stm.setInt(5, p.getPrice());
            stm.setDouble(6, p.getStatus());
            stm.setString(7, p.getIdKM());

            stm.executeUpdate();

            try {
                conn.commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

    }

    public void deleteProduct(String id) throws SQLException {
        pb.loadProduct_bill(id);
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM product where id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);
            stm.executeUpdate();
//            in.infoBox("Success", "Delete Product", "1");
        } catch (Exception e) {
//            in.infoBox("No Success", "Delete Product", "-1");
        }

    }

    public boolean checkBarcode(String barcode) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM product WHERE barcode = ?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, barcode);
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
                return false;
            }
        }
        in.infoBox("Barcodes don't exist", "Check Barcode", "0");
        return true;
    }

    public product getProduct(String barcode) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM product WHERE barcode = ?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, barcode);
            ResultSet rs = prepare.executeQuery();
            product c = null;
            if (rs.next()) {

                product p = new product(
                        rs.getString("id"),
                        rs.getString("barcode"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getDouble("status")
                );
                p.setIdKM(rs.getString("idPr"));
                return p;
            };
            return c;
        }
    }

      public product getProductByID(String id) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM product WHERE id = ?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, id);
            ResultSet rs = prepare.executeQuery();
            product c = null;
            if (rs.next()) {

                product p = new product(
                        rs.getString("id"),
                        rs.getString("barcode"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("price"),
                        rs.getDouble("status")
                );
                p.setIdKM(rs.getString("idPr"));
                return p;
            }
            return c;
        }
    }
    
    public boolean updateProduct(product p) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "UPDATE product set id=?,name=?,type=?,price=?,status=?,idPr=? where id=?";

            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, p.getId());
            stm.setString(2, p.getName());
            stm.setString(3, p.getType());
            stm.setInt(4, p.getPrice());
            stm.setDouble(5, p.getStatus());
            stm.setString(6, p.getIdKM());
            stm.setString(7, p.getId());

            return stm.executeUpdate() > 0;
        }

    }

    public void updateAmount(ObservableList<product_bill> dspb) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "UPDATE product SET status = status-? WHERE id =?";
            Connection connect = JdbcUtils.getConn();
            PreparedStatement prepare = connect.prepareStatement(sql);
            for (product_bill pb : dspb) {
                prepare.setDouble(1, pb.getAmount());
                prepare.setString(2, pb.getIdProduct());
                prepare.executeUpdate();
            }
        }
    }

   
}