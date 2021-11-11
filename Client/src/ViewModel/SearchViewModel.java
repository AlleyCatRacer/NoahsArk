package ViewModel;

import Model.SearchItems.SearchItem;
import Model.ViewModelInterfaces.SearchModel;
import View.SearchStrategy.Strategy;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchViewModel
{
  private SearchModel model;
  private ViewState viewState;
  private StringProperty usernameProperty;
  private StringProperty searchText;
  private StringProperty errorLabel;
  private ObservableList<SearchItem> searchResult;
  private BooleanProperty searchAllFilter;
  private BooleanProperty serviceFilter;
  private BooleanProperty facilityFilter;
  private BooleanProperty userFilter;

  public SearchViewModel(SearchModel model, ViewState state)
  {
    this.model = model;
    this.viewState = state;
    viewState.setUsername(this.model.getUsername());
    this.usernameProperty = new SimpleStringProperty(viewState.getUsername());
    this.errorLabel = new SimpleStringProperty("");
    this.searchText = new SimpleStringProperty();
    this.searchResult = FXCollections.observableArrayList();
    searchResult.addAll(model.getServices());
    searchResult.addAll(model.getFacilities());

    searchAllFilter = new SimpleBooleanProperty(true);
    serviceFilter = new SimpleBooleanProperty(false);
    facilityFilter = new SimpleBooleanProperty(false);
    userFilter = new SimpleBooleanProperty(false);
  }

  public void reset()
  {
    usernameProperty.set(viewState.getUsername());
    clear();
    searchAllFilter.set(true);
    searchText.set("");
    searchResult.setAll(model.getServices());
    searchResult.addAll(model.getFacilities());
  }
  
  public boolean professional()
  {
    reset();
    return model.isProfessional(viewState.getUsername());
  }
  
  public void clear()
  {
    errorLabel.set("");
    searchAllFilter.set(false);
    serviceFilter.set(false);
    facilityFilter.set(false);
    userFilter.set(false);
  }

  public StringProperty usernameProperty()
  {
    return usernameProperty;
  }

  public StringProperty getSearchText()
  {
    return searchText;
  }

  public ObservableList<SearchItem> getSearchResult()
  {
    return searchResult;
  }

  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }

  public BooleanProperty isSearchAllFilter()
  {
    return searchAllFilter;
  }

  public BooleanProperty isServiceFilter()
  {
    return serviceFilter;
  }

  public BooleanProperty isFacilityFilter()
  {
    return facilityFilter;
  }

  public BooleanProperty isUserFilter()
  {
    return userFilter;
  }

  public void search(Strategy search)
  {
    clear();
    search.search(this, model);
  }

  public void openProfile(String username)
  {
    viewState.setProfileUsername(username);
  }
  
  public boolean isProfessional(String username)
  {
    return model.isProfessional(username);
  }
}
