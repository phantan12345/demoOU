/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.pojo;

/**
 *
 * @author ADMIN
 */
public class product {

    /**
     * @return the km_id
     */
    public int getKm_id() {
        return km_id;
    }

    /**
     * @param km_id the km_id to set
     */
    public void setKm_id(int km_id) {
        this.km_id = km_id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
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
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    private int id;
    private String name;
    private String type;
    private int price;
    private String status;
    private int km_id;
    
    
    public product(){}
    public product( int id,String name,String type,int price,String status){
     this.id=id;
     this.name=name;
     this.type=type;
     this.price=price;
     this.status=status;
    }


    
}
