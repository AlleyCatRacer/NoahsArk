package Model.Home;

import java.io.Serializable;
import java.util.ArrayList;

public class ArticleList implements Serializable
{
  private ArrayList<Article> articles;
  private static final long serialVersionUID = 45L;
  
  public ArticleList()
  {
    this.articles = new ArrayList<>();
  }

  public Article getArticleByIndex(int index)
  {
    return articles.get(index);
  }
}
