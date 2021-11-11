package Model.Forum;

import java.io.Serializable;
import java.time.LocalDate;

public class Comment implements Serializable
{
   private String      user;
   private LocalDate timestamp;
   private String    message;
   private static final long serialVersionUID = 9L;
   
   public Comment(String message, String user)
   {
      this.user      = user;
      this.message   = message;
      this.timestamp = LocalDate.now();
   }
   
   @Override public String toString()
   {
      return user + ": " + message + " [" + timestamp + "]";
   }
}
