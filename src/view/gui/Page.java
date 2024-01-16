package view.gui;

import java.util.Map;

import controller.gui.ControllerGui;

/**
 * This interface defines all methods needed to interact with each page of the application. The page
 * here represents a panel which can be set or removed when neded.
 */
public interface Page {

  /**
   * This method is used to construct a page by adding a panel.
   */
  void setPanel();

  /**
   * This method is used to remove the panel from the page.
   */
  void removePanel();

  /**
   * This method is used to retrieve inputs from the user.
   *
   * @return a map of all the inputs from the user.
   */
  Map<String, String> getInputs();

  /**
   * This method is used to set the controller as action listeners for buttons of
   * the panels.
   *
   * @param features represents the ControllerGui
   */
  void addFeatures(ControllerGui features);

  /**
   * This method is used to reset the values of all input fields on this page.
   */
  void resetInputs();
}
