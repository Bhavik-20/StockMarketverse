package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to execute the feature of saving the set of transactions to the portfolio.
 */
public class P4SaveTransactions implements Command {

  private String uname;
  private String portfolioName;
  private GuiView view;

  /**
   * Constructor to initialize the variables needed to execute the feature.
   *
   * @param uname         username
   * @param portfolioName portfolio name
   * @param view          GuiView object to display messages
   */
  public P4SaveTransactions(String uname, String portfolioName, GuiView view) {
    this.uname = uname;
    this.portfolioName = portfolioName;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    flexibleModel.mainModConvertAllStocksToStockObj();
    if (flexibleModel.mainModCreatePortfolio(uname, portfolioName)) {
      if (flexibleModel.loadPortfolio(uname, portfolioName)) {
        view.showMessage("\nSUCCESS: "
                + "The list of transaction/s have been logged in the portfolio " +
                "successfully!\n");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3");
      }
    } else {
      view.showError("Error: Could not save the transactions. Please try again.");
    }
  }
}
