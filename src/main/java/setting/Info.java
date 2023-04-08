/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package setting;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 *
 * @author admin
 */
public class Info {

    public static void infoBox(String infoMessage, String titleMessage, String Type) {
        if (Type == "1") {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(titleMessage);
            alert.setHeaderText("");
            alert.setContentText(infoMessage);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(titleMessage);
            alert.setHeaderText("");
            alert.setContentText(infoMessage);
            alert.showAndWait();
        }
    }

    public boolean conFir() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to proceed with this action?");

        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(okButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton) {
            return false;
        }
        return true;
    }
}
