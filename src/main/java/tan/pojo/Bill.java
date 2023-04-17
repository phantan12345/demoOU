/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 *
 * @author admin
 */
public class bill {

    private String id;
    private String payDate;
    private Integer total;
    private Integer cash;
    private Integer excessMoney;

    {
        setId(UUID.randomUUID().toString());
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setPayDate(currentDate.format(formatter));
    }

    public bill(Integer total) {
        this.total = total;
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
     * @return the payDate
     */
    public String getPayDate() {
        return payDate;
    }

    /**
     * @param payDate the payDate to set
     */
    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the cash
     */
    public Integer getCash() {
        return cash;
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(Integer cash) {
        this.cash = cash;
    }

    /**
     * @return the excessMoney
     */
    public Integer getExcessMoney() {
        return excessMoney;
    }

    /**
     * @param excessMoney the excessMoney to set
     */
    public void setExcessMoney(Integer excessMoney) {
        this.excessMoney = excessMoney;
    }

}
