package View;

import Model.SearchItems.SearchItem;
import View.SearchStrategy.*;
import ViewModel.SearchViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

public class SearchController extends ViewController
{
   @FXML TextField                       searchTextField;
   @FXML TableView<SearchItem>           searchResultTable;
   @FXML TableColumn<SearchItem, String> titleColumn;
   @FXML TableColumn<SearchItem, String> postcodeColumn;
   @FXML RadioButton                     searchAllFilter;
   @FXML RadioButton                     serviceFilter;
   @FXML RadioButton                     facilityFilter;
   @FXML RadioButton                     userFilter;
   @FXML Label                           usernameLabel;
   @FXML Label                           errorLabel;
   
   Strategy search;
   
   private SearchViewModel viewModel;
   
   public SearchController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel = getFactory().getSearchViewModel();
      usernameLabel.textProperty().bind(viewModel.usernameProperty());
      searchResultTable.setItems(viewModel.getSearchResult());
      searchTextField.textProperty().bindBidirectional(viewModel.getSearchText());
      viewModel.reset();
      errorLabel.textProperty().bind(viewModel.getErrorLabel());
      
      searchAllFilter.selectedProperty().bindBidirectional(viewModel.isSearchAllFilter());
      serviceFilter.selectedProperty().bindBidirectional(viewModel.isServiceFilter());
      facilityFilter.selectedProperty().bindBidirectional(viewModel.isFacilityFilter());
      userFilter.selectedProperty().bindBidirectional(viewModel.isUserFilter());
      
      titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
      postcodeColumn.setCellValueFactory(new PropertyValueFactory<>("Postcode"));
      
      search = new SearchAll();
   }
   
   public void reset()
   {
      checkPro();
      checkSearch();
   }
   
   private void checkPro()
   {
      if (viewModel.professional())
      {
         usernameLabel.textFillProperty().set(GREEN);
         usernameLabel.setFont(Font.font(viewModel.usernameProperty().get(), FontWeight.BOLD, FontPosture.ITALIC, 13));
      }
      else
      {
         usernameLabel.textFillProperty().set(BLACK);
         usernameLabel.setFont(
               Font.font(viewModel.usernameProperty().get(), FontWeight.NORMAL, FontPosture.REGULAR, 13));
      }
   }
   
   private void checkSearch()
   {
      if (search instanceof SearchUsers)
      {
         titleColumn.setText("Username");
         postcodeColumn.setVisible(false);
      }
      else
      {
         titleColumn.setText("Title");
         postcodeColumn.setVisible(true);
      }
   }
   
   private boolean usernameTitle()
   {
      return titleColumn.getText().equals("Username");
   }
   
   @FXML private void searchAllClicked()
   {
      search = new SearchAll();
      submitSearch();
   }
   
   @FXML private void servicesClicked()
   {
      search = new SearchServices();
      submitSearch();
   }
   
   @FXML private void facilitiesClicked()
   {
      search = new SearchFacilities();
      submitSearch();
   }
   
   @FXML private void usersClicked()
   {
      checkSearch();
      search = new SearchUsers();
      submitSearch();
   }
   
   @FXML private void submitSearch()
   {
      checkSearch();
      viewModel.search(search);
      searchResultTable.sort();
   }
   
   @FXML private void itemDetails(MouseEvent event)
   {
      if (event.getClickCount() == 2)
      {
         SearchItem item = searchResultTable.getSelectionModel().getSelectedItem();
         if (userFilter.selectedProperty().get())
         {
            viewModel.openProfile(item.getTitle());
            if (viewModel.isProfessional(item.getTitle()))
            {
               getViewHandler().openView(ViewKey.PROFESSIONAL_PROFILE);
            }
            else
            {
               getViewHandler().openView(ViewKey.PROFILE);
            }
         }
         else
         {
            details(item);
         }
      }
   }
   
   private void details(SearchItem item)
   {
      String text = item.getTitle();
      String content = "POSTCODE: " + item.getPostcode() +
                       "\nCONTACT:   " + item.getContactInfo() +
                       "\nDETAILS:     " + item.getDetails();
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("DETAILS");
      alert.setHeaderText(text);
      alert.setContentText(content);
      alert.setResizable(true);
      alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
      alert.show();
   }
   
   @FXML private void onEnter()
   {
      submitSearch();
   }
   
   @FXML private void homeClicked()
   {
      getViewHandler().openView(ViewKey.HOME);
   }
   
   @FXML private void logoutClicked()
   {
      getViewHandler().openView(ViewKey.LOGIN);
   }
   
   @FXML private void profileClicked()
   {
      if (viewModel.professional())
      {
         getViewHandler().openView(ViewKey.EDIT_PROFESSIONAL_PROFILE);
      }
      else
      {
         getViewHandler().openView(ViewKey.EDIT_PROFILE);
      }
   }
   
   @FXML private void FAQClicked()
   {
      getViewHandler().openView(ViewKey.FAQ);
   }
   
   @FXML private void forumClicked()
   {
      getViewHandler().openView(ViewKey.FORUM);
   }
}
