package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import model.stock.StockModel;

/**
 * This abstract class implements the MainModel interface and contains the common functionalities
 * between the main models of Flexible and inflexible portfolios.
 */
public abstract class AbstractMainModel implements MainModel {
  List<String> allTicker;
  List<StockModel> stockList;
  List<String> allStocks;
  final String defaultPath;

  /**
   * Constructor initializes all the fields of the AbstractMainModel object.
   */
  public AbstractMainModel() {
    this.defaultPath = System.getProperty("user.dir") + "/data/";
    this.allTicker = getTickerFromCSV();
    this.stockList = new ArrayList<>();
    this.allStocks = new ArrayList<>();
  }

  @Override
  public boolean userDirectoryExist(String uname) {
    Path p = Paths.get(defaultPath + uname);
    return Files.isDirectory(p);
  }

  @Override
  public boolean checkFileIsXML(String path) {
    String[] pathArray = path.split("/");
    return pathArray[pathArray.length - 1].contains(".xml");
  }

  boolean checkValidPath(String path) {
    File f = new File(path);
    return f.exists() && !f.isDirectory();
  }

  @Override
  public boolean checkFutureDate(String date) {
    LocalDate d = LocalDate.now(ZoneId.systemDefault());
    LocalDate g2 = LocalDate.parse(date);

    return g2.isAfter(d);
  }

  @Override
  public boolean checkFileNameFormat(String portfolioName) {
    return portfolioName.matches("^[A-za-z0-9.]{1,255}$");
  }


  @Override
  public boolean checkDateFormat(String date) {
    String regex = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    if (date.matches(regex)) {
      try {
        LocalDate.parse(date);
        format.parse(date);
        return true;
      } catch (Exception e) {
        return false;
      }
    }
    return false;
  }

  @Override
  public boolean mainModCheckPathExists(String path) {
    return checkValidPath(path);
  }

  @Override
  public boolean checkTodayDate(String date) {
    LocalDate g2 = LocalDate.parse(date);

    return (g2.isEqual(LocalDate.now()));
  }

  @Override
  public boolean checkHoliday(String date) {
    LocalDate g2 = LocalDate.parse(date);
    return (g2.getDayOfWeek().toString().trim().equals("SUNDAY")
            || g2.getDayOfWeek().toString().trim().equals("SATURDAY"));
  }

  List<String> getTickerFromCSV() {
    List ticks = new ArrayList<>();

    try {
      BufferedReader br = new BufferedReader(new FileReader("listOfStockTickers.csv"));

      String line;
      while ((line = br.readLine()) != null && !line.isEmpty()) {
        String[] fields = line.split(",");
        String name = fields[0];
        ticks.add(name);
      }
      br.close();
    } catch (IOException e) {
      return ticks;
    }
    return ticks;
  }

  @Override
  public boolean checkInvestWeightFormat(String inputs) {
    try {
      String[] vals = inputs.split(",");
      String mainVal = vals[0];
      for (int i = 0; i < vals.length; i++) {
        String[] curr = vals[i].trim().split("\\s");
        String val1 = curr[0];
        String val2 = curr[1];
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
