package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * A command class that is responsible to execute the cost basis feature by calling the methods from
 * the MainModel.
 */
public class P3CostBasis implements Command {
  private final String uname;
  private final String portfolioName;
  private final String date;
  private GuiView view;

  /**
   * Constructor of the command class to initialize variables needed to execute the feature.
   *
   * @param uname         username
   * @param portfolioName portfolio name
   * @param date          date on which cost basis needs to be determined
   * @param view          GuiView object to show messages to the user
   */
  public P3CostBasis(String uname, String portfolioName, String date, GuiView view) {
    this.uname = uname;
    this.portfolioName = portfolioName;
    this.date = date;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      if (flexibleModel.checkDateFormat(date)) {
        if (flexibleModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("File Not Found")) {
          view.showError("Error: This Portfolio does not exist. Please enter an existing" +
                  " portfolio name.");
        } else if (flexibleModel.checkValidDateAndName(uname, portfolioName, date)
                .equals("Incorrect Date Format")) {
          view.showError("Error: The date entered has an incorrect format.");
        } else {
          String costBasis = flexibleModel.calculateCostBasis(portfolioName, date);
          view.showMessage("Cost basis: " + costBasis);
          view.setRemovePanel("remove_all");
          view.setAddPanel("Page3");
        }
      } else {
        view.showError("Error: Date entered is an invalid date.");
      }
    } else {
      view.showError("Error: This Portfolio does not exist. Please enter an existing" +
              " portfolio name.");
    }
  }
}
