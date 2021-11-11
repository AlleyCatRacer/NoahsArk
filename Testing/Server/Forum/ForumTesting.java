package Server.Forum;

import Model.Forum.Forum;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ForumTesting
{

  private Forum zero;
  private Forum one;
  private Forum many1;
  private Forum many2;

  @BeforeEach
  void buildUp() {
    zero = new Forum();

    one = new Forum();
    one.addTopic("Topic1", "d1");

    many1 = new Forum();
    many1.addTopic("Topic1", "description1");
    many1.addTopic("Topic2", "description2");
    many1.addTopic("Topic3", "description3");
    many1.addTopic("Topic4", "description4");
    many1.addTopic("Topic5", "description5");

    many2 = new Forum();
    many2.addTopic("Topic1", "description1");
    many2.addTopic("Topic2", "description2");
    many2.addTopic("Topic3", "description3");
    many2.addTopic("Topic4", "description4");
    many2.addTopic("Topic5", "description5");
    many2.addTopic("Topic6", "description6");
    many2.addTopic("Topic7", "description7");



  }
  @AfterEach
  void tearDown() {

  }

  // testing Forum() constructor
  @Test
  public void forumConstructor() {
    //The constructor does not have any parameters so it always behaves in the same way
  }

  // testing getTopicByTitle(String topicTitle) method
  @Test
  public void gettingOneElementFromAnEmptyList() {
    assertNull(zero.getTopicByTitle("Topic1"));
  }

  @Test
  public void gettingOneElement() {
    assertEquals("Topic1", one.getTopicByTitle("Topic1").getTitle());
  }
  @Test
  public void gettingOneOutOfManyEP1() {
    assertEquals("Topic3", many1.getTopicByTitle("Topic3").getTitle());
  }
  @Test
  public void gettingOneOutOfManyEP2()
  {

    assertEquals("Topic6", many2.getTopicByTitle("Topic6").getTitle());
  }
  @Test
  public void gettingAnElementThatIsNotInTheList() {
    assertNull(many1.getTopicByTitle("Topic6"));
  }
  @Test
  public void gettingAnException() {
    //No exceptions are thrown in this method
  }

  // testing getTopicByIndex(int index) method

  @Test
  public void gettingByIndexFromAnEmptyList() {
    assertThrows(IndexOutOfBoundsException.class,()-> zero.getTopicByIndex(0));
  }
  @Test
  public void gettingByIndexOutOfOne() {
    assertEquals("Topic1", one.getTopicByIndex(0).getTitle());
  }
  @Test
  public void gettingByIndexOutOfManyEP1() {
    assertEquals("Topic3", many1.getTopicByIndex(2).getTitle());
  }
  @Test
  public void gettingByIndexOutOfManyEP2()
  {
    assertEquals("Topic6", many2.getTopicByIndex(5).getTitle());
  }
  @Test
  public void gettingByIndexNearTheBoundary() {
    assertEquals("Topic5", many2.getTopicByIndex(4).getTitle());
    assertThrows(IndexOutOfBoundsException.class,()-> many1.getTopicByIndex(5));
    assertThrows(IndexOutOfBoundsException.class,()-> many1.getTopicByIndex(6));
  }
  @Test
  public void gettingByIndexException() {
    assertThrows(IndexOutOfBoundsException.class,()-> many1.getTopicByIndex(100));
  }

  // testing addCommentOnTopic(String topicTitle, String message, String user) method

  @Test
  public void addNoComment() {
    //Not testable because adding no comment does not make sense
  }
  @Test
  public void addingOneComment() {
    assertDoesNotThrow(()->one.addCommentOnTopic("Topic1", "Hello", "Bob"));
    assertEquals(1, one.getTopicByTitle("Topic1").getNumberOfComments());
  }
  @Test
  public void addingOneNullComment() {
    assertThrows(IllegalArgumentException.class,()-> one.addCommentOnTopic(null, null, null));
  }
  @Test
  public void addingManyCommentsEP1() {
    one.addCommentOnTopic("Topic1", "Hello", "Bob");
    one.addCommentOnTopic("Topic1", "Hello", "Bob");
    one.addCommentOnTopic("Topic1", "Hello", "Bob");
    assertEquals(3, one.getTopicByTitle("Topic1").getNumberOfComments());
  }
  @Test
  public void addingManyCommentsEP2() {
    one.addCommentOnTopic("Topic1", "Hello", "Bob");
    one.addCommentOnTopic("Topic1", "Hello", "Bob");
    one.addCommentOnTopic("Topic1", "Hello", "Bob");
    one.addCommentOnTopic("Topic1", "Hello", "Bob");
    one.addCommentOnTopic("Topic1", "Hello", "Bob");
    assertEquals(5, one.getTopicByTitle("Topic1").getNumberOfComments());
  }
  @Test
  public void addingCommentBoundaries() {
    //adding comment does not have boundaries
  }
  @Test
  public void addingCommentExceptions() {
    //exceptions thrown were tested in the adding one comment part
  }


  // testing addTopic(title : String, description : String) method

  @Test
  public void addingNoTopic() {
    //used in the BeforeEach
    assertEquals(0, zero.getNumberOfTopics());
  }
  @Test
  public void addingOneTopic() {
    //used in the BeforeEach
    assertEquals(1, one.getNumberOfTopics());
  }
  @Test
  public void addingTwoTopicsWithIdenticalTitles() {
    assertThrows(IllegalArgumentException.class,()-> one.addTopic("Topic1", "d2"));
    assertEquals(1, one.getNumberOfTopics());
  }
  @Test
  public void addingManyTopicEP1() {
    //used in the BeforeEach
    assertEquals(5, many1.getNumberOfTopics());
  }
  @Test
  public void addingManyTopicEP2() {
    //used in the BeforeEach
    assertEquals(7, many2.getNumberOfTopics());
  }
  @Test
  public void addingTopicsBoundaries() {
    //Does not have any boundaries
  }
  @Test
  public void addingTopicsExceptions() {
    assertThrows(IllegalArgumentException.class,()-> zero.addTopic(null, null));
  }

  // testing getNumberOfTopics() method

  @Test
  public void gettingNumberOfTopics() {
    //tested in addTopic method
  }

  // testing removeTopic(topic : String) method

  @Test
  public void removeFromAnEmptyList() {
    assertThrows(IllegalArgumentException.class,()-> zero.removeTopic("Topic1"));
  }
  @Test
  public void removeOneElement() {
    one.removeTopic("Topic1");
    assertEquals(0, one.getNumberOfTopics());
  }
  @Test
  public void removeOneOutOfManyEP1() {
    many1.removeTopic("Topic4");
    assertEquals(4, many1.getNumberOfTopics());
  }
  @Test
  public void removeOneOutOfManyEP2() {
    many2.removeTopic("Topic4");
    assertEquals(6, many2.getNumberOfTopics());
  }
  @Test
  public void removeOneBoundaries() {
    // there are no boundaries
  }
  @Test
  public void removeOneExceptions() {
    assertThrows(IllegalArgumentException.class,()-> many1.removeTopic("Topic8"));
  }
}