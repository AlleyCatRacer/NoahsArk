package ViewModel;

import Model.ViewModelInterfaces.ForumModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class ForumDiscussionViewModel implements LocalListener<String, String>
{
  private ForumModel model;
  private ViewState viewState;
  private SimpleStringProperty usernameProperty;
  private SimpleStringProperty topicLabel;
  private SimpleStringProperty comment;
  private SimpleStringProperty subscription;
  private ObservableList<String> commentList;

  public ForumDiscussionViewModel(ForumModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.usernameProperty = new SimpleStringProperty();
    this.topicLabel = new SimpleStringProperty();
    this.comment = new SimpleStringProperty();
    this.subscription = new SimpleStringProperty("Subscribe");
    this.commentList = FXCollections.observableArrayList();

    model.addListener(this);
  }

  public void reset()
  {
    usernameProperty.set(viewState.getUsername());
    topicLabel.set(viewState.getForumTopic());
    commentList.setAll(model.getComments(topicLabel.get()));
    if(model.isSubscribed(viewState.getForumTopic())) {
      subscription.set("Unsubscribe");
    }
    else
      subscription.set("Subscribe");
  }
  
  public boolean professional()
  {
    reset();
    return model.isProfessional(viewState.getUsername());
  }

  public StringProperty usernameProperty()
  {
    return usernameProperty;
  }

  public StringProperty topicLabelProperty()
  {
    return topicLabel;
  }

  public StringProperty commentProperty()
  {
    return comment;
  }

  public StringProperty subscriptionProperty()
  {
    return subscription;
  }

  public ObservableList<String> getComments()
  {
    return commentList;
  }

  public void subscribeClicked()
  {
    if (model.isSubscribed(viewState.getForumTopic()))
    {
      model.unsubscribe(viewState.getForumTopic());
      subscription.set("Subscribe");
    }
    else
    {
       model.subscribe(viewState.getForumTopic());
       subscription.set("Unsubscribe");
    }
  }

  public void postComment()
  {
    model.addCommentOnTopic(topicLabel.get(), comment.get());
  }

  private void subscriptionAlert(String thread)
  {

    String content = thread + " was updated";
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(thread + " subscription");
    alert.setHeaderText(content);
    alert.setResizable(true);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.show();
  }

  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    Platform.runLater(() -> {
      if (event.getPropertyName().equals("NewComment"))
      {
        topicLabel.set(viewState.getForumTopic());
        commentList.setAll(model.getComments(topicLabel.get()));
      }
      else if (event.getPropertyName().equals("Subscription"))
      {
        subscriptionAlert(event.getValue2());
      }
    });
  }
}


