package Mediator;

import Model.FAQ.FAQ;
import Model.Forum.Forum;
import Model.Home.ArticleList;
import Model.Profile;
import Model.SearchItems.Facility;
import Model.ServerModel;
import Model.SearchItems.Service;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Server side mediator class that implements the RemoteModel and delegates to the ServerModel, and therefore to the ServerModelManager.
 *
 * @author Group 1
 * @version 3.0 - 01.06.21
 */

public class Server implements RemoteModel, LocalListener<String, String>
{

  private ServerModel serverModel;
  private PropertyChangeHandler<String, String> property;

  /**
   * Constructor initializing the serverModel instance variable, setting up PropertyChangeHandler,
   * starting Registry and Server, as well as adding itself as a listener to the Server Model.
   *
   * @param model Object of ServerModel interface that is implemented by ServerModelManager
   */
  public Server(ServerModel model)
  {
    this.serverModel = model;
    property = new PropertyChangeHandler<>(this, true);
    startRegistry();
    startServer();
    serverModel.addListener(this);
    serverModel.addListener(this, "DYI");
  }

  /**
   * Starts the registry required to establish the RMI connection
   */
  private void startRegistry()
  {
    try {
      LocateRegistry.createRegistry(1099);
      System.out.println("Registry started");
    }
    catch (RemoteException e) {
      System.out.println("The registry may have been already started " + e.getMessage());
    }
  }

  /**
   * Starts the Server so that the clients can connect to it
   */
  private void startServer()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Naming.rebind("Ark", this);
      System.out.println("Server started");
    }
    catch (Exception e) {
      System.out.println("Problem with with the server initiation " + e.getMessage() );
    }

  }
  
  @Override public void login(String username, String password)
  {
    serverModel.login(username, password);
  }
  
  @Override public void addUser(String username, String password)
  {
    serverModel.addUser(username, password);
  }
  
  @Override public void addCommentOnTopic(String topicTitle, String message, String username)
  {
    serverModel.addCommentOnTopic(topicTitle, message, username);
  }
  
  @Override public void subscribe(String thread, String username)
  {
    serverModel.subscribeToThread(thread, username);
  }
  
  @Override public boolean isSubscribed(String thread, String username)
  {
    return serverModel.isSubscribed(thread, username);
  }
  
  @Override public void unsubscribe(String thread, String username)
  {
    serverModel.unsubscribe(thread, username);
  }
  
  @Override public void updateProfile(Profile profile)
  {
    serverModel.updateProfile(profile);
  }

  @Override public Profile profileInfo(String username)
  {
    return serverModel.profileInfo(username);
  }
  
  @Override public Forum getForum()
  {
    return serverModel.getForum();
  }
  
  @Override public FAQ getFaqs()
  {
    return serverModel.getFaqs();
  }
  
  @Override public ArticleList getArticles()
  {
    return serverModel.getArticles();
  }
  
  @Override public ArrayList<String> getComments(String forumTopic)
  {
   return serverModel.getComments(forumTopic);
  }
  
  @Override public ArrayList<Service> getServices()
  {
    return serverModel.getServices();
  }
  
  @Override public ArrayList<Service> getServicesByInfo(String searchInfo)

  {
    return serverModel.getServicesByInfo(searchInfo);
  }
  
  @Override public ArrayList<Facility> getFacilities()
  {
    return serverModel.getFacilities();
  }
  
  @Override public ArrayList<Facility> getFacilitiesByInfo(String searchInfo)
  {
    return serverModel.getFacilitiesByInfo(searchInfo);
  }

  @Override public ArrayList<String> userSearch(String search)
      throws RemoteException
  {
    return serverModel.userSearch(search);
  }
  
  @Override public void addFriend(String username, String friend)
      throws RemoteException
  {
    serverModel.addFriend(username,friend);
  }
  
  @Override public void unfriend(String username, String friend)
      throws RemoteException
  {
    serverModel.unfriend(username, friend);
  }
  
  @Override public ArrayList<String> getFriendsList(String username)
      throws RemoteException
  {
    return serverModel.getFriendList(username);
  }
  
  @Override public boolean isFriend(String username, String friend) throws RemoteException
  {
    return serverModel.isFriend(username,friend);
  }
  
  @Override public boolean isProfessional(String username) throws RemoteException
  {
    return serverModel.isProfessional(username);
  }
  
  @Override public void professionalApplication(String username) throws RemoteException
  {
    serverModel.professionalApplication(username);
  }

  @Override public ArrayList<String> getReportList(String username)
      throws RemoteException
  {
    return serverModel.getReportList(username);
  }

  @Override public boolean againReported(String username, String report)
      throws RemoteException
  {
    return serverModel.againReported(username,report);
  }

  @Override public void addReport(String username, String report)
      throws RemoteException
  {
    serverModel.addReport(username, report);
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
