package Model;


import Model.ViewModelInterfaces.*;
import utility.observer.listener.LocalListener;

public interface ClientModel extends
    LocalListener<String, String>,
    CreateProfileModel,
    EditProfileModel,
    FAQModel,
    ForumModel,
    FriendsModel,
    HomeModel,
    LoginModel,
    ProfileModel,
    SearchModel
{
   void loadFromServer();
}
