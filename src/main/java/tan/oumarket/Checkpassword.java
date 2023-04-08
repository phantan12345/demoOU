/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tan.oumarket;

/**
 *
 * @author admin
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checkpassword {
    
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
    
    private final Pattern pattern;
    private Matcher matcher;
    
    public Checkpassword() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }
    
    public boolean validate(final String password) {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

