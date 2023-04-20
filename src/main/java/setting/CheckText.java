/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package setting;

import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.AccessibleAttribute;

/**
 *
 * @author admin
 */
public class CheckText {

    Info i = new Info();

    public boolean checkEmpty(int text) {
        if (text < 0) {
            i.infoBox("Please fill in the information", "Lack of information", "0");
            return true;
        }
        return false;
    }

    public boolean checkEmpty(Date text) {
        if (text == null) {
            i.infoBox("Please fill in the information", "Lack of information", "0");
            return true;
        }
        return false;
    }

    public boolean checkEmpty(String text) {
        if (text.isEmpty()) {
            i.infoBox("Please fill in the information", "Lack of information", "0");
            return true;
        }
        return false;
    }

    public boolean checkBarcode(String bar) {
            if (bar.matches("\\d{9}")) {
            return false;
        }
        i.infoBox("Invalid Barcode", "Barcode", "0");
        return true;
    }

    public boolean checkPhone(String phone) {
        if (phone.matches("\\d{10}")) {
            return false;
        }
        i.infoBox("Invalid phone number", "Phone Number", "0");
        return true;
    }

    public boolean checkTotal(String t, int t2) {
        int t1 = 0;
        try {
            t1 = Integer.parseInt(t);
        } catch (NumberFormatException numberFormatException) {
            i.infoBox("Invalid amount", "Cash", "-1");
            return true;
        }
        if (t2 > t1) {
            i.infoBox("Insufficient balance", "Payment", "0");
            return true;
        }
        return false;
    }

    public String removeExtraSpaces(String str) {
        // Xóa các khoảng trắng đầu và cuối
        str = str.trim();
        // Thay thế các khoảng trắng dư bằng một khoảng trắng duy nhất
        str = str.replaceAll("\\s+", " ");
        return str;
    }

    public boolean containsSpecialCharacter(String name) {
        String regex = "[!@#$%^&*()_+=\\[\\]{};':\"\\\\|,.<>\\/?]";
        if(name.matches(".*" + regex + ".*")){
            Info.infoBox("Name Contains Special Character", "CUSTOMER'S NAME", "-1");
            return true;
        }
        return false;
    }
    
    public  boolean checkDatepromotion(Date d1,Date d2){
        if(d1.compareTo(d2)<0){
            Info.infoBox("wrong date", "CUSTOMER'S NAME", "-1");
            return true;
        }
        return false;
    }
     public  boolean checkDatepromotionNow(Date d1,Date d2){
        LocalDate date=LocalDate.now();
        Date d=Date.valueOf(date);
        if(d1.compareTo(d)<0 && d2.compareTo(d)<0){
            Info.infoBox("wrong date now", "CUSTOMER'S NAME", "-1");
            return true;
        }
        return false;
     }
     
      public  boolean checkValue(int d1,int d2){
      
        if(d1<d2){
            Info.infoBox("wrong value", "CUSTOMER'S NAME", "-1");
            return true;
        }
        return false;
     }

     public  boolean checkValue1(int d1){
      
        if(d1>1000){
            Info.infoBox("too great value", "CUSTOMER'S NAME", "-1");
            return true;
        }
        return false;
     }

     public boolean checkQuantity(String quantity,String type){
        try{
            if(type.equals("KG")){
                Double.parseDouble(quantity);
            }else{
                Integer.parseInt(quantity);
            }
        }catch(Exception e){
            Info.infoBox("wrong value", "Quantity", "-1");
            return true;
        }
        return false;
     }

     public  boolean checkValue3(int d1,int d2){
      
        if(d1<d2){
            Info.infoBox("wrong value", "CUSTOMER'S NAME", "-1");
            return true;
        }
        return false;
     }

    

}
