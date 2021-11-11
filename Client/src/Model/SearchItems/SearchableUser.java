package Model.SearchItems;

public class SearchableUser implements SearchItem

{
  private String username;

  public SearchableUser(String username)
  {
    this.username = username;
  }

  @Override public String getTitle()
  {
    return username;
  }

  @Override public String getDetails()
  {
    return "";
  }

  @Override public String getContactInfo()
  {
    return "";
  }

  @Override public String getPostcode()
  {
    return "";
  }
}
