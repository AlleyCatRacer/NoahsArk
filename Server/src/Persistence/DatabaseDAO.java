package Persistence;

import Model.FAQ.FAQ;
import Model.Forum.Forum;
import Model.Home.ArticleList;
import Model.Profile;
import Model.SearchItems.Facility;
import Model.SearchItems.Service;
import Model.User;

import java.util.ArrayList;

public interface DatabaseDAO
{

  ArticleList loadArticles();

  ArrayList<Facility> loadFacilities();

  ArrayList<Service> loadServices();

  Forum loadForum();

  FAQ loadFAQ();

  ArrayList<User> loadUsers();

  ArrayList<String> loadComments(String forumTopic);



  void updateProfile(Profile profile);

  void addCommentOnTopic(String topicTitle, String message, String username);

  void addUser(String username, String password);

  void subscribeToThread(String thread, String username);

  void unsubscribe(String thread, String username);

  void addFriend(String username, String friend);

  void addReport(String username, String report);

  void unfriend(String username, String friend);
}
