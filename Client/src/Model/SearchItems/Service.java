package Model.SearchItems;

import java.io.Serializable;

public class Service implements SearchItem, Serializable
{

  private String title;
  private String provider;
  private String details;
  private int price;
  private String postcode;
  private static final long serialVersionUID = 50L;

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
      throw new IllegalArgumentException("A unique title, postcode and details are required");
  }
  
  public Service(String serviceTitle, String provider, String postcode, String details, int price)
  {

    if(serviceTitle != null && provider != null && postcode != null && details != null) {
      this.title = serviceTitle;
      this.provider = provider;
      this.postcode = postcode;
      this.details = details;
      this.price = price;
    }
    else
      throw new IllegalArgumentException("A unique title, postcode and details are required");
  }

 @Override public String getTitle()
  {
    return title;
  }

  @Override public String getDetails()
  {
    if(price == -10)
    return details;
    else
      return details + "\nPRICE:         " + price+" Kr.";
  }

 @Override public String getContactInfo()
  {
    return provider;
  }

  @Override public String getPostcode()
  {
    return postcode;
  }
  
  public int getPostcodeInt()
  {
     return Integer.parseInt(postcode);
  }
   
   public int getPrice()
   {
      return price;
   }
   
   public String getOnlyDetails()
   {
      return details;
   }
}
