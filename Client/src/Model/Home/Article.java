package Model.Home;

import java.io.Serializable;

public class Article implements Serializable
{
  private String headline;
  private String text;
  private static final long serialVersionUID = 46L;
  
  public Article(String headline, String text)
  {
    this.headline = headline;
    this.text = text;
  }

  public String getHeadline()
  {
    return headline;
  }

  public String getText()
  {
    return text;
  }
}
