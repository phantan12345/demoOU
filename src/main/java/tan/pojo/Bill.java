/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.pojo;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Bill {
    
    private String id;
    private Date paymentDate=new Date();
    private Integer totall;

    public Bill(String id, Integer totall) {
        this.id = id;
        this.totall = totall;
    }

    public Bill() {
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the paymentDate
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the totall
     */
    public Integer getTotall() {
        return totall;
    }

    /**
     * @param totall the totall to set
     */
    public void setTotall(Integer totall) {
        this.totall = totall;
    }
    
}
