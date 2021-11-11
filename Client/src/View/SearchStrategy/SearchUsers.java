package View.SearchStrategy;

import Model.ViewModelInterfaces.SearchModel;
import ViewModel.SearchViewModel;

public class SearchUsers implements Strategy
{
  @Override public void search(SearchViewModel vm, SearchModel model)
  {
    vm.isUserFilter().set(true);
    vm.getSearchResult().setAll(model.searchUser(vm.getSearchText().get()));
    if (vm.getSearchResult().isEmpty())
      vm.getErrorLabel().set("No match");
  }
}
