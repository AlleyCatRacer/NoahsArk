package View;

import Model.SearchItems.Service;
import ViewModel.ProfessionalProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.util.Optional;

public class ProfessionalProfileController extends ViewController
{
   @FXML private Label                        usernameLabel;
   @FXML private Label                        profileUsernameLabel;
   @FXML private Button                       friendButton;
   @FXML private TextArea                     profileTextArea;
   @FXML private ListView<String>             storyList;
   @FXML private Button                       reportButton;
   @FXML private Label                        phoneLabel;
   @FXML private Label                        emailLabel;
   @FXML private TableView<Service>           serviceList;
   @FXML private TableColumn<Service, String> titleCol;
   @FXML private TableColumn<Service, String> postcodeCol;
   
   private ProfessionalProfileViewModel viewModel;
   
   public ProfessionalProfileController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel = getFactory().getProfessionalProfileModel();
      viewModel.reset();
      usernameLabel.textProperty().bind(viewModel.usernameProperty());
      profileUsernameLabel.textProperty().bind(viewModel.profileUsernameProperty());
      profileTextArea.textProperty().bind(viewModel.profileInfoProperty());
      storyList.setItems(viewModel.getStoryList());
      friendButton.textProperty().bind(viewModel.friendButton());
      phoneLabel.textProperty().bind(viewModel.getPhoneNumberProperty());
      emailLabel.textProperty().bind(viewModel.getEmailProperty());
      serviceList.setItems(viewModel.getServiceList());
      
      titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
      postcodeCol.setCellValueFactory(new PropertyValueFactory<>("Postcode"));
   }
   
   @Override public void reset()
   {
      reportButton.setDisable(viewModel.againReported());
   }
   
   private boolean confirmation()
   {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("Are you sure?");
      alert.setContentText("Please confirm.");
      
      alert.setResizable(true);
      alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
      
      Optional<ButtonType> result = alert.showAndWait();
      return ((result.isPresent()) && (result.get() == ButtonType.OK));
   }
   
   @FXML public void reportClicked()
   {
      try
      {
         if (confirmation())
         {
            viewModel.reportClicked();
            reportButton.setDisable(true);
         }
      }
      catch (Exception e)
      {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Oh no!");
         alert.setContentText(e.getMessage());
         alert.setResizable(true);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
      }
   }
   
   @FXML public void friendAction()
   {
      try
      {
         if (confirmation())
         {
            viewModel.friendAction();
         }
      }
      catch (Exception e)
      {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Oh no!");
         alert.setContentText(e.getMessage());
         alert.setResizable(true);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
      }
   }
   
   @FXML private void serviceDetails(MouseEvent event)
   {
      if (event.getClickCount() == 2)
      {
         Service service = serviceList.getSelectionModel().getSelectedItem();
         String  text    = service.getTitle();
         String content = "Postcode: " + service.getPostcode() +
                          "\nProvider:   " + service.getContactInfo() +
                          "\nDetails:     " + service.getOnlyDetails();
         
         if (service.getPrice() != -10)
         {
            content += "\nPrice:       " + service.getPrice();
         }
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Details");
         alert.setHeaderText(text);
         alert.setContentText(content);
         alert.setResizable(true);
         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         alert.show();
      }
   }
   
   @FXML private void homeClicked()
   {
      getViewHandler().openView(ViewKey.HOME);
   }
   
   @FXML private void searchClicked()
   {
      getViewHandler().openView(ViewKey.SEARCH);
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
