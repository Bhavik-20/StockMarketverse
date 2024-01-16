package model.portfolio;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.api.Api;
import model.api.ApiImpl;
import model.database.Database;
import model.database.DatabaseFlexibleImpl;
import model.stock.StockModel;
import model.strategy.InvestmentStrategy;

/**
 * This class extends the functionalities of the abstract portfolio class and implements all the
 * methods that can be performed on a flexible portfolio.
 */
public class PortfolioFlexibleImpl extends AbstractPortfolio {

  /**
   * Initializes the values of all the fields of a PortfolioFlexibleImpl object.
   */
  public PortfolioFlexibleImpl() {
    super();
  }

  protected Database getDatabaseObject(String uname) {
    Database file = new DatabaseFlexibleImpl(defaultPath + uname + "/flexible/");
    return file;
  }

  @Override
  protected int getFlagForWrite() {
    return 2;
  }

  @Override
  public String viewPortfolio(String uname, String portfolioName, String date) {
    Database file = new DatabaseFlexibleImpl(defaultPath + uname + "/flexible/");
    List<StockModel> stocks = file.readFromFlexiPortfolio(portfolioName, 2);
    if (stocks == null) {
      return "Incorrect file format";
    } else if (stocks.isEmpty()) {
      return "You have no stocks in this portfolio";
    } else if (stocks.size() == 1 && stocks.get(0).getTicker().equals("INVALID")) {
      return "Portfolio contains invalid ticker symbols";
    } else {
      stocks = removeStocksAfterGivenDate(stocks, date);
      return getFlexiCompo(stocks);
    }
  }

  private List<StockModel> removeStocksAfterGivenDate(List<StockModel> stocks, String date) {
    List<StockModel> filtered = new ArrayList<>();
    for (int i = 0; i < stocks.size(); i++) {
      if (!LocalDate.parse(stocks.get(i).getPurDate()).isAfter(LocalDate.parse(date))) {
        filtered.add(stocks.get(i));
      }
    }
    return filtered;
  }

  private String getFlexiCompo(List<StockModel> stocks) {
    Map<String, Double> map = new HashMap<String, Double>();
    for (int i = 0; i < stocks.size(); i++) {
      if (map.containsKey(stocks.get(i).getTicker())) {
        if (stocks.get(i).getTransaction().equals("Buy")) {
          map.put(stocks.get(i).getTicker(),
                  map.get(stocks.get(i).getTicker()) + stocks.get(i).getQuantity());
        } else {
          map.put(stocks.get(i).getTicker(),
                  map.get(stocks.get(i).getTicker()) - stocks.get(i).getQuantity());
        }
      } else {
        map.put(stocks.get(i).getTicker(),
                stocks.get(i).getQuantity());
      }
    }

    String fmt = "";
    for (Map.Entry<String, Double> entry : map.entrySet()) {
      fmt = fmt + "\n" + entry.getKey() + " " + entry.getValue();
    }
    return fmt;
  }

  @Override
  public String getValueOnDate(String uname, String portfolioName, String date) {
    return null;
  }

  @Override
  public void deleteFile(String uname, String fname) {
    File f = new File(defaultPath + uname + "/flexible/" + fname + ".xml");
    callDelete(f);
  }

  @Override
  public String calculateCostBasis(String date, List<String> pDate,
                                   List<String> pType, List<String> pQuantity, List<String> pTicks,
                                   List<Double> pCostPrice,
                                   List<Double> pCommFee, List<StockModel> currData) {
    double numberOfTx = 0;
    double costBasis = 0;
    LocalDate enteredDate = LocalDate.parse(date);
    for (int i = 0; i < currData.size(); i++) {
      LocalDate stockDate = LocalDate.parse(pDate.get(i));
      if ((stockDate.isBefore(enteredDate) || stockDate.isEqual(enteredDate))
              && pType.get(i).equals("Buy")) {
        costBasis += (pCostPrice.get(i) * Double.parseDouble(pQuantity.get(i)));
        numberOfTx += pCommFee.get(i);
      } else if ((stockDate.isBefore(enteredDate) || stockDate.isEqual(enteredDate))
              && pType.get(i).equals("Sell")) {
        numberOfTx += pCommFee.get(i);
      }
    }
    return String.valueOf(costBasis + numberOfTx);
  }

  @Override
  public List<InvestmentStrategy> getStrategiesFromPortfolio(String uname, String portName) {
    Database file = new DatabaseFlexibleImpl(defaultPath + uname + "/flexible/");
    return file.getStrategyFromPortfolio(portName, 2);
  }

  @Override
  public String getFlexiValueOnDate(String date, List<StockModel> currData) {
    double val = 0;
    Map<String, Double> map = new HashMap<String, Double>();
    LocalDate newStock = LocalDate.parse(date);
    for (int i = 0; i < currData.size(); i++) {
      LocalDate stockDate = LocalDate.parse(currData.get(i).getPurDate());
      if (newStock.isEqual(stockDate) || newStock.isAfter(stockDate)) {
        if (map.containsKey(currData.get(i).getTicker())) {
          if (currData.get(i).getTransaction().equals("Buy")) {
            map.put(currData.get(i).getTicker(),
                    map.get(currData.get(i).getTicker()) + currData.get(i).getQuantity());

          } else if (currData.get(i).getTransaction().equals("Sell")) {
            map.put(currData.get(i).getTicker(),
                    map.get(currData.get(i).getTicker()) - currData.get(i).getQuantity());
          }
        } else {
          map.put(currData.get(i).getTicker(),
                  currData.get(i).getQuantity());
        }
      }
    }
    for (Map.Entry<String, Double> entry : map.entrySet()) {
      Api api = new ApiImpl();
      val += entry.getValue() * api.getStockValueOnDate(entry.getKey(), date);
    }

    return val + "";
  }

  @Override
  public double getQuantityOfStockBeforeDate(String stockName, String purDate, List<String> pDate,
                                             List<String> pType, List<String> pQuantity,
                                             List<String> pTicks, List<Double> pCostPrice,
                                             List<Double> pCommFee, List<StockModel> currData) {
    double q = 0;
    LocalDate newStockDate = LocalDate.parse(purDate);
    for (int i = 0; i < pTicks.size(); i++) {
      LocalDate currStockDate = LocalDate.parse(pDate.get(i));
      if (pType.get(i).equals("Buy") && pTicks.get(i).equals(stockName) &&
              (currStockDate.isBefore(newStockDate) || currStockDate.isEqual(newStockDate))) {
        q += Double.parseDouble(pQuantity.get(i));
      } else if (pType.get(i).equals("Sell") && pTicks.get(i).equals(stockName) &&
              (currStockDate.isBefore(newStockDate) || currStockDate.isEqual(newStockDate))) {
        q -= Double.parseDouble(pQuantity.get(i));
      }
    }
    return q;
  }

  @Override
  public double getTotalQuantityOfAStock(String stockName, List<String> pDate,
                                         List<String> pType, List<String> pQuantity,
                                         List<String> pTicks, List<Double> pCostPrice,
                                         List<Double> pCommFee, List<StockModel> currData) {
    double totq = 0;
    for (int i = 0; i < pTicks.size(); i++) {
      if (pType.get(i).equals("Buy") && pTicks.get(i).equals(stockName)) {
        totq += Double.parseDouble(pQuantity.get(i));
      } else if (pType.get(i).equals("Sell") && pTicks.get(i).equals(stockName)) {
        totq -= Double.parseDouble(pQuantity.get(i));
      }
    }
    return totq;
  }

}
