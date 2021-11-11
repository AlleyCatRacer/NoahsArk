package ViewModel;

import Model.Profile;
import Model.SearchItems.Service;
import Model.ViewModelInterfaces.EditProfileModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EditProfessionalProfileViewModel
{
   private EditProfileModel        model;
   private ViewState               viewState;
   private StringProperty          usernameProperty;
   private StringProperty          profileInfoProperty;
   private ObservableList<String>  storyList;
   private StringProperty          postProperty;
   private StringProperty          errorProperty;
   private BooleanProperty         professional;
   private StringProperty          phoneNumberProperty;
   private StringProperty          emailProperty;
   private ObservableList<Service> serviceList;
   
   public EditProfessionalProfileViewModel(EditProfileModel model, ViewState viewState)
   {
      this.model     = model;
      this.viewState = viewState;
      //TODO set Services in ViewState when you can get the services from DB
      this.usernameProperty    = new SimpleStringProperty();
      this.profileInfoProperty = new SimpleStringProperty();
      this.storyList           = FXCollections.observableArrayList();
      this.postProperty        = new SimpleStringProperty();
      this.errorProperty       = new SimpleStringProperty("");
      this.professional        = new SimpleBooleanProperty(true);
      this.phoneNumberProperty = new SimpleStringProperty("");
      this.emailProperty       = new SimpleStringProperty("");
      this.serviceList         = FXCollections.observableArrayList();
   }
   
   public void reset()
   {
      usernameProperty.set(viewState.getUsername());
      profileInfoProperty.set(model.getProfileInfo(viewState.getUsername()).getInfo());
      storyList.setAll(model.getProfileInfo(viewState.getUsername()).getStories());
      postProperty.set("");
      errorProperty.set("");
      professional.set(model.isProfessional(viewState.getUsername()));
      serviceList.setAll(model.getProfileInfo(viewState.getUsername()).getServices());
      phoneNumberProperty.set(model.getProfileInfo(viewState.getUsername()).getPhoneNumber());
      emailProperty.set(model.getProfileInfo(viewState.getUsername()).getEmail());
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
   
   public StringProperty phoneProperty()
   {
      return phoneNumberProperty;
   }
   
   public StringProperty emailProperty()
   {
      return emailProperty;
   }
   
   public ObservableList<Service> serviceList()
   {
      return serviceList;
   }
   
   public void save()
   {
      try
      {
         Profile p = new Profile(usernameProperty.get(), profileInfoProperty().get(), new ArrayList<>(storyList),
                                 professional.get(), phoneNumberProperty.get(), emailProperty.get(),
                                 (viewState.getServices()));
         model.updateProfile(p);
         errorProperty.set("");
      }
      catch (Exception e)
      {
         errorProperty.set(e.getMessage());
      }
   }
   
   public void post()
   {
      errorProperty.set("");
      if (!postProperty.get().isEmpty() || !postProperty.get().equals(""))
      {
         storyList.add(postProperty.get());
         postProperty.set("");
         save();
      }
      else
      {
         errorProperty.set("Cannot post an empty story");
      }
   }
   
   public void deletePost(String post)
   {
      errorProperty.set("");
      storyList.remove(post);
      save();
   }
   
   public void professionalApplication()
   {
      model.professionalApplication(viewState.getUsername());
      reset();
   }
   
   public void setSelectedService(Service service)
   {
      viewState.setSelectedService(service);
      viewState.setCreateListing(false);
      viewState.setServices(new ArrayList<>(serviceList));
   }
   
   public void createListing()
   {
      viewState.setCreateListing(true);
      viewState.setSelectedService(null);
   }
}
