package Model.Home;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ArticleList class that contains all the Article objects in an ArrayList.
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 1.0 - 19.05.21
 */
public class ArticleList implements Serializable
{
  private ArrayList<Article> articles;
  private static final long serialVersionUID = 45L;

  /**
   * Zero argument constructor that initializes the ArrayList.
   */
  public ArticleList()
  {
    this.articles = new ArrayList<>();
  }
  
  /**
   * Retrieves Article object based on the argument provided, regardless of upper or lower casing.
   *
   * @param headline String representing the headline of an Article
   * @return Article object based on the headline that is provided as an argument
   */
  public Article getArticleByHeadline(String headline)
  {
    Article temp=new Article("Error404","Not found");
    for (Article t:articles)
    {
      if (t.getHeadline().equalsIgnoreCase(headline))
      {
        temp=t;
        break;
      }
    }
    return temp;
  }
  
  /**
   * Retrieves Article object based on the index provided
   *
   * @param index integer representing the index of the Article within the ArrayList
   * @return Article object based on the index that was provided as argument
   */
  public Article getArticleByIndex(int index)
  {
    return articles.get(index);
  }
  
  /**
   * Creates and adds a new Article object to the ArrayList.
   *
   * @param headline String representing the headline of Article
   * @param text String representing the content of Article
   */
  public void addArticle(String headline, String text)
  {
    articles.add(new Article(headline,text));
  }

  /**
   * Removes the specified Article object from the ArrayList.
   *
   * @param article Article object that should be removed
   */
  public void removeArticle(Article article)
  {
    articles.remove(article);
  }
}
