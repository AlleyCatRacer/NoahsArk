package View.SearchStrategy;

import Model.ViewModelInterfaces.SearchModel;
import ViewModel.SearchViewModel;

public class SearchFacilities implements Strategy
{
  @Override public void search(SearchViewModel vm, SearchModel model)
  {
    vm.isFacilityFilter().set(true);
    vm.getSearchResult().setAll(model.searchFacilities(vm.getSearchText().get()));
    if (vm.getSearchResult().isEmpty())
      vm.getErrorLabel().set("No match");
  }
}
