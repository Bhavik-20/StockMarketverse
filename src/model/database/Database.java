package model.database;

import java.util.List;

import model.stock.StockModel;
import model.strategy.InvestmentStrategy;

/**
 * This interface defines the different read and write methods that can be performed on a file
 * which saves the composition of a portfolio.
 */
public interface Database {

  /**
   * This method takes a list of stocks, and it's quantity as input, writes it to the file with the
   * portfolioName as its name.
   *
   * @param stockList     which signifies a list of stocks and it's quantities
   * @param stratList     list of strategies
   * @param portfolioName which signifies the name of the file that needs to be written
   * @return true if the file is writte successfully, else returns false.
   */
  boolean writeToPortfolio(List<StockModel> stockList, List<InvestmentStrategy> stratList,
                           String portfolioName, int flag);

  /**
   * This method is created to read the contents of the file.
   *
   * @param portfolioName which signifies the name of the file that needs to be read
   * @return a string that contains the composition of the file
   */
  String readFromPortfolio(String portfolioName, int flag);


  /**
   * This method copies a file from a given path to the /data directory in the application's
   * system.
   *
   * @param fpath which represents the path from which a file is to be copied
   * @return true if the file is copied successfully else returns false
   */
  boolean copyFileFromPath(String fpath);

  /**
   * This method is used to read the contents from a flexible portfolio.
   *
   * @param portfolioName signifies the name of a portfolio whose contents need to be read
   * @param i             flag to check what type of portfolio is being worked upon
   * @return a list of objects of stock model
   */
  List<StockModel> readFromFlexiPortfolio(String portfolioName, int i);

  /**
   * This method is used to read the list of stratergies from a flexible portfolio.
   *
   * @param portfolioName the portfolio for which strategy needs to be retrieved
   * @param i             flag indicating if method called from flexible or inflexible portfolio
   * @return List of InvestmentStrategy
   */
  List<InvestmentStrategy> getStrategyFromPortfolio(String portfolioName, int i);
}
