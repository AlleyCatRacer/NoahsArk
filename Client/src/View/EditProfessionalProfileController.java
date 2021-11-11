package View;

import Model.SearchItems.Service;
import ViewModel.EditProfessionalProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class EditProfessionalProfileController extends ViewController
{
   @FXML private Label                        usernameLabel;
   @FXML private TextArea                     profileTextArea;
   @FXML private Label                        errorLabel;
   @FXML private ListView<String>             storyList;
   @FXML private TextField                    postField;
   @FXML private AnchorPane                   mainStage;
   @FXML private TableView<Service>           serviceList;
   @FXML private TableColumn<Service, String> titleCol;
   @FXML private TableColumn<Service, String> postcodeCol;
   @FXML private TextField                    phoneField;
   @FXML private TextField                    emailField;
   
   private EditProfessionalProfileViewModel viewModel;
   
   public EditProfessionalProfileController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel = getFactory().getEditProfessionalProfileViewModel();
      viewModel.reset();
      usernameLabel.textProperty().bind(viewModel.usernameProperty());
      serviceList.setItems(viewModel.serviceList());
      storyList.setItems(viewModel.storyList());
      postField.textProperty().bindBidirectional(viewModel.postProperty());
      errorLabel.textProperty().bind(viewModel.errorProperty());
      profileTextArea.textProperty().bindBidirectional(viewModel.profileInfoProperty());
      phoneField.textProperty().bindBidirectional(viewModel.phoneProperty());
      emailField.textProperty().bindBidirectional(viewModel.emailProperty());
      
      titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
      postcodeCol.setCellValueFactory(new PropertyValueFactory<>("Postcode"));
   }
   
   public void reset()
   {
      if (mainStage.isDisabled())
      {
         mainStage.setDisable(false);
      }
   }
   
   @FXML private void save()
   {
      viewModel.save();
   }
   
   @FXML private void cancel()
   {
      viewModel.reset();
   }
   
   @FXML private void post()
   {
      viewModel.post();
   }
   
   @FXML private void delete()
   {
      viewModel.deletePost(storyList.getSelectionModel().getSelectedItem());
   }
   
   @FXML private void friendsClicked()
   {
      getViewHandler().openSubView(ViewKey.FRIENDS);
      mainStage.setDisable(true);
   }
   
   @FXML private void professionalClicked()
   {
      String s;
      String content;
      
      try
      {
         viewModel.professionalApplication();
         viewModel.reset();
         s       = "Status changed";
         content = "You are no longer a professional user";
      }
      catch (IllegalStateException e)
      {
         s       = "Error";
         content = errorLabel.getText();
      }
      
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle(s);
      alert.setHeaderText(s);
      alert.setContentText(content);
      alert.setResizable(true);
      alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
      alert.show();
      
      getViewHandler().openView(ViewKey.EDIT_PROFILE);
   }
   
   @FXML private void editServiceClicked()
   {
      viewModel.setSelectedService(serviceList.getSelectionModel().getSelectedItem());
      getViewHandler().openSubView(ViewKey.EDIT_LISTING);
      mainStage.setDisable(true);
   }
   
   @FXML private void createServiceClicked()
   {
      viewModel.createListing();
      getViewHandler().openSubView(ViewKey.EDIT_LISTING);
      mainStage.setDisable(true);
   }
   
   @FXML private void homeClicked()
   {
      viewModel.reset();
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
   
   @FXML private void FAQClicked()
   {
      getViewHandler().openView(ViewKey.FAQ);
   }
   
   @FXML private void forumClicked()
   {
      getViewHandler().openView(ViewKey.FORUM);
   }
}
