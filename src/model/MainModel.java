package model;

import java.util.List;

/**
 * This interface is used as the main model that interacts with the controller. It defines all
 * the methods that are performed in the Stock-Marketverse application.
 */
public interface MainModel {

  /**
   * This method checks if the given file has a .xml extension.
   *
   * @param path signifies the path of the file whose extension needs to be checked.
   * @return true if the file has .xml extension else returns false.
   */
  boolean checkFileIsXML(String path);

  /**
   * This method is used to checks if the date entered is valid, (i.e. days cannot be greater than
   * 31, months cannot be greater than 12) and the portfolioName mentioned is correct.
   */
  String checkValidDateAndName(String uname, String portfolioName, String date);

  /**
   * Checks if the given date is in the future.
   *
   * @param date signifies the date entered by the user
   * @return true if the date is in future else returns false
   */
  boolean checkFutureDate(String date);

  /**
   * Checks if the user directory is created successfully.
   *
   * @param uname signifies the username
   * @return true if the directory is created successfully else returns false
   */
  boolean createUserDirectory(String uname);

  /**
   * Checks if the user directory exists.
   *
   * @param uname signifies the username
   * @return true if the directory exists else returns false
   */
  boolean userDirectoryExist(String uname);

  /**
   * Checks if the format of the file name entered is valid.
   *
   * @param portfolioName signifies the name of the portfolio
   * @return true if the file name is valid else returns false
   */
  boolean checkFileNameFormat(String portfolioName);

  /**
   * Checks whether the format of the date is yyy-mm-dd.
   *
   * @param date signifies the date inputted by the user
   * @return returns true if the date format is correct else returns false
   */
  boolean checkDateFormat(String date);

  /**
   * This method retrieves the value of a portfolio on a particular date.
   *
   * @param uname         signifies the name of the user
   * @param portfolioName signifies the name of the portfolio whose value needs to be calculated
   * @param date          signifies the date at which the value of the portfolio needs to be
   *                      calculated
   * @return a string that contains the value of the portfolio on a particular date
   */
  String mainModGetValueOnDate(String uname, String portfolioName, String date);

  /**
   * Checks if the portfolio name entered exists in the user directory.
   *
   * @param uname         signifies username
   * @param portfolioName signifies the name of the portfolio
   * @return true if the portfolio exists else returns false
   */
  boolean mainModCheckPortfolioNameExists(String uname, String portfolioName);

  /**
   * Checks if the ticker symbol and the stock quantity entered is valid or not.
   *
   * @param stockName signifies the ticker symbol entered by the user
   * @param stockQty  signifies the quantity of stocks entered by the user
   * @return true if the stockName and stockQty are valid else returns false
   */
  boolean mainModHandleData(String stockName, String stockQty);

  /**
   * This method converts a stock's information to a stock object.
   */
  void mainModConvertAllStocksToStockObj();

  /**
   * Checks if a portfolio is created successfully.
   *
   * @param uname         signifies the username
   * @param portfolioName signifies the name of the portfolio
   * @return true if the portfolio is created else returns false
   */
  boolean mainModCreatePortfolio(String uname, String portfolioName);

  /**
   * This method checks if the path entered by the user exists.
   *
   * @param path signifies the path of a portfolio entered by the user
   * @return true if the path exists else returns false
   */
  boolean mainModCheckPathExists(String path);

  /**
   * This method is used to upload and check if the portfolio is uploaded successfully.
   *
   * @param uname signifies the name of the user
   * @param path  signifies the path entered by the user
   * @return true if portfolio is uploaded successfully else returns false
   */
  boolean mainModUploadPortfolio(String uname, String path);

  /**
   * This method retrieves the values of a portfolio for user to view.
   *
   * @param uname         signifies the username
   * @param portfolioName signifies the name of the portfolio
   * @param date          signifies the date at which the portfolio is viewed
   * @return a string that denotes the composition of a portfolio.
   */
  String mainModViewPortfolio(String uname, String portfolioName, String date); // needed

  /**
   * Checks if the date is a weekend.
   *
   * @param date signifies the date entered by the user
   * @return true if the date is a weekend else returns false
   */
  boolean checkHoliday(String date);

  /**
   * Checks if the date entered is today's date.
   *
   * @param date signifies the date entered by the user
   * @return true if the date is today's date, else returns false
   */
  boolean checkTodayDate(String date);

  /**
   * This method deletes a specific a file from the user directory.
   *
   * @param uname signifies the username
   * @param fname signifies the name of the file to be deleted
   */
  void deleteFile(String uname, String fname);

  /**
   * This method is used to add a stock to the portfolio.
   *
   * @param stockName signifies the ticker symbol of the stock
   * @param stockQty  signifies the quantity of the stock
   * @param purDate   signifies teh date at which the stock was purchased
   * @param commFee   commission fee
   * @param flag      flag
   * @return true if the stock is successfully added to the portfolio else returns false
   */
  boolean addStockToPortfolio(String stockName, String stockQty, String purDate, String
          commFee, int flag);

  /**
   * This method removes a stock from the portfolio.
   *
   * @param stockName signifies the ticker symbol of a stock
   * @param stockQty  signifies the quantity of the stock
   * @param purDate   signifies teh date at which the stock was purchased
   * @param commFee   signifies commission fee
   * @return true if the stock is successfully removed from the portfolio else returns false
   */
  String removeStockFromPortfolio(String stockName, String stockQty, String purDate, String
          commFee);

  /**
   * Resets the value of a portfolio.
   *
   * @param name          signifies the username
   * @param portfolioName signifies teh name of the portfolio
   * @return true if the values are reset successfully else returns false
   */
  boolean loadPortfolio(String name, String portfolioName);

  /**
   * This method is used to calculate the cost basis of a portfolio.
   *
   * @param portfolioName signifies the name of a portfolio
   * @param date          signifies the date till which cost basis should be calculated
   * @return a string value that denotes the total cost basis value of a portfolio
   */

  String calculateCostBasis(String portfolioName, String date);

  /**
   * This method is used to set the time stamp scale (scale for y-axis) of the bar graph plot to
   * visualize the performance of a portfolio.
   *
   * @param fromDate signifies the date at which the visualization of performance of a portfolio
   *                 must begin
   * @param toDate   signifies the date at which the visualization of performance of a portfolio
   *                 must end
   * @return true if the time stamp scale is set else returns false
   */
  boolean returnPerformanceTimestampScale(String fromDate, String toDate);

  /**
   * This method is used to get the scale of x-axis of bar graph.
   *
   * @param fromDate signifies the date at which the visualization of performance of a portfolio
   *                 must begin
   * @param tillDate signifies the date at which the visualization of performance of a portfolio
   *                 must end
   * @return a double value that denotes the scale of x-axis
   */
  double getScaleBarGraph(String fromDate, String tillDate);

  /**
   * This method retrieves a list of strings that denotes the time stamp values for the y-axis.
   *
   * @return a list of strings that denotes the time stamp values for the y-axis
   */
  List<String> getTimeStampForYaxis();

  /**
   * This method retrieves a list of strings that denotes the number of stars that must be printed
   * in a bar of the visualization.
   *
   * @param scale denotes the scale of x-axis, i.e. the value of one *
   * @return a list of integers that denotes the number of stars in each bar of visualization
   */
  List<Integer> getNumOfStars(double scale);

  /**
   * This method calculates the quantity of stocks in a portfolio file uploaded by the user.
   *
   * @return true if the quantity of uploaded file is greater than 0 else returns false
   */
  boolean calcQuantityOfUploadedFile();

  /**
   * This method checks if commission fee is not negative.
   *
   * @param commissionFee to be checked
   * @return true or false depending upon the check
   */
  boolean checkAmount(double commissionFee);

  /**
   * Invests a fixed amount provided by the user in the portfolio.
   *
   * @param uname         signifies the user name
   * @param portfolioName signifies the name of the portfolio in which the strategy needs to be
   *                      applied
   * @param maps          signifies the map of all inputs needed to invest a fixed amount
   * @param purDate       signifies the date at which the amount needs to be invested
   * @param commFee       signifies the commission fee charged for each transaction
   * @param investValue   signifies the amount that needs to be invested in the portfolio
   * @return a string value "true" if invested successfully else appropriate error string
   */
  String investFixedAmt(String uname, String portfolioName, String maps, String purDate,
                        String commFee, String investValue);

  /**
   * This method checks if the format in which the tickers and it's weights provided are in this
   * format: ticker1 weight1, ticker2, weight2, etc. (eg: GOOG 50, AAPL 50).
   *
   * @param stockW signifies input string provided by user that needs to be checked
   * @return true if the format is as expected else returns false
   */
  boolean checkInvestWeightFormat(String stockW);

  /**
   * Applies the dollar cost averaging strategy on the portfolio.
   *
   * @param uname         signifies the user name
   * @param portfolioName signifies the name of the portfolio in which the strategy needs to be
   *                      applied
   * @param startDate     signifies the start date of the strategy
   * @param endDate       signifies the date at which the recurrence of strategy nust be ended
   * @param iVal          signifies the value that must be invested
   * @param interval      signifies the duration at which the strategy must re-occur
   * @param commFee       signifies the commission fee charged for each transaction
   * @param tickerWts     signifies a string containing the tickers and it's weights in the
   *                      following format: ticker1 weight1, ticker2, weight2, etc.
   *                      (eg: GOOG 50, AAPL 50)
   * @return a string value "true" if applied successfully else appropriate error string
   */
  String applyDCAOnPortfolio(String uname, String portfolioName, String startDate,
                             String endDate, String iVal, String interval,
                             String commFee, String tickerWts);
}
