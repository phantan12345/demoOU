package tan.pojo;

import java.util.UUID;

public class branch {

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param id the id to set
     */
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    private String id;   
{
    id = UUID.randomUUID().toString();
}
    private String name;
    private String address;



    public branch(){}

    public branch(String id,String name,String address){
        this.id=id;
        this.name=name;
        this.address=address;
    }
     public branch(String name,String address){
        this.id=id;
        this.name=name;
        this.address=address;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getAddress();
    }




}
