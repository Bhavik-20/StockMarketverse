package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to execute the feature of investing fixed amount to a portfolio using the
 * MainModel methods.
 */
public class P3IFABuy implements Command {

  private String uname;
  private String portfolioName;
  private String stockW;
  private String investValue;
  private GuiView view;
  private String date;
  private String commFee;

  /**
   * Constructor to initialize variables needed to execute the feature.
   *
   * @param uname    username
   * @param portName portfolio name
   * @param maps     string containing ticker symbol and its weightage
   * @param iValue   investment value for the operation
   * @param date     date on which investment needs to be made
   * @param commFee  commission fee to be considered for the investment
   * @param view     GuiView object to display messages to the user
   */
  public P3IFABuy(String uname, String portName, String maps, String iValue,
                  String date, String commFee, GuiView view) {
    this.investValue = iValue;
    this.stockW = maps;

    this.view = view;
    this.date = date;
    this.uname = uname;
    this.portfolioName = portName;
    this.commFee = commFee;
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
        } else if (!flexibleModel.checkAmount(Double.parseDouble(commFee))) {
          view.showError("Error: Commission Fee cannot be negative.");
        } else if (!flexibleModel.checkAmount(Double.parseDouble(investValue))) {
          view.showError("Error: Investment Amount cannot be negative.");
        } else {
          if (flexibleModel.checkInvestWeightFormat(stockW)) {
            String res = flexibleModel.investFixedAmt(uname, portfolioName, stockW, date, commFee,
                    investValue);
            if (res.equals("true")) {
              flexibleModel.mainModConvertAllStocksToStockObj();
              if (flexibleModel.mainModCreatePortfolio(uname, portfolioName)) {
                if (flexibleModel.loadPortfolio(uname, portfolioName)) {
                  view.showMessage("Successfully Invested! The portfolio has been updated.");
                  view.setRemovePanel("remove_all");
                  view.setAddPanel("Page3");
                } else {
                  view.showError("An error occurred in loading the file.");
                }
              } else {
                view.showError("An error occurred in saving the stocks to portfolio!");
              }
            } else {
              view.showError(res);
            }
          } else {
            view.showMessage("Error: Weights entered in incorrect format!\n" +
                    "Format expected: TICKER1 QUANT1, TICKER2 QUANT2");
          }
        }
      } else {
        view.showError("Error: Date entered is an invalid date.");
      }
    } else {
      view.showError("Error: This Portfolio does not exist. "
              + "Please enter a valid portfolio name.");
    }
  }
}

