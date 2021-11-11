package Model.FAQ;

import java.io.Serializable;

public class Question implements Serializable
{
   private String question;
   private String answer;
   private static final long serialVersionUID = 84L;

   public Question(String question, String answer)
   {
      this.question=question;
      this.answer=answer;
   }
   
   public String getQuestionString()
   {
      return question;
   }
   
   public String getAnswer()
   {
      return answer;
   }
}
