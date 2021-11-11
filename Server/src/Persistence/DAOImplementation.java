package Persistence;

import Model.FAQ.FAQ;
import Model.Forum.Comment;
import Model.Forum.Forum;
import Model.Home.ArticleList;
import Model.Password;
import Model.Profile;
import Model.SearchItems.Facility;
import Model.SearchItems.Service;
import Model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAOImplementation implements DatabaseDAO
{

  private static DAOImplementation daoImplementation;

  private DAOImplementation()
  {
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException e) {
      throw new IllegalStateException("The PostgreSQL driver was not registered");
    }
  }

  private Connection getConnection()
  {
    try {
      return DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=noahs_ark",
          "postgres", "mypassword");
    }
    catch (SQLException e) {
      throw new IllegalStateException("The connection to the database was not established");
    }

  }

  public static synchronized DAOImplementation getInstance()
  {

    if(daoImplementation == null) {
      daoImplementation = new DAOImplementation();
    }
    return daoImplementation;
  }

  @Override public ArticleList loadArticles()
  {
    try(Connection connection = getConnection()) {
      ArticleList articles = new ArticleList();
      PreparedStatement statement = connection.prepareStatement("Select * from article");
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        String headline = resultSet.getString("headline");
        String content = resultSet.getString("content");
        articles.addArticle(headline, content);
      }
      return articles;
    }
    catch (SQLException e) {
      throw new IllegalStateException("Something went wrong while retrieving articles");
    }
  }

  @Override public ArrayList<Facility> loadFacilities()
  {
    try(Connection connection = getConnection()) {
      ArrayList<Facility> facilities = new ArrayList<>();
      PreparedStatement statement = connection.prepareStatement("Select * from facility;");
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        String name = resultSet.getString("name");
        String postcode = resultSet.getString("postcode");
        String details = resultSet.getString("description");
        String contactInfo = resultSet.getString("phone");
        facilities.add(new Facility(name, postcode, details, contactInfo));
      }
      return facilities;
    }
    catch (SQLException e) {
      throw new IllegalStateException("Something went wrong while retrieving facilities");
    }

  }

  @Override public ArrayList<Service> loadServices()
  {
    try(Connection connection = getConnection()) {
      ArrayList<Service> services = new ArrayList<>();
      PreparedStatement statement = connection.prepareStatement("Select * from service;");
      ResultSet resultSet = statement.executeQuery();

      while(resultSet.next()) {
        String title = resultSet.getString("service_title");
        String provider = resultSet.getString("provider");
        String postcode = resultSet.getString("postcode");
        String details = resultSet.getString("details");
        int price = resultSet.getInt("price");
        services.add(new Service(title, provider, postcode, details, price));
      }
      return services;
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalStateException("Something went wrong while retrieving services");
    }
  }

  @Override public Forum loadForum()
  {
    try(Connection connection = getConnection()) {
      Forum forum = new Forum();
      PreparedStatement statement = connection.prepareStatement("Select * from forum_thread;");
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        forum.addTopic(title, description);
      }
      return forum;
    }
    catch (SQLException e) {
      throw new IllegalStateException("Something went wrong while retrieving forum");
    }
  }

  @Override public FAQ loadFAQ()
  {
    try(Connection connection = getConnection()) {
      FAQ faq = new FAQ();
      PreparedStatement statement1 = connection.prepareStatement("Select * from faq_topic;");
      ResultSet resultSet = statement1.executeQuery();
      while(resultSet.next()) {
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        faq.addTopic(title, description);

        PreparedStatement statement2 = connection.prepareStatement("Select * from question where topic = ?;");
        statement2.setString(1, title);
        ResultSet questions = statement2.executeQuery();

        while(questions.next())
        {
          String question = questions.getString("question");
          String answer = questions.getString("answer");
          faq.getTopicByName(title).addQuestion(question, answer);
        }

      }

      return faq;
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalStateException("Something went wrong while retrieving FAQ");
    }
  }

  @Override public ArrayList<User> loadUsers()
  {
    try(Connection connection = getConnection()) {
      ArrayList<User> users = new ArrayList<>();
      PreparedStatement statement = connection.prepareStatement("Select * from app_user;");
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        String name = resultSet.getString("username");
        String password = resultSet.getString("password");
        User user = new User(name, new Password(password));
        users.add(user);

        //adding aboutMe to this user
        PreparedStatement info = connection.prepareStatement("Select about_me from app_user where username = ?;");
        info.setString(1, name);
        ResultSet infoResult = info.executeQuery();
        if(infoResult.next()) {
          user.setInfo(infoResult.getString("about_me"));
        }

        //adding subscriptions to this user
        PreparedStatement fetchingID = connection.prepareStatement("Select user_id from app_user where username = ?;");
        fetchingID.setString(1, name);
        ResultSet userID = fetchingID.executeQuery();
        int id = 0;
        if(userID.next()) {
          id = userID.getInt("user_id");
        }
        PreparedStatement subscriptions = connection.prepareStatement("Select * from subscriptions where user_id = ?");
        subscriptions.setInt(1, id);
        ResultSet subscriptionsResult = subscriptions.executeQuery();
        while(subscriptionsResult.next()) {
          String thread = subscriptionsResult.getString("title");
          user.addSubscription(thread);
        }
        //fetching User's friendList
        PreparedStatement friends = connection.prepareStatement("Select * from friends where user_id = ?;");
        friends.setInt(1, id);
        ResultSet friendList = friends.executeQuery();
        while(friendList.next()) {
          int fID = friendList.getInt("friend_id");
          PreparedStatement friendsNames = connection.prepareStatement("Select username from app_user where user_id = ?;");
          friendsNames.setInt(1, fID);
          ResultSet friendResult = friendsNames.executeQuery();
          if(friendResult.next()) {
            String fName = friendResult.getString("username");
            user.addFriend(fName);
          }
        }
        //services
        PreparedStatement services = connection.prepareStatement("Select * from service where provider = ?;");
        services.setString(1, name);
        ResultSet servicesResult = services.executeQuery();
        ArrayList<Service> serviceList = new ArrayList<>();
        while(servicesResult.next()) {
          String title = servicesResult.getString("service_title");
          String provider = servicesResult.getString("provider");
          String postcode = servicesResult.getString("postcode");
          String details = servicesResult.getString("details");
          int price = servicesResult.getInt("price");
          serviceList.add(new Service(title, provider, postcode, details, price));
        }
        if(serviceList.size() > 0) {
          user.setProfessionalStatus();
        }
        user.setServices(serviceList);
        //adding stories to this user
                PreparedStatement stories = connection.prepareStatement("Select * from stories where user_id = ?;");
                ArrayList<String> storyList = new ArrayList<>();
                stories.setInt(1, id);
                ResultSet storiesResult = stories.executeQuery();
                while(storiesResult.next()) {
                  String story = storiesResult.getString("story");
                  storyList.add(story);
                }
                user.setStories(storyList);
        //contactInfo
        PreparedStatement contactInfo = connection.prepareStatement("Select * from professional_user where username = ?;");
        contactInfo.setString(1, name);
        ResultSet contactResult = contactInfo.executeQuery();
        if(contactResult.next()) {
          String email = contactResult.getString("email");
          String phone = contactResult.getString("phone_no");
          user.setEmail(email);
          user.setPhoneNumber(phone);
        }

        //fetching User's reports
        PreparedStatement reports = connection.prepareStatement("Select * from reports where user_id = ?;");
        reports.setInt(1, id);
        ResultSet reportsResult = reports.executeQuery();
        while(reportsResult.next()) {
          int reportedID = reportsResult.getInt("reported_user_id");
          PreparedStatement usernameStatement = connection.prepareStatement("Select username from app_user where user_id = ?;");
          usernameStatement.setInt(1, reportedID);
          ResultSet usernameResult = usernameStatement.executeQuery();
          if(usernameResult.next()) {
            String reportedUsername = usernameResult.getString("username");
            user.addReport(reportedUsername);
          }
        }
      }



      return users;
    }
    catch (SQLException e) {
      throw new IllegalStateException("Something went wrong while retrieving comments for topic");
    }
  }

  @Override public ArrayList<String> loadComments(String forumTopic)
  {
    try(Connection connection = getConnection()) {
      ArrayList<String> comments = new ArrayList<>();
      PreparedStatement statement = connection.prepareStatement("Select * from comment where thread = ?;");
      statement.setString(1, forumTopic);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        String message = resultSet.getString("text");
        String user = resultSet.getString("signature");
        Comment comment = new Comment(message, user);
        comments.add(comment.toString());
      }
      return comments;
    }
    catch (SQLException e) {
      throw new IllegalStateException("Something went wrong while retrieving comments for topic");
    }
  }

  @Override public void updateProfile(Profile profile)
  {
    try(Connection connection = getConnection()) {
      PreparedStatement user = connection.prepareStatement("Select * from app_user where username = ?");
      user.setString(1, profile.getUsername());
      ResultSet userResult = user.executeQuery();
      int uID = 0;
      if(userResult.next()) {
        uID = userResult.getInt("user_id");
      }
      //updating aboutMe
      PreparedStatement aboutMe = connection.prepareStatement("Update app_user set about_me = ? where user_id = ?;");
      aboutMe.setString(1, profile.getInfo());
      aboutMe.setInt(2, uID);
      aboutMe.executeUpdate();
      //updating stories
      PreparedStatement deletingStories = connection.prepareStatement("Delete from stories where user_id = ?;");
      deletingStories.setInt(1, uID);
      deletingStories.executeUpdate();
      for(int i = 0; i < profile.getStories().size(); i++) {
        PreparedStatement addingStories = connection.prepareStatement("Insert into stories (user_id, story, date) values (?, ?, ?);");
        addingStories.setInt(1, uID);
        addingStories.setString(2, profile.getStories().get(i) + " [" + LocalDate.now().toString() + "]");
        addingStories.setDate(3, Date.valueOf(LocalDate.now()));
        addingStories.executeUpdate();
      }
      if(profile.getProfessionalStatus()) {
        //updating email
        PreparedStatement email = connection.prepareStatement("Update professional_user set email = ? where username = ?;");
        email.setString(1, profile.getEmail());
        email.setString(2, profile.getUsername());
        email.executeUpdate();
        //updating phone number
        PreparedStatement phoneNumber = connection.prepareStatement("Update professional_user set phone_no = ? where username = ?;");
        phoneNumber.setString(1, profile.getPhoneNumber());
        phoneNumber.setString(2, profile.getUsername());
        phoneNumber.executeUpdate();
        //updating services
        PreparedStatement deletingServices = connection.prepareStatement("Delete from service where provider = ?;");
        deletingServices.setString(1, profile.getUsername());
        deletingServices.executeUpdate();
        for(int i = 0; i < profile.getServices().size(); i++) {
          PreparedStatement addingServices = connection.prepareStatement("Insert into service (service_title, provider, postcode, details, price) values (?, ?, ?, ?, ?);");
          addingServices.setString(1, profile.getServices().get(i).getTitle());
          addingServices.setString(2, profile.getServices().get(i).getContactInfo());
          addingServices.setInt(3, profile.getServices().get(i).getPostcodeInt());
          addingServices.setString(4, profile.getServices().get(i).getOnlyDetails());
          addingServices.setDouble(5, profile.getServices().get(i).getPrice());
          addingServices.executeUpdate();
        }
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalStateException("Something went wrong while updating User's profile");
    }
  }

  @Override public void addCommentOnTopic(String topicTitle, String message, String username)
  {
    try(Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement("Insert into comment(signature, text, date, thread) values (?, ?, ?, ?);");
      statement.setString(1, username);
      statement.setString(2, message);
      statement.setDate(3, Date.valueOf(LocalDate.now()));
      statement.setString(4, topicTitle);
      statement.executeUpdate();
    }
    catch (SQLException e) {
      throw new IllegalStateException("Something went wrong while adding comment to topic");
    }
  }

  @Override public void addUser(String username, String password)
  {
    try(Connection connection = getConnection()) {
      User user = new User(username, new Password(password));
      PreparedStatement statement = connection.prepareStatement("Insert into app_user (username, password, about_me) values (?, ?, ?);");
      statement.setString(1, username);
      statement.setString(2, password);
      statement.setString(3, user.getInfo());
      statement.executeUpdate();
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalStateException("Something went wrong while adding a user to the system");
    }
  }

  @Override public void subscribeToThread(String thread, String username)
  {
    try(Connection connection = getConnection()) {
      PreparedStatement statement1 = connection.prepareStatement("Select user_id from app_user where username = ?;");
      statement1.setString(1, username);
      ResultSet userID = statement1.executeQuery();
      int id = 0;
      if(userID.next())
      {
        id = userID.getInt("user_id");
      }

      PreparedStatement statement2 = connection.prepareStatement("Insert into subscriptions (user_id, title) values (?, ?);");
      statement2.setInt(1, id);
      statement2.setString(2, thread);
      statement2.executeUpdate();

    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalStateException("Something went wrong while adding user's subscription");
    }
  }

  @Override public void unsubscribe(String thread, String username)
  {
    try(Connection connection = getConnection()) {
      PreparedStatement statement1 = connection.prepareStatement("Select user_id from app_user where username = ?;");
      statement1.setString(1, username);
      ResultSet userID = statement1.executeQuery();
      int id = 0;
      if(userID.next())
      {
        id = userID.getInt("user_id");
      }

      PreparedStatement statement2 = connection.prepareStatement("Delete from subscriptions where user_id = ? and title = ?");
      statement2.setInt(1, id);
      statement2.setString(2, thread);
      statement2.executeUpdate();

    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalStateException("Something went wrong while removing user's subscription");
    }
  }

  @Override public void addFriend(String username, String friend)
  {
    try(Connection connection = getConnection()) {
      PreparedStatement user = connection.prepareStatement("Select user_id from app_user where username = ?;");
      user.setString(1, username);
      ResultSet userID = user.executeQuery();
      int uID = 0;
      if(userID.next())
      {
        uID = userID.getInt("user_id");
      }
      PreparedStatement friendName = connection.prepareStatement("Select user_id from app_user where username = ?;");
      friendName.setString(1, friend);
      ResultSet friendID = friendName.executeQuery();
      int fID = 0;
      if(friendID.next())
      {
        fID = friendID.getInt("user_id");
      }

      PreparedStatement addingFriend = connection.prepareStatement("Insert into friends (user_id, friend_id) values (?, ?);");
      addingFriend.setInt(1, uID);
      addingFriend.setInt(2, fID);
      addingFriend.executeUpdate();

    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new IllegalStateException("Something went wrong while adding user's friend");
    }
  }

  @Override public void addReport(String username, String report)
  {
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement reporter = connection.prepareStatement("Select user_id from app_user where username = ?;");
        reporter.setString(1, username);
        ResultSet reporterResult = reporter.executeQuery();
        int reporterID = 0;
        if(reporterResult.next()) {
          reporterID = reporterResult.getInt("user_id");
        }
        PreparedStatement reportee = connection.prepareStatement("Select user_id from app_user where username = ?;");
        reportee.setString(1, report);
        ResultSet reporteeResult = reportee.executeQuery();
        int reporteeID = 0;
        if(reporteeResult.next()) {
          reporteeID = reporteeResult.getInt("user_id");
        }
        PreparedStatement insertReport = connection.prepareStatement("Insert into reports (user_id, reported_user_id) values (?, ?);");
        insertReport.setInt(1, reporterID);
        insertReport.setInt(2, reporteeID);
        insertReport.executeUpdate();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
        throw new IllegalStateException("Something went wrong while reporting a user");
      }
    }
  }

  @Override public void unfriend(String username, String friend)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement user = connection.prepareStatement("Select user_id from app_user where username = ?;");
      user.setString(1, username);
      ResultSet userID = user.executeQuery();
      int uID = 0;
      if (userID.next())
      {
        uID = userID.getInt("user_id");
      }
      PreparedStatement friendName = connection.prepareStatement("Select user_id from app_user where username = ?;");
      friendName.setString(1, friend);
      ResultSet friendID = friendName.executeQuery();
      int fID = 0;
      if (friendID.next())
      {
        fID = friendID.getInt("user_id");
      }

      PreparedStatement addingFriend = connection.prepareStatement("Delete from friends where user_id = ? and friend_id = ?");
      addingFriend.setInt(1, uID);
      addingFriend.setInt(2, fID);
      addingFriend.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      throw new IllegalStateException("Something went wrong while removing user's friend");
    }
  }
}
