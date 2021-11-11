package View;

import ViewModel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends ViewController
{
   @FXML private TextField     usernameField;
   @FXML private PasswordField passwordField;
   @FXML private Label         errorLabel;
   
   private       LoginViewModel viewModel;
   
   public LoginController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel = getFactory().getLoginViewModel();
      usernameField.textProperty().bindBidirectional(viewModel.usernameProperty());
      passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
      errorLabel.textProperty().bind(viewModel.errorProperty());
      viewModel.clear();
   }
   
   @FXML private void onLogin()
   {
      if (viewModel.login())
      {
         getViewHandler().openView(ViewKey.HOME);
      }
      usernameField.requestFocus();
   }
   
   @FXML private void onEnter(ActionEvent evt)
   {
      if (evt.getSource() == usernameField)
      {
         passwordField.requestFocus();
      }
      else
      {
         onLogin();
      }
   }

   @FXML private void onCreateProfile()
   {
      getViewHandler().openView(ViewKey.CREATE_PROFILE);
   }
}
