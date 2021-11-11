package ViewModel;

import Model.ViewModelInterfaces.FriendsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FriendsViewModel
{
   FriendsModel model;
   ViewState viewState;
   ObservableList<String> friendsList;
   
   FriendsViewModel(FriendsModel model, ViewState viewState)
   {
      this.model=model;
      this.viewState= viewState;
      friendsList = FXCollections.observableArrayList();
   }
   
   public void reset()
   {
      friendsList.setAll(model.getFriendsList(viewState.getUsername()));
   }
   
   public boolean professional()
   {
      return model.isProfessional(viewState.getUsername());
   }
   
   public boolean isProfessional()
   {
      return model.isProfessional(viewState.getProfileUsername());
   }
   
   public ObservableList<String> getFriendsList()
   {
      return friendsList;
   }
   
   public void openProfile(String username)
   {
      viewState.setProfileUsername(username);
   }
}
