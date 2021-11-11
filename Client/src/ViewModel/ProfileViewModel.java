package ViewModel;

import Model.ViewModelInterfaces.ProfileModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfileViewModel
{
   private ProfileModel model;
   private ViewState viewState;
   private StringProperty usernameProperty;
   private StringProperty profileUsernameProperty;
   private StringProperty profileInfoProperty;
   private ObservableList<String> storyList;
   private StringProperty friendButton;
   private BooleanProperty professional;
   
   public ProfileViewModel(ProfileModel model, ViewState viewState)
   {
      this.model=model;
      this.viewState=viewState;
      usernameProperty = new SimpleStringProperty(viewState.getUsername());
      friendButton = new SimpleStringProperty("");
      profileUsernameProperty = new SimpleStringProperty();
      profileInfoProperty = new SimpleStringProperty();
      storyList = FXCollections.observableArrayList();
      professional = new SimpleBooleanProperty(false);
   }
   
   public void reset()
   {
      isFriend();
      profileUsernameProperty.set(viewState.getProfileUsername());
      professional.set(model.isProfessional(profileUsernameProperty.get()));
      usernameProperty.set(viewState.getUsername());
      profileInfoProperty.set(model.getProfileInfo(viewState.getProfileUsername()).getInfo());
      storyList.setAll(model.getProfileInfo(viewState.getProfileUsername()).getStories());
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
   
   public boolean isProfessional()
   {
      return professional.get();
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
