/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package setting;

import javafx.scene.AccessibleAttribute;

/**
 *
 * @author admin
 */
public class CheckText {
    Info i= new Info();
    public boolean checkEmpty(String text){
        if (text.isEmpty()){
            i.infoBox("Please fill in the information", "Lack of information", "0");
            return true;
        }
        return false;
    }
    public boolean checkPhone(String phone){
        if(phone.matches("\\d{10}")){
            return false;
        }
        i.infoBox("Invalid phone number", "Phone Number", "0");
        return true;
    }
    public boolean checkTotal(int t1,int t2){
        if(t2>t1){
            i.infoBox("Insufficient balance", "Payment", "0");
            return true;
        }
        return false;
    }
}
