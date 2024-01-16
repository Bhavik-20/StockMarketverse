package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to execute the feature to apply dollar cost average strategy on a portfolio.
 */
public class P3Dca implements Command {

  private String uname;
  private String portfolioName;
  private String startDate;
  private String endDate;
  private String iVal;
  private String interval;
  private String commFee;
  private String tickerWts;
  private GuiView view;


  /**
   * Constructor to initialize all variables needed to execute the feature.
   *
   * @param uname     username
   * @param portName  portfolio name
   * @param startDate start date of strategy
   * @param endDate   end date of strategy
   * @param iVal      investment value for strategy
   * @param interval  interval at which periodic investment needs to be done
   * @param commFee   commission Fee for the transactions
   * @param tickerWts stock symbols along with their weightage
   * @param view      GuiView object to display messages
   */
  public P3Dca(String uname, String portName, String startDate, String endDate,
               String iVal, String interval, String commFee, String tickerWts, GuiView view) {
    this.uname = uname;
    this.portfolioName = portName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.iVal = iVal;
    this.interval = interval;
    this.commFee = commFee;
    this.tickerWts = tickerWts;
    this.view = view;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
      if (flexibleModel.checkDateFormat(startDate) && flexibleModel.checkDateFormat(endDate)) {
        if (flexibleModel.checkValidDateAndName(uname, portfolioName, startDate)
                .equals("File Not Found")) {
          view.showError("Error: This Portfolio does not exist. "
                  + "Please enter a valid portfolio " +
                  "name.");
        } else if (!flexibleModel.checkAmount(Double.parseDouble(commFee))) {
          view.showError("Error: Commission Fee cannot be negative.");
        } else if (!flexibleModel.checkAmount(Double.parseDouble(iVal))) {
          view.showError("Error: Investment Amount cannot be negative.");
        } else {
          if (flexibleModel.checkInvestWeightFormat(tickerWts)) {
            String res = flexibleModel.applyDCAOnPortfolio(uname, portfolioName, startDate, endDate,
                    iVal, interval, commFee, tickerWts);
            if (!res.equals("true")) {
              view.showError(res);
            } else {

              flexibleModel.mainModConvertAllStocksToStockObj();
              if (flexibleModel.mainModCreatePortfolio(uname, portfolioName)) {
                if (flexibleModel.loadPortfolio(uname, portfolioName)) {
                  view.showMessage("Strategy Applied and Saved Successfully!");
                  view.setRemovePanel("Page3_GetDCAInputs");
                } else {
                  view.showError("An error occurred in loading the portfolio!");
                }
              } else {
                view.showError("An error occurred in saving the strategy");
              }
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
      view.showError("Error: This Portfolio does not exist. Please enter an existing" +
              " portfolio name.");
    }
  }
}
