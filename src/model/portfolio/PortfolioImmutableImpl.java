package model.portfolio;

import java.io.File;
import java.util.List;

import model.api.Api;
import model.api.ApiImpl;
import model.database.Database;
import model.database.DatabaseImmutableImpl;
import model.stock.StockModel;
import model.strategy.InvestmentStrategy;

/**
 * This class implements the portfolioModel interface and implements various methods that can be
 * performed on a portfolio.
 */
public class PortfolioImmutableImpl extends AbstractPortfolio {
  
  /**
   * This constructor initializes the allTicker list to a list of ticker symbols that
   * Stock-Marketverse can support and the default path to the /data directory in the system.
   */
  public PortfolioImmutableImpl() {
    super();
  }

  Database getDatabaseObject(String uname) {
    Database file = new DatabaseImmutableImpl(defaultPath + uname + "/immutable/");
    return file;
  }

  @Override
  int getFlagForWrite() {
    return 1;
  }

  @Override
  public List<InvestmentStrategy> getStrategiesFromPortfolio(String uname, String portName) {
    return null;
  }

  @Override
  public String viewPortfolio(String uname, String portfolioName, String date) {
    if (prevReadFname.equals(uname + portfolioName) && !prevReadContents.equals("File Not Found")
            && !prevReadContents.equals("")) {
      prevReadFname = uname + portfolioName;
      return prevReadContents;
    }
    Database file = new DatabaseImmutableImpl(defaultPath + uname + "/immutable/");
    prevReadFname = uname + portfolioName;
    prevReadContents = file.readFromPortfolio(portfolioName, 1);
    return prevReadContents;
  }

  @Override
  public String getValueOnDate(String uname, String portfolioName, String date) {
    String[] fileContents;
    if (prevReadFname.equals(uname + portfolioName) && prevGetValDate.equals(date) &&
            !prevVal.equals("0.0")) {
      return prevVal;
    } else if (prevReadFname.equals(uname + portfolioName) && prevGetValFileContents != null) {
      fileContents = prevGetValFileContents;
    } else {
      fileContents = viewPortfolio(uname, portfolioName, "").split("\n");
      prevReadFname = uname + portfolioName;
      prevGetValDate = date;
      prevGetValFileContents = fileContents;
    }

    double ans = 0;

    for (int i = 0; i < fileContents.length; i++) {
      String[] out = fileContents[i].split(" -> ");
      Api api = new ApiImpl();
      ans += api.getStockValueOnDate(out[0], date) *
              Double.parseDouble(out[1].split("\\s+")[0]);
    }
    prevVal = ans + "";
    return prevVal;
  }

  @Override
  public void deleteFile(String uname, String fname) {
    File f = new File(defaultPath + uname + "/immutable/" + fname + ".xml");
    callDelete(f);
  }

  @Override
  public String calculateCostBasis(String date, List<String> pDate,
                                   List<String> pType, List<String> pQuantity, List<String> pTicks,
                                   List<Double> pCostPrice, List<Double> pCommFee,
                                   List<StockModel> currData) {
    return null;
  }

  @Override
  public String getFlexiValueOnDate(String date, List<StockModel> currData) {
    return null;
  }

  @Override
  public double getQuantityOfStockBeforeDate(String stockName, String purDate, List<String> pDate,
                                             List<String> pType, List<String> pQuantity,
                                             List<String> pTicks, List<Double> pCostPrice,
                                             List<Double> pCommFee, List<StockModel> currData) {
    return 0;
  }

  @Override
  public double getTotalQuantityOfAStock(String stockName, List<String> pDate, List<String> pType,
                                         List<String> pQuantity, List<String> pTicks,
                                         List<Double> pCostPrice, List<Double> pCommFee,
                                         List<StockModel> currData) {
    return 0;
  }

}

