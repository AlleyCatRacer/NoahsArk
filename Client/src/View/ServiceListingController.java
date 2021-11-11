package View;

import ViewModel.ServiceListingViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServiceListingController extends ViewController
{
   @FXML private TextField titleField;
   @FXML private TextField postcodeField;
   @FXML private TextField priceField;
   @FXML private TextArea  detailsTextArea;
   @FXML private Label     errorLabel;
   @FXML private Button    deleteButton;
   
   private ServiceListingViewModel viewModel;
   
   public ServiceListingController()
   {
      //
   }
   
   @Override protected void initBindings()
   {
      viewModel = getFactory().getServiceListingModel();
      titleField.textProperty().bindBidirectional(viewModel.titleProperty());
      postcodeField.textProperty().bindBidirectional(viewModel.postcodeProperty());
      priceField.textProperty().bindBidirectional(viewModel.priceProperty());
      detailsTextArea.textProperty().bindBidirectional(viewModel.detailsProperty());
      errorLabel.textProperty().bind(viewModel.errorProperty());
      reset();
   }
   
   public void reset()
   {
      viewModel.reset();
      if (viewModel.create())
      {
         deleteButton.setDisable(true);
         viewModel.clear();
      }
      else
      {
         deleteButton.setDisable(false);
      }
   }
   
   @FXML private void submitClicked()
   {
      if (!viewModel.create())
      {
         viewModel.updateService();
      }
      else
      {
         viewModel.addService();
      }
      reset();
      getViewHandler().closeSubView();
      getViewHandler().openView(ViewKey.EDIT_PROFESSIONAL_PROFILE);
   }
   
   @FXML private void cancelClicked()
   {
      viewModel.clear();
      reset();
      getViewHandler().openView(ViewKey.EDIT_PROFESSIONAL_PROFILE);
      getViewHandler().closeSubView();
   }
   
   @FXML private void deleteClicked()
   {
      reset();
      viewModel.deleteService();
      getViewHandler().openView(ViewKey.EDIT_PROFESSIONAL_PROFILE);
      getViewHandler().closeSubView();
   }
}
