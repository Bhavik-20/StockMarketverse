package model.database;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.stock.StockModel;
import model.stock.StockModelImpl;
import model.strategy.DollarCostImpl;
import model.strategy.InvestmentStrategy;

/**
 * This class extends the Abstract databse class and implements all the methods that can be
 * performed on database for a flexible portfolio.
 */
public class DatabaseFlexibleImpl extends AbstractDatabase {

  final String defaultPath;

  /**
   * Initializes all the values of the DatabaseFlexibleImpl object.
   *
   * @param p signifies the path that is set as default path for a file
   */
  public DatabaseFlexibleImpl(String p) {
    super();
    this.defaultPath = p;
  }


  @Override
  public String readFromPortfolio(String portfolioName, int flag) {
    return "";
  }


  @Override
  public List<StockModel> readFromFlexiPortfolio(String portfolioName, int i) {
    List<StockModel> stocks = new ArrayList<>();
    try {
      File fXmlFile = new File(defaultPath + portfolioName + ".xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList list = doc.getElementsByTagName("stock");
      for (int temp = 0; temp < list.getLength(); temp++) {
        Node node = list.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element ele = (Element) node;
          if (allTicker.contains(ele.getElementsByTagName("name").item(0).getTextContent())) {
            if (Double.parseDouble(ele.getElementsByTagName("costPrice").item(0)
                    .getTextContent()) > 0 && Double.parseDouble(ele.getElementsByTagName("fee")
                    .item(0).getTextContent()) > 0) {
              StockModel currStock = new StockModelImpl(
                      ele.getElementsByTagName("name").item(0).getTextContent(),
                      Double.parseDouble(ele.getElementsByTagName("quantity").item(0)
                              .getTextContent()),
                      Double.parseDouble(ele.getElementsByTagName("costPrice").item(0)
                              .getTextContent()),
                      ele.getElementsByTagName("purchaseDate").item(0).getTextContent(),
                      ele.getElementsByTagName("transactionType").item(0).getTextContent(),
                      Double.parseDouble(ele.getElementsByTagName("fee")
                              .item(0).getTextContent()
                      ));
              stocks.add(currStock);
            } else {
              return errorStocks(stocks);
            }
          } else {
            return errorStocks(stocks);
          }
        }
      }
      return stocks;
    } catch (Exception e) {
      if (e.toString().equals("java.lang.NullPointerException")) {
        return null;
      }
      stocks.clear();
      return stocks;
    }
  }


  @Override
  public List<InvestmentStrategy> getStrategyFromPortfolio(String portfolioName, int i) {
    List<InvestmentStrategy> strategies = new ArrayList<>();
    try {
      File fXmlFile = new File(defaultPath + portfolioName + ".xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      doc.getDocumentElement().normalize();

      NodeList list = doc.getElementsByTagName("strategy");

      for (int temp = 0; temp < list.getLength(); temp++) {
        Node node = list.item(temp);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element ele = (Element) node;
          InvestmentStrategy currStock = new DollarCostImpl(
                  ele.getElementsByTagName("startDate").item(0).getTextContent(),
                  ele.getElementsByTagName("endDate").item(0)
                          .getTextContent(),
                  ele.getElementsByTagName("ticker").item(0)
                          .getTextContent(),
                  ele.getElementsByTagName("interval").item(0)
                          .getTextContent(),
                  Double.parseDouble(ele.getElementsByTagName("investAmt")
                          .item(0).getTextContent()),
                  Double.parseDouble(ele.getElementsByTagName("fee").item(0).getTextContent()
                  ));
          strategies.add(currStock);
        }
      }
      return strategies;
    } catch (Exception e) {
      strategies.clear();
      return strategies;
    }
  }

  private List<StockModel> errorStocks(List<StockModel> stocks) {
    stocks.clear();
    StockModel error = new StockModelImpl("INVALID", 0, 0, "", "", 0);
    stocks.add(error);
    return stocks;
  }

  @Override
  protected String getDefaultPath() {
    return defaultPath;
  }
}
