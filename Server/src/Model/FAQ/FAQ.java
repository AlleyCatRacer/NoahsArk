package Model.FAQ;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A Frequently Asked Questions class that contains the topics.
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 1.0 - 19.05.21
 */
public class FAQ implements Serializable
{
   private ArrayList<FAQTopic> topics;
   private static final long serialVersionUID = 41L;

   /**
    * Zero argument constructor that initializes the ArrayList field containing FAQ Topics.
    */
   public FAQ()
   {
      this.topics=new ArrayList<>();
   }

   /**
    * Creates and returns a FAQ Topic based on the String provided as the argument.
    *
    * @param title String of the Topic title
    * @return FAQ Topic that contains all the questions and answers associated with the Topic or if no such topic was found it returns a Topic object with fields of "Error404" and "Not Found"
    */
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
   
   /**
    * Returns the size of the ArrayList field containing FAQ topics
    *
    * @return integer of amount of Topics within the ArrayList
    */
   public int getNumberOfFaqs()
   {
      return topics.size();
   }
   
   /**
    * Retrieves FAQ Topic based on the index provided.
    *
    * @param index integer of the desired Topic
    * @return FAQ Topic object based on the index that was provided as the argument
    * @throws IndexOutOfBoundsException If the provided index is out of bounds of the ArrayList = "Index [insert int argument] is out of bounds"
    */
   public FAQTopic getTopicByIndex(int index)
   {
      if (index < topics.size())
      {
         return topics.get(index);
      }
      else
      {
         throw new IndexOutOfBoundsException("Index "+index+" is out of bounds");
      }
   }
   
   /**
    * Creates and adds a new Topic object to the ArrayList of Topic objects based on provided arguments.
    *
    * @param title String of Topic title
    * @param description String of Topic description
    * @throws IllegalArgumentException If a Topic with such title already exists = "A topic with the title [insert title argument] already exists"
    */
   public void addTopic(String title, String description)
   {
      for (FAQTopic topic : topics)
      {
         if (topic.getTitle().equalsIgnoreCase(title))
         {
            throw new IllegalArgumentException("A topic with the title \"" + title + "\" already exists");
         }
      }
      topics.add(new FAQTopic(title, description));
   }

   /**
    * Removes a Topic from the ArrayList based on the title provided
    *
    * @param title String of the Topic title
    * @throws NoSuchElementException If a Topic with such title does not exist within the ArrayList = "No topic was found with a title of [insert title argument]"
    */
   public void removeTopic(String title)
   {
      int i= topics.size();
      topics.remove(getTopicByName(title));
      if (i == topics.size())
      {
         throw new NoSuchElementException("No topic was found with a title of "+title);
      }
   }
}
