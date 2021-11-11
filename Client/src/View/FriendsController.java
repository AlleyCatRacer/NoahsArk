package View;

import ViewModel.FriendsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class FriendsController extends ViewController
{
   @FXML ListView<String> friendsList;
   
   FriendsViewModel viewModel;
   
   public FriendsController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel=getFactory().getFriendsViewModel();
      viewModel.reset();
      friendsList.setItems(viewModel.getFriendsList());
   }
   
   @FXML private void friendDetails(MouseEvent event)
   {
      if(event.getClickCount() == 2)
      {
         viewModel.openProfile(friendsList.getSelectionModel().getSelectedItem());
         
         if (viewModel.isProfessional())
         {
            getViewHandler().openView(ViewKey.PROFESSIONAL_PROFILE);
         }
         else
         {
            getViewHandler().openView(ViewKey.PROFILE);
         }
         getViewHandler().closeSubView();
      }
   }
   
   @FXML private void closeClicked()
   {
      getViewHandler().closeSubView();
      if (viewModel.professional())
      {
         getViewHandler().openView(ViewKey.EDIT_PROFESSIONAL_PROFILE);
      }
      else
      {
         getViewHandler().openView(ViewKey.EDIT_PROFILE);
      }
   }
}
