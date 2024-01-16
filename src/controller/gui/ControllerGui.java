package controller.gui;

/**
 * This interface defines a controller for the Stock-Marketverse application. It consists of a go
 * method which takes input and tells model and view what to do. It delegates all the work to model
 * and the GUI-based view.
 */
public interface ControllerGui {

  /**
   * This is the primary method of the controller which takes input from the user and executes
   * functions of Stock-Marketverse's GUI-based application based on the inputs received from
   * the user.
   */
  void goGui(String ch);
}
