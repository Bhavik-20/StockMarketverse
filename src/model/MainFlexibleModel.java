package model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.api.Api;
import model.api.ApiImpl;
import model.database.Database;
import model.database.DatabaseFlexibleImpl;
import model.performance.PerformanceImpl;
import model.performance.PerformanceModel;
import model.portfolio.PortfolioFlexibleImpl;
import model.portfolio.PortfolioModel;
import model.stock.StockModel;
import model.stock.StockModelImpl;
import model.strategy.DollarCostImpl;
import model.strategy.InvestmentStrategy;

/**
 * This class extends the AbstractMainModel and class and implements the methods that delegate
 * tasks to other models for the flexible portfolio.
 */
public class MainFlexibleModel extends AbstractMainModel {
  private final PortfolioModel fP;
  private List<String> pTicks;
  private List<String> pQuantity;
  private List<String> pDate;
  private List<String> pType;
  private List<Double> pCostPrice;
  private List<Double> pCommFee;
  private List<StockModel> currData;
  private PerformanceModel pModel;
  private List<InvestmentStrategy> stratList;
  private String stratUpdaterFlag;
  private Api api;


  /**
   * The constructor initializes all the fields of the MainFlexibleModel object.
   */
  public MainFlexibleModel() {
    super();
    api = new ApiImpl();
    this.fP = new PortfolioFlexibleImpl();
    this.currData = new ArrayList<>();
    this.pTicks = new ArrayList<>();
    this.pQuantity = new ArrayList<>();
    this.pDate = new ArrayList<>();
    this.pType = new ArrayList<>();
    this.pCostPrice = new ArrayList<>();
    this.pModel = new PerformanceImpl();
    this.pCommFee = new ArrayList<>();
    this.stratList = new ArrayList<>();
    this.stratUpdaterFlag = "";
  }

  @Override
  public String checkValidDateAndName(String uname, String portfolioName, String date) {
    String regex = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    if (!checkValidPath(defaultPath + uname + "/flexible/" + portfolioName + ".xml")) {
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
      Files.createDirectories(Paths.get(defaultPath + uname + "/flexible"));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public String mainModGetValueOnDate(String uname, String portfolioName, String date) {
    return fP.getFlexiValueOnDate(date, currData);

  }

  @Override
  public boolean mainModCheckPortfolioNameExists(String uname, String portfolioName) {
    return checkValidPath(defaultPath + uname + "/flexible/" + portfolioName + ".xml");
  }

  @Override
  public void mainModConvertAllStocksToStockObj() {
    convertAllStocksToStockObj();
  }

  private void convertAllStocksToStockObj() {
    stockList.clear();
    for (int i = 0; i < pTicks.size(); i += 1) {
      StockModel stock = new StockModelImpl(pTicks.get(i),
              Double.parseDouble(pQuantity.get(i)),
              pCostPrice.get(i), pDate.get(i), pType.get(i), pCommFee.get(i));
      stockList.add(stock);
    }
    pQuantity.clear();
    pType.clear();
    pTicks.clear();
    pDate.clear();
    pCostPrice.clear();
    pCommFee.clear();
  }

  @Override
  public boolean mainModCreatePortfolio(String uname, String portfolioName) {
    loadPortfolio(uname, portfolioName);
    return fP.createPortfolio(uname, portfolioName, stockList, stratList);
  }

  @Override
  public boolean mainModUploadPortfolio(String uname, String path) {
    return fP.uploadPortfolio(uname, path);
  }

  @Override
  public String mainModViewPortfolio(String uname, String portfolioName, String date) {
    return fP.viewPortfolio(uname, portfolioName, date);
  }

  @Override
  public void deleteFile(String uname, String fname) {
    fP.deleteFile(uname, fname);
  }

  @Override
  public boolean mainModHandleData(String stockName, String stockQty) {
    return false;
  }

  @Override
  public String investFixedAmt(String uname, String portfolioName,
                               String inputs, String purDate, String commFee,
                               String investValue) {
    double iVal = Double.parseDouble(investValue);
    double cFee = Double.parseDouble(commFee);

    String[] vals = inputs.split(",");
    Map<String, Double> stockW = new HashMap<>();
    double percentSum = 0;
    for (int i = 0; i < vals.length; i++) {
      String[] curr = vals[i].trim().split("\\s");
      if (!allTicker.contains(curr[0].toUpperCase())) {
        return "Could not find the ticker \'" + curr[0] + "\'.";
      } else {
        if (Double.parseDouble(curr[1]) != 0) {
          iVal -= cFee;
          if (iVal <= 0) {
            return "Commission Fee > Investment Amount. Cannot apply strategy!";
          }
        }
      }
    }
    for (int i = 0; i < vals.length; i++) {
      String[] curr = vals[i].trim().split("\\s");
      if (Double.parseDouble(curr[1]) != 0) {
        stockW.put(curr[0].toUpperCase(),
                Double.parseDouble(curr[1]) / 100 * iVal);
        percentSum += Double.parseDouble(curr[1]);
      }
    }
    if (percentSum != 100) {
      return "The sum of weights does not amount to 100%";
    }
    for (String key : stockW.keySet()) {
      if (!addStockToPortfolio(key, stockW.get(key) + "", purDate, commFee, 1)) {
        return "An error occurred. Could not invest, Please try again!";
      }
    }
    return "true";
  }

  @Override
  public String applyDCAOnPortfolio(String uname, String portfolioName, String startDate,
                                    String endDate, String iVal,
                                    String interval, String commFee, String tickerWts) {
    LocalDate sDate = LocalDate.parse(startDate);
    LocalDate eDate = LocalDate.parse(endDate);
    interval = getDaysInterval(interval);
    if (interval.equals("none")) {
      return "Invalid frequency entered";
    }
    long freq = Long.parseLong(interval);
    if (freq < 0) {
      return "Interval value cannot be negative";
    } else {
      if (sDate.isAfter(eDate)) {
        return "Start date is after end date.";
      } else if (!sDate.isAfter(LocalDate.now()) && !eDate.isAfter(LocalDate.now())) {
        return dcaHelperPastDates(uname, portfolioName, sDate, eDate, freq, commFee, iVal,
                tickerWts);
      }
      String res = dcaHelperOngoing(uname, portfolioName,
              sDate, eDate, freq, commFee, iVal, tickerWts);
      return res;
    }
  }

  private String dcaHelperOngoing(String uname, String portfolioName,
                                  LocalDate sDate, LocalDate eDate, long freq,
                                  String commFee, String iVal, String tickerWts) {
    String res = dcaHelperPastDates(uname, portfolioName,
            sDate, LocalDate.now().minusDays(1), freq,
            commFee, iVal, tickerWts);
    if (!res.equals("true")) {
      return res;
    }
    while (!sDate.plusDays(freq).isAfter(LocalDate.now())) {
      sDate = sDate.plusDays(freq);
    }
    stratList.add(new DollarCostImpl(sDate.toString(), eDate.toString(), tickerWts,
            freq + "", Double.parseDouble(iVal), Double.parseDouble(commFee)));
    return "true";
  }

  private String dcaHelperPastDates(String uname, String portfolioName,
                                    LocalDate sDate, LocalDate eDate, long freq, String commFee,
                                    String iVal, String tickerWts) {
    LocalDate temp = sDate;
    while (!temp.isAfter(eDate)) {
      String res = this.investFixedAmt(uname, portfolioName,
              tickerWts, temp.toString(), commFee, iVal);
      if (!res.equals("true")) {
        return res;
      }
      temp = temp.plusDays(freq);
    }
    return "true";
  }

  private String getDaysInterval(String interval) {
    String[] freq = interval.trim().split("\\s+");
    if (freq.length == 1) {
      return freq[0];
    }
    if (freq[1].equals("days")) {
      return freq[0];
    } else if (freq[1].equals("months")) {
      return (Long.parseLong(freq[0]) * 30) + "";
    } else if (freq[1].equals("years")) {
      return (Long.parseLong(freq[0]) * 365) + "";
    }
    return "none";
  }

  @Override
  public boolean addStockToPortfolio(String stockName, String stockQty, String purDate,
                                     String commFee, int flag) {

    if (allTicker.contains(stockName) && stockQty.matches("^[1-9]\\d*(\\.\\d+)?$")) {
      double costPrice = api.getStockValueOnDate(stockName, purDate);
      double qty;
      if (flag == 1) {
        double amt = Double.parseDouble(stockQty);
        qty = amt / costPrice;
        stockQty = qty + "";
      } else {
        qty = Double.parseDouble(stockQty);
        Double q = qty;
        if (q.intValue() != q) {
          return false;
        }
      }

      if (qty < 0) {
        return false;
      } else {
        if (pTicks.size() != 0) {
          boolean present = false;
          for (int i = 0; i < pTicks.size(); i += 1) {
            if (pTicks.get(i).equals(stockName) &&
                    pDate.get(i).equals(purDate) && pType.get(i).equals("Buy")
                    && pCommFee.get(i) == Double.parseDouble(commFee)) {
              double temp = Double.parseDouble(pQuantity.get(i));
              pQuantity.set(i, String.valueOf(temp + qty));
              present = true;
              break;
            }
          }
          if (!present) {
            pTicks.add(stockName);
            pQuantity.add(stockQty);
            pDate.add(purDate);
            pType.add("Buy");
            pCommFee.add(Double.parseDouble(commFee));
            pCostPrice.add(costPrice);
          }
        } else {
          pTicks.add(stockName);
          pQuantity.add(stockQty);
          pDate.add(purDate);
          pType.add("Buy");
          pCommFee.add(Double.parseDouble(commFee));
          pCostPrice.add(costPrice);
        }
        return true;
      }
    }
    return false;
  }

  @Override
  public String removeStockFromPortfolio(String stockName, String stockQty, String purDate,
                                         String commFee) {
    if (allTicker.contains(stockName) && stockQty.matches("^[1-9]\\d*(\\.\\d+)?$")) {
      double qty = Double.parseDouble(stockQty);
      Double q = qty;
      if (qty < 0) {
        return "negative";
      } else if (q.intValue() != q) {
        return "fractional";
      } else {
        double totQ = fP.getTotalQuantityOfAStock(stockName, pDate, pType, pQuantity,
                pTicks, pCostPrice, pCommFee, currData);
        if (totQ >= Double.parseDouble(stockQty)) {
          double currQuant = fP.getQuantityOfStockBeforeDate(stockName, purDate,
                  pDate, pType, pQuantity, pTicks, pCostPrice, pCommFee, currData);
          if (currQuant >= Double.parseDouble(stockQty)) {
            if (pTicks.size() != 0) {
              boolean present = false;
              for (int i = 0; i < pTicks.size(); i += 1) {
                if (pTicks.get(i).equals(stockName) && pDate.get(i).equals(purDate) && pType.get(i)
                        .equals("Sell")) {
                  double temp = Double.parseDouble(pQuantity.get(i));
                  pQuantity.set(i, String.valueOf(temp + qty));
                  present = true;
                  break;
                }
              }
              if (!present) {
                pTicks.add(stockName);
                pQuantity.add(stockQty);
                pDate.add(purDate);
                pType.add("Sell");
                pCommFee.add(Double.parseDouble(commFee));
                pCostPrice.add(api.getStockValueOnDate(stockName, purDate));
              }
            } else {
              pTicks.add(stockName);
              pQuantity.add(stockQty);
              pDate.add(purDate);
              pType.add("Sell");
              pCommFee.add(Double.parseDouble(commFee));
              pCostPrice.add(api.getStockValueOnDate(stockName, purDate));
            }
            return "true";
          }
          return "NA";
        }
        return "NP";
      }
    }
    return "invalidTicker";
  }

  @Override
  public boolean loadPortfolio(String uname, String portfolioName) {
    try {
      pTicks.clear();
      pQuantity.clear();
      pDate.clear();
      pType.clear();
      pCostPrice.clear();
      pCommFee.clear();
      currData.clear();
      if (!stratUpdaterFlag.equals((uname + portfolioName).trim())) {
        stratList.clear();
        executeStrategy(uname, portfolioName);

        stratUpdaterFlag = (uname + portfolioName).trim();
      }

      Database db = new DatabaseFlexibleImpl(defaultPath + uname + "/flexible/");
      currData = db.readFromFlexiPortfolio(portfolioName, 2);
      for (int i = 0; i < currData.size(); i++) {
        pTicks.add(currData.get(i).getTicker());
        pQuantity.add(currData.get(i).getQuantity() + "");
        pDate.add(currData.get(i).getPurDate());
        pType.add(currData.get(i).getTransaction());
        pCostPrice.add(currData.get(i).getCostPrice());
        pCommFee.add(currData.get(i).getCommissionFee());
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private void executeStrategy(String uname, String portfolioName) {
    Database db = new DatabaseFlexibleImpl(defaultPath + uname + "/flexible/");
    List<InvestmentStrategy> temp = db.getStrategyFromPortfolio(portfolioName, 2);
    for (int i = 0; i < temp.size(); i++) {
      String res = this.applyDCAOnPortfolio(uname, portfolioName, temp.get(i).getStartDate(),
              temp.get(i).getEndDate(), String.valueOf(temp.get(i).getInvestAmt()),
              temp.get(i).getInterval(), String.valueOf(temp.get(i).getCommissionFee()),
              temp.get(i).getTicker());
      if (!res.equals("true")) {
        // Could not apply stored strategy"
      }
    }
  }

  @Override
  public String calculateCostBasis(String portfolioName, String date) {
    return fP.calculateCostBasis(date, pDate, pType, pQuantity, pTicks, pCostPrice,
            pCommFee, currData);
  }

  @Override
  public boolean returnPerformanceTimestampScale(String fromDate, String toDate) {
    return pModel.returnPerformanceTimestampScale(fromDate, toDate);
  }

  @Override
  public double getScaleBarGraph(String fromDate, String tillDate) {
    return pModel.getScaleForBarGraph(fromDate, tillDate, currData);
  }

  @Override
  public List<String> getTimeStampForYaxis() {
    return pModel.getTimeStampForYaxis();
  }

  @Override
  public List<Integer> getNumOfStars(double scale) {
    List<Integer> intVal = new ArrayList<>();
    List<Double> tss = pModel.getValueTimeStampForXaxis();
    for (int i = 0; i < pModel.getValueTimeStampForXaxis().size(); i++) {
      intVal.add((int) (Math.ceil(tss.get(i) / scale)));
    }
    return intVal;
  }

  @Override
  public boolean calcQuantityOfUploadedFile() {
    Map<String, Double> map = new HashMap<String, Double>();
    for (int i = 0; i < currData.size(); i++) {
      if (map.containsKey(currData.get(i).getTicker())) {
        if (currData.get(i).getTransaction().equals("Buy")) {
          map.put(currData.get(i).getTicker(),
                  map.get(currData.get(i).getTicker()) + currData.get(i).getQuantity());
        } else {
          map.put(currData.get(i).getTicker(),
                  map.get(currData.get(i).getTicker()) - currData.get(i).getQuantity());
        }
      } else {
        map.put(currData.get(i).getTicker(),
                currData.get(i).getQuantity());
      }
    }
    for (double i : map.values()) {
      if (i < 0) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean checkAmount(double commissionFee) {
    return commissionFee >= 0;
  }
}
