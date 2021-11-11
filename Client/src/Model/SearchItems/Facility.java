package Model.SearchItems;

import java.io.Serializable;

public class Facility implements SearchItem, Serializable
{
   private String name;
   private String details;
   private String contactInfo;
   private String postcode;
   private static final long serialVersionUID = 21L;
   
   public Facility(String name, String postcode,String details,String contactInfo)
   {
      this.name=name;
      this.postcode=postcode;
      this.details=details;
      this.contactInfo=contactInfo;
   }
   
   @Override public String getTitle()
   {
      return name;
   }
   
   @Override public String getDetails()
   {
      return details;
   }
   
   @Override public String getContactInfo()
   {
      return contactInfo;
   }
   
   @Override public String getPostcode()
   {
      return postcode;
   }
}
