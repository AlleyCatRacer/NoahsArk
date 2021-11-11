package ViewModel;

import Model.ViewModelInterfaces.EditProfileModel;
import Model.Profile;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EditProfileViewModel
{
   private EditProfileModel model;
   private ViewState      viewState;
   private StringProperty usernameProperty;
   private StringProperty profileInfoProperty;
   private ObservableList<String> storyList;
   private StringProperty postProperty;
   private StringProperty errorProperty;
   private BooleanProperty professional;
   
   public EditProfileViewModel(EditProfileModel model, ViewState viewState)
   {
      this.model = model;
      this.viewState = viewState;
      this.usernameProperty = new SimpleStringProperty(viewState.getUsername());
      this.profileInfoProperty = new SimpleStringProperty();
      this.storyList = FXCollections.observableArrayList();
      this.postProperty = new SimpleStringProperty();
      this.errorProperty = new SimpleStringProperty("");
      this.professional = new SimpleBooleanProperty(false);
   }
   
   public void reset()
   {
      usernameProperty.set(viewState.getUsername());
      profileInfoProperty.set(model.getProfileInfo(usernameProperty.get()).getInfo());
      storyList.setAll(model.getProfileInfo(usernameProperty.get()).getStories());
      postProperty.set("");
      errorProperty.set("");
      professional.set(model.isProfessional(viewState.getUsername()));
   }
   
   public boolean professional()
   {
      reset();
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
   
   public ObservableList<String> storyList()
   {
      return storyList;
   }
   
   public StringProperty postProperty()
   {
      return postProperty;
   }
   
   public StringProperty errorProperty()
   {
      return errorProperty;
   }
   
   public void save()
   {
      try
      {
         Profile p = new Profile(usernameProperty.get(), profileInfoProperty().get(), new ArrayList<>(storyList), professional.get());
         model.updateProfile(p);
      }
      catch (IllegalStateException e)
      {
         e.printStackTrace();
         errorProperty.set(e.getMessage());
      }
   }
   
   public void post()
   {
      storyList.add(postProperty.get());
      postProperty.set("");
      save();
   }
   
   public void deletePost(String post)
   {
      storyList.remove(post);
      save();
   }
   
   public void professionalApplication()
   {
      model.professionalApplication(viewState.getUsername());
      reset();
   }
}
