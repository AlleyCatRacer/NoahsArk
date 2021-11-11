package Model.Forum;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Forum class containing all the information associated with the Forum section in the form of Thread ArrayList.
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 1.0 - 19.05.21
 */
public class Forum implements Serializable
{
  private ArrayList<Thread> threads;
  private static final long serialVersionUID = 42L;

  /**
   * Zero argument constructor initializing the ArrayList containing Topic objects.
   */
  public Forum()
  {
    this.threads = new ArrayList<>();
  }

  /**
   * Retrieves Thread object based on the argument provided.
   *
   * @param title String of the desired Thread title
   * @return Thread object found based on the title argument provided, if not found then a null value is returned
   */
  public Thread getTopicByTitle(String title)
  {
    for (Thread t : threads)
    {
      if (t.getTitle().equals(title))
      {
        return t;
      }
    }
    return null;
  }

  /**
   * Retrieves Thread object based on the index provided.
   *
   * @param index integer representing the index of desired Thread object within the ArrayList
   * @return Thread object based on the index provided or a null value if there is nothing there
   */
  public Thread getTopicByIndex(int index)
  {
    try
    {
      return threads.get(index);
    }
    catch (NullPointerException n)
    {
      return null;
    }
  }

  /**
   * Adds a comment to the specified thread.
   *
   * @param topicTitle String of the Thread title
   * @param message String of the comment that is to be added
   * @param user String of the user that posted the comment
   * @throws IllegalArgumentException If one of the arguments is null = "Title, message and username cannot be null"
   */
  public void addCommentOnTopic(String topicTitle, String message, String user)
  {
    if(topicTitle != null && message != null && user != null)
    {
      getTopicByTitle(topicTitle).addComment(message, user);
    }
    else
      throw new IllegalArgumentException("Title, message and username cannot be null");
  }

  /**
   * Creates and adds a Thread object to the ArrayList based on given arguments.
   *
   * @param title String of the Thread title
   * @param description String of the Thread description
   * @throws IllegalArgumentException If title or description is null = "Please provide a title and a description" or a topic with such title already exists = "Please provide a unique title"
   */
  public void addTopic(String title, String description)
  {
    if(title != null && description != null) {
      for(Thread threadlocal : threads)
      {
        if (threadlocal.getTitle().equals(title))
        {
          throw new IllegalArgumentException("Please provide a unique title");
        }
      }
      threads.add(new Thread(title, description));
    }
    else
      throw new IllegalArgumentException("Please provide a title and a description");
  }

  /**
   * Removes Thread object from the ArrayList based on the argument provided.
   *
   * @param topic String of the Thread topic's title that should be removed
   * @throws IllegalArgumentException If the Thread object with provided title was not found = "Thread not found"
   */
  public void removeTopic(String topic)
  {
    int removed = 0;
    for(int i = 0; i < threads.size(); i++) {
      if(threads.get(i).getTitle().equals(topic)) {
        threads.remove(i);
        removed++;
        break;
      }
    }
    if(removed == 0) {
      throw new IllegalArgumentException("Thread not found");
    }
  }

  /**
   * Returns the size of the ArrayList containing Threads.
   *
   * @return integer representing the size of the ArrayList
   */
  public int getNumberOfTopics()
  {
    return threads.size();
  }
}
