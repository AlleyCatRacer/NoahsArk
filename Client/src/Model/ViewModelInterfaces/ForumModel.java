package Model.ViewModelInterfaces;

import Model.Forum.Forum;
import utility.observer.subject.LocalSubject;

import java.util.ArrayList;

public interface ForumModel extends LocalSubject<String, String>, UserModel
{
  Forum getForum();

  ArrayList<String> getComments(String forumTopic);

  void addCommentOnTopic(String topicTitle, String message);

  void subscribe(String thread);

  boolean isSubscribed(String thread);

  void unsubscribe(String thread);
}
