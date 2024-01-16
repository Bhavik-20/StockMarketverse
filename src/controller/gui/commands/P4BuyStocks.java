package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to execute the feature of buying stock on a date using the MainModel methods.
 */
public class P4BuyStocks implements Command {
  private String portfolioName;
  private String uname;
  private String stockName;
  private String stockQty;
  private String purDate;
  private GuiView view;
  private String commFee;

  /**
   * Constructor to initialize the variables needed to execute the feature.
   *
   * @param pName    portfolio name
   * @param uname    username
   * @param ticker   ticker symbol of the stock to be bought
   * @param quantity quantity of stock to be bought
   * @param date     date on which purchase needs to be made
   * @param commFee  commission fee for the transaction
   * @param view     GuiView object to display messages
   */
  public P4BuyStocks(String pName, String uname, String ticker,
                     String quantity, String date, String commFee, GuiView view) {
    this.portfolioName = pName;
    this.uname = uname;
    this.stockName = ticker;
    this.stockQty = quantity;
    this.purDate = date;
    this.view = view;
    this.commFee = commFee;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      if (flexibleModel.checkDateFormat(purDate)) {
        if (flexibleModel
                .checkValidDateAndName(uname, portfolioName, purDate).equals("File Not Found")) {
          view.showError("Error: This Portfolio does not exist. " +
                  "Please enter an existing portfolio name.");
        } else if (flexibleModel.checkValidDateAndName(uname, portfolioName, purDate)
                .equals("Incorrect Date Format")) {
          view.showError("Error: The date entered is an invalid date.");
        } else if (flexibleModel.checkFutureDate(purDate)) {
          view.showError("Error: The entered date is in the future.");
        } else if (!flexibleModel.checkAmount(Double.parseDouble(commFee))) {
          view.showError("Error: Commission fee cannot be a negative value!");
        } else {
          if (flexibleModel.addStockToPortfolio(stockName.toUpperCase(), stockQty, purDate, commFee,
                  0)) {
            view.showMessage("Stock Added Successfully. \nSelect save changes to log your" +
                    "changes in portfolio, if you don't wish to buy/sell any other stock.");
            view.setRemovePanel("Page3_MakeTx");
            view.setAddPanel("Page3_MakeTx");
          } else {
            view.showError("Error: The ticker or quantity entered is invalid. " +
                    "\nPlease enter correct ticker "
                    + "symbol (eg: GOOG) and quantity as a whole number greater than 0 (eg: 10)"
                    + " for the stock.");
          }
        }
      } else {
        view.showError("Error: Date entered is an invalid date.");
      }
    } else {
      view.showError("Error: This Portfolio does not exist. Please enter an existing " +
              "portfolio name.");
    }
  }
}
