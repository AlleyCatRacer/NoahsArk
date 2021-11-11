package ViewModel;

import Model.SearchItems.Service;

import java.util.ArrayList;

public class ViewState
{
   private String username;
   private String profileUsername;
   private String faqTopic;
   private String forumTopic;
   private ArrayList<Service> services;
   private Service selectedService;
   private boolean createListing;
   
   public ViewState()
   {
      this.username   = "";
      this.profileUsername = "";
      this.faqTopic   = "";
      this.forumTopic = "";
      this.services = new ArrayList<>();
      this.selectedService = null;
      createListing = true;
   }
   
   public String getUsername()
   {
      return username;
   }
   
   public String getProfileUsername()
   {
      return profileUsername;
   }
   
   public String getFaqTopic()
   {
      return faqTopic;
   }
   
   public String getForumTopic()
   {
      return forumTopic;
   }
   
   public ArrayList<Service> getServices()
   {
      return services;
   }
   
   public Service getSelectedService()
   {
      return selectedService;
   }
   
   public boolean getCreateListing()
   {
      return createListing;
   }
   
   public void setUsername(String username)
   {
      this.username = username;
   }
   
   public void setProfileUsername(String profileUsername)
   {
      this.profileUsername = profileUsername;
   }
   
   public void setFaqTopic(String topic)
   {
      faqTopic = topic;
   }
   
   public void setForumTopic(String forumTopic)
   {
      this.forumTopic = forumTopic;
   }
   
   public void setServices(ArrayList<Service> services)
   {
      this.services=services;
   }
   
   public void setSelectedService(Service service)
   {
      this.selectedService=service;
   }
   
   public void setCreateListing(boolean b)
   {
      createListing = b;
   }
}
