package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to execute the feature to get portfolio value on a certain date.
 */
public class P3GetPortfolioValue implements Command {

  private String uname;
  private String portfolioName;
  private GuiView view;
  private String date;

  /**
   * Constructor to initialize the variables needed to execute the feature.
   *
   * @param uname         username
   * @param portfolioName portfolio name
   * @param date          date on which value needs to be determined
   * @param view          GuiView object to display messages to the user
   */
  public P3GetPortfolioValue(String uname, String portfolioName, String date, GuiView view) {
    this.uname = uname;
    this.portfolioName = portfolioName;
    this.view = view;
    this.date = date;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      if (flexibleModel.checkDateFormat(date)) {
        if (flexibleModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("File Not Found")) {
          view.showError("Error: This Portfolio does not exist. Please enter a valid " +
                  "portfolio name.");
        } else if (flexibleModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("Incorrect Date Format")) {
          view.showError("Error: The date entered has an incorrect format.");
        } else if (flexibleModel.checkFutureDate(date)) {
          view.showError("Error: The entered date is in the future. "
                  + "Please enter a past date.");
        } else {
          String res = flexibleModel.mainModGetValueOnDate(uname, portfolioName, date);
          view.setAddPanel("Page3_GetPortfolioValue");
          view.showMessage("The value of the portfolio on " + date + " is $" + res);
          view.setRemovePanel("remove_all");
          view.setAddPanel("Page3");
        }
      } else {
        view.showError("Error: Date entered is an invalid date.");
      }
    } else {
      view.showError("Error: This Portfolio does not exist. Please enter a valid " +
              "portfolio name.");
    }
  }
}
