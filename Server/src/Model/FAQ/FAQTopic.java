package Model.FAQ;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * FAQ Topic class that contains all the information about Topic in FAQ section
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 1.0 - 19.05.21
 */

public class FAQTopic implements Serializable
{
   private String title;
   private String description;
   private ArrayList<Question> questions;
   private static final long serialVersionUID = 40L;

   /**
    * Constructor taking 2 arguments, initializing title and description accordingly and an empty ArrayList for the Questions
    *
    * @param title String of Topic title
    * @param description String of Topic description
    * @throws IllegalArgumentException If the topic title is null or blank = "Topic must have a title"
    */
   public FAQTopic(String title, String description)
   {
      if (title==null || title.isBlank())
      {
         throw new IllegalArgumentException("Topic must have a title");
      }
      else
      {
         this.title       = title;
         this.description = description;
         this.questions   = new ArrayList<>();
      }
   }

   /**
    * Getter for title instance variable.
    *
    * @return String of Topic title
    */
   public String getTitle()
   {
      return title;
   }

   /**
    * Getter for description instance variable.
    *
    * @return String of Topic description
    */
   public String getDescription()
   {
      if (description.isBlank() || description==null)
      {
         return "No description given";
      }
      return description;
   }
   
   /**
    * Returns the size of the ArrayList instance variable.
    *
    * @return integer representing the size of the ArrayList
    */
   public int getNumberOfQuestions()
   {
      return questions.size();
   }
   
   /**
    * Retrieves the Question object based on the specified index from the ArrayList instance variable.
    *
    * @param index integer which determines the Question object that will be returned
    * @return Question object based on the index argument
    * @throws IndexOutOfBoundsException If the index provided is out of bounds of the ArrayList = "[insert integer argument] is out of bounds"
    */
   public Question getQuestionByIndex(int index)
   {
      if (index < questions.size())
      {
         return questions.get(index);
      }
      else
      {
         throw new IndexOutOfBoundsException(index+" is out of bounds");
      }
   }

   /**
    * Setter for the title instance variable.
    *
    * @param title String to which the title instance variable will be set
    * @throws IllegalArgumentException If the title argument is blank or null = "Topic must have a title"
    */
   public void setTitle(String title)
   {
      if (title==null || title.isBlank())
      {
         throw new IllegalArgumentException("Topic must have a title");
      }
      else
      {
         this.title = title;
      }
   }

   /**
    * Setter for the description instance variable.
    *
    * @param description String to which the description instance variable will be set
    */
   public void setDescription(String description)
   {
      this.description = description;
   }

   /**
    * Creates and adds a Question object to the ArrayList field based on the arguments.
    *
    * @param question String containing the question
    * @param answer String containing the answer
    * @throws IllegalArgumentException If the question with such String already exists = "That question already exists"
    */
   public void addQuestion(String question, String answer)
   {
      for (Question value : questions)
      {
         if (value.getQuestionString().equalsIgnoreCase(question))
         {
            throw new IllegalArgumentException("That question already exists");
         }
      }
      questions.add(new Question(question, answer));
   }

   /**
    * Removes specified Question from the ArrayList field.
    *
    * @param question Question object that you want to remove from the list
    * @throws IllegalArgumentException If the provided Question object was not found in the ArrayList = "Question not found"
    */
   public void removeQuestion(Question question)
   {
      if (questions.contains(question))
      {
         questions.remove(question);
      }
      else
         throw new IllegalArgumentException("Question not found");
   }

   /**
    * Returns the title and description (if present) in the form of a String
    *
    * @return String containing the title, appended will be the description if it is not null or blank
    */
   @Override public String toString()
   {
      if (description==null || description.isBlank())
      {
         return getTitle();
      }
      return getTitle()+": "+getDescription();
   }
}
