/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.oumarket;

import setting.data;
import setting.database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import setting.Info;
import setting.Singleton;
import setting.SwitchPage;
import tan.pojo.user;

/**
 *
 * @author WMarcoMan
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;
    
    
    public String usernameString;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;



    
  

    public void login() {
        usernameString = username.getText();
        String sql = "SELECT * FROM user WHERE SDT = ? and password = ?";
        connect = database.connectDb();
        Info info= new Info();
        SwitchPage sw;
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                info.infoBox("Please fill all blank fields", "Error Message", "2");
            } else {
                if (result.next()) {
                    // Lấy đối tượng Singleton
                    Singleton singleton = Singleton.getInstance();

                    // Cập nhật giá trị cho biến toàn cục
                    singleton.setUserID(result.getString("id"));                    
                    singleton.setName(result.getString("name"));
                    
                    if (password.getText().length()>=8){
                    info.infoBox("Successfully Login!", "Information Message", "1");
                    // TO HIDE YOUR LOGIN FORM
                    loginBtn.getScene().getWindow().hide();
                        if(result.getBoolean("active")){
                            // LINK YOUR DASHBOARD ADMIN
                            Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
                            sw=new SwitchPage(root);
                        }else{
                            // LINK YOUR DASHBOARD EMPLOYEE
                            Parent root = FXMLLoader.load(getClass().getResource("employee.fxml"));
                            sw=new SwitchPage(root);
                        }
                    }else {
                        info.infoBox("Please change your password when you first log in!", "Information Message", "1");
                        loginBtn.getScene().getWindow().hide();
                        // LINK YOUR DASHBOARD
                        Parent root = FXMLLoader.load(getClass().getResource("changepass.fxml"));
                        sw=new SwitchPage(root);

                    }
                } else {
                    // IF WRONG USERNAME/PASSWORD YOU'VE ENTERED
                    info.infoBox("Wrong Username/Password", "Error Message", "2");
                }
            }

        } catch (IOException | SQLException e) {
        }

    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
