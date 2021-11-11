package Launch;

import Model.ClientModel;
import Model.ClientModelManager;
import View.ViewHandler;
import ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyApplication extends Application
{
   private ClientModel model;
   private ViewHandler view;
   
   @Override public void start(Stage primaryStage)
   {
      try
      {
         this.model = new ClientModelManager();
         ViewModelFactory viewModelFactory = new ViewModelFactory(model);
         this.view = new ViewHandler(viewModelFactory);
         
         view.start(primaryStage);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
//   @Override public void stop() throws Exception
//   {
//      model.logout(model.getUsername());
//      model.disconnect();
//      view.close();
//   }
}

