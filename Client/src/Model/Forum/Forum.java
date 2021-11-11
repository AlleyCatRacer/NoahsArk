package Model.Forum;

import java.io.Serializable;
import java.util.ArrayList;

public class Forum  implements Serializable
{
  private ArrayList<Thread> threads;
  private static final long serialVersionUID = 42L;

  public Forum()
  {
    this.threads = new ArrayList<>();
  }

  public Thread getTopicByIndex(int index)
  {
    if (index <= threads.size() - 1)
      return threads.get(index);
    else
      return new Thread("error", "topic not found");
  }
  
  public int getNumberOfTopics()
  {
    return threads.size();
  }
}
