package tan.pojo;

import java.util.Random;
import java.util.UUID;

public class employee {

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public String getNamebr() {
        return namebr;
    }

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

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setNamebr(String name) {
        this.namebr = name;
    }

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
    private String id;

    private String name;
    private String phone;
    private String password;
    private int active;
    private String idbr;
    private String namebr;
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int PASSWORD_LENGTH = 7;

    {
        setId(UUID.randomUUID().toString());
        Random random = new Random();
        StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        System.out.print(sb.toString());
        setPassword(sb.toString());
    }

    public employee() {
    }

    public employee(String id, String name, String phone, String idbr, int active) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.idbr = idbr;
        this.active = active;
    }

    public employee(String name, String phone, int active, String idbr) {
        this.name = name;
        this.phone = phone;
        this.active = active;
        this.idbr = idbr;

    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
