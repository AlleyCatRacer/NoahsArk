package Mediator;

import Model.FAQ.FAQ;
import Model.Forum.Forum;
import Model.Home.ArticleList;
import Model.Profile;
import Model.SearchItems.Facility;
import Model.SearchItems.Service;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * A Client class that calls methods on the remote server via stub.
 *
 * @author Group 1
 * @version 3.0 - 01.06.21
 */

public class Client implements RemoteListener<String, String>, LocalSubject<String, String>
{
   private RemoteModel server;
   private PropertyChangeHandler<String, String> property;
   
   /**
    * Zero-argument constructor initializing the RemoteModel instance variable to the remote server stub downloaded from the registry.
    */
   public Client()
   {
      try
      {
         server = (RemoteModel) Naming.lookup("rmi://localhost:1099/Ark");
         UnicastRemoteObject.exportObject(this, 0);
         server.addListener(this);
         property = new PropertyChangeHandler<>(this, true);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   /**
    * Passing login credentials to the server for verification.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param username String of the username
    * @param password String of the password
    */
   public void login(String username, String password)
   {
      try
      {
        server.login(username, password);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
      }
   }
   
   /**
    * Passing credentials to the server for verification to create a new User object.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param username String of the username
    * @param password String of the password
    */
   public void addUser(String username, String password)
   {
      try
      {
         server.addUser(username, password);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
      }
   }
   
   /**
    * Adding a forum thread title to a user's subscription list.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param thread String of the forum thread's title
    * @param username String of the subscribing user's username
    */
   public void subscribe(String thread, String username)
   {
      try {
         server.subscribe(thread, username);
      }
      catch(RemoteException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Boolean checking if a user is subscribed to a given forum thread.
    * Catches RemoteExceptions, prints the stack trace and returns 'false'.
    *
    * @param thread String of the forum thread's title
    * @param username String of a user's username
    * @return 'true' if the given thread is on the given user's subscription list, 'false' if not or a RemoteException is thrown
    */
   public boolean isSubscribed(String thread, String username)
   {
      try {
         return server.isSubscribed(thread, username);
      }
      catch(RemoteException e) {
         e.printStackTrace();
         return false;
      }
   }
   
   /**
    * Removing a forum thread title from a user's subscriptions.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param thread String of the forum thread's title
    * @param username String of the unsubscribing user's username
    */
   public void unsubscribe(String thread, String username)
   {
      try {
         server.unsubscribe(thread, username);
      }
      catch(RemoteException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * Setter for the profile instance variables contained in a Profile object.
    *
    * @param profile Profile object containing the new profile to send to the Server
    */
   public void updateProfile(Profile profile) throws IllegalStateException
   {
      try
      {
         server.updateProfile(profile);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
         throw new IllegalStateException(r.getMessage());
      }
   }
   
   /**
    * Getter for a Profile object containing a user's public profile details.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @param username String of the username to search for
    * @return Profile object created based on the user found from the String argument or null if a RemoteException is thrown
    */
   public Profile profileInfo(String username)
   {
      try
      {
         return server.profileInfo(username);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
         return null;
      }
   }
   
   /**
    * Adding a comment to the specified topic.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param topicTitle String of the topic to which comment should be appended
    * @param message String of the comment
    * @param username String of the username that is adding the comment
    */
   public void addCommentOnTopic(String topicTitle, String message, String username)
   {
      try {
         server.addCommentOnTopic(topicTitle, message, username);
      }
      catch (RemoteException e) {
         e.printStackTrace();
      }
   }

   /**
    * Fetches the Forum from the Server.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @return Forum object which contains all the topics and comments or null if a RemoteException is thrown
    */
   public Forum getForum()
   {
      try {
         return server.getForum();
      }
      catch (RemoteException e) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * Fetches the FAQ from the Server.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @return FAQ object which contains all topics, questions and answers or null if a RemoteException is thrown
    */
   public FAQ getFaqs()
   {
      try {
         return server.getFaqs();
      }
      catch (RemoteException e) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * Fetches the list of articles from the Server.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @return ArticleList object which contains all the Article objects or null if a RemoteException is thrown
    */
   public ArticleList getArticles()
   {
      try {
         return server.getArticles();
      }
      catch (RemoteException e) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * Fetches all the services from the Server.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @return ArrayList of Service objects that contain all the services within the system or null if a RemoteException is thrown
    */
   public ArrayList<Service> services()
   {
      try {
         return server.getServices();
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
         return null;
      }
   }
   
   /**
    * Fetches all the facilities from the Server.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @return ArrayList of Facility objects that contain all the facilities within the system or null if a RemoteException is thrown
    */
   public ArrayList<Facility> facilities()
   {
      try
      {
         return server.getFacilities();
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * Loads all the comments from the Server for the specified topic.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @param forumTopic String of the topic title
    * @return ArrayList of Strings that contain all the comments for the specified topic or null if a RemoteException is thrown
    */
   public ArrayList<String> getComments(String forumTopic)
   {
      try {
         return server.getComments(forumTopic);
      }
      catch (RemoteException e) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * Loads all the services that match the search criteria.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @param searchInfo String of criteria by which the ArrayList of Service objects will be populated
    * @return ArrayList of Service objects that match the criteria or null if a RemoteException is thrown
    */
   public ArrayList<Service> getServices(String searchInfo)
   {
      try {
         return server.getServicesByInfo(searchInfo);
      }
      catch (RemoteException e) {
         e.printStackTrace();
         return null;
      }
   }
   
   /**
    * Fetches all the facilities from the Server matching the search String.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @param info String to search by
    * @return ArrayList of Facility objects that contain all the facilities within the system that match the search or null if a RemoteException is thrown
    */
   public ArrayList<Facility> getFacilities(String info)
   {
      try
      {
         return server.getFacilitiesByInfo(info);
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
         return null;
      }
   }
   
   /**
    * Fetches all the usernames from the Server matching the search String.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @param search String to search by
    * @return ArrayList of Strings that contain the usernames within the system that match the search or null if a RemoteException is thrown
    */
   public ArrayList<String> userSearch(String search)
   {
      try
      {
         return server.userSearch(search);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
         return null;
      }
   }
   
   /**
    * Adding a user to a user's friends.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param username String of username by which the specific user is found
    * @param friend String of criteria by which the soon-to-be-friend will be found
    */
   public void addFriend(String username, String friend)
   {
      try
      {
         server.addFriend(username, friend);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
      }
   }
   
   /**
    * Removing a user from a user's list of friends.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param username String of username by which the specific user is found
    * @param friend String of criteria by which the ex-friend will be found
    */
   public void unfriend(String username, String friend)
   {
      try
      {
         server.unfriend(username, friend);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
      }
   }
   
   /**
    * Gets a user's list of friend user's usernames.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @param username String of criteria by which the specific user is found
    * @return ArrayList of Strings containing the usernames of friends that the specific user has or null if a RemoteException is thrown
    */
   public ArrayList<String> getFriendsList(String username)
   {
      try
      {
         return server.getFriendsList(username);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
         return null;
      }
   }
   
   /**
    * Checks whether a user is already a friend of a specific user or not.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param username String of criteria by which the user is found
    * @param friend String of criteria by which the specific friend will be found
    * @return 'true' if the friend String is already in the first user's friend list, otherwise 'false'.
    * @throws IllegalStateException if a RemoteException is thrown = "Could not retrieve data from the server database"
    */
   public boolean isFriend(String username, String friend) throws IllegalStateException
   {
      try
      {
         return server.isFriend(username, friend);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
         throw new IllegalStateException("Could not retrieve data from the server database");
      }
   }
   
   /**
    * Checks whether a user is a professional.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param username String of criteria by which the user is found
    * @return 'true' if the user is already a professional, otherwise 'false'.
    * @throws IllegalStateException if a RemoteException is thrown = "Could not retrieve data from the server database"
    */
   public boolean isProfessional(String username)
   {
      try
      {
         return server.isProfessional(username);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
         throw new IllegalStateException("Could not retrieve data from the server database");
      }
   }
   
   /**
    * Changing a user's professional status.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param username String of the user's username who is to be altered
    */
   public void professionalApplication(String username)
   {
      try
      {
         server.professionalApplication(username);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
      }
   }
   
   /**
    * Adding a user's list of reported users.
    * Catches RemoteExceptions and prints the stack trace.
    *
    * @param username String of the reporters username
    * @param report String of the reportee's username
    */
   public void addReport(String username, String report)
   {
      try
      {
         server.addReport(username, report);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
      }
   }
   
   /**
    * Getter for a list of usernames the given user has reported.
    * Catches RemoteExceptions, prints the stack trace and returns null.
    *
    * @param username String of the username to search for
    * @return a String ArrayList of users that have been reported by the argument user or null if a RemoteException is thrown
    */
   public ArrayList<String> getReportList(String username)
   {
      try
      {
         return server.getReportList(username);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
         return null;
      }
   }
   
   /**
    * Boolean checking whether a user has already reported a specific user.
    *
    * @param username String to search for and get list of reports from
    * @param report String of the username to check if it's already been reported by the user
    * @return 'true' if the user with this username has already reported the user with the second String as a username, returns 'false' if not or null if a RemoteException is thrown
    * @throws IllegalStateException if a RemoteException is thrown = "Could not retrieve data from the server database"
    */
   public boolean againReported(String username, String report) throws IllegalStateException
   {
      try
      {
         return server.againReported(username, report);
      }
      catch (RemoteException r)
      {
         r.printStackTrace();
         throw new IllegalStateException("Could not retrieve data from the server database");
      }
   }

   @Override public boolean addListener(GeneralListener<String, String> listener, String... propertyNames)
   {
      return property.addListener(listener, propertyNames);
   }

   @Override public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames)
   {
      return property.removeListener(listener, propertyNames);
   }

   @Override public void propertyChange(ObserverEvent<String, String> event)
   {
      if(event.getPropertyName().equals("NewComment")) {
         property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
      }
      else
         property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
   }

}
