package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to execute the feature of selling stock on a date using the MainModel methods.
 */
public class P4SellStocks implements Command {
  private String portfolioName;
  private String uname;
  private String stockName;
  private String stockQty;
  private String purDate;
  private String commFee;
  private GuiView view;

  /**
   * Constructor to initialize the variables needed to execute the feature.
   *
   * @param pName    portfolio name
   * @param uname    username
   * @param ticker   ticker symbol of the stock to be sold
   * @param quantity quantity of stock to be sold
   * @param date     date on which purchase needs to be made
   * @param commFee  commission fee for the transaction
   * @param view     GuiView object to display messages
   */
  public P4SellStocks(String pName, String uname, String ticker, String quantity,
                      String date, String commFee, GuiView view) {
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
        if (flexibleModel.checkValidDateAndName(uname, portfolioName, purDate)
                .equals("File Not Found")) {
          view.showError("Error: This Portfolio does not exist. Please enter an existing"
                  + " portfolio name.");
        } else if (flexibleModel.checkFutureDate(purDate)) {
          view.showError("Error: The entered date is in the future.");
        } else if (!flexibleModel.checkAmount(Double.parseDouble(commFee))) {
          view.showError("Error: Commission fee cannot be a negative value!");
        } else {
          String res = flexibleModel.removeStockFromPortfolio(stockName.toUpperCase(),
                  stockQty, purDate, commFee);
          if (res.equals("true")) {
            view.showMessage("Stock has been sold successfully! "
                    + "\nSelect save changes to log your "
                    + "changes in portfolio, if you don't wish to buy/sell any other stock.");
            view.setRemovePanel("Page3_MakeTx");
            view.setAddPanel("Page3_MakeTx");
          } else if (res.equals("negative")) {
            // negative quantity entered
            view.showError("Error: Negative Quantity of Stocks Entered");
            //            throw new Exception("Negative Quantity of Stocks Entered");
          } else if (res.equals("fractional")) {
            // fractional quantity entered
            view.showError("Error: Fractional Quantity of Stocks Entered");
            //            throw new Exception("Fractional Quantity of Stocks Entered");
          } else if (res.equals("NA")) {
            // These many stocks not available to sell for the given date
            view.showError("Error: You do not have stocks for this company.");
          } else if (res.equals("invalidTicker")) {
            // Ticker is invalid
            view.showError("Invalid Ticker Entered");
          } else if (res.equals("NP")) {
            view.showError("Error: Cannot perform transaction! "
                    + "(Insufficient stocks of the company)");
          }
        }
      } else {
        view.showError("Error: Date entered is an invalid date.");
      }
    } else {
      view.showError("Error: This Portfolio does not exist. "
              + "Please enter an existing portfolio name.");
    }
  }
}
