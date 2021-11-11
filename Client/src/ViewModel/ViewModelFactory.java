package ViewModel;

import Model.ClientModel;

public class ViewModelFactory
{
   private LoginViewModel                   loginModel;
   private CreateProfileViewModel           createProfileModel;
   private HomeViewModel                    homeModel;
   private SearchViewModel                  searchModel;
   private EditProfileViewModel             editModel;
   private FriendsViewModel                 friendsModel;
   private FAQViewModel                     FAQModel;
   private FAQTopicViewModel                FAQTopicModel;
   private ForumViewModel                   forumModel;
   private ForumDiscussionViewModel         forumDiscussionModel;
   private ProfileViewModel                 profileModel;
   private ProfessionalProfileViewModel     professionalProfileModel;
   private EditProfessionalProfileViewModel editProfessionalProfileModel;
   private ServiceListingViewModel          serviceListingModel;
   
   public ViewModelFactory(ClientModel model)
   {
      ViewState viewState = new ViewState();
      loginModel                   = new LoginViewModel(model, viewState);
      homeModel                    = new HomeViewModel(model, viewState);
      FAQModel                     = new FAQViewModel(model, viewState);
      FAQTopicModel                = new FAQTopicViewModel(model, viewState);
      forumModel                   = new ForumViewModel(model, viewState);
      forumDiscussionModel         = new ForumDiscussionViewModel(model, viewState);
      createProfileModel           = new CreateProfileViewModel(model, viewState);
      searchModel                  = new SearchViewModel(model, viewState);
      editModel                    = new EditProfileViewModel(model, viewState);
      profileModel                 = new ProfileViewModel(model, viewState);
      friendsModel                 = new FriendsViewModel(model, viewState);
      editProfessionalProfileModel = new EditProfessionalProfileViewModel(model, viewState);
      serviceListingModel           = new ServiceListingViewModel(model, viewState);
      professionalProfileModel     = new ProfessionalProfileViewModel(model, viewState);
   }
   
   public LoginViewModel getLoginViewModel()
   {
      return loginModel;
   }
   
   public CreateProfileViewModel getCreateProfileViewModel()
   {
      return createProfileModel;
   }
   
   public HomeViewModel getHomeViewModel()
   {
      return homeModel;
   }
   
   public SearchViewModel getSearchViewModel()
   {
      return searchModel;
   }
   
   public EditProfileViewModel getEditViewModel()
   {
      return editModel;
   }
   
   public EditProfessionalProfileViewModel getEditProfessionalProfileViewModel()
   {
      return editProfessionalProfileModel;
   }
   
   public FriendsViewModel getFriendsViewModel()
   {
      return friendsModel;
   }
   
   public FAQViewModel getFAQViewModel()
   {
      return FAQModel;
   }
   
   public FAQTopicViewModel getFAQTopicViewModel()
   {
      return FAQTopicModel;
   }
   
   public ForumViewModel getForumModel()
   {
      return forumModel;
   }
   
   public ForumDiscussionViewModel getForumDiscussionModel()
   {
      return forumDiscussionModel;
   }
   
   public ProfileViewModel getProfileViewModel()
   {
      return profileModel;
   }
   
   public ServiceListingViewModel getServiceListingModel()
   {
      return serviceListingModel;
   }
   
   public ProfessionalProfileViewModel getProfessionalProfileModel()
   {
      return professionalProfileModel;
   }
}
