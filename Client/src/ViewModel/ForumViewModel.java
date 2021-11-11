package ViewModel;

import Model.ViewModelInterfaces.ForumModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ForumViewModel
{
   private ForumModel           model;
   private ViewState            viewState;
   private SimpleStringProperty usernameProperty;
   private SimpleStringProperty thread1;
   private SimpleStringProperty thread2;
   private SimpleStringProperty thread3;
   private SimpleStringProperty thread4;
   private SimpleStringProperty threadDes1;
   private SimpleStringProperty threadDes2;
   private SimpleStringProperty threadDes3;
   private SimpleStringProperty threadDes4;
   
   public ForumViewModel(ForumModel model, ViewState viewState)
   {
      this.model            = model;
      this.viewState        = viewState;
      this.usernameProperty = new SimpleStringProperty(viewState.getUsername());
      
      this.thread1 = new SimpleStringProperty("");
      this.thread2 = new SimpleStringProperty("");
      this.thread3 = new SimpleStringProperty("");
      this.thread4 = new SimpleStringProperty("");
      
      this.threadDes1 = new SimpleStringProperty("");
      this.threadDes2 = new SimpleStringProperty("");
      this.threadDes3 = new SimpleStringProperty("");
      this.threadDes4 = new SimpleStringProperty("");
      
      loadThreadsAndDescriptions();
   }
   
   public void reset()
   {
      usernameProperty.set(viewState.getUsername());
      loadThreadsAndDescriptions();
   }
   
   public boolean professional()
   {
      reset();
      return model.isProfessional(viewState.getUsername());
   }
   
   public StringProperty usernameProperty()
   {
      return usernameProperty;
   }
   
   public SimpleStringProperty thread1Property()
   {
      return thread1;
   }
   
   public SimpleStringProperty thread2Property()
   {
      return thread2;
   }
   
   public SimpleStringProperty thread3Property()
   {
      return thread3;
   }
   
   public SimpleStringProperty thread4Property()
   {
      return thread4;
   }
   
   public SimpleStringProperty threadDes1Property()
   {
      return threadDes1;
   }
   
   public SimpleStringProperty threadDes2Property()
   {
      return threadDes2;
   }
   
   public SimpleStringProperty threadDes3Property()
   {
      return threadDes3;
   }
   
   public SimpleStringProperty threadDes4Property()
   {
      return threadDes4;
   }
   
   public void setTopic(String topic)
   {
      viewState.setForumTopic(topic);
   }
   
   private void loadThreadsAndDescriptions()
   {
      thread1.set(model.getForum().getTopicByIndex(0).getTitle());
      thread2.set(model.getForum().getTopicByIndex(1).getTitle());
      thread3.set(model.getForum().getTopicByIndex(2).getTitle());
      thread4.set(model.getForum().getTopicByIndex(3).getTitle());
      
      threadDes1.set(model.getForum().getTopicByIndex(0).getDescription());
      threadDes2.set(model.getForum().getTopicByIndex(1).getDescription());
      threadDes3.set(model.getForum().getTopicByIndex(2).getDescription());
      threadDes4.set(model.getForum().getTopicByIndex(3).getDescription());
   }
}
