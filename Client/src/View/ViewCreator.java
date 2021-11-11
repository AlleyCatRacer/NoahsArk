package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewCreator
{
   private Map<ViewKey, ViewController> views = new HashMap<>();
   
   public ViewCreator()
   {
   
   }
   
   public ViewController getViewController(ViewKey id)
   {
      ViewController v = views.get(id);
      if (v == null)
      {
         try
         {
            v = loadFromFXML(id.getFxmlFile());
            views.put(id, v);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
      v.reset();
      return v;
   }
   
   protected abstract void initViewController(ViewController v, Region root);
   
   private ViewController loadFromFXML(String fxmlFile) throws IOException
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      Region         root = loader.load();
      ViewController v    = loader.getController();
      initViewController(v, root);
      return v;
   }
}
