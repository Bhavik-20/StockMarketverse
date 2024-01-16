package controller.gui.commands;

import model.MainModel;

/**
 * This is an interface for all the features related to the application. The interface is a part of
 * the command design pattern implementation and has just 1 method which will be implemented by
 * all the features.
 */
public interface Command {

  /**
   * A method to be implemented by all command classes of the controller. It takes in the MainModel
   * object and uses the object's methods to implement the feature.
   *
   * @param m the MainModel object from which methods are called to implement the feature.
   */
  void runCommand(MainModel m);
}
