package model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import model.portfolio.PortfolioImmutableImpl;
import model.portfolio.PortfolioModel;
import model.stock.StockModel;
import model.stock.StockModelImpl;

/**
 * This class extends the AbstractMainModel and delegates tasks to various models for immutable
 * portfolio.
 */
public class MainImmutableModel extends AbstractMainModel {

  PortfolioModel p;

  /**
   * The constructor initializes the value of the default path to the /data directory of the
   * application system and p to an object of portfolioModelImpl.
   */
  public MainImmutableModel() {
    super();
    this.p = new PortfolioImmutableImpl();
  }

  private void convertAllStocksToStockObj() {
    for (int i = 0; i < allStocks.size(); i += 2) {
      StockModel stock = new StockModelImpl(allStocks.get(i),
              Double.parseDouble(allStocks.get(i + 1)),
              0, null, null, 0);
      stockList.add(stock);
    }
    allStocks.clear();
  }

  private boolean handleData(String stockName, String stockQty) {
    if (allTicker.contains(stockName.toUpperCase()) && stockQty
            .matches("^[1-9]\\d*(\\.\\d+)?$")) {
      double qty = Double.parseDouble(stockQty);
      Double q = qty;
      if (qty < 0) {
        return false;
      } else if (q.intValue() != q) {
        return false;
      } else {
        if (allStocks.size() != 0) {
          boolean present = false;
          for (int i = 0; i < allStocks.size(); i += 2) {
            if (allStocks.get(i).equals(stockName)) {
              double temp = Double.parseDouble(allStocks.get(i + 1));
              allStocks.set(i + 1, String.valueOf(temp + qty));
              present = true;
              break;
            }
          }
          if (!present) {
            allStocks.add(stockName);
            allStocks.add(stockQty);
          }
        } else {
          allStocks.add(stockName);
          allStocks.add(stockQty);
        }
        return true;
      }
    }
    return false;
  }

  @Override
  public String checkValidDateAndName(String uname, String portfolioName, String date) {
    String regex = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    if (!checkValidPath(defaultPath + uname + "/immutable/" + portfolioName + ".xml")) {
      return "File Not Found";
    }
    if (!date.matches(regex)) {
      return "Incorrect Date Format";
    }
    return "valid";
  }

  @Override
  public boolean createUserDirectory(String uname) {
    try {
      Files.createDirectories(Paths.get(defaultPath + uname + "/immutable"));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean mainModCheckPortfolioNameExists(String uname, String portfolioName) {
    return checkValidPath(defaultPath + uname + "/immutable/" + portfolioName + ".xml");
  }

  @Override
  public boolean mainModHandleData(String stockName, String stockQty) {
    return handleData(stockName, stockQty);
  }

  @Override
  public void mainModConvertAllStocksToStockObj() {
    convertAllStocksToStockObj();
  }

  @Override
  public String mainModGetValueOnDate(String uname, String portfolioName, String date) {
    return p.getValueOnDate(uname, portfolioName, date);
  }

  @Override
  public boolean mainModCreatePortfolio(String uname, String portfolioName) {
    return p.createPortfolio(uname, portfolioName, stockList, null);
  }

  @Override
  public boolean mainModUploadPortfolio(String uname, String path) {
    return p.uploadPortfolio(uname, path);
  }

  @Override
  public String mainModViewPortfolio(String uname, String portfolioName, String date) {
    return p.viewPortfolio(uname, portfolioName, "");
  }

  @Override
  public void deleteFile(String uname, String fname) {
    p.deleteFile(uname, fname);
  }

  @Override
  public boolean addStockToPortfolio(String stockName, String stockQty, String purDate,
                                     String commFee, int flag) {
    return false;
  }

  @Override
  public String removeStockFromPortfolio(String stockName, String stockQty, String purDate,
                                         String commFee) {
    return "false";
  }

  @Override
  public boolean loadPortfolio(String name, String portfolioName) {
    return false;
  }

  @Override
  public String calculateCostBasis(String portfolioName, String date) {
    return null;
  }

  @Override
  public boolean returnPerformanceTimestampScale(String fromDate, String toDate) {
    return false;
  }

  @Override
  public double getScaleBarGraph(String fromDate, String tillDate) {
    return 0;
  }

  @Override
  public List<String> getTimeStampForYaxis() {
    return null;
  }

  @Override
  public List<Integer> getNumOfStars(double scale) {
    return null;
  }

  @Override
  public boolean calcQuantityOfUploadedFile() {
    return false;
  }

  @Override
  public boolean checkAmount(double commissionFee) {
    return false;
  }

  @Override
  public String investFixedAmt(String uname, String portfolioName,
                               String maps, String purDate, String commFee, String investValue) {
    return "false";
  }

  @Override
  public String applyDCAOnPortfolio(String uname, String portfolioName, String startDate,
                                    String endDate, String iVal, String interval,
                                    String commFee, String tickerWts) {
    return "false";
  }
}
