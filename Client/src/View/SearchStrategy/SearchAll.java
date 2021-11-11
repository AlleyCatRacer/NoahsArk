package View.SearchStrategy;

import Model.ViewModelInterfaces.SearchModel;
import ViewModel.SearchViewModel;

public class SearchAll implements Strategy
{
  @Override public void search(SearchViewModel vm, SearchModel model)
  {
    vm.isSearchAllFilter().set(true);

    if (model.searchServices(vm.getSearchText().get()).isEmpty())
      vm.getSearchResult()
          .setAll(model.searchFacilities(vm.getSearchText().get()));

    vm.getSearchResult().setAll(model.searchServices(vm.getSearchText().get()));
    vm.getSearchResult()
        .addAll(model.searchFacilities(vm.getSearchText().get()));

    if (vm.getSearchResult().isEmpty())
      vm.getErrorLabel().set("No match");
  }
}


