package Launch;

import Mediator.Server;
import Model.ServerModel;
import Model.ServerModelManager;

public class ServerMain
{

  public static void main(String[] args) {

    ServerModel model = new ServerModelManager();
    Server server = new Server(model);
  }
}
