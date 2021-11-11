package Model;

import Model.FAQ.FAQ;
import Model.Forum.Forum;
import Model.Home.ArticleList;
import Model.SearchItems.Facility;
import Model.SearchItems.SearchItemList;
import Model.SearchItems.Service;
import Persistence.DAOImplementation;
import Persistence.DatabaseDAO;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.util.*;

/**
 * Server side model manager class that implements the ServerModel interface and accesses the database.
 *
 * @author Group 1
 * @version 3.0 - 01.06.21
 */
public class ServerModelManager implements ServerModel
{
  private UserList userList;
  private Forum forum;
  private FAQ faqs;
  private ArticleList articles;
  private SearchItemList searchItems;
  private PropertyChangeHandler<String, String> property;
  private DatabaseDAO database;
  
  /**
   * Zero argument constructor initializing the database data access object singleton and using it to populate
   * the user list, search items list, forum, faq's and articles.
   */
  public ServerModelManager()
  {
    database = DAOImplementation.getInstance();

    userList = new UserList();
    loadUsers();

    forum = database.loadForum();
    faqs = database.loadFAQ();
    articles = database.loadArticles();

    searchItems = new SearchItemList();
    for (int i = 0; i < database.loadFacilities().size(); i++)
    {
      searchItems.addFacility(database.loadFacilities().get(i));
    }
    for (int i = 0; i < database.loadServices().size(); i++)
    {
      searchItems.addService(database.loadServices().get(i));
    }
    property = new PropertyChangeHandler<>(this, true);
  }
  
  /**
   * Private method that removes all users from the user list field and re-populates it with users from the database.
   */
  private void loadUsers()
  {
    userList.getUsers().clear();
    for (int i = 0; i < database.loadUsers().size(); i++)
    {
      userList.addUser(database.loadUsers().get(i));
    }
  }
  
  /**
   * Getter for the Forum.
   *
   * @return Forum object which contains all the threads and their comments
   */
  @Override public Forum getForum()
  {
    return forum;
  }
  
  /**
   * Getter for the Frequently Asked Questions.
   *
   * @return FAQ object which contains all topics, questions and answers
   */
  @Override public FAQ getFaqs()
  {
    return faqs;
  }
  
  /**
   * Getter for the ArticleList object.
   *
   * @return ArticleList object which contains all the Articles
   */
  @Override public ArticleList getArticles()
  {
    return articles;
  }
  
  /**
   * Gets all the comments for the specified forum thread topic
   *
   * @param forumTopic String of the topic title
   * @return ArrayList of Strings that contains all the comments for the specified topic
   */
  @Override public ArrayList<String> getComments(String forumTopic)
  {
    return database.loadComments(forumTopic);
  }
  
  /**
   * Fetches all the Services.
   *
   * @return ArrayList of Services that contains all the Services within the system
   */
  @Override public ArrayList<Service> getServices()
  {
    return searchItems.getAllServices();
  }
  
  /**
   * Gets all the Services that match the search criteria.
   *
   * @param info String of criteria by which the ArrayList of Services will be populated
   * @return ArrayList of Services that match the criteria
   */
  @Override public ArrayList<Service> getServicesByInfo(String info)
  {
    return searchItems.getServiceBySearch(info);
  }
  
  /**
   * Getter for all facilities in the database.
   *
   * @return an ArrayList of Facility objects
   */
  @Override public ArrayList<Facility> getFacilities()
  {
    return searchItems.getAllFacilities();
  }
  
  /**
   * Getter for all facilities in the database matching the given argument.
   *
   * @param info String to search for in the facility's instance variables
   * @return an ArrayList of Facility objects
   */
  @Override public ArrayList<Facility> getFacilitiesByInfo(String info)
  {
    return searchItems.getFacilityBySearch(info);
  }
  
  /**
   * Getter for all user's usernames in the database matching the given argument.
   *
   * @param search String to search for in the user list
   * @return a String ArrayList of user's usernames
   */
  @Override public ArrayList<String> userSearch(String search)
  {
    return userList.searchUsers(search);
  }
  
  /**
   * Adding a friend to a specific users FriendList
   *
   * @param username String of criteria by which the specific user is found
   * @param friend String of criteria by which a new friend will be added to an existing FriendList
   */
  @Override public void addFriend(String username, String friend)
  {
    if (username.equals(friend))
    {
      throw new IllegalArgumentException(
          "I'm sorry but you can only friend others");
    }
    else
    {
      if (isFriend(username, friend))
      {
        throw new IllegalArgumentException("You can't add a friend twice.");
      }
      else
      {
        database.addFriend(username, friend);
        loadUsers();
      }
    }
  }
  
  /**
   * Removing a friend from a specific users FriendList
   *
   * @param username String of criteria by which the specific user is found
   * @param friend String of criteria by which a new friend will be removed from an existing FriendList
   */
  @Override public void unfriend(String username, String friend)
  {
    if (username.equals(friend))
    {
      throw new IllegalArgumentException("You can't unfriend yourself.");
    }
    database.unfriend(username, friend);
    loadUsers();
  }
  
  /**
   * Loads a specific user's FriendList
   *
   * @param username String of criteria by which the specific user is found
   * @return ArrayList of Strings containing the usernames of friends that the specific user has
   */
  @Override public ArrayList<String> getFriendList(String username)
  {
    return userList.getUsers().get(username).getFriendList();

  }
  
  /**
   * Checks whether an existing user is a friend of a specific friend already or not
   *
   * @param username String of criteria by which the user is found
   * @param friend String of criteria by which the specific friend will be found
   * @return 'true' if the friend String is already in the first user's friend list, otherwise 'false'.
   */
  @Override public boolean isFriend(String username, String friend)
  {
    return userList.getUsers().get(username).getFriendList().contains(friend);
  }
  
  /**
   * Checks whether a user is a professional or not.
   *
   * @param username String of criteria by which the user is found
   * @return 'true' if the user is a professional, otherwise 'false'.
   */
  @Override public boolean isProfessional(String username)
  {
    return userList.getUsers().get(username).getProfessionalStatus();
  }
  
  /**
   * Getter for a list of usernames the given user has reported.
   *
   * @param username String of the username to search for in the user list
   * @return a String ArrayList of user's usernames that have been reported by the argument user
   */
  @Override public ArrayList<String> getReportList(String username)
  {
    return userList.getUsers().get(username).getReportList();
  }
  
  /**
   * Boolean checking whether a username is already on a user's report list.
   *
   * @param username String to search for in the user list and get their list of reports
   * @param report String of the username to check if it's already on the user's report list
   * @return 'true' if the user with this username has already reported the user with the second String as a username, otherwise returns 'false'
   */
  @Override public boolean againReported(String username, String report)
  {
    return userList.getUsers().get(username).getReportList().contains(report);
  }
  
  /**
   * Adding a username to a user's list of reported usernames.
   *
   * @param username String of the reporters username
   * @param report String of the reportee's username
   */
  @Override public void addReport(String username, String report)
  {
    database.addReport(username, report);
    loadUsers();
  }
  
  /**
   * Logs in an already existing user if the provided credentials are correct.
   *
   * @param username String of username
   * @param password String of password
   */
  @Override public void login(String username, String password)
  {
    userList.verified(username, password);
  }
  
  /**
   * Adds a new user if the provided credentials pass the checks in subsequently called methods and constructor.
   *
   * @param username String of username
   * @param password String of password
   */
  @Override public void addUser(String username, String password)
  {
    database.addUser(username, password);
    loadUsers();

  }
  
  /**
   * Adding a forum thread title to a user's subscription list.
   *
   * @param thread String of the forum thread's title
   * @param username String of the subscribing user's username
   */
  @Override public void subscribeToThread(String thread, String username)
  {
    database.subscribeToThread(thread, username);
    loadUsers();
  }
  
  /**
   * Boolean checking if a user is subscribed to a given forum thread.
   *
   * @param thread String of the forum thread's title
   * @param username String of a user's username
   * @return 'true' if the given thread is on the given user's subscription list, 'false' if not
   */
  @Override public boolean isSubscribed(String thread, String username)
  {
    return userList.getUsers().get(username).getSubscriptions().contains(thread);
  }
  
  /**
   * Removing a forum thread title from a user's subscription list
   *
   * @param thread String of the forum thread's title
   * @param username String of the unsubscribing user's username
   */
  @Override public void unsubscribe(String thread, String username)
  {
   database.unsubscribe(thread, username);
   loadUsers();
  }
  
  /**
   * Private method that fires a property change that is received by the users who are subscribed to the thread.
   *
   * @param thread String of the forum thread's title
   * @param username String of the user's username who posted a comment on the thread, used to exclude the original
   *                 commenter from receiving a notification of their own actions
   */
  private void notifyingSubscribers(String thread, String username)
  {
    for (User user : userList.getUsers().values())
    {
      if (user.getSubscriptions().contains(thread) && !username.equals(user.getUsername()))
      {
        property.firePropertyChange(user.getUsername(), null, thread);
      }
    }

  }
  
  /**
   * Mutator for a user's boolean field of professional.
   *
   * @param username String of the user's username who is to be altered
   */
  @Override public void professionalApplication(String username)
  {
    userList.getUsers().get(username).setProfessionalStatus();
  }
  
  /**
   * Adding a comment to the specified topic on the Server side.
   *
   * @param topicTitle String of the topic thread to which comment should be appended
   * @param message String of the comment
   * @param username String of the username that is adding the comment
   */
  @Override public void addCommentOnTopic(String topicTitle, String message, String username)
  {
    database.addCommentOnTopic(topicTitle, message, username);
    property.firePropertyChange("NewComment", null, message);

    notifyingSubscribers(topicTitle, username);
  }
  
  /**
   * Setter for the user instance variables contained in a Profile object to update the database and reload the data.
   *
   * @param profile Profile object containing the new profile information from the client
   */
  @Override public void updateProfile(Profile profile)
  {
    database.updateProfile(profile);
    loadUsers();
  }
  
  /**
   * Getter for a Profile object containing a user's public profile details.
   *
   * @param username String of the username to search for in the user list
   * @return Profile object created based on the user found from the String argument
   */
  @Override public Profile profileInfo(String username)
  {
    if (username != null)
    {
      User temp = userList.getUsers().get(username);
      if (temp != null)
      {
        if (userList.getUsers().get(username).getProfessionalStatus())
        {
          return new Profile(temp.getUsername(), temp.getInfo(),
              temp.getStories(), true,  temp.getPhoneNumber(), temp.getEmail(),
                             temp.getServices());
        }
        else
        {
          return new Profile(temp.getUsername(), temp.getInfo(),
              temp.getStories(), false);
        }
      }
      throw new IllegalArgumentException("No user with such username found");
    }
    throw new IllegalArgumentException("Null username not allowed");
  }
  
  @Override public boolean addListener(GeneralListener<String, String> listener,
                                       String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }
}