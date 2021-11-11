package Model.FAQ;

import java.io.Serializable;
import java.util.ArrayList;

public class FAQ implements Serializable
{
   private ArrayList<FAQTopic> topics;
   private static final long serialVersionUID = 41L;

   public FAQ()
   {
      this.topics=new ArrayList<>();
   }
   
   public FAQTopic getTopicByName(String title)
   {
      FAQTopic temp=new FAQTopic("Error404","Not found");
      for (FAQTopic t:topics)
      {
         if (t.getTitle().equalsIgnoreCase(title))
         {
            temp=t;
            break;
         }
      }
      return temp;
   }
   
   public FAQTopic getTopicByIndex(int index)
   {
      return topics.get(index);
   }

   public int getNumberOfFaqs()
   {return topics.size();};
}
