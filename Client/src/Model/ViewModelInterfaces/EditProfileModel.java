package Model.ViewModelInterfaces;

import Model.Profile;

public interface EditProfileModel extends UserModel
{
  void updateProfile(Profile profile) throws IllegalStateException;

  Profile getProfileInfo(String username);

  void professionalApplication(String username);
}
