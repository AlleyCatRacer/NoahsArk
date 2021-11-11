package Server;

import Model.Password;
import Model.SearchItems.Service;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
  private User bob;
  private User[] many;
  private ArrayList<String> stories;
  private ArrayList<Service> services;

  @BeforeEach void setUp()
  {
    System.out.println("---> setUp()");
    bob = new User("Bob", new Password("Password123"));
    stories = new ArrayList<String>();
    services = new ArrayList<>();
  }

  @AfterEach void tearDown()
  {
    System.out.println("<---tearDown()");
  }

  //------------User constructor tested in UserListTesting-------------------------------------------------------

  //------------getSubscription() method----------------------------------------------------------------------------------
  // getSubscription() tested in addSubscription() tests
  @Test void getSubscription_Zero()
  {
    assertTrue(bob.getSubscriptions().isEmpty());
  }

  //------------addSubscription() method----------------------------------------------------------------------------------

  @Test void addSubscription_Zero()
  {
    bob.addSubscription("thread");
    assertTrue(bob.getSubscriptions().contains("thread"));
  }

  @Test void addSubscription_One()
  {
    bob.addSubscription("thread");
    bob.addSubscription("thread1");
    assertTrue(bob.getSubscriptions().contains("thread1"));
  }

  @Test void addSubscription_Many()
  {
    for (int i = 0; i < 10; i++)
    {
      bob.addSubscription("thread" + i);
    }
    assertTrue(bob.getSubscriptions().contains("thread9"));

    for (int i = 10; i < 20; i++)
    {
      bob.addSubscription("thread" + i);
    }
    assertTrue(bob.getSubscriptions().contains("thread19"));
  }

  @Test void addSubscription_Boundary()
  {
    //lower left boundary is unreachable
    //lower right boundary is tested in addSubscription_Zero()
    //no upper boundary for adding Subscriptions
  }

  @Test void addSubscription_Exceptions()
  {
    //no exceptions are thrown

    //threads must be added from existing threads so cannot be null

    //duplicate subscription are checked with isSubscribed() method in ServerModelManager
  }

  //------------verifyPassword() method----------------------------------------------------------------------------------

  @Test void verifyPassword_Zero()
  {
    assertFalse(bob.verifyPassword(""));
  }

  @Test void verifyPassword_One()
  {
    assertTrue(bob.verifyPassword("Password123"));
  }

  @Test void verifyPassword_Many()
  {
    User[] many = new User[] {new User("0Bob", new Password("Password123")),
        new User("1Bob", new Password("123Password")),
        new User("2Bob", new Password("passWord123")),
        new User("3Bob", new Password("paSS1234WORDpaSS1234WORD")),};

    assertTrue(many[0].verifyPassword("Password123"));
    assertTrue(many[1].verifyPassword("123Password"));
    assertTrue(many[2].verifyPassword("passWord123"));
    assertTrue(many[3].verifyPassword("paSS1234WORDpaSS1234WORD"));
  }

  @Test void verifyPassword_Boundary()
  {
    //no boundaries for this method
  }

  @Test void verifyPassword_Exceptions()
  {
    //no exceptions are thrown
  }

  //------------setInfo() method----------------------------------------------------------------------------------

  @Test void setInfo_Zero()
  {
    bob.setInfo(null);
    assertEquals("", bob.getInfo());
  }

  @Test void setInfo_One()
  {
    bob.setInfo("one");
    assertEquals("one", bob.getInfo());
  }

  @Test void setInfo_Many()
  {
    bob.setInfo("one");
    assertEquals("one", bob.getInfo());
    bob.setInfo("two");
    assertEquals("two", bob.getInfo());
    bob.setInfo("three");
    assertEquals("three", bob.getInfo());
    bob.setInfo("four");
    assertEquals("four", bob.getInfo());
    bob.setInfo("five");
    assertEquals("five", bob.getInfo());
  }

  @Test void setInfo_Boundary()
  {
    //no boundaries for this method
  }

  @Test void setInfo_Exceptions()
  {
    //no exceptions are thrown
  }

  //------------getInfo() method----------------------------------------------------------------------------------
  // tested in setInfo() method

  //------------setStories() method----------------------------------------------------------------------------------

  @Test void setStories_Zero()
  {
    bob.setStories(stories);
    assertTrue(bob.getStories().isEmpty());
  }

  @Test void setStories_One()
  {
    stories.add("hello");
    bob.setStories(stories);
    assertEquals("hello", bob.getStories().get(0));
  }

  @Test void setStories_Many()
  {
    for (int i = 0; i < 10; i++)
    {
      stories.add("story" + i);
    }
    bob.setStories(stories);
    assertEquals("story9", bob.getStories().get(9));

    for (int i = 10; i < 20; i++)
    {
      stories.add("story" + i);
    }
    bob.setStories(stories);
    assertEquals("story19", bob.getStories().get(19));
  }

  @Test void setStories_Boundary()
  {
    //no boundaries for this method
  }

  @Test void setStories_Exceptions()
  {
    stories.add("hello");
    bob.setStories(stories);

    stories = null;
    bob.setStories(stories);

    assertEquals("hello", bob.getStories().get(0));
  }

  //------------getFriendList() method----------------------------------------------------------------------------------
  // getFriendList() tested in addFriend() tests

  @Test void getFriendList_Zero()
  {
    assertTrue(bob.getFriendList().isEmpty());
  }

  //------------addFriend() method----------------------------------------------------------------------------------

  @Test void addFriend_Zero()
  {
    bob.addFriend("notBob");
    assertTrue(bob.getFriendList().contains("notBob"));
  }

  @Test void addFriend_One()
  {
    bob.addFriend("notBob");
    bob.addFriend("notBob1");
    assertTrue(bob.getFriendList().contains("notBob1"));
  }

  @Test void addFriend_Many()
  {
    for (int i = 0; i < 10; i++)
    {
      bob.addFriend("notBob" + i);
    }
    assertTrue(bob.getFriendList().contains("notBob9"));
    bob.unfriend("notBob9");
    assertFalse(bob.getFriendList().contains("notBob9"));

    for (int i = 10; i < 20; i++)
    {
      bob.addFriend("notBob" + i);
    }
    assertTrue(bob.getFriendList().contains("notBob19"));
    bob.unfriend("notBob19");
    assertFalse(bob.getFriendList().contains("notBob19"));
  }

  @Test void addFriend_Boundary()
  {
    //not relevant
  }

  @Test void addFriend_Exceptions()
  {
    //no exceptions are thrown
    bob.addFriend(null);
    assertFalse(bob.getFriendList().contains(null));
  }
  //------------unfriend() method----------------------------------------------------------------------------------

  @Test void unfriend_Zero()
  {
    assertDoesNotThrow(() -> bob.unfriend("notBob"));
  }

  @Test void unfriend_One()
  {
    bob.addFriend("notBob");
    bob.unfriend("notBob");
    assertFalse(bob.getFriendList().contains("notBob"));
  }

  @Test void unfriend_Many()
  {
    //tested in addFriend_Many()
  }

  @Test void unfriend_Boundary()
  {
    //not relevant
  }

  @Test void unfriend_Exceptions()
  {
    //no exceptions are thrown
    bob.addFriend("notBob");
    ArrayList<String> test = bob.getFriendList();
    assertDoesNotThrow(() -> bob.unfriend(null));
    assertEquals(test, bob.getFriendList());
  }
  //------------getReportList() method----------------------------------------------------------------------------------
  // getReportList() tested in addFriend() tests

  @Test void getReportList_Zero()
  {
    assertTrue(bob.getReportList().isEmpty());
  }

  //------------addReport() method----------------------------------------------------------------------------------

  @Test void addReport_Zero()
  {
    bob.addReport("notBob");
    assertTrue(bob.getReportList().contains("notBob"));
  }

  @Test void addReport_One()
  {
    bob.addReport("notBob");
    bob.addReport("notBob1");
    assertTrue(bob.getReportList().contains("notBob1"));
  }

  @Test void addReport_Many()
  {
    for (int i = 0; i < 10; i++)
    {
      bob.addReport("notBob" + i);
    }
    assertTrue(bob.getReportList().contains("notBob9"));
    for (int i = 10; i < 20; i++)
    {
      bob.addReport("notBob" + i);
    }
    assertTrue(bob.getReportList().contains("notBob19"));
  }

  @Test void addReport_Boundary()
  {
    //not relevant
  }

  @Test void addReport_Exceptions()
  {
    //no exceptions are thrown
    bob.addReport(null);
    assertFalse(bob.getReportList().contains(null));
  }

  //------------setPhoneNumber() method----------------------------------------------------------------------------------

  @Test void setPhoneNumber_Zero()
  {
    assertThrows(IllegalArgumentException.class, () -> bob.setPhoneNumber(""));
  }

  @Test void setPhoneNumber_One()
  {
    assertThrows(IllegalArgumentException.class, () -> bob.setPhoneNumber("1"));
  }

  @Test void setPhoneNumber_Many()
  {
    bob.setPhoneNumber("12345678");
    assertEquals("12345678", bob.getPhoneNumber());

    bob.setPhoneNumber("12-34-0987");
    assertEquals("12-34-0987", bob.getPhoneNumber());

    bob.setPhoneNumber("35 34 8765");
    assertEquals("35 34 8765", bob.getPhoneNumber());

    bob.setPhoneNumber("12 08-1994");
    assertEquals("12 08-1994", bob.getPhoneNumber());

    bob.setPhoneNumber("08-32 5348");
    assertEquals("08-32 5348", bob.getPhoneNumber());

    bob.setPhoneNumber("08-32 53-48");
    assertEquals("08-32 53-48", bob.getPhoneNumber());

    bob.setPhoneNumber("08-32 53 48");
    assertEquals("08-32 53 48", bob.getPhoneNumber());

  }

  @Test void setPhoneNumber_Boundary()
  {
    //    lower boundary
    assertThrows(IllegalArgumentException.class,
        () -> bob.setPhoneNumber("1234567"));
    assertThrows(IllegalArgumentException.class,
      () -> bob.setPhoneNumber("12 34 567"));
    assertThrows(IllegalArgumentException.class,
      () -> bob.setPhoneNumber("12-34-567"));

    //    upper boundary
    assertThrows(IllegalArgumentException.class,
        () -> bob.setPhoneNumber("123456789"));
    assertThrows(IllegalArgumentException.class,
        () -> bob.setPhoneNumber("12 34 56789"));
    assertThrows(IllegalArgumentException.class,
        () -> bob.setPhoneNumber("12-34-56789"));
  }

  @Test void setPhoneNumber_Exceptions()
  {
    assertThrows(IllegalArgumentException.class,
        () -> bob.setPhoneNumber(null));
}

  //------------setEmail() method----------------------------------------------------------------------------------

  @Test void setEmail_Zero()
  {
    assertThrows(IllegalArgumentException.class,()->bob.setEmail(""));
   }

  @Test void setEmail_One()
  {
    bob.setEmail("h@i.c");
    assertThrows(IllegalArgumentException.class,()->bob.setEmail("h"));
    assertThrows(IllegalArgumentException.class,()->bob.setEmail("1"));
  }

  @Test void setEmail_Many()
  {
    bob.setEmail("bob@via.dk");
    assertEquals("bob@via.dk", bob.getEmail());
    bob.setEmail("bob@v.d");
    assertEquals("bob@v.d", bob.getEmail());
    bob.setEmail("a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-@a-zA-Z0-9.-dk");
    assertEquals("a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-@a-zA-Z0-9.-dk", bob.getEmail());
  }

  @Test void setEmail_Boundary()
  {
    //tested in setEmail_One()
  }

  @Test void setEmail_Exceptions()
  {
    assertThrows(IllegalArgumentException.class,
        () -> bob.setEmail(null));

    assertThrows(IllegalArgumentException.class,
        () -> bob.setEmail("disclosure2021[at]gmail.com"));

    assertThrows(IllegalArgumentException.class,
        () -> bob.setEmail("()@gmail.com"));

    assertThrows(IllegalArgumentException.class,
        () -> bob.setEmail("regularUssr@!#$%&'*+/=?`{|}~^cantstartwithasymbol.com"));
  }

  //------------setServices() method----------------------------------------------------------------------------------

  @Test void setServices_Zero()
  {
    bob.setServices(services);
    assertTrue(bob.getServices().isEmpty());
  }

  @Test void setServices_One()
  {
    Service s = new Service("", "", "", "");
    services.add(s);
    bob.setServices(services);
    assertEquals(s, bob.getServices().get(0));
  }

  @Test void setServices_Many()
  {
    Service s = new Service("", "", "", "");
    for (int i = 0; i < 10; i++)
    {
      services.add(s);
    }
    bob.setServices(services);
    assertEquals(s, bob.getServices().get(9));

    for (int i = 10; i < 20; i++)
    {
      services.add(s);
    }
    bob.setServices(services);
    assertEquals(s, bob.getServices().get(9));

  }

  @Test void setServices_Boundary()
  {
    //no boundaries for this method
  }

  @Test void setServices_Exceptions()
  {
    Service s = new Service("", "", "", "");
    services.add(s);
    bob.setServices(services);

    services = null;
    bob.setStories(stories);

    assertEquals(s, bob.getServices().get(0));
  }
}