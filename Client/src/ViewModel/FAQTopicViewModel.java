package ViewModel;

import Model.ViewModelInterfaces.FAQModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FAQTopicViewModel
{
  private FAQModel model;
  private ViewState viewState;
  private SimpleStringProperty usernameProperty;

  private SimpleStringProperty questionTitle1;
  private SimpleStringProperty questionTitle2;
  private SimpleStringProperty questionTitle3;

  private SimpleStringProperty answer1;
  private SimpleStringProperty answer2;
  private SimpleStringProperty answer3;

  private SimpleStringProperty topicLabel;

  public FAQTopicViewModel(FAQModel model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.usernameProperty = new SimpleStringProperty();
    this.topicLabel = new SimpleStringProperty();
    this.questionTitle1 = new SimpleStringProperty("");
    this.questionTitle2 = new SimpleStringProperty("");
    this.questionTitle3 = new SimpleStringProperty("");
    this.answer1 = new SimpleStringProperty("");
    this.answer2 = new SimpleStringProperty("");
    this.answer3 = new SimpleStringProperty("");
  }

  public void reset()
  {
    usernameProperty.set(viewState.getUsername());
    topicLabel.set(viewState.getFaqTopic());
  }

  public StringProperty usernameProperty()
  {
    return usernameProperty;
  }

  public StringProperty topicProperty()
  {
    this.topicLabel.set(viewState.getFaqTopic());
    return topicLabel;
  }

    public StringProperty questionTitle1Property(){return questionTitle1;}
    public StringProperty questionTitle2Property(){return questionTitle2;}
    public StringProperty questionTitle3Property(){return questionTitle3;}
    public StringProperty answer1Property(){return answer1;}
    public StringProperty answer2Property(){return answer2;}
    public StringProperty answer3Property(){return answer3;}


  public void loadQuestionsAndAnswers()
  {
    questionTitle1.set(model.getFaqs().getTopicByName(viewState.getFaqTopic()).getQuestionByIndex(0).getQuestionString());
    questionTitle2.set(model.getFaqs().getTopicByName(viewState.getFaqTopic()).getQuestionByIndex(1).getQuestionString());
    questionTitle3.set(model.getFaqs().getTopicByName(viewState.getFaqTopic()).getQuestionByIndex(2).getQuestionString());
    answer1.set(model.getFaqs().getTopicByName(viewState.getFaqTopic()).getQuestionByIndex(0).getAnswer());
    answer2.set(model.getFaqs().getTopicByName(viewState.getFaqTopic()).getQuestionByIndex(1).getAnswer());
    answer3.set(model.getFaqs().getTopicByName(viewState.getFaqTopic()).getQuestionByIndex(2).getAnswer());

    topicLabel.set(viewState.getFaqTopic());
  }
  
  public boolean professional()
  {
    reset();
    return model.isProfessional(viewState.getUsername());
  }
}
