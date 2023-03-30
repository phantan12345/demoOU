/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package setting;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author admin
 */
public class Info {


    public static void infoBox(String infoMessage, String titleMessage, String Type)
    {
        if(Type == "1"){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(titleMessage);
            alert.setHeaderText("");
            alert.setContentText(infoMessage);
            alert.showAndWait();
        }else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(titleMessage);
            alert.setHeaderText("");
            alert.setContentText(infoMessage);
            alert.showAndWait();
        }
    }
}

