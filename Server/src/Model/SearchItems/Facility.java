package Model.SearchItems;

import java.io.Serializable;

/**
 * Facility class containing all the information about a specific Facility.
 * Implements SearchItem and Serializable.
 *
 * @author Group 1
 * @version 1.0 - 20.05.2021
 */
public class Facility implements SearchItem, Serializable
{
   private String name;
   private String details;
   private String contactInfo;
   private String postcode;
   private static final long serialVersionUID = 21L;

   /**
    * Constructor initializing all relevant instance variables to the arguments provided.
    *
    * @param name String of Facility name
    * @param postcode String of Facility postcode
    * @param details String of Facility details
    * @param contactInfo String of Facility contact information
    * @throws IllegalArgumentException If any of the arguments provided is null = "Invalid input"
    */
   public Facility(String name, String postcode, String details, String contactInfo)
   {
      if(name != null && postcode != null && details != null && contactInfo != null)
      {
         this.name = name;
         this.postcode = postcode;
         this.details = details;
         this.contactInfo = contactInfo;
      }
      else
         throw new IllegalArgumentException("Invalid input");
   }

   /**
    * Getter for the title.
    *
    * @return String representing Facility name
    */
   @Override public String getTitle()
   {
      return name;
   }

   /**
    * Getter for the details.
    * @return String representing Facility details
    */
   @Override public String getDetails()
   {
      return details;
   }

   /**
    * Getter for the contact information.
    *
    * @return String representing Facility contact information
    */
   @Override public String getContactInfo()
   {
      return contactInfo;
   }

   /**
    * Getter for the postcode.
    *
    * @return String representing Facility postcode
    */
   @Override public String getPostcode()
   {
      return postcode;
   }
}
