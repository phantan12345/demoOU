/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.oumarket;

import java.io.IOException;
import java.sql.Connection;
import setting.JdbcUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import setting.Info;
import setting.Singleton;
import setting.SwitchPage;

/**
 *
 * @author admin
 */
public class ChangePassword {

    @FXML
    private Button changeBtn;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    private Connection connect;
    private PreparedStatement prepare;

    public void changePass() throws SQLException, IOException {
        Info in = new Info();
        Checkpassword check = new Checkpassword();
        connect = JdbcUtils.getConn();
        if (check.validate(password1.getText())) {
            if (password1.getText().equals(password2.getText())) {
                Singleton singleton = Singleton.getInstance();
                in.infoBox(String.valueOf(singleton.getName()), "Information Message", "1");
                String sql = "UPDATE employee set password = ? WHERE id = ?";
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, password1.getText());
                // Lấy đối tượng Singleton
                prepare.setString(2, singleton.getUserID());
                // prepare.setInt(2, data.id_user);
                prepare.executeUpdate();
                connect.close();
                changeBtn.getScene().getWindow().hide();

                // LINK YOUR DASHBOARD
                if (singleton.getActive() == 0) {
                    Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                    SwitchPage sw = new SwitchPage(root);

                } else {
                    Parent root = FXMLLoader.load(getClass().getResource("employee.fxml"));
                    SwitchPage sw = new SwitchPage(root);

                }

            } else {
                in.infoBox("password does not match", "Error Message", "2");
                in.infoBox("password does not match", "Error Message", "2");
                in.infoBox("password does not match", "Error Message", "2");
                in.infoBox(password1.getText(), password2.getText(), "");
            }
        } else {
            in.infoBox("the password is not safe", "Error Message", "2");
        }
    }

}
