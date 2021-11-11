package View;

import ViewModel.CreateProfileViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateProfileController extends ViewController
{

  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private PasswordField passwordCheckField;
  @FXML private Label errorLabel;

  private CreateProfileViewModel viewModel;

  public CreateProfileController()
  {
    //
  }

  @Override protected void initBindings()
  {
    viewModel = getFactory().getCreateProfileViewModel();
    usernameField.textProperty()
        .bindBidirectional(viewModel.usernameProperty());
    passwordField.textProperty()
        .bindBidirectional(viewModel.passwordProperty());
    passwordCheckField.textProperty()
        .bindBidirectional(viewModel.passwordCheckProperty());
    errorLabel.textProperty().bind(viewModel.errorProperty());
  }

  @FXML private void onJoin()
  {
    if(viewModel.join())
    getViewHandler().openView(ViewKey.HOME);
    usernameField.requestFocus();
  }

  @FXML private void onEnter(ActionEvent evt)
  {
    if (evt.getSource() == usernameField)
    {
      passwordField.requestFocus();
    }
    else if (evt.getSource() == passwordField)
    {
      passwordCheckField.requestFocus();
    }
    else
    {
      onJoin();
    }
  }

  @FXML private void onCancel()
  {
    getViewHandler().openView(ViewKey.LOGIN);
  }
}
