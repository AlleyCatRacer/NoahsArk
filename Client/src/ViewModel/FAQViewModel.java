package ViewModel;

import Model.ViewModelInterfaces.FAQModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FAQViewModel
{
  private FAQModel model;
  private ViewState viewState;
  private SimpleStringProperty usernameProperty;

  private SimpleStringProperty topic1;
  private SimpleStringProperty topic2;
  private SimpleStringProperty topic3;
  private SimpleStringProperty topic4;

  private SimpleStringProperty faqDes1;
  private SimpleStringProperty faqDes2;
  private SimpleStringProperty faqDes3;
  private SimpleStringProperty faqDes4;

  public FAQViewModel(FAQModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.usernameProperty = new SimpleStringProperty(viewState.getUsername());
    this.topic1 = new SimpleStringProperty("");
    this.topic2 = new SimpleStringProperty("");
    this.topic3 = new SimpleStringProperty("");
    this.topic4 = new SimpleStringProperty("");
    this.faqDes1 = new SimpleStringProperty("");
    this.faqDes2 = new SimpleStringProperty("");
    this.faqDes3 = new SimpleStringProperty("");
    this.faqDes4 = new SimpleStringProperty("");
    loadTopicsAndDescriptions();
  }

  public StringProperty usernameProperty()
  {
    return this.usernameProperty;
  }

  public void reset()
  {
    this.usernameProperty.set(viewState.getUsername());
    loadTopicsAndDescriptions();
  }

  public SimpleStringProperty topic1Property()
  {
    return topic1;
  }

  public SimpleStringProperty topic2Property()
  {
    return topic2;
  }

  public SimpleStringProperty topic3Property()
  {
    return topic3;
  }

  public SimpleStringProperty topic4Property()
  {
    return topic4;
  }

  public SimpleStringProperty faqDes1Property()
  {
    return faqDes1;
  }

  public SimpleStringProperty faqDes2Property()
  {
    return faqDes2;
  }

  public SimpleStringProperty faqDes3Property()
  {
    return faqDes3;
  }

  public SimpleStringProperty faqDes4Property()
  {
    return faqDes4;
  }

  public void setTopic(String topic)
  {
    viewState.setFaqTopic(topic);
  }


  private void loadTopicsAndDescriptions()
  {
    topic1.set(model.getFaqs().getTopicByIndex(0).getTitle());
    topic2.set(model.getFaqs().getTopicByIndex(1).getTitle());
    topic3.set(model.getFaqs().getTopicByIndex(2).getTitle());
    topic4.set(model.getFaqs().getTopicByIndex(3).getTitle());
    faqDes1.set(model.getFaqs().getTopicByIndex(0).getDescription());
    faqDes2.set(model.getFaqs().getTopicByIndex(1).getDescription());
    faqDes3.set(model.getFaqs().getTopicByIndex(2).getDescription());
    faqDes4.set(model.getFaqs().getTopicByIndex(3).getDescription());
  }
  
  public boolean professional()
  {
    reset();
    return model.isProfessional(viewState.getUsername());
  }
}
