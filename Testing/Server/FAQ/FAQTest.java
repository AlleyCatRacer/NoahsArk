package Server.FAQ;

import Model.FAQ.FAQ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FAQTest
{
   private final FAQ f=new FAQ();
   
   @Test void zeroTopicQuestion()
   {
      assertEquals("Error404",f.getTopicByName("title").getTitle());
   }
   
   @Test void oneTopicQuestion()
   {
      //add + FAQ: getNumberOfFaqs
      f.addTopic("Title","desc");
      assertEquals(1, f.getNumberOfFaqs());
      //remove
      f.removeTopic("Title");
      assertEquals(0, f.getNumberOfFaqs());
   }
   
   @Test void manyTopicsQuestions()
   {
      //add
      for (int i = 0; i < 5; i++)
      {
         f.addTopic("title"+(i+1),"desc"+(i+1));
         f.getTopicByIndex(i).addQuestion("q"+(i+1),"a"+(i+1));
      }
      assertEquals(5,f.getNumberOfFaqs());
      
      //FAQ: getByIndex + Topic: getTitle + Question: getQuestion, getAnswer
      assertEquals("title1", f.getTopicByIndex(0).getTitle());
      assertEquals("title5",f.getTopicByIndex(f.getNumberOfFaqs()-1).getTitle());
      
      //remove
      for (int i = 0; i < 2; i++)
      {
         f.removeTopic(f.getTopicByIndex((i+1)).getTitle());
      }
      assertEquals(3, f.getNumberOfFaqs());
      assertEquals("title1",f.getTopicByIndex(0).getTitle());
   }
   
   @Test void exceptions()
   {
      //get non-existent topic
      assertThrows(IndexOutOfBoundsException.class,()->f.getTopicByIndex(0));
   
      //empty topic description allowed, not title
      assertThrows(IllegalArgumentException.class,()->f.addTopic("",""));
      assertDoesNotThrow(()->f.addTopic("title",null));
      assertDoesNotThrow(()->f.addTopic("other title",""));
      
      //empty answer allowed, not question
      assertThrows(IllegalArgumentException.class,()->f.getTopicByIndex(0).addQuestion("",""));
      assertThrows(IllegalArgumentException.class,()->f.getTopicByIndex(0).addQuestion("","a"));
      assertThrows(IllegalArgumentException.class,()->f.getTopicByIndex(0).addQuestion(null,"a"));
      assertThrows(IllegalArgumentException.class,()->f.getTopicByIndex(0).addQuestion(null,null));
      assertDoesNotThrow(()->f.getTopicByIndex(0).addQuestion("q",""));
      assertDoesNotThrow(()->f.getTopicByIndex(0).addQuestion("q1",null));
      
      //duplicate questions not allowed
      assertThrows(IllegalArgumentException.class,()->f.getTopicByIndex(0).addQuestion("q","a"));
      
      //get out of bounds
      for (int i = 0; i < 5; i++)
      {
         f.addTopic("title"+(i+10),"desc"+(i+10));
         f.getTopicByIndex(i+1).addQuestion("q"+(i+10),"a"+(i+10));
      }
      assertThrows(IndexOutOfBoundsException.class,()->f.getTopicByIndex(10));
   }
}
//Zero
//One
//Many
//Boundary
//Exception