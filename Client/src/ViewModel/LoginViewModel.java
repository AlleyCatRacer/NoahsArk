package ViewModel;

import Model.ViewModelInterfaces.LoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel
{
   private LoginModel model;
   private ViewState      viewState;
   private StringProperty usernameProperty;
   private StringProperty passwordProperty;
   private StringProperty errorProperty;
   
   public LoginViewModel(LoginModel model, ViewState viewState)
   {
      this.model            = model;
      this.viewState        = viewState;
      this.usernameProperty = new SimpleStringProperty();
      this.passwordProperty = new SimpleStringProperty();
      this.errorProperty    = new SimpleStringProperty("");
   }
   
   public StringProperty usernameProperty()
   {
      return usernameProperty;
   }
   
   public StringProperty passwordProperty()
   {
      return passwordProperty;
   }
   
   public StringProperty errorProperty()
   {
      return errorProperty;
   }

   public void clear()
   {
      usernameProperty.set("");
      passwordProperty.set("");
      errorProperty.set("");
   }
   
   public boolean login()
   {
      try
      {
         if(usernameProperty.get() != null && usernameProperty.get().length() >= 3)
         {
          model.login(usernameProperty.get(), passwordProperty.get());
          viewState.setUsername(model.getUsername());
          return true;
         }
         else
         {
            errorProperty.set("Username must be at least 3 characters long");
         }
      }
      catch (IllegalArgumentException e)
      {
         clear();
         errorProperty.set("Username or password is incorrect");
      }
      return false;
   }
}
