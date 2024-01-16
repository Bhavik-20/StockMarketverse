package model.portfolio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.database.Database;
import model.stock.StockModel;
import model.strategy.InvestmentStrategy;

/**
 * Abstract class containing the common functionalities between the Flexible portfolio and the
 * Inflexible portfolio models.
 */
public abstract class AbstractPortfolio implements PortfolioModel {

  final String defaultPath;

  String prevReadFname;
  String prevGetValParam;

  String[] prevGetValFileContents;
  String prevReadContents;

  String prevGetValDate;
  String prevVal;

  /**
   * Initializes the values of al the fields of the AbstractPortfolio object.
   */
  public AbstractPortfolio() {
    defaultPath = System.getProperty("user.dir") + "/data/";
    prevReadContents = "";
    prevReadFname = "";
    prevGetValDate = "";
    prevGetValParam = "";
    prevVal = "";
  }

  abstract Database getDatabaseObject(String uname);

  abstract int getFlagForWrite();

  @Override
  public boolean createPortfolio(String uname, String portfolioName, List<StockModel> stockList,
                                 List<InvestmentStrategy> stratList) {
    Database file = getDatabaseObject(uname);
    List<StockModel> temp = new ArrayList<>();
    temp.addAll(stockList);
    stockList.clear();
    return file.writeToPortfolio(temp, stratList, portfolioName, getFlagForWrite());
  }

  @Override
  public boolean uploadPortfolio(String uname, String path) {
    Database file = getDatabaseObject(uname);
    return file.copyFileFromPath(path);
  }

  void callDelete(File f) {
    prevReadContents = "";
    prevReadFname = "";
    prevGetValDate = "";
    prevGetValParam = "";
    prevVal = "";
    f.delete();
  }

}
