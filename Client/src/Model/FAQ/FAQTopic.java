package Model.FAQ;

import java.io.Serializable;
import java.util.ArrayList;

public class FAQTopic implements Serializable
{
   private String title;
   private String description;
   private ArrayList<Question> questions;
   private static final long serialVersionUID = 40L;
   
   public FAQTopic(String title,String description)
   {
      this.title=title;
      this.description=description;
      this.questions=new ArrayList<>();
   }
   
   public String getTitle()
   {
      return title;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public int getNumberOfQuestions()
   {
      return questions.size();
   }

   public Question getQuestionByIndex(int index)
   {
      return questions.get(index);
   }
}
