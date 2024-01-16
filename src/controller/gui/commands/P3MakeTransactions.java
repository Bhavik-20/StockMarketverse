package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to load the next panel that will take in inputs for making a transaction in the
 * portfolio.
 */
public class P3MakeTransactions implements Command {
  private GuiView view;
  private String uname;
  private String portfolioName;

  /**
   * Constructor to initialize the variables needed to display the next set of panel.
   *
   * @param uname         username
   * @param portfolioName portfolio name
   * @param view          GuiView object to display messages
   */
  public P3MakeTransactions(String uname, String portfolioName, GuiView view) {
    this.uname = uname;
    this.portfolioName = portfolioName;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.loadPortfolio(uname, portfolioName)) {
      view.setRemovePanel("remove_all");
      view.setAddPanel("Page3");
      view.setAddPanel("Page3_MakeTx");
    } else {
      view.showError("Error: Could Not Load Portfolio...Please Try Again later!");
    }
  }
}
