package Model;

import Model.SearchItems.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A class representing a User of the system.
 *
 * @author Group 1
 * @author OWASP Validation Regex repository
 * @author baeldung
 * @version 3.0 - 01.06.21
 */

public class User implements Serializable
{
  private String aboutMe;
  private ArrayList<String> stories;
  private String username;
  private boolean professional;
  private Password password;
  private ArrayList<String> subscriptions;
  private ArrayList<String> friendList;
  private ArrayList<String> reportList;
  private String phoneNumber;
  private String email;
  private ArrayList<Service> services;

  /**
   * Two-argument constructor. The two instance variables are initialized to the two arguments,
   * ArrayLists of stories, subscriptions, friends, reports and services are initialized to empty ArrayLists.
   * Boolean field of professional is initialized to false and the String fields of phone number and email are
   * initialized to null values.
   *
   * @param username a String represent the User's alias
   * @param password a Password object to allow the user access to the system
   * @throws IllegalArgumentException - Username must have at least 3 characters
   */
  public User(String username, Password password)
  {
    if (username == null || username.length() < 3)
    {
      throw new IllegalArgumentException(
          "Username must have at least 3 characters");
    }
    aboutMe = "";
    stories = new ArrayList<>();
    this.username = username;
    professional = false;
    this.password = password;
    subscriptions = new ArrayList<>();
    friendList = new ArrayList<>();
    reportList = new ArrayList<>();
    this.phoneNumber = "";
    this.email = "";
    this.services = new ArrayList<>();
  }
  
  /**
   * Gets the information associated with the user.
   *
   * @return A string containing the user's general information about themselves
   */
  public String getInfo()
  {
    return aboutMe;
  }
  
  /**
   * Getter for the user's stories.
   *
   * @return ArrayList of stories
   */
  public ArrayList<String> getStories()
  {
    return stories;
  }
  
  /**
   * Getter for the username.
   *
   * @return username as a String
   */
  public String getUsername()
  {
    return username;
  }
  
  /**
   * Getter for the boolean instance variable of professional.
   *
   * @return 'true' if the user is a professional, 'false' if not.
   */
  public boolean getProfessionalStatus()
  {
    return professional;
  }
  
  /**
   * Getter for the user's subscriptions.
   *
   * @return ArrayList of subscriptions
   */
  public ArrayList<String> getSubscriptions()
  {
    return subscriptions;
  }
  
  /**
   * Getter for the friendList.
   *
   * @return the friendList as an ArrayList of type String
   */
  public ArrayList<String> getFriendList()
  {
    return friendList;
  }
  
  /**
   * Getter for the user's list of user profiles reported.
   *
   * @return an ArrayList of Strings containing usernames that the user has reported
   */
  public ArrayList<String> getReportList()
  {
    return reportList;
  }
  
  /**
   * Setter for personal information that only updates the field if the value given is not null.
   *
   * @param aboutMe String of the user's introduction of themselves
   */
  public void setInfo(String aboutMe)
  {
    if(aboutMe!=null)
      this.aboutMe = aboutMe;
  }
  
  /**
   * Setter for the user's stories that only updates the field if the value given is not null.
   *
   * @param stories an ArrayList of Strings that the user has posted on their profile
   */
  public void setStories(ArrayList<String> stories)
  {
    if(stories!=null)
    this.stories = stories;
  }
  
  /**
   * Mutator for boolean professional instance variable, turning 'true' to 'false' and vice versa.
   */
  public void setProfessionalStatus()
  {
    professional =! professional;
  }

  /**
   * Adds the provided thread to the ArrayList of User's subscriptions.
   *
   * @param thread String representing the thread that should be added to the ArrayList
   */
  public void addSubscription(String thread)
  {
    subscriptions.add(thread);
  }
  
  /**
   * Checking if password given matches User objects password by calling a method in Password class through the
   * User's password field.
   *
   * @param password = String of a password
   * @return 'true' if the password argument given matches the User's, otherwise returns 'false'
   */
  public boolean verifyPassword(String password)
  {
    return this.password.verifyPassword(password);
  }

  /**
   * Adds a new string to the friendList that only updates the field if the value given is not null.
   *
   * @param username String representing the user that is being added as a friend
   */
  public void addFriend(String username)
  {
    if (username != null)
      friendList.add(username);
  }

  /**
   * Method to remove a user from the friendList that only updates the field if the value given is not null.
   *
   * @param username String to be removed
   */
  public void unfriend(String username)
  {
    if (username != null)
      getFriendList().remove(username);
  }
  
  /**
   * Method to add a username to the reportList that only updates the field if the value given is not null.
   *
   * @param username String of the reported user's username
   */
  public void addReport(String username)
  {
    if (username != null)
      reportList.add(username);
  }
  
  /**
   * Getter for the user's phone number
   *
   * @return user's phone number as a String
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  
  /**
   * Getter for the user's email address.
   *
   * @return String of user's email
   */
  public String getEmail()
  {
    return email;
  }
  
  /**
   * Getter for the user's services.
   *
   * @return an ArrayList of Service objects that the user is a provider for
   */
  public ArrayList<Service> getServices()
  {
    return services;
  }
  
  /**
   * @author OWASP Validation Regex repository
   * @author Group1
   *
   * Setter for the phone number field that only updates the field if the value given is valid.
   *
   * @param phoneNumber String of the new phone number
   * @throws IllegalArgumentException if the String contains anything other than 8 consecutive digits or four 2 digit
   * intervals separated by a space or hyphen.
   * Exception message is "Invalid phone number"
   */
  public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException
  {
    String check = "^(\\d{2}[- ]?)(\\d{2}[- ]?)(\\d{2}[- ]?)\\d{2}$";
    Pattern pattern = Pattern.compile(check);

    if (phoneNumber != null && pattern.matcher(phoneNumber).matches())
    {
      this.phoneNumber = phoneNumber;
    }
    else
    {
      throw new IllegalArgumentException("Invalid phone number");
    }
  }
  
  /**
   * @author baeldung
   * @author Group1
   *
   * Setter for the email instance variable.
   *
   * @param email String of the new email address
   * @throws IllegalArgumentException if the String is null, contains illegal characters or violates the format of
   * a subString followed by '@', a second subString, a dot and a third subString.
   * Exception message is "Invalid email address"
   */
  public void setEmail(String email) throws IllegalArgumentException
  {
    String check = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9-]+.+$";
    Pattern pattern = Pattern.compile(check);
    
    if (email != null && pattern.matcher(email).matches())
    {
      this.email = email;
    }
    else
    {
      throw new IllegalArgumentException("Invalid email address");
    }
  }
  
  /**
   * Setter for the user's services which only updates the field if the given argument is not null.
   *
   * @param services an ArrayList of Service objects representing the user's services
   */
  public void setServices(ArrayList<Service> services)
  {
    if (services != null)
    {
      this.services = services;
    }
  }
}
