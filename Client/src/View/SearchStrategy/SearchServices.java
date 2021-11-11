package View.SearchStrategy;

import Model.ViewModelInterfaces.SearchModel;
import ViewModel.SearchViewModel;

public class SearchServices implements Strategy
{
  @Override public void search(SearchViewModel vm, SearchModel model)
  {
    vm.isServiceFilter().set(true);
    vm.getSearchResult().setAll(model.searchServices(vm.getSearchText().get()));
    if (vm.getSearchResult().isEmpty())
      vm.getErrorLabel().set("No match");
  }
}
