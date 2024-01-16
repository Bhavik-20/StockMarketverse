package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * This command class is responsible for loading the next panel that will show features related
 * to an existing portfolio.
 */
public class P2ExistingPortfolio implements Command {

  private String portfolioName;
  private GuiView view;
  private String uname;

  /**
   * Initializes the private variables of the command class that are needed to implement the
   * feature.
   *
   * @param uname username
   * @param pName portfolioname
   * @param view  GuiView object to show messages on the GUI
   */
  public P2ExistingPortfolio(String uname, String pName, GuiView view) {
    this.uname = uname;
    this.portfolioName = pName;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      if (flexibleModel.loadPortfolio(uname, portfolioName)) {
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3");
      } else {
        view.showError("Error: Could not load portfolio....Please try again!");
      }
    } else {
      view.showError("Error: This Portfolio does not exist. Please enter an existing" +
              " portfolio name.");
    }
  }
}
