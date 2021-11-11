package View;

import ViewModel.ProfileViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Optional;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

public class ProfileController extends ViewController
{
   @FXML private Label usernameLabel;
   @FXML private Label profileUsernameLabel;
   @FXML private Button friendButton;
   @FXML private TextArea profileInfoArea;
   @FXML private ListView<String> storyList;
   @FXML private Button reportButton;
   
   private ProfileViewModel viewModel;
   
   public ProfileController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel=getFactory().getProfileViewModel();
      viewModel.reset();
      usernameLabel.textProperty().bind(viewModel.usernameProperty());
      profileUsernameLabel.textProperty().bind(viewModel.profileUsernameProperty());
      profileInfoArea.textProperty().bind(viewModel.profileInfoProperty());
      storyList.setItems(viewModel.getStoryList());
      friendButton.textProperty().bind(viewModel.friendButton());
   }
   
   public void reset()
   {
      if (viewModel.isProfessional())
      {
         profileUsernameLabel.textFillProperty().set(GREEN);
         profileUsernameLabel.setFont(Font.font(viewModel.usernameProperty().get(), FontWeight.BOLD, FontPosture.ITALIC, 13));
      }
      else
      {
         profileUsernameLabel.textFillProperty().set(BLACK);
         profileUsernameLabel.setFont(Font.font(viewModel.usernameProperty().get(), FontWeight.NORMAL, FontPosture.REGULAR, 13));
      }
      if (viewModel.professional())
      {
         usernameLabel.textFillProperty().set(GREEN);
         usernameLabel.setFont(Font.font(viewModel.usernameProperty().get(), FontWeight.BOLD, FontPosture.ITALIC, 13));
      }
      else
      {
         usernameLabel.textFillProperty().set(BLACK);
         usernameLabel.setFont(Font.font(viewModel.usernameProperty().get(), FontWeight.NORMAL, FontPosture.REGULAR, 13));
      }
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
         if(confirmation())
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
         if(confirmation())
         viewModel.friendAction();
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
