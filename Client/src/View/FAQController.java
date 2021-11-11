package View;

import ViewModel.FAQViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

public class FAQController extends ViewController
{
   @FXML private Button topic1Button;
   @FXML private Button topic2Button;
   @FXML private Button topic3Button;
   @FXML private Button topic4Button;

   @FXML private Label faqDesLabel1;
   @FXML private Label faqDesLabel2;
   @FXML private Label faqDesLabel3;
   @FXML private Label faqDesLabel4;

   @FXML private Label  usernameLabel;
   private       FAQViewModel viewModel;
   
   public FAQController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      this.viewModel = getFactory().getFAQViewModel();
      viewModel.reset();
      usernameLabel.textProperty().bind(viewModel.usernameProperty());
      topic1Button.textProperty().bind(viewModel.topic1Property());
      topic2Button.textProperty().bind(viewModel.topic2Property());
      topic3Button.textProperty().bind(viewModel.topic3Property());
      topic4Button.textProperty().bind(viewModel.topic4Property());
      faqDesLabel1.textProperty().bind(viewModel.faqDes1Property());
      faqDesLabel2.textProperty().bind(viewModel.faqDes2Property());
      faqDesLabel3.textProperty().bind(viewModel.faqDes3Property());
      faqDesLabel4.textProperty().bind(viewModel.faqDes4Property());
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

   @FXML private void topic1Clicked()
   {
      viewModel.setTopic(topic1Button.getText());
      getViewHandler().openView(ViewKey.FAQ_TOPIC);
   }

   @FXML private void topic2Clicked()
   {
      viewModel.setTopic(topic2Button.getText());
      getViewHandler().openView(ViewKey.FAQ_TOPIC);
   }

   @FXML private void topic3Clicked()
   {
      viewModel.setTopic(topic3Button.getText());
      getViewHandler().openView(ViewKey.FAQ_TOPIC);
   }

   @FXML private void topic4Clicked()
   {
      viewModel.setTopic(topic4Button.getText());
      getViewHandler().openView(ViewKey.FAQ_TOPIC);
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
         getViewHandler().openView(ViewKey.EDIT_PROFILE);
   }
   
   @FXML private void forumClicked()
   {
      getViewHandler().openView(View.ViewKey.FORUM);
   }

}
