package Model.ViewModelInterfaces;

import Model.SearchItems.Facility;
import Model.SearchItems.SearchItem;
import Model.SearchItems.Service;

import java.util.ArrayList;

public interface SearchModel extends UserModel
{
  ArrayList<Service> getServices();

  ArrayList<Service> searchServices(String searchInfo);

  ArrayList<Facility> getFacilities();

  ArrayList<Facility> searchFacilities(String info);

  ArrayList<SearchItem> searchUser(String username);
}
