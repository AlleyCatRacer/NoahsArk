package View;

import ViewModel.FAQTopicViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;

public class FAQTopicController extends View.ViewController
{
  @FXML private Label usernameLabel;
  @FXML private Label topicLabel;
  @FXML private TitledPane questionTitle1;
  @FXML private TextArea answer1;
  @FXML private TitledPane questionTitle2;
  @FXML private TextArea answer2;
  @FXML private TitledPane questionTitle3;
  @FXML private TextArea answer3;

  private FAQTopicViewModel viewModel;

  public FAQTopicController()
  {
    //
  }

  @Override protected void initBindings()
  {
    this.viewModel = getFactory().getFAQTopicViewModel();
    usernameLabel.textProperty().bind(viewModel.usernameProperty());
    topicLabel.textProperty().bind(viewModel.topicProperty());
    viewModel.reset();
    answer1.textProperty().bind(viewModel.answer1Property());
    answer2.textProperty().bind(viewModel.answer2Property());
    answer3.textProperty().bind(viewModel.answer3Property());
    questionTitle1.textProperty().bind(viewModel.questionTitle1Property());
    questionTitle2.textProperty().bind(viewModel.questionTitle2Property());
    questionTitle3.textProperty().bind(viewModel.questionTitle3Property());
    viewModel.loadQuestionsAndAnswers();
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
  
  @FXML private void backToTopicsClicked()
  {
    getViewHandler().openView(ViewKey.FAQ);
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
    getViewHandler().openView(ViewKey.FORUM);
  }

}
