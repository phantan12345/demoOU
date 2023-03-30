/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.pojo;

/**
 *
 * @author ADMIN
 */
public class user {

    private String id;
    private String Name;
    private String passWord;
    private String SDT;
    private boolean vaiTro;

    public user(String id, String Name, String passWord, String SDT, boolean vaiTro) {
        this.id = id;
        this.Name = Name;
        this.passWord = passWord;
        this.SDT = SDT;
        this.vaiTro = vaiTro;
    }
    
}
