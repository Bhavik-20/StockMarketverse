package view.gui;

import java.util.List;
import java.util.Map;

import controller.gui.ControllerGui;

/**
 * This interface defines all the functions of the view. It defines all the methods to create the
 * Graphical User Interface of the Stock-Marketverse application.
 */
public interface GuiView {

  /**
   * This method is used to construct a page by setting different panels based on the case passed.
   *
   * @param ch signifies the choice of panels that needs to be added to the current window.
   */
  void setAddPanel(String ch);

  /**
   * This method is used to remove unwanted panels from the window.
   *
   * @param ch signifies the choice of panels that need to be removed from the current window.
   */
  void setRemovePanel(String ch);

  /**
   * This method delegates the task of setting the controller as action listeners for buttons of
   * different panels.
   *
   * @param conrollerGui signifies the controller that works with the GuiView.
   */
  void addFeatures(ControllerGui conrollerGui);

  /**
   * This method is used to show a message in a dialog box using JOption pane to the user.
   *
   * @param msg signifies the message that needs to be printed.
   */
  void showMessage(String msg);

  /**
   * This method is used to show an error in a dialog box using JOption pane to the user.
   *
   * @param error signifies the error message that needs to be printed.
   */
  void showError(String error);

  /**
   * This method is used to portray the composition of a portfolio in a table format which has
   * two columns: Ticker symbol, and it's Quantity.
   *
   * @param composition signifies the composition of the portfolio on the specified date.
   */
  void showComposition(String composition);

  /**
   * This method is used to reset the input values in different panels when no longer needed.
   *
   * @param ch signifies the choice of panels whose input values need to be reset.
   */
  void reset(String ch);

  /**
   * This method is used to print a bar chart that signifies the portfolio's performance over a
   * period of time, using the JFreeChart Library.
   *
   * @param startDate     signifies the start of time period for which performance needs to be
   *                      visualized.
   * @param endDate       signifies the start of time period for which performance needs to be
   *                      visualized.
   * @param portfolioName signifies the name of the portfolio whose performance needs to be
   *                      visualized.
   * @param xaxis         represents the x-axis labels of the bar chart (scaled value of portfolio
   *                      in $)
   * @param yaxis         represents the y-axis labels of the bar chart(duration in days, months
   *                      or years)
   * @param scale         represents the scale for x-axis values
   */
  void printBarChart(String startDate, String endDate, String portfolioName, List<String> xaxis,
                     List<Integer> yaxis, double scale);

  /**
   * Used to retrieve the inputs from the Create a Portfolio page.
   *
   * @return a hashmap of strings which returns the name of the portfolio to be created.
   */
  Map<String, String> getPortfolioNameCreate();

  /**
   * Used to retrieve the path of the file that needs to be uploaded.
   *
   * @return a string that signifies the path of the file.
   */
  String getUploadPath();

  /**
   * Used to retrieve the inputs from the work with existing portfolio page.
   *
   * @return a hashmap that returns the name of the portfolio user wants to work with.
   */
  Map<String, String> getPortfolioNameExisting();

  /**
   * Used to retrieve inputs from the get value of portfolio page.
   *
   * @return a hashmap that consists of the date at which the value of the portfolio is required.
   */
  Map<String, String> getDatePortfolioValue();

  /**
   * Used to retrieve inputs from the view composition of the portfolio page.
   *
   * @return a hashmap that consists of the date at which the composition of the portfolio is
   *         required.
   */
  Map<String, String> getDateViewPortfolio();

  /**
   * Used to retrieve inputs from make a transaction page.
   *
   * @return a hashmap that consists type of transaction, date of transaction, ticker symbol and
   *         quantity of stocks and the commission fee of the stocks.
   */
  Map<String, String> getTransactionInputs();

  /**
   * Used to retrieve the inputs from get the cost basis of the portfolio page.
   *
   * @return a hashmap of strings that consists of the date at which the cost basis is needed.
   */
  Map<String, String> getCostBasisInputs();

  /**
   * Used to retrieve inputs from the Invest a fixed amount page.
   *
   * @return a hashmap that consists of the amount to be invested, commission fee charged,
   *         ticker symbols and their percentage weights (eg: "GOOG 10") and date at which the
   *         investment must be made.
   */
  Map<String, String> getInvestFixedAmtInputs();

  /**
   * Used to retrieve inputs from the dollar cost averaging page.
   *
   * @return a hashmap that consists of the start and end date of strategy, frequency of
   *         re-occurrence, amount to be invested, commission fee and ticker symbols and their
   *         percentage weights (eg: "GOOG 10")
   */
  Map<String, String> getDCAInputs();

  /**
   * Used to retrieve the inputs from the visualize performance of portfolio page.
   *
   * @return a hashmap that consists of the start and end date of the visualization.
   */
  Map<String, String> getPerformanceInputs();

  /**
   * Used to retrieve the inputs from the create a portfolio with Dollar Cost Averaging Strategy.
   *
   * @return a hashmap that consists of start date, end date, frequency of re-occurence, investment
   *         value needed to apply DCA strategy.
   */
  Map<String, String> getCreateDCA();

  /**
   * Used to retrieve inputs from the welcome page.
   *
   * @return a hashmap that consists of the user name.
   */
  Map<String, String> getUsernameInput();

}
