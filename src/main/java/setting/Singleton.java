package setting;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tan.pojo.bill;
import tan.pojo.branch;
import tan.pojo.product_bill;

public class Singleton {
    
    private static Singleton instance;
    private String userID ;
    private String name ;
    private int cash;
    private bill b;
    private int active;
    private ObservableList<product_bill> pdsList=FXCollections.observableArrayList(); 
    private String IDbr;
    


    private Singleton() {
        // constructor private để không cho phép tạo đối tượng từ bên ngoài
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    
    public ObservableList<product_bill> getPdsList() {
        return pdsList;
    }

    /**
     * @param pdsList the pdsList to set
     */
    public void setPdsList(ObservableList<product_bill> pdsList) {
        this.pdsList = pdsList;
    }
    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cash
     */
    public int getCash() {
        return cash;
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(int cash) {
        this.cash = cash;
    }

    /**
     * @return the b
     */
    public bill getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(bill b) {
        this.b = b;
    }

    /**
     * @return the pbsList
     */
    

    /**
     * @return the IDbr
     */
    public String getIDbr() {
        return IDbr;
    }

    /**
     * @param IDbr the IDbr to set
     */
    public void setIDbr(String IDbr) {
        this.IDbr = IDbr;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * @return the pdsList
     */
    

    /**
     * @return the br
     */
    
    
}