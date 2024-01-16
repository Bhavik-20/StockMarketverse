package controller.gui.commands;

import model.MainModel;
import view.gui.GuiView;

/**
 * This command class is responsible for creating a portfolio with Dollar cost average in a single
 * operation.
 */
public class P2CreateDcaPortfolio implements Command {

  private String uname;
  private String portfolioName;
  private String sDate;
  private String eDate;
  private String freq;
  private String investAmt;
  private String commFee;
  private String tickerWts;
  private GuiView view;

  /**
   * Initialises all the private variables of the command class that are needed to execute the
   * feature.
   *
   * @param uname         username
   * @param portfolioName portfolio name
   * @param sDate         start date
   * @param eDate         end date
   * @param freq          frequency
   * @param investAmt     investment amount
   * @param commFee       commission fee
   * @param tickerWts     stocks to be invested along with their tickers ("GOOG 50, AAPL 50")
   * @param view          GuiView object to display the messages
   */
  public P2CreateDcaPortfolio(String uname, String portfolioName,
                              String sDate, String eDate, String freq,
                              String investAmt, String commFee, String tickerWts,
                              GuiView view) {
    this.uname = uname;
    this.portfolioName = portfolioName;
    this.sDate = sDate;
    this.eDate = eDate;
    this.freq = freq;
    this.investAmt = investAmt;
    this.commFee = commFee;
    this.tickerWts = tickerWts;
    this.view = view;

  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    if (flexibleModel.checkFileNameFormat(portfolioName)) {
      if (flexibleModel.mainModCheckPortfolioNameExists(uname, portfolioName)) {
        view.showError("Error: Portfolio with this name already exist!");
      } else {
        if (flexibleModel.mainModCreatePortfolio(uname, portfolioName)) {
          if (flexibleModel.checkDateFormat(sDate) && flexibleModel.checkDateFormat(eDate)) {
            if (flexibleModel.checkValidDateAndName(uname, portfolioName, sDate)
                    .equals("File Not Found")) {
              view.showError("Error: This Portfolio does not exist. "
                      + "Please enter a valid portfolio " +
                      "name.");
            } else if (!flexibleModel.checkAmount(Double.parseDouble(commFee))) {
              view.showError("Error: Commission Fee cannot be negative.");
            } else if (!flexibleModel.checkAmount(Double.parseDouble(investAmt))) {
              view.showError("Error: Investment Amount cannot be negative.");
            } else {
              if (flexibleModel.checkInvestWeightFormat(tickerWts)) {
                if (flexibleModel.loadPortfolio(uname, portfolioName)) {

                  String res = flexibleModel.applyDCAOnPortfolio(uname, portfolioName, sDate, eDate,
                          investAmt, freq, commFee, tickerWts);
                  if (!res.equals("true")) {
                    flexibleModel.deleteFile(uname, portfolioName);
                    view.showError(res);
                  } else {
                    flexibleModel.mainModConvertAllStocksToStockObj();
                    if (flexibleModel.mainModCreatePortfolio(uname, portfolioName)) {
                      if (flexibleModel.loadPortfolio(uname, portfolioName)) {
                        view.showMessage("Strategy Applied and Saved Successfully!");
                        view.setRemovePanel("remove_all");
                        view.setAddPanel("Page2");
                      } else {
                        view.showError("An error occurred in loading the portfolio!");
                      }
                    } else {
                      flexibleModel.deleteFile(uname, portfolioName);
                      view.showError("An error occurred in saving the strategy");
                    }
                  }
                } else {
                  view.showError("An error occurred in loading the portfolio!");
                }
              } else {
                view.showMessage("Error: Weights entered in incorrect format!\n" +
                        "Format expected: TICKER1 QUANT1, TICKER2 QUANT2");
              }
            }
          } else {
            flexibleModel.deleteFile(uname, portfolioName);
            view.showError("Error: Date entered is an invalid date.");
          }
        } else {
          flexibleModel.deleteFile(uname, portfolioName);
          view.showError("Error: Portfolio Creation Failed. Please try again!");
        }
      }
    } else {
      view.showError("Error: Invalid Portfolio Name. Should only contain "
              + "letters and digits");
    }
  }
}
