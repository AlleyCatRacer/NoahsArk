package ViewModel;

import Model.ViewModelInterfaces.CreateProfileModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CreateProfileViewModel
{
  private CreateProfileModel model;
  private ViewState viewState;
  private StringProperty usernameProperty;
  private StringProperty passwordProperty;
  private StringProperty passwordCheckProperty;
  private StringProperty errorProperty;

  public CreateProfileViewModel(CreateProfileModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.usernameProperty = new SimpleStringProperty();
    this.passwordProperty = new SimpleStringProperty();
    this.passwordCheckProperty = new SimpleStringProperty();
    this.errorProperty = new SimpleStringProperty("");
  }

  public void reset()
  {
    usernameProperty.set("");
    passwordProperty.set("");
    passwordCheckProperty.set("");
    errorProperty.set("");
  }

  public StringProperty usernameProperty()
  {
    return usernameProperty;
  }

  public StringProperty passwordProperty()
  {
    return passwordProperty;
  }

  public StringProperty passwordCheckProperty()
  {
    return passwordCheckProperty;
  }

  public StringProperty errorProperty()
  {
    return errorProperty;
  }

  public void clear()
  {
      usernameProperty.set("");
      passwordProperty.set("");
      passwordCheckProperty.set("");
  }

  public boolean join()
  {
    try
    {
      if (passwordProperty.get().equals(passwordCheckProperty.get()))
      {
        model.join(usernameProperty.get(), passwordProperty.get());
        viewState.setUsername(model.getUsername());
        reset();
        return true;
      }
      else
      {
        errorProperty.set("Passwords must match");
        passwordProperty.set("");
        passwordCheckProperty.set("");
      }
    }
    catch (Exception e)
    {
      errorProperty.set(e.getMessage());
      clear();
    }
    return false;
  }
}
