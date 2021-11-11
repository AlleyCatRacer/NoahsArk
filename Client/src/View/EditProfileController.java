package View;

import ViewModel.EditProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Optional;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

public class EditProfileController extends ViewController
{
   @FXML Label            usernameLabel;
   @FXML TextArea         profileTextArea;
   @FXML Label            errorLabel;
   @FXML Button           professionalButton;
   @FXML ListView<String> storyList;
   @FXML TextField        postField;
   @FXML AnchorPane       mainStage;
   
   private EditProfileViewModel viewModel;
   
   public EditProfileController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel = getFactory().getEditViewModel();
      viewModel.reset();
      errorLabel.textProperty().bind(viewModel.errorProperty());
      usernameLabel.textProperty().bind(viewModel.usernameProperty());
      profileTextArea.textProperty().bindBidirectional(viewModel.profileInfoProperty());
      storyList.setItems(viewModel.storyList());
      postField.textProperty().bindBidirectional(viewModel.postProperty());
      profileTextArea.requestFocus();
   }
   
   public void reset()
   {
      mainStage.setDisable(false);
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
      String  s;
      String  content;
      boolean ok = confirmation();
      try
      {
         if (ok)
         {
            viewModel.professionalApplication();
            viewModel.reset();
         }
         s       = "Congratulations!";
         content = "You are now a professional user!";
         getViewHandler().openView(ViewKey.EDIT_PROFESSIONAL_PROFILE);
      }
      catch (IllegalStateException e)
      {
         s       = "Error";
         content = errorLabel.getText();
      }
      
      if (ok)
      {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle(s);
         alert.setHeaderText(s);
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
   
   @FXML private void FAQClicked()
   {
      getViewHandler().openView(ViewKey.FAQ);
   }
   
   @FXML private void forumClicked()
   {
      getViewHandler().openView(ViewKey.FORUM);
   }
   
   private boolean confirmation()
   {
      String content = "Please confirm your application for professional status.";
      
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText("Are you sure?");
      alert.setContentText(content);
      
      alert.setResizable(true);
      alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
      
      Optional<ButtonType> result = alert.showAndWait();
      return ((result.isPresent()) && (result.get() == ButtonType.OK));
   }
}
