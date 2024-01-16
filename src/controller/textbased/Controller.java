package controller.textbased;

/**
 * This interface defines a controller for the Stock-Marketverse application. It consists of a go
 * method which takes input and tells model and view what to do. It delegates all the work to model
 * and the text-based view.
 */
public interface Controller {

  /**
   * This is the primary method of the controller which takes input from the user and delegates the
   * tasks to model and view.
   */
  void goController();
}
