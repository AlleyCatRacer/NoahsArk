package Model.Forum;


import java.io.Serializable;
import java.time.LocalDate;

/**
 * Comment class containing the message, timestamp (when the comment was added) and the signature (by whom the comment was added).
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 1.0 - 19.05.21
 */
public class Comment implements Serializable
{
   private String      signature;
   private LocalDate timestamp;
   private String    message;
   private static final long serialVersionUID = 9L;

   /**
    * Constructor taking 2 arguments and initializing message and user by the arguments and setting timestamp to current time.
    *
    * @param message String of the comment's text message
    * @param user String of the commenter's username
    */
   public Comment(String message, String user)
   {
      this.signature      = user;
      this.message   = message;
      this.timestamp = LocalDate.now();
   }

   /**
    * Getter for a specifically formatted signature, message and timestamp as a String
    *
    * @return String containing the signature, message and timestamp belonging to the object
    */
   @Override public String toString()
   {
      return signature + ": " + message + " [" + timestamp + "]";
   }
}
