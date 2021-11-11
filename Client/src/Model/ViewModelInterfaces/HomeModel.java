package Model.ViewModelInterfaces;

import Model.Home.ArticleList;

public interface HomeModel extends UserModel
{
  ArticleList getArticles();
}
