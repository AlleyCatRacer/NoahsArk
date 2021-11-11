package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A UserList class that stores User objects in a HashMap with the username field from the User as the key.
 *
 * @author Group 1
 * @version 1.5 - 30.05.21
 */
public class UserList
{
  private Map<String, User> users;

  /**
   * Zero-argument constructor initialising a new HashMap
   */
  public UserList()
  {
    users = new HashMap<>();
  }

  /**
   * Getter for HashMap containing all users.
   *
   * @return Map<String, User> that contains all the users
   */
  public Map<String, User> getUsers()
  {
    return users;
  }

  /**
   * Method to add new User to the HashMap.
   *
   * @param username user's alias as a String
   * @param password user's password as a String
   * @throws IllegalArgumentException -   Adding an existing user throws an exception.
   */
  public void addUser(String username, String password)
  {
    if (!users.containsKey(username))
    {
      users.put(username, new User(username, new Password(password)));
    }
    else
    {
      throw new IllegalArgumentException("Username taken");
    }
  }
  
  /**
   * Method to add a new User to the HashMap.
   *
   * @param user User object
   */
  public void addUser(User user)
  {
    users.put(user.getUsername(), user);
  }

  /**
   * Method to verify user's credentials for access to the app.
   *
   * @param username user's alias as a String
   * @param password user's password as a String
   * @throws IllegalArgumentException - username not found in the Hashmap, or incorrect passwords throw an exception
   */
  public void verified(String username, String password)
  {
    if (!users.containsKey(username) || !users.get(username).verifyPassword(password))
    {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Method to search for a user in the HashMap with a String.
   *
   * @param search String to be searched for
   * @return an ArrayList of Strings containing the users' usernames that matched the search String
   */
  public ArrayList<String> searchUsers(String search)
  {
    ArrayList<String> userSearch = new ArrayList<>();
    if(search==null)
      return userSearch;

    for (String username : users.keySet())
    {
      if (username.contains(search))
        userSearch.add(username);
    }
    return userSearch;
  }
}
