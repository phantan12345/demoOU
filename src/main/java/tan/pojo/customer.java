/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.pojo;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author admin
 */
public class customer {

    private String id;
    private String fullName;
    private String birthDay;
    private String phoneNumber;

    {
        id = UUID.randomUUID().toString();
    }

    public customer(String fullName, String birthDay, String phoneNumber) {
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
    }

    public customer(String idString, String fullName, String birthDay, String phoneNumber) {
        this.id = idString;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
    }

    public customer() {
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
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the birthDay
     */
    public String getBirthDay() {
        return birthDay;
    }

    /**
     * @param birthDay the birthDay to set
     */
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
