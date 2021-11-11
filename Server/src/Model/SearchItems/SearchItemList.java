package Model.SearchItems;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * SearchItemList class that contains SearchItem objects in an ArrayList.
 * Implements Serializable.
 *
 * @author Group 1
 * @version 1.0 - 20.05.2021
 */
public class SearchItemList implements Serializable
{
   private ArrayList<SearchItem> searchItems;

   /**
    * Zero argument constructor that initializes the ArrayList of SearchItem objects.
    */
   public SearchItemList()
   {
      this.searchItems = new ArrayList<>();
   }

   /**
    * Getter for all services.
    *
    * @return An ArrayList of all the Service objects from the ArrayList of SearchItem
    */
   public ArrayList<Service> getAllServices()
   {
      ArrayList<Service> s=new ArrayList<>();
      for (SearchItem item : searchItems)
      {
         if (item instanceof Service)
         {
            s.add((Service) item);
         }
      }
      return s;
   }

   /**
    * Getter for all facilities.
    *
    * @return An ArrayList of all Facility objects from the ArrayList of SearchItem
    */
   public ArrayList<Facility> getAllFacilities()
   {
      ArrayList<Facility> f=new ArrayList<>();
      for (SearchItem item : searchItems)
      {
         if (item instanceof Facility)
         {
            f.add((Facility) item);
         }
      }
      return f;
   }

   /**
    * Getter for services who's field(s) match the given argument.
    *
    * @param info String containing the information based on which the search of Services will be performed
    * @return ArrayList of Services based on the provided argument
    */
   public ArrayList<Service> getServiceBySearch(String info)
   {
      ArrayList<Service> filteredServices = new ArrayList<>();

      if(info==null)
         return filteredServices;
   
      for (SearchItem item : searchItems)
      {
         if (item instanceof Service && (item.getTitle()
                                             .toLowerCase()
                                             .contains(info.toLowerCase()) || item.getPostcode().contains(info)))
         {
            filteredServices.add((Service) item);
         }
      }
      return filteredServices;
   }

   /**
    * Getter for facilities who's field(s) match the given argument.
    *
    * @param info String containing the information based on which the search of Facilities will be performed
    * @return ArrayList of Facilities based on the provided argument
    */
   public ArrayList<Facility> getFacilityBySearch(String info)
   {
      ArrayList<Facility> filteredFacilities = new ArrayList<>();

      if(info==null)
         return filteredFacilities;

      for (SearchItem searchItem : searchItems)
      {
         if (searchItem instanceof Facility && (searchItem.getTitle().toLowerCase().contains(info.toLowerCase()) || searchItem.getPostcode().contains(info)))
         {
            filteredFacilities.add((Facility) searchItem);
         }
      }
      return filteredFacilities;
   }

   /**
    * Adds a Service object to the ArrayList of SearchItem
    *
    * @param service Service object that should be added to the ArrayList
    * @throws IllegalArgumentException If the Service object is null = "Cannot add a null service"
    */
   public void addService(Service service)
   {
      if (service != null)
      {
         searchItems.add(service);
      }
      else
      {
         throw new IllegalArgumentException("Cannot add a null service");
      }
   }

   /**
    * Removes Service object from SearchItem ArrayList based on the provided argument.
    *
    * @param title String representing the title of the Service that should be removed
    * @throws IllegalArgumentException If the Service object was not found based on the provided title = "Service not found"
    */
   public void removeServiceByTitle(String title)
   {
      int removed = 0;
      for (int i = 0; i < searchItems.size(); i++)
      {
         if (searchItems.get(i) instanceof Service && searchItems.get(i).getTitle().equalsIgnoreCase(title))
         {
            removed++;
            searchItems.remove(searchItems.get(i));
            break;
         }
      }
      if (removed==0)
      {
         throw new IllegalArgumentException("Service not found");
      }
   }

   /**
    * Adds a Facility object to the ArrayList of SearchItem
    *
    * @param facility Facility object that should be added to the ArrayList
    * @throws IllegalArgumentException If the Facility object is null = "Cannot add a null facility"
    */
   public void addFacility(Facility facility)
   {
      if (facility != null)
      {
         searchItems.add(facility);
      }
      else
      {
         throw new IllegalArgumentException("Cannot add a null facility");
      }
   }

   /**
    * Removes Facility object based on the provided argument.
    *
    * @param name String representing the title of the Facility that should be removed
    * @throws IllegalArgumentException If the Facility object was not found based on the provided title = "Facility not found"
    */
   public void removeFacilityByName(String name)
   {
      int removed=0;
      for (int i = 0; i < searchItems.size(); i++)
      {
         if (searchItems.get(i) instanceof Facility && searchItems.get(i).getTitle().equalsIgnoreCase(name))
         {
            removed++;
            searchItems.remove(searchItems.get(i));
            break;
         }
      }
      if (removed==0)
      {
         throw new IllegalArgumentException("Facility not found");
      }
   }
}
