package model.portfolio;

import java.util.List;

import model.stock.StockModel;
import model.strategy.InvestmentStrategy;

/**
 * This interface defines the various functions that can be performed on a Portfolio. A portfolio
 * represents a collection of stocks of different companies and usually includes the stock name and
 * quantity of the stock in that portfolio.
 */
public interface PortfolioModel {

  /**
   * This method is used to create a xml file that consists of a portfolio for the user.
   *
   * @param uname         signifies the name of the user for whom a portfolio should be created
   * @param portfolioName signifies the name of the portfolio (name of file to be created)
   * @param stratList     signifies the list of InvestmentStrategy objects of the portfolio
   * @return true if portfolio is created successfully else returns false
   */
  boolean createPortfolio(String uname, String portfolioName, List<StockModel> stockList,
                          List<InvestmentStrategy> stratList);

  /**
   * This method id used to view the composition of the portfolio.
   *
   * @param uname         signifies the name of the user who wants to view a portfolio's composition
   * @param portfolioName signifies the name of the portfolio (name of file whose composition must
   *                      be read)
   * @return a string that contains the composition of the portfolio
   */
  String viewPortfolio(String uname, String portfolioName, String date); //needed

  /**
   * This method is used to upload a portfolio by uploading a xml file.
   *
   * @param uname signifies the name of the user who wants to upload a portfolio
   * @param path  signifies the path of the file which needs to be uploaded to the system
   * @return true if the portfolio is uploaded successfully else returns false
   */
  boolean uploadPortfolio(String uname, String path);


  /**
   * This method is used to get the value of a portfolio on a given date.
   *
   * @param uname         signifies the name of the user who wants to examine the total value of the
   *                      portfolio
   * @param portfolioName signifies the name of the portfolio whose total value needs to be
   *                      calculated
   * @param date          signifies the date for which the total value of the portfolio needs to be
   *                      calculated
   * @return a string value that contains the total value of the portfolio
   */
  String getValueOnDate(String uname, String portfolioName, String date);

  /**
   * This method is used to delete a portfolio file if the file contains invalid tickers or invalid
   * structure of XML.
   *
   * @param uname the username of the current user.
   * @param fname the portfolio name of the file to be deleted.
   */
  void deleteFile(String uname, String fname);

  /**
   * This method is used to calculate the cost basis of a portfolio until the specified date.
   *
   * @param date       the date till which the cost basis needs to be evaluated
   * @param pDate      the list of purchase dates of the stocks contained in the portfolio
   * @param pType      the list of transaction types of the stocks contained in the portfolio
   * @param pQuantity  the list of quantities of the stocks contained in the portfolio
   * @param pTicks     the list of ticker symbols of the stocks contained in the portfolio
   * @param pCostPrice the list of cost price of the stocks contained in the portfolio
   * @param pCommFee   the fee involved with the transaction
   * @param currData   the list of Stock objects contained in the portfolio
   * @return a string result for the computation of cost basis
   */
  String calculateCostBasis(String date, List<String> pDate,
                            List<String> pType, List<String> pQuantity, List<String> pTicks,
                            List<Double> pCostPrice, List<Double> pCommFee,
                            List<StockModel> currData);

  /**
   * This method computes the value of a flexible portfolio on the specified date.
   *
   * @param date     the date on which the value needs to be computed
   * @param currData the portfolio represented by a list of stock objects
   * @return a string result for the calculated value of portfolio.
   */
  String getFlexiValueOnDate(String date, List<StockModel> currData);

  /**
   * This method finds the number of stocks of a company till the specified date.
   *
   * @param stockName  the stock name for which quantity needs to be calculated
   * @param purDate    the date till which stocks need to be considered
   * @param pDate      the list of purchase dates of the stocks contained in the portfolio
   * @param pType      the list of transaction types of the stocks contained in the portfolio
   * @param pQuantity  the list of quantities of the stocks contained in the portfolio
   * @param pTicks     the list of ticker symbols of the stocks contained in the portfolio
   * @param pCostPrice the list of cost price of the stocks contained in the portfolio
   * @param pCommFee   the fee involved with the transaction
   * @param currData   the list of Stock objects contained in the portfolio
   * @return a double value that is the number of stocks for the given company
   */
  double getQuantityOfStockBeforeDate(String stockName, String purDate, List<String> pDate,
                                      List<String> pType, List<String> pQuantity,
                                      List<String> pTicks, List<Double> pCostPrice,
                                      List<Double> pCommFee, List<StockModel> currData);

  /**
   * This method calculates the total quantity of stocks of a company in the entire portfolio.
   *
   * @param stockName  the stock name for which quantity needs to be calculated
   * @param pDate      the list of purchase dates of the stocks contained in the portfolio
   * @param pType      the list of transaction types of the stocks contained in the portfolio
   * @param pQuantity  the list of quantities of the stocks contained in the portfolio
   * @param pTicks     the list of ticker symbols of the stocks contained in the portfolio
   * @param pCostPrice the list of cost price of the stocks contained in the portfolio
   * @param pCommFee   the fee involved with the transaction
   * @param currData   the list of Stock objects contained in the portfolio
   * @return a double value that denotes the total quantity of a stock
   */
  double getTotalQuantityOfAStock(String stockName, List<String> pDate,
                                  List<String> pType, List<String> pQuantity, List<String> pTicks,
                                  List<Double> pCostPrice, List<Double> pCommFee,
                                  List<StockModel> currData);

  /**
   * A method that delegates the task of reading investment strategies from a portfolio to the
   * Database model object.
   *
   * @param uname    username currently active in the application
   * @param portName portfolio name
   * @return List of InvestmentStrategy model object
   */
  List<InvestmentStrategy> getStrategiesFromPortfolio(String uname, String portName);
}
