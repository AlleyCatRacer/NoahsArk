package Model;

import Model.SearchItems.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
   
   public Profile(String username, String aboutMe, ArrayList<String> stories, boolean professional)
   {
      if (username != null && aboutMe != null && stories != null)
      {
         this.username = username;
         this.aboutMe = aboutMe;
         this.stories = stories;
         this.professional = professional;
         this.phoneNumber = "";
         this.email = "";
         this.services = new ArrayList<>();
      }
   }
   
   public Profile(String username, String aboutMe, ArrayList<String> stories, boolean professional, String phoneNumber, String email, ArrayList<Service> services)
   {
      String emailCheck   = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.+$";
      Pattern  ePattern = Pattern.compile(emailCheck);
      String phoneCheck = "^(\\d{2}[- ]?)(\\d{2}[- ]?)(\\d{2}[- ]?)\\d{2}$";
      Pattern  pPattern = Pattern.compile(phoneCheck);
      
      if (username != null && aboutMe != null && stories != null)
      {
         this.username = username;
         this.aboutMe = aboutMe;
         this.stories = stories;
         this.professional = professional;
         this.services = services;
         
         if (email == null)
         {
            this.email = "";
         }
         else if (ePattern.matcher(email).matches())
         {
            this.email = email;
         }
         else
         {
            throw new IllegalStateException("Invalid email address");
         }
         
         if (phoneNumber == null)
         {
            this.phoneNumber = "";
         }
         else if (pPattern.matcher(phoneNumber).matches())
         {
            this.phoneNumber = phoneNumber;
         }
         else
         {
            throw new IllegalStateException("Invalid phone number");
         }
         
      }
      else
      {
         throw new IllegalArgumentException("Invalid information");
      }
   }
   
   public String getInfo()
   {
      return aboutMe;
   }
   
   public ArrayList<String> getStories()
   {
      return stories;
   }
   
   public String getUsername()
   {
      return username;
   }
   
   public boolean getProfessionalStatus()
   {
      return professional;
   }
   
   public String getPhoneNumber()
   {
      return phoneNumber;
   }
   
   public String getEmail()
   {
      return email;
   }
   
   public ArrayList<Service> getServices()
   {
      return services;
   }
}
