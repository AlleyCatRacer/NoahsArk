package Model;

import java.io.Serializable;

/**
 * A class representing a password.
 *
 * @author Steffen Vissing Andersen
 * @author Group 1
 * @version [N/A].5 - 20.05.21
 */

public class Password implements Serializable
{
    private String password;
    private static final long serialVersionUID = 50L;

    /**
     * One-argument constructor.
     * Password is checked for null value and is verified with isLegal() method.
     *
     * @param password = password as a String
     * @throws IllegalArgumentException - for null passwords
     * @throws IllegalArgumentException - for illegal passwords
     */
    public Password(String password)
    {
        if (password == null)
        {
            throw new IllegalArgumentException("Null password");
        }
        String message = Password.isLegal(password);
        if (message != null)
        {
            throw new IllegalArgumentException(message);
        }
        this.password = password;
    }

    /**
     * Check for valid passwords
     *
     * @param password = password as a String
     * @return String of null for valid passwords or an error description message for invalid passwords.
     */
    private static String isLegal(String password)
    {
        if (password == null || password.length() < 6)
        {
            return "Password must have at least 6 characters";
        }
        int lower = 0;
        int upper = 0;
        int digit = 0;
        int special = 0;
        for (int i=0; i<password.length(); i++)
        {
            char ch = password.charAt(i);
            if (Character.isDigit(ch))
            {
                digit++;
            }
            else if (Character.isLowerCase(ch) && Character.isLetter(ch))
            {
                lower++;
            }
            else if (Character.isUpperCase(ch) && Character.isLetter(ch))
            {
                upper++;
            }
            else if (ch == '_' || ch == '-')
            {
                special++;
            }
        }
        if (lower + upper + digit + special < password.length())
        {
            return "Password may only contain letters, digits, hyphens and underscore characters";
        }
        if (lower == 0 || upper == 0 || digit == 0)
        {
            return "Password must contain at least one of each of the following: Uppercase letter, lowercase letter and digit";
        }

        return null;
    }

    /**
     * Check for correct password in login
     *
     * @param password = password as a String
     * @return 'true' if the password argument given matches the Password object's string instance variable,
     * otherwise returns 'false'
     */
    public boolean verifyPassword(String password)
    {
        return this.password.equals(password);
    }
}
