package tan.pojo;

import java.util.UUID;

public class employee {

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

<<<<<<< HEAD
=======
    
>>>>>>> son
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the idbr
     */
    public String getIdbr() {
        return idbr;
    }

<<<<<<< HEAD
=======



>>>>>>> son
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
<<<<<<< HEAD
=======
    public String getNamebr() {
        return namebr;
    }
>>>>>>> son

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * @param id the id to set
     */


    /**
     * @param idbr the idbr to set
     */
    public void setIdbr(String idbr) {
        this.idbr = idbr;
    }

<<<<<<< HEAD
=======

>>>>>>> son
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
=======
    public void setNamebr(String name) {
        this.namebr = name;
    }

>>>>>>> son
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
<<<<<<< HEAD
    private static int dem=0;
    private String id;
    {
        id = String.format("NV%d", dem++);
    }
    private String name;
    private String phone;
    private String password;
    private int active;
    private String idbr;
=======
    private String id;
  
{
        setId(UUID.randomUUID().toString());
}
    private String name;
    private String phone;    
    private String password;
    private int active;
    private String idbr;
    private String namebr;
>>>>>>> son

    public employee(){}

    public employee(String id, String name,String phone,String idbr,int active){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.idbr=idbr;
        this.active=active;
    }

    public employee(String name,String phone,int active,String idbr){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.active=active;
        this.idbr=idbr;
        
    }
<<<<<<< HEAD
=======

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
>>>>>>> son
}
