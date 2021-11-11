package Model.Forum;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Thread object class containing it's title, description and an ArrayList of Comment objects.
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 1.0 - 19.05.21
 */
public class Thread implements Serializable
{
   private String             title;
   private String             description;
   private ArrayList<Comment> comments;
   private static final long serialVersionUID = 26L;

   /**
    * Constructor taking 2 arguments and initializing Thread title, description and the ArrayList containing comments.
    *
    * @param title String of Thread title
    * @param description String of Thread description
    */
   public Thread(String title, String description)
   {
      this.title       = title;
      this.description = description;
      this.comments    = new ArrayList<>();
   }

   /**
    * Retrieves the size of the ArrayList field containing the Comment objects.
    *
    * @return integer representing the size of the ArrayList
    */
   public int getNumberOfComments()
   {
      return comments.size();
   }

   /**
    * Retrieves title of the Thread.
    *
    * @return String of Thread's title
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * Retrieves description of the Thread.
    *
    * @return String of Thread's description
    */
   public String getDescription()
   {
      return description;
   }

   /**
    * Creates and adds a Comment to the ArrayList field of Comment objects.
    *
    * @param message String representing the comment
    * @param user String representing the user that made the comment
    */
   public void addComment(String message, String user)
   {
      comments.add(new Comment(message, user));
   }
}
