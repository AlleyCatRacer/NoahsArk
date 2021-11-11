package View;

import ViewModel.HomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

public class HomeController extends ViewController
{
   @FXML private Label         usernameLabel;
   @FXML private Label         headline1;
   @FXML private TextArea      article1;
   @FXML private Label         headline2;
   @FXML private TextArea      article2;
   
   private       HomeViewModel viewModel;
   
   public HomeController()
   {
      //
   }

   @Override protected void initBindings()
   {
      this.viewModel = getFactory().getHomeViewModel();
      headline1.textProperty().bind(viewModel.headline1Property());
      article1.textProperty().bind(viewModel.article1Property());
      headline2.textProperty().bind(viewModel.headline2Property());
      article2.textProperty().bind(viewModel.article2Property());
      usernameLabel.textProperty().bind(viewModel.usernameProperty());
      viewModel.reset();
   }
   
   public void reset()
   {
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
