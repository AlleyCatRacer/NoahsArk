package View;

import ViewModel.ForumDiscussionViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

public class ForumDiscussionController extends ViewController
{

  @FXML private Label usernameLabel;
  @FXML private Label topicLabel;
  @FXML private TextField commentTextField;
  @FXML private Button subscribeButton;
  @FXML private ListView<String> commentList;

  private ForumDiscussionViewModel viewModel;

  public ForumDiscussionController()
  {
    //
  }

  @Override protected void initBindings()
  {
    viewModel = getFactory().getForumDiscussionModel();
    viewModel.reset();
    usernameLabel.textProperty().bind(viewModel.usernameProperty());
    topicLabel.textProperty().bindBidirectional(viewModel.topicLabelProperty());
    commentTextField.textProperty().bindBidirectional(viewModel.commentProperty());
    subscribeButton.textProperty().bind(viewModel.subscriptionProperty());
    commentList.setItems(viewModel.getComments());
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

  @FXML private void postComment()
  {
    viewModel.postComment();
    commentTextField.clear();
  }

  @FXML private void backToThreads()
  {
    getViewHandler().openView(ViewKey.FORUM);
  }

  @FXML private void subscribeClicked()
  {
    viewModel.subscribeClicked();
  }
  
  @FXML private void onEnter()
  {
    postComment();
  }
  
  @FXML private void homeClicked()
  {
    getViewHandler().openView(ViewKey.HOME);
  }
  
  @FXML private void FAQClicked()
  {
    getViewHandler().openView(ViewKey.FAQ);
  }

  @FXML private void logoutClicked()
  {
    getViewHandler().openView(ViewKey.LOGIN);
  }

  @FXML private void searchClicked()
  {
    getViewHandler().openView(ViewKey.SEARCH);
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
}
