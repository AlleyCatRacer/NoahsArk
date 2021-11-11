package View;

import ViewModel.ViewModelFactory;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler extends ViewCreator
{
   private Stage            stage;
   private Scene            scene;
   private Stage subStage;
   private Scene subScene;
   private ViewModelFactory factory;
   
   public ViewHandler(ViewModelFactory factory)
   {
      this.scene   = new Scene(new Region());
      this.subStage = new Stage();
      this.subScene = new Scene(new Region());
      this.factory = factory;
   }
   
   public void start(Stage stage)
   {
      this.stage = stage;
      openView(ViewKey.LOGIN);
   }
   
   public void openView(ViewKey id)
   {
      ViewController open = getViewController(id);
      Region         root = open.getRoot();
      initViewController(open, root);
      scene.setRoot(root);
      String title = "";
      if (root.getUserData() != null)
      {
         title += root.getUserData();
      }
      open.reset();
      stage.setTitle(title);
      stage.setScene(scene);
      stage.setWidth(root.getPrefWidth());
      stage.setHeight(root.getPrefHeight());
      stage.show();
   }
   
   public void openSubView(ViewKey id)
   {
      ViewController open = getViewController(id);
      Region         root = open.getRoot();
      initViewController(open, root);
      String title = "";
      if (root.getUserData() != null)
      {
         title += root.getUserData();
      }
      open.reset();
      subScene.setRoot(root);
      subStage.setTitle(title);
      subStage.setScene(subScene);
      subStage.setWidth(root.getPrefWidth());
      subStage.setHeight(root.getPrefHeight());
      subStage.show();
   }
   
   public void close()
   {
      closeSubView();
      stage.close();
   }
   
   public void closeSubView()
   {
      subStage.close();
   }
   
   @Override protected void initViewController(ViewController v, Region root)
   {
      v.init(this, factory, root);
   }
}
