package Model;

import Model.SearchItems.Service;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing the fields of a User object that are visible to other users.
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 2.0 - 31.05.21
 */
public class Profile implements Serializable
{
  private String aboutMe;
  private ArrayList<String> stories;
  private String username;
  private boolean professional;
  private String phoneNumber;
  private String email;
  private ArrayList<Service> services;
  private static final long serialVersionUID = 99L;
  
  /**
   * Constructor taking 4 arguments and initializing the fields to the arguments where compatible and setting other
   * instance variables to empty strings or an empty ArrayList.
   *
   * @param username String of the username
   * @param aboutMe String of the user's introductory text
   * @param stories ArrayList of Strings containing the user's posted stories
   * @param professional boolean variable indicating whether the user is a professional or not
   */
  public Profile(String username, String aboutMe, ArrayList<String> stories,
      boolean professional)
  {
    this.username = username;
    this.aboutMe = aboutMe;
    this.stories = stories;
    this.professional = professional;
    this.phoneNumber = "";
    this.email = "";
    this.services = new ArrayList<>();
  }
  
  /**
   * Constructor taking 7 arguments and initializing all relevant instance variables accordingly.
   *
   * @param username String of the username
   * @param aboutMe String of the user's introductory text
   * @param stories ArrayList of Strings containing the user's posted stories
   * @param professional boolean variable indicating whether the user is a professional or not
   * @param phoneNumber String of the user's phone number
   * @param email String of the user's email address
   * @param services ArrayList of Service objects the user is a provider for
   */
  public Profile(String username, String aboutMe, ArrayList<String> stories,
      boolean professional, String phoneNumber, String email,
      ArrayList<Service> services)
  {
    this.username = username;
    this.aboutMe = aboutMe;
    this.stories = stories;
    this.professional = professional;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.services = services;
  }
  
  /**
   * Getter for the introductory profile text.
   *
   * @return String of introductory text
   */
  public String getInfo()
  {
    return aboutMe;
  }
  
  /**
   * Getter for posted stories.
   *
   * @return ArrayList of String stories
   */
  public ArrayList<String> getStories()
  {
    return stories;
  }
  
  /**
   * Getter for the username.
   *
   * @return String of the username
   */
  public String getUsername()
  {
    return username;
  }
  
  /**
   * Boolean check for whether the user is a professional or not.
   *
   * @return 'true' if the professional boolean field is true, 'false' if not
   */
  public boolean getProfessionalStatus()
  {
    return professional;
  }
  
  /**
   * Getter for the phone number.
   *
   * @return String of the phone number
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  
  /**
   * Getter for the email address.
   *
   * @return String of the email address
   */
  public String getEmail()
  {
    return email;
  }
  
  /**
   * Getter for the services.
   *
   * @return ArrayList of Service objects the user is a provider for
   */
  public ArrayList<Service> getServices()
  {
    return services;
  }
}
