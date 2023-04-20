/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.pojo;

import java.util.UUID;

/**
 *
 * @author admin
 */
public class product_bill {

    private String id;
    private String name;
    private String type;
    private int price;
    private double amount;
    private int proPrice;
    private String idProduct;
    private String idBill;
    {
        id = UUID.randomUUID().toString();
    }

    public product_bill( String name, String type, int price, double amount) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.amount = amount;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the proPrice
     */
    public int getProPrice() {
        return proPrice;
    }

    /**
     * @param proPrice the proPrice to set
     */
    public void setProPrice(int proPrice) {
        this.proPrice = proPrice;
    }
    public String getIdProduct() {
        return idProduct;
    }

    /**
     * @param idProduct the idProduct to set
     */
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * @return the idBill
     */
    public String getIdBill() {
        return idBill;
    }

    /**
     * @param idBill the idBill to set
     */
    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }
    
}
