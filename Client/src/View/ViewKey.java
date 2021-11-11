package View;

public enum ViewKey
{
   LOGIN("LoginView.fxml"),
   CREATE_PROFILE("CreateProfileView.fxml"),
   HOME("HomeView.fxml"),
   FAQ("FAQView.fxml"),
   FAQ_TOPIC("FAQTopicView.fxml"),
   FORUM("ForumView.fxml"),
   FORUM_TOPIC("ForumDiscussionView.fxml"),
   SEARCH("SearchView.fxml"),
   EDIT_PROFILE("EditProfileView.fxml"),
   FRIENDS("FriendsView.fxml"),
   EDIT_PROFESSIONAL_PROFILE("EditProfessionalProfileView.fxml"),
   PROFILE("ProfileView.fxml"),
   PROFESSIONAL_PROFILE("ProfessionalProfileView.fxml"),
   EDIT_LISTING("ServiceListingView.fxml");
   
   private final String fxmlFile;
   
   ViewKey(String fxmlFile)
   {
      this.fxmlFile = fxmlFile;
   }
   
   public String getFxmlFile()
   {
      return fxmlFile;
   }
}