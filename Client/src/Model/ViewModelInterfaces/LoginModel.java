package Model.ViewModelInterfaces;

public interface LoginModel extends UserModel
{
  void login(String username, String password);
}
