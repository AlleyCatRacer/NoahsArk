package Model.Home;

import java.io.Serializable;

/**
 * Article class that contains headline and text String type fields.
 * Implements Serializable and has a field for the serial version.
 *
 * @author Group 1
 * @version 1.0 - 19.05.21
 */
public class Article implements Serializable
{
  private String headline;
  private String text;
  private static final long serialVersionUID = 46L;

  /**
   * Constructor taking 2 arguments and initializing headline and text instance variables to the arguments provided.
   *
   * @param headline String of Article's headline
   * @param text String of Article's text
   * @throws IllegalArgumentException If one of the arguments provided to the constructor is null = "Please provide a headline and text content"
   */
  public Article(String headline, String text)
  {
    if(headline != null && text != null)
    {
      this.headline = headline;
      this.text = text;
    }
    else
      throw new IllegalArgumentException("Please provide a headline and text content");
  }

  /**
   * Getter for the headline.
   *
   * @return String that contains headline of the Article
   */
  public String getHeadline()
  {
    return headline;
  }
  
  /**
   * Getter for the text content.
   *
   * @return String that contains text of the Article
   */
  public String getText()
  {
    return text;
  }

  /**
   * Setter for the headline of the Article to the provided argument if it is not null.
   *
   * @param headline String representing the new headline
   */
  public void setHeadline(String headline)
  {
    if (headline != null)
    {
      this.headline = headline;
    }
  }

  /**
   * Setter for the text content of the Article to the provided argument if it is not null.
   *
   * @param text String representing the new text content
   */
  public void setText(String text)
  {
    if (text != null)
    {
      this.text = text;
    }
  }
}
