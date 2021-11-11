package ViewModel;

import Model.SearchItems.Service;
import Model.ViewModelInterfaces.ProfileModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ServiceListingViewModel
{
   private ProfileModel   model;
   private ViewState      viewState;
   private StringProperty titleProperty;
   private StringProperty postcodeProperty;
   private StringProperty priceProperty;
   private StringProperty detailsProperty;
   private StringProperty errorProperty;
   
   public ServiceListingViewModel(ProfileModel model, ViewState viewState)
   {
      this.model            = model;
      this.viewState        = viewState;
      this.titleProperty    = new SimpleStringProperty("");
      this.postcodeProperty = new SimpleStringProperty("");
      this.priceProperty    = new SimpleStringProperty("");
      this.detailsProperty  = new SimpleStringProperty("");
      this.errorProperty    = new SimpleStringProperty("");
   }
   
   public void reset()
   {
      if (viewState.getSelectedService() != null)
      {
         titleProperty.set(viewState.getSelectedService().getTitle());
         postcodeProperty.set(viewState.getSelectedService().getPostcode());
         priceProperty.set(String.valueOf(viewState.getSelectedService().getPrice()));
         detailsProperty.set(viewState.getSelectedService().getOnlyDetails());
      }
      errorProperty.set("");
   }
   
   public void clear()
   {
      titleProperty.set("");
      postcodeProperty.set("");
      priceProperty.set("");
      detailsProperty.set("");
      errorProperty.set("");
   }
   
   public StringProperty titleProperty()
   {
      return titleProperty;
   }
   
   public StringProperty postcodeProperty()
   {
      return postcodeProperty;
   }
   
   public StringProperty priceProperty()
   {
      return priceProperty;
   }
   
   public StringProperty detailsProperty()
   {
      return detailsProperty;
   }
   
   public StringProperty errorProperty()
   {
      return errorProperty;
   }
   
   public boolean create()
   {
      return viewState.getCreateListing();
   }
   
   public void updateService()
   {
      Set<Service> combo = new HashSet<>(viewState.getServices());
      combo.addAll(model.getProfileInfo(viewState.getUsername()).getServices());
      try
      {
         combo.remove(viewState.getSelectedService());
         if (priceProperty.get() == null || priceProperty.get().equals(""))
         {
            combo.add(new Service(titleProperty().get(), viewState.getUsername(), postcodeProperty.get(),
                                  detailsProperty.get()));
         }
         else
         {
            combo.add(new Service(titleProperty().get(), viewState.getUsername(), postcodeProperty.get(),
                                  detailsProperty.get(), Integer.parseInt(priceProperty().get())));
         }
         viewState.setServices(new ArrayList<>(combo));
         viewState.setCreateListing(false);
         model.updateServices(viewState.getServices());
      }
      catch (IllegalArgumentException e)
      {
         e.printStackTrace();
         errorProperty.set(e.getMessage());
      }
   }
   
   public void addService()
   {
      Set<Service> combo = new HashSet<>(viewState.getServices());
      combo.addAll(model.getProfileInfo(viewState.getUsername()).getServices());
      try
      {
         if (priceProperty.get() == null || priceProperty.get().equals(""))
         {
            combo.add(new Service(titleProperty().get(), viewState.getUsername(), postcodeProperty.get(),
                                  detailsProperty.get()));
         }
         else
         {
            combo.add(new Service(titleProperty().get(), viewState.getUsername(), postcodeProperty.get(),
                                  detailsProperty.get(), Integer.parseInt(priceProperty().get())));
         }
         viewState.setServices(new ArrayList<>(combo));
         viewState.setCreateListing(false);
         model.updateServices(viewState.getServices());
      }
      catch (IllegalArgumentException e)
      {
         e.printStackTrace();
         errorProperty.set(e.getMessage());
      }
   }
   
   public void deleteService()
   {
      try
      {
         Set<Service> combo = new HashSet<>(viewState.getServices());
         combo.addAll(model.getProfileInfo(viewState.getUsername()).getServices());
         combo.remove(viewState.getSelectedService());
         viewState.setServices(new ArrayList<>(combo));
         model.updateServices(viewState.getServices());
      }
      catch (IllegalArgumentException e)
      {
         e.printStackTrace();
         errorProperty.set(e.getMessage());
      }
   }
}
