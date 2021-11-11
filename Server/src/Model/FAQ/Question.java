package Model.FAQ;

import java.io.Serializable;

/**
 * Question class that contains a question and answer in the format of Strings.
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 1.0 - 19.05.21
 */

public class Question implements Serializable
{
   private String question;
   private String answer;
   private static final long serialVersionUID = 84L;

   /**
    * Constructor taking two arguments, initializing question and answer instance variables.
    *
    * @param question String of question parameter
    * @param answer String of answer parameter
    * @throws IllegalArgumentException If question is empty or null = "Question cannot be empty or null"
    */
   public Question(String question, String answer)
   {
      if (question==null || question.isBlank())
      {
         throw new IllegalArgumentException("Question cannot be empty or null");
      }
      else
      {
         this.question = question;
         this.answer   = answer;
      }
   }

   /**
    * Gets the question in a format of a String.
    *
    * @return String of the question instance variable
    */
   public String getQuestionString()
   {
      return question;
   }

   /**
    * Gets the answer in a format of a String.
    *
    * @return String of the answer instance variable
    */
   public String getAnswer()
   {
      return answer;
   }

   /**
    * Formats the contents of the object as String.
    *
    * @return String that contains question and appends the answer if there is one or "Unanswered" if not
    */
   @Override public String toString()
   {
      if (answer.isBlank() || answer==null)
      {
         return question+"?\nUnanswered";
      }
      else
         return question+"?\n"+answer;
   }
}
