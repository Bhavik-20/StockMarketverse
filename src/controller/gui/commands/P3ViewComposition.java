package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to execute the feature for viewing composition of a portfolio on a date.
 */
public class P3ViewComposition implements Command {
  private String uname;
  private String portfolioName;
  private String date;
  private GuiView view;

  /**
   * Constructor to initialize variables needed to execute the feature.
   *
   * @param uname username
   * @param pName portfolio name
   * @param date  date on which composition is to be viewed
   * @param view  GuiView object to display messages
   */
  public P3ViewComposition(String uname, String pName, String date, GuiView view) {
    this.uname = uname;
    this.portfolioName = pName;
    this.date = date;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.checkDateFormat(date)) {
      if (!flexibleModel.checkFutureDate(date)) {
        viewFlexiContentsHelper(portfolioName, date, flexibleModel);
      } else {
        view.showError("Error: The entered date is in the future.");
      }
    } else {
      view.showError("Error: Date entered is an invalid date.");
    }
  }

  private void viewFlexiContentsHelper(String fname, String date, MainModel flexibleModel) {
    if (flexibleModel.mainModViewPortfolio(uname, fname, date).equals("File Not Found")) {
      view.showError("Error: This Portfolio does not exist. Please enter an existing" +
              " portfolio name.");
    } else if (flexibleModel.mainModViewPortfolio(uname, fname, date)
            .equals("Incorrect file format")) {
      flexibleModel.deleteFile(uname, fname);
      view.showError("Incorrect File format Uploaded!\nALERT: PORTFOLIO DELETED!");
    } else if (flexibleModel.mainModViewPortfolio(uname, fname, date)
            .equals("Error: Portfolio contains invalid ticker symbols")) {
      flexibleModel.deleteFile(uname, fname);
      view.showError("File contains Invalid Tickers!\nALERT: PORTFOLIO DELETED!");
    } else if (!flexibleModel.calcQuantityOfUploadedFile()) {
      flexibleModel.deleteFile(uname, fname);
      view.showError("Portfolio has illegal transactions! (Negative number of stocks" +
              " for a company)\nALERT: PORTFOLIO DELETED!");
    } else {
      view.setRemovePanel("remove_all");
      view.setAddPanel("Page3");
      String res = flexibleModel.mainModViewPortfolio(uname, fname, date);
      if (res.equals("Incorrect file format") || res.equals("You have no stocks in this portfolio")
              || res.equals("Portfolio contains invalid ticker symbols")) {
        view.showError(res);
      } else {
        view.showComposition(res);
      }
    }
  }
}
