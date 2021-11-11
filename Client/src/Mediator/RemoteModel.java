package Mediator;

import Model.FAQ.FAQ;
import Model.Forum.Forum;
import Model.Home.ArticleList;
import Model.Profile;
import Model.SearchItems.Facility;
import Model.SearchItems.Service;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RemoteModel extends RemoteSubject<String, String>
{
  void login(String username, String password) throws RemoteException;
  
  void addUser(String username, String password) throws RemoteException;

  void addCommentOnTopic(String topicTitle, String message, String username) throws RemoteException;

  void subscribe(String thread, String username) throws RemoteException;

  boolean isSubscribed(String thread, String username) throws RemoteException;

  void unsubscribe(String thread, String username) throws RemoteException;

  void updateProfile(Profile profile) throws RemoteException;
  
  Profile profileInfo(String username) throws RemoteException;
  
  Forum getForum() throws RemoteException;

  FAQ getFaqs() throws RemoteException;

  ArticleList getArticles() throws RemoteException;

  ArrayList<String> getComments(String forumTopic) throws RemoteException;

  ArrayList<Service> getServices() throws RemoteException;

  ArrayList<Service> getServicesByInfo(String searchInfo) throws RemoteException;
  
  ArrayList<Facility> getFacilities() throws RemoteException;
  
  ArrayList<Facility> getFacilitiesByInfo(String info) throws RemoteException;

  ArrayList<String> userSearch(String search) throws RemoteException;
  
  void addFriend(String username,String friend) throws RemoteException;
  
  void unfriend(String username,String friend) throws RemoteException;
  
  ArrayList<String> getFriendsList(String username) throws RemoteException;
  
  boolean isFriend(String username, String friend) throws RemoteException;
  
  boolean isProfessional(String username) throws RemoteException;
  
  void professionalApplication(String username) throws RemoteException;

  ArrayList<String> getReportList(String username) throws RemoteException;

  boolean againReported(String username, String report) throws RemoteException;

  void addReport(String username, String report) throws RemoteException;
}
