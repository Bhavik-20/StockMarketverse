package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Initialises all the private variables of the command class that are needed to execute the
 * feature which is to show the next panel in the gui if everything is correct.
 */
public class P2CreatePortfolio implements Command {
  private String portName;
  private GuiView view;

  private String uname;

  /**
   * Constructor to initialize the private variables of the class that will be used to execute the
   * feature.
   *
   * @param uname username
   * @param pName portfolio name
   * @param view  GuiView object to show messages
   */
  public P2CreatePortfolio(String uname, String pName, GuiView view) {
    this.uname = uname;
    this.portName = pName;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.checkFileNameFormat(portName)) {
      if (flexibleModel.mainModCheckPortfolioNameExists(uname, portName)) {
        view.showError("Error: Portfolio with this name already exist!");
      } else {
        if (flexibleModel.mainModCreatePortfolio(uname, portName)) {
          view.showMessage("Portfolio Created Successfully!");
          view.setRemovePanel("remove_all");
          view.setAddPanel("Page2");
        } else {
          view.showError("Error: Portfolio Creation Failed. Please try again!");
        }
      }
    } else {
      view.showError("Error: Invalid Portfolio Name. Should only contain "
              + "letters and digits");
    }
  }
}
