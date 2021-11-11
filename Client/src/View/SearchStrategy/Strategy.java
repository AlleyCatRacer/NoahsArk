package View.SearchStrategy;

import Model.ViewModelInterfaces.SearchModel;
import ViewModel.SearchViewModel;

public interface Strategy
{
  void search(SearchViewModel vm, SearchModel model);
}
