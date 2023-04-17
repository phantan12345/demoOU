/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.pojo;

import java.sql.Date;
import java.text.ParseException;
import java.util.UUID;
import setting.setting;

/**
 *
 * @author admin
 */
public class promotion {

  /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    public int getAative() {
        return active;
    }

    /**
     * @return the star
     */
    public Date getStar() {
        return star;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * @param star the star to set
     */
    public void setStar(Date star) {
        this.star = star;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
    private String id;  
{
    id = UUID.randomUUID().toString();
}
    private int discount;

    private Date star;
    private Date end;
    private int active;

    public int getDiscount() {
        return discount;
    }

    public String getId() {
        return id;
    }

 
   
    public promotion(){}
    public promotion(String id, int dis,Date start,Date end ){
        this.id=id;
        this.discount=dis;
        this.star=start;
        this.end=end;

    }

    public promotion(String id, int dis,Date start,Date end,int ac ){
        this.id=id;
        this.discount=dis;
        this.star=start;
        this.end=end;
        this.active=ac;
    }

    public promotion( int dis,Date start,Date end,int ac ){
        this.id=id;
        this.discount=dis;
        this.star=start;
        this.end=end;
        this.active=ac;

    }
    public promotion(String id, int dis,String start,String end,int ac ) throws ParseException{
        this.id=id;
        this.discount=dis;
        this.star=(Date)setting.dateFormat.parse(start);
        this.end=(Date)setting.dateFormat.parse(end);
        this.active=ac;


    }
    @Override
    public String toString() {
      
        return String.valueOf(this.getDiscount());
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
