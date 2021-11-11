package Model;

import Model.FAQ.FAQ;
import Model.Forum.Forum;
import Model.Home.ArticleList;
import Model.SearchItems.Facility;
import Model.SearchItems.Service;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Server model interface that delegates to server model manager.
 *
 * @author Group 1
 * @version 4.0 - 01.06.21
 */
public interface ServerModel extends LocalSubject<String, String>
{
   void login(String username, String password);

   void addUser(String username, String password);

   void addCommentOnTopic(String topicTitle, String message, String username);

   void subscribeToThread(String thread, String username);

   boolean isSubscribed(String thread, String username);

   void unsubscribe(String thread, String username);

   void updateProfile(Profile profile);

   Profile profileInfo(String username);

   Forum getForum();

   FAQ getFaqs();

   ArticleList getArticles();

   ArrayList<String> getComments(String forumTopic);

   ArrayList<Service> getServices();

   ArrayList<Service> getServicesByInfo(String info);
   
   ArrayList<Facility> getFacilities();
   
   ArrayList<Facility> getFacilitiesByInfo(String info);

   ArrayList<String> userSearch(String search);

   void addFriend(String username, String friend);

   void unfriend(String username, String friend);

   ArrayList<String> getFriendList(String username);

   boolean isFriend(String username,String friend);

   boolean isProfessional(String username);
   
   void professionalApplication(String username);

   ArrayList<String> getReportList(String username);

   boolean againReported(String username, String report);

   void addReport(String username, String report);
}
