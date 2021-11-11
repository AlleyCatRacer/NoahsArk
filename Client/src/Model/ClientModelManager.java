package Model;

import Mediator.Client;
import Model.FAQ.FAQ;
import Model.Forum.Forum;
import Model.Home.ArticleList;
import Model.SearchItems.SearchableUser;
import Model.SearchItems.Facility;
import Model.SearchItems.SearchItem;
import Model.SearchItems.Service;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.util.ArrayList;

public class ClientModelManager implements ClientModel
{
  private String username;
  private PropertyChangeHandler<String, String> property;
  private Forum forum;
  private FAQ faqs;
  private ArticleList articles;
  private ArrayList<Service> services;
  private Client client;

  public ClientModelManager()
  {
    username = "";
    property = new PropertyChangeHandler<>(this);
    client = new Client();
    client.addListener(this);
    loadFromServer();
  }

  @Override public Forum getForum()
  {
    return forum;
  }

  @Override public FAQ getFaqs()
  {
    return faqs;
  }

  @Override public ArticleList getArticles()
  {
    return articles;
  }

  @Override public ArrayList<String> getComments(String forumTopic)
  {
    return client.getComments(forumTopic);
  }

  @Override public ArrayList<Service> getServices()
  {
    return services;
  }

  @Override public ArrayList<Service> searchServices(String searchInfo)
  {
    return client.getServices(searchInfo);
  }
  
  @Override public ArrayList<Facility> getFacilities()
  {
    return client.facilities();
  }
  
  @Override public ArrayList<Facility> searchFacilities(String info)
  {
    return client.getFacilities(info);
  }
  
  @Override public ArrayList<SearchItem> searchUser(String username)
  {
    ArrayList<String> searchResult = client.userSearch(username);
    ArrayList<SearchItem> display = new ArrayList<>();
    for (String s : searchResult)
    {
      display.add(new SearchableUser(s));
    }
    return display;
  }
  
  @Override public void addFriend(String username, String friend)
  {
    client.addFriend(username, friend);
  }
  
  @Override public void unfriend(String username, String friend)
  {
    client.unfriend(username, friend);
  }
  
  @Override public ArrayList<String> getFriendsList(String username)
  {
    return client.getFriendsList(username);
  }
  
  @Override public boolean isFriend(String username, String friend)
  {
    return client.isFriend(username, friend);
  }
  
  @Override public boolean isProfessional(String username)
  {
    return client.isProfessional(username);
  }
  
  @Override public void professionalApplication(String username)
  {
    this.username=username;
    client.professionalApplication(username);
  }

  @Override public void addReport(String username, String report)
  {
    client.addReport(username, report);
  }

  @Override public boolean againReported(String username, String report)
  {
    return client.againReported(username, report);
  }
  
  @Override public void loadFromServer()
  {
      forum = client.getForum();
      faqs = client.getFaqs();
      articles = client.getArticles();
      services = client.services();
  }

  @Override public void login(String username, String password)
  {
    client.login(username, password);
    this.username = username;
  }
  
  @Override public void join(String username, String password)
  {
    client.addUser(username, password);
    this.username = username;
  }

  @Override public void subscribe(String thread)
  {
    client.subscribe(thread, username);
  }

  @Override public boolean isSubscribed(String thread)
  {
    return client.isSubscribed(thread, username);
  }

  @Override public void unsubscribe(String thread)
  {
    client.unsubscribe(thread, username);
  }
  
  @Override public void updateProfile(Profile profile) throws IllegalStateException
  {
    client.updateProfile(profile);
  }
  
  @Override public Profile getProfileInfo(String username)
  {
    return client.profileInfo(username);
  }
  
  @Override public String getUsername()
  {
    return username;
  }
  
  @Override public void updateServices(ArrayList<Service> services)
  {
    Profile temp = getProfileInfo(username);
    Profile profile = new Profile(username,temp.getInfo(),temp.getStories(),temp.getProfessionalStatus(), temp.getPhoneNumber(), temp.getEmail(), services);
    updateProfile(profile);
  }
  
  @Override public void addCommentOnTopic(String topicTitle, String message)
  {
      client.addCommentOnTopic(topicTitle, message, username);
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

  @Override public void propertyChange(ObserverEvent<String, String> event)
  {
    if(event.getPropertyName().equals("NewComment")) {
      property.firePropertyChange(event.getPropertyName(), event.getValue1(), event.getValue2());
    }
    else if(event.getPropertyName().equals(username)) {
      property.firePropertyChange("Subscription", username, event.getValue2());
    }
  }
}
