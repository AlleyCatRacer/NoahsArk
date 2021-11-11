package Model.Forum;

import java.io.Serializable;
import java.util.ArrayList;

public class Thread implements Serializable
{
   private String             title;
   private String             description;
   private ArrayList<Comment> comments;
   private static final long serialVersionUID = 26L;


   public Thread(String title, String description)
   {
      this.title       = title;
      this.description = description;
      this.comments    = new ArrayList<>();
   }
   
   public String getTitle()
   {
      return title;
   }

   public String getDescription()
   {
      return description;
   }
}
