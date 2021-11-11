package View;

import ViewModel.ViewModelFactory;
import javafx.scene.layout.Region;

public abstract class ViewController
{
   private ViewHandler      viewHandler;
   private ViewModelFactory factory;
   private Region           root;
   
   public ViewController()
   {
      //
   }
   
   protected abstract void initBindings();
   
   public void init(ViewHandler viewHandler, ViewModelFactory factory, Region root)
   {
      this.viewHandler = viewHandler;
      this.factory     = factory;
      this.root        = root;
      initBindings();
   }
   
   public void reset()
   {
   
   }
   
   public Region getRoot()
   {
      return root;
   }
   
   public ViewModelFactory getFactory()
   {
      return factory;
   }
   
   public ViewHandler getViewHandler()
   {
      return viewHandler;
   }
}