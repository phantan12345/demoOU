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
public class product {
    private String id;
    {
        id = UUID.randomUUID().toString();
    }
    private String name;
    private String type;
    private int price;
    private double status;
    private String idKM;
    private String barcode;
    
     public product( String id,String name,String type,int price,Double status){
     this.id=id;
     this.name=name;
     this.type=type;
     this.price=price;
     this.status=status;
    }

    public product( String id,String name,String type,int price,Double status,String km_id){
        this.id=id;
        this.name=name;
        this.type=type;
        this.price=price;
        this.status=status;
        this.idKM=km_id;
       }
       public product( String name,String type,int price,Double status,String km_id,String bar){
        this.id=id;
        this.name=name;
        this.type=type;
        this.price=price;
        this.status=status;
        this.idKM=km_id;
        this.barcode=bar;
       }
    
    public product(String id,String barcode, String name, String type, int price, Double status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
        this.barcode = barcode;
    }

    public product(String id, String name, String type, int price, Double status, String idKM, String barcode) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
        this.idKM = idKM;
        this.barcode = barcode;
    }
    
    public product() {
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
     * @return the status
     */
    public double getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Double status) {
        this.status = status;
    }

    /**
     * @return the idKM
     */
    public String getIdKM() {
        return idKM;
    }

    /**
     * @param idKM the idKM to set
     */
    public void setIdKM(String idKM) {
        this.idKM = idKM;
    }

    /**
     * @return the barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    
}
