package Model.SearchItems;

import java.io.Serializable;

/**
 * Service class containing all the information about a specific Service.
 * Implements SearchItem and Serializable.
 *
 * @author Group 1
 * @version 1.1 - 29.05.2021
 */
public class Service implements SearchItem, Serializable
{
  private String title;
  private String provider;
  private String details;
  private int price;
  private String postcode;
  private static final long serialVersionUID = 50L;

  /**
   * Constructor initializing all relevant instance variables to the arguments provided and price to zero
   * @param serviceTitle String of service title
   * @param provider String of providing user's username
   * @param postcode String of postcode
   * @param details String of details
   * @throws IllegalArgumentException If any of the arguments provided is null = "Invalid input"
   */
  public Service(String serviceTitle, String provider, String postcode, String details)
  {

    if(serviceTitle != null && provider != null && postcode != null && details != null) {
    this.title = serviceTitle;
    this.provider = provider;
    this.postcode = postcode;
    this.details = details;
    price = 0;
    }
    else
      throw new IllegalArgumentException("Invalid input");
  }

  /**
   * Constructor initializing all relevant instance variables to the arguments provided (overloaded constructor)
   *
   * @param serviceTitle String of service title
   * @param provider String of providing user's username
   * @param postcode String of postcode
   * @param details String of details
   * @param price integer representing the price
   * @throws IllegalArgumentException If any of the String arguments is null or the price is less than zero = "Invalid input"
   */
  public Service(String serviceTitle, String provider, String postcode, String details, int price)
  {

    if(serviceTitle != null && provider != null && postcode != null && details != null && price >= 0) {
      this.title = serviceTitle;
      this.provider = provider;
      this.postcode = postcode;
      this.details = details;
      this.price = price;
    }
    else
      throw new IllegalArgumentException("Invalid input");
  }

  /**
   * Getter for the title.
   *
   * @return String representing Service title
   */
  @Override public String getTitle()
  {
    return title;
  }

  /**
   * Getter for the details that appends the price if it is not zero.
   *
   * @return String representing Service details and price if not zero
   */
  @Override public String getDetails()
  {
    if(price == 0)
    return details;
    else
      return details + "\nPRICE:         " + price+" Kr.";
  }
  
  /**
   * Getter for the provider's username.
   *
   * @return String representing Service provider's username
   */
  @Override public String getContactInfo()
  {
    return provider;
  }

  /**
   * Getter for the postcode.
   *
   * @return String representing Service postcode
   */
  @Override public String getPostcode()
  {
    return postcode;
  }
  
  /**
   * Getter for the postcode as an integer.
   *
   * @return integer value of the postcode
   */
  public int getPostcodeInt()
  {
    return Integer.parseInt(postcode);
  }
  
  /**
   * Getter for the price.
   *
   * @return integer value of the price
   */
  public int getPrice()
  {
    return price;
  }
  
  /**
   * Getter for only the details, not appending the price even if it isn't set to zero.
   *
   * @return String of details
   */
  public String getOnlyDetails()
  {
    return details;
  }
}
