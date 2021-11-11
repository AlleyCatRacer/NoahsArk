package Model.ViewModelInterfaces;

import Model.Profile;
import Model.SearchItems.Service;

import java.util.ArrayList;

public interface ProfileModel extends UserModel
{
  void addFriend(String username, String friend);

  void unfriend(String username, String friend);

  boolean isFriend(String username, String friend);

  void addReport(String username, String report);

  boolean againReported(String username, String report);

  Profile getProfileInfo(String username);

  ArrayList<Service> searchServices(String searchInfo);

  void updateServices(ArrayList<Service> services);
}
