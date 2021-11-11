package Model.ViewModelInterfaces;

public interface CreateProfileModel extends UserModel
{
  void join(String username, String password);
}
