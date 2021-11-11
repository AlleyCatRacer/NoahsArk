package View;

import ViewModel.ForumViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

public class ForumController extends ViewController
{
   @FXML private Label  usernameLabel;
   @FXML private Button thread1Button;
   @FXML private Button thread2Button;
   @FXML private Button thread3Button;
   @FXML private Button thread4Button;
   @FXML private Label  threadDesLabel1;
   @FXML private Label  threadDesLabel2;
   @FXML private Label  threadDesLabel3;
   @FXML private Label  threadDesLabel4;
   
   private ForumViewModel viewModel;
   
   public ForumController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel = getFactory().getForumModel();
      viewModel.reset();
      usernameLabel.textProperty().bind(viewModel.usernameProperty());
      thread1Button.textProperty().bind(viewModel.thread1Property());
      thread2Button.textProperty().bind(viewModel.thread2Property());
      thread3Button.textProperty().bind(viewModel.thread3Property());
      thread4Button.textProperty().bind(viewModel.thread4Property());
      threadDesLabel1.textProperty().bind(viewModel.threadDes1Property());
      threadDesLabel2.textProperty().bind(viewModel.threadDes2Property());
      threadDesLabel3.textProperty().bind(viewModel.threadDes3Property());
      threadDesLabel4.textProperty().bind(viewModel.threadDes4Property());
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
         usernameLabel.setFont(
               Font.font(viewModel.usernameProperty().get(), FontWeight.NORMAL, FontPosture.REGULAR, 13));
      }
   }
   
   @FXML private void thread1Clicked()
   {
      viewModel.setTopic(thread1Button.getText());
      getViewHandler().openView(ViewKey.FORUM_TOPIC);
   }
   
   @FXML private void thread2Clicked()
   {
      viewModel.setTopic(thread2Button.getText());
      getViewHandler().openView(ViewKey.FORUM_TOPIC);
   }
   
   @FXML private void thread3Clicked()
   {
      viewModel.setTopic(thread3Button.getText());
      getViewHandler().openView(ViewKey.FORUM_TOPIC);
   }
   
   @FXML private void thread4Clicked()
   {
      viewModel.setTopic(thread4Button.getText());
      getViewHandler().openView(ViewKey.FORUM_TOPIC);
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
}
