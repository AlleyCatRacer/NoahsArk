package Server;

import Model.UserList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserListTesting
{
  private UserList users;

  @org.junit.jupiter.api.BeforeEach void setUp()
  {
    System.out.println("---> setUp()");
    users = new UserList();
  }

  @org.junit.jupiter.api.AfterEach void tearDown()
  {
    System.out.println("<---tearDown()");
  }

  /*
   * ----------------addUser() method---------------------------
   */
  @Test public void addUser_Zero()
  {
    System.out.println("--- addUser_Zero()");
    users.addUser("Bob", "Password123");
    assertDoesNotThrow(() -> users.verified("Bob", "Password123"));
  }

  @Test public void addUser_One()
  {
    System.out.println("--- addUser_One()");
    users.addUser("Bob", "Password123");
    users.addUser("Bob1", "Password123");
    assertDoesNotThrow(() -> users.verified("Bob1", "Password123"));
  }

  @Test public void addUser_Many()
  {
    System.out.println("--- addUser_Many()");

    String user = "Bob";
    for (int i = 0; i < 10; i++)
    {
      String username = user + i;
      users.addUser(username, "Password123");
    }
    assertDoesNotThrow(() -> users.verified("Bob9", "Password123"));

    for (int i = 10; i < 20; i++)
    {
      String username = user + i;
      users.addUser(username, "Password123");
    }
    assertDoesNotThrow(() -> users.verified("Bob19", "Password123"));
  }

  @Test public void addUser_Boundary()
  {
    System.out.println("--- addUser_Boundary()");
    //lower left boundary is unreachable
    //lower right boundary is tested in addUser_Zero()
    //no upper boundary for users
  }

  @Test public void addUser_Exceptions()
  {
    System.out.println("--- addUser_Exceptions()");

    // null users
    assertThrows(IllegalArgumentException.class,
        () -> users.addUser("", "Password123"));

    // illegal user name
    assertThrows(IllegalArgumentException.class,
        () -> users.addUser("Bo", "Password123"));

    // username taken
    users.addUser("Bob", "Password123");
    assertThrows(IllegalArgumentException.class,
        () -> users.addUser("Bob", "Password123"));

    // null passwords
    assertThrows(IllegalArgumentException.class,
        () -> users.addUser("Bob1", ""));
    assertThrows(IllegalArgumentException.class,
        () -> users.addUser("Bob2", null));

    // illegal passwords
    assertThrows(IllegalArgumentException.class,
        () -> users.addUser("Bob3", "qwerty_1234"));
    assertThrows(IllegalArgumentException.class, () -> users.addUser("Bob4",
        "wefh£$^%&*&%^$%£w900859][f]d[][sksfaA'S;DGLKKS'FGKS;LKDVK;LFSLLD.FGLK"));
    assertThrows(IllegalArgumentException.class,
        () -> users.addUser("Bob5", "Qwerty_-"));
    assertThrows(IllegalArgumentException.class,
        () -> users.addUser("Bob6", "qwerty_-"));
  }


  /*
   * ----------------verified() method---------------------------
   */

  @Test public void verified_Zero()
  {
    System.out.println("--- verified_Zero()");
    //tested in addUser_Zero();
  }

  @Test public void verified_One()
  {
    System.out.println("--- verified_One()");
    //tested in addUser_One();

  }

  @Test public void verified_Many()
  {
    System.out.println("--- verified_Many()");

    //tested in addUser_Many()
  }

  @Test public void verified_Boundary()
  {
    System.out.println("--- verified_Boundary()");
    //lower left boundary is unreachable
    //lower right boundary is tested in addUser_Zero()
    //no upper boundary for users
  }

  @Test public void verified_Exceptions()
  {
    System.out.println("--- verified_Exceptions()");

    users.addUser("Bob", "Password123");
    users.addUser("Bob1", "Password567");
    assertThrows(IllegalArgumentException.class,
        () -> users.verified("Bob", "Password12"));
    assertThrows(IllegalArgumentException.class,
        () -> users.verified("Bob1", "Password123"));
    assertThrows(IllegalArgumentException.class,
        () -> users.verified("Bob", "Password567"));
    assertThrows(IllegalArgumentException.class,
        () -> users.verified("Bob12", "Password567"));
    assertDoesNotThrow(() -> users.verified("Bob", "Password123"));
  }

  /*
   * ----------------searchUsers() method---------------------------
   */

  @Test public void searchUsers_Zero()
  {
    System.out.println("--- searchUsers_Zero()");

    assertTrue(users.searchUsers("Bob").isEmpty());

    users.addUser("Bob", "Password123");
    assertEquals("Bob", users.searchUsers("").get(0));
    assertTrue(users.searchUsers(null).isEmpty());
  }

  @Test public void searchUsers_One()
  {
    System.out.println("--- searchUsers_One()");

    users.addUser("Bob", "Password123");
    assertEquals("Bob", users.searchUsers("B").get(0));
  }

  @Test public void searchUsers_Many()
  {
    System.out.println("--- searchUsers_Many()");

    String user = "Bob";
    for (int i = 0; i < 10; i++)
    {
      String username = user + i;
      users.addUser(username, "Password123");
    }
    assertEquals("Bob5", users.searchUsers("Bob5").get(0));
    assertEquals("Bob7", users.searchUsers("7").get(0));
    assertEquals("Bob3", users.searchUsers("b3").get(0));

  }

  @Test public void searchUsers_Boundary()
  {
    System.out.println("--- searchUsers_Boundary()");

    //not relevant
  }

  @Test public void searchUsers_Exceptions()
  {
    //no exceptions thrown
  }

}