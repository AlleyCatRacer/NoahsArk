package ViewModel;

import Model.ViewModelInterfaces.HomeModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HomeViewModel
{
   private HomeModel model;
   private ViewState            viewState;
   private SimpleStringProperty usernameLabel;
   private SimpleStringProperty headline1;
   private SimpleStringProperty headline2;
   private SimpleStringProperty article1;
   private SimpleStringProperty article2;
   
   public HomeViewModel(HomeModel model, ViewState viewState)
   {
      this.model     = model;
      this.viewState = viewState;
      viewState.setUsername(model.getUsername());
      this.article1 =new SimpleStringProperty("");
      this.headline1 =new SimpleStringProperty("");
      this.article2 =new SimpleStringProperty("");
      this.headline2 =new SimpleStringProperty("");
      this.usernameLabel = new SimpleStringProperty(viewState.getUsername());
      loadArticles();
   }
   
   public StringProperty usernameProperty()
   {
      return usernameLabel;
   }

   public void reset()
   {
      viewState.setUsername(model.getUsername());
      usernameLabel.set(viewState.getUsername());
   }
   
   public boolean professional()
   {
      reset();
      return model.isProfessional(viewState.getUsername());
   }
   
   public StringProperty article1Property()
   {
      return article1;
   }
   
   public StringProperty article2Property()
   {
      return article2;
   }
   
   public StringProperty headline1Property()
   {
      return headline1;
   }

   public StringProperty headline2Property()
   {
      return headline2;
   }

   private void loadArticles()
   {
      headline1.set(model.getArticles().getArticleByIndex(0).getHeadline());
      article1.set(model.getArticles().getArticleByIndex(0).getText());
      headline2.set(model.getArticles().getArticleByIndex(1).getHeadline());
      article2.set(model.getArticles().getArticleByIndex(1).getText());
   }
}
