package ViewModel;

import Model.SearchItems.Service;
import Model.ViewModelInterfaces.ProfileModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfessionalProfileViewModel
{
   private ProfileModel            model;
   private ViewState               viewState;
   private StringProperty          usernameProperty;
   private StringProperty          profileUsernameProperty;
   private StringProperty          profileInfoProperty;
   private ObservableList<String>  storyList;
   private StringProperty          friendButton;
   private BooleanProperty         professional;
   private StringProperty          phoneNumberProperty;
   private StringProperty          emailProperty;
   private ObservableList<Service> serviceList;
   
   ProfessionalProfileViewModel(ProfileModel model, ViewState viewState)
   {
      this.model              = model;
      this.viewState          = viewState;
      usernameProperty        = new SimpleStringProperty(viewState.getUsername());
      friendButton            = new SimpleStringProperty("");
      profileUsernameProperty = new SimpleStringProperty(viewState.getProfileUsername());
      profileInfoProperty     = new SimpleStringProperty();
      storyList               = FXCollections.observableArrayList();
      professional            = new SimpleBooleanProperty(true);
      phoneNumberProperty     = new SimpleStringProperty("");
      emailProperty           = new SimpleStringProperty("");
      serviceList             = FXCollections.observableArrayList();
   }
   
   public void reset()
   {
      isFriend();
      profileUsernameProperty.set(viewState.getProfileUsername());
      usernameProperty.set(viewState.getUsername());
      profileInfoProperty.set(model.getProfileInfo(viewState.getProfileUsername()).getInfo());
      storyList.setAll(model.getProfileInfo(viewState.getProfileUsername()).getStories());
      professional.set(model.isProfessional(viewState.getUsername()));
      serviceList.setAll(model.searchServices(viewState.getProfileUsername()));
      phoneNumberProperty.set(model.getProfileInfo(viewState.getProfileUsername()).getPhoneNumber());
      emailProperty.set(model.getProfileInfo(viewState.getProfileUsername()).getEmail());
   }
   
   public boolean professional()
   {
      return model.isProfessional(viewState.getUsername());
   }
   
   public StringProperty usernameProperty()
   {
      return usernameProperty;
   }
   
   public StringProperty profileInfoProperty()
   {
      return profileInfoProperty;
   }
   
   public StringProperty profileUsernameProperty()
   {
      return profileUsernameProperty;
   }
   
   public StringProperty friendButton()
   {
      return friendButton;
   }
   
   public ObservableList<String> getStoryList()
   {
      return storyList;
   }
   
   public StringProperty getPhoneNumberProperty()
   {
      return phoneNumberProperty;
   }
   
   public StringProperty getEmailProperty()
   {
      return emailProperty;
   }
   
   public ObservableList<Service> getServiceList()
   {
      return serviceList;
   }
   
   public boolean isProfessional()
   {
      return model.isProfessional(viewState.getProfileUsername());
   }
   
   private void isFriend()
   {
      if (model.isFriend(viewState.getUsername(), viewState.getProfileUsername()))
      {
         friendButton.set("Unfriend");
      }
      else
      {
         friendButton.set("Add friend");
      }
   }
   
   public void friendAction()
   {
      if (model.isFriend(viewState.getUsername(), viewState.getProfileUsername()))
      {
         model.unfriend(viewState.getUsername(), profileUsernameProperty.get());
      }
      else
      {
         model.addFriend(viewState.getUsername(), profileUsernameProperty.get());
      }
      reset();
   }
   
   public void reportClicked()
   {
      if (!model.againReported(viewState.getUsername(), viewState.getProfileUsername()))
      {
         model.addReport(viewState.getUsername(), profileUsernameProperty.get());
      }
      reset();
   }
   
   public boolean againReported()
   {
      return model.againReported(viewState.getUsername(), profileUsernameProperty.get());
   }
}
