package model.database;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.stock.StockModel;
import model.strategy.InvestmentStrategy;

/**
 * Abstract class containing the common functionalities between the Flexible portfolio and the
 * Inflexible portfolio models' database.
 */
abstract class AbstractDatabase implements Database {

  List<String> allTicker;

  /**
   * Initializes the allTicker list to a list of all tickers that the program can support.
   */
  public AbstractDatabase() {
    this.allTicker = getTickerFromCSV();
  }

  protected List<String> getTickerFromCSV() {
    List ticks = new ArrayList<>();

    try {
      BufferedReader br = new BufferedReader(
              new FileReader("listOfStockTickers.csv"));

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
  public boolean writeToPortfolio(List<StockModel> stockList, List<InvestmentStrategy> stratList,
                                  String portfolioName, int flag) {
    try {
      this.writeHelper(stockList, stratList, portfolioName, flag);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  protected void writeHelper(List<StockModel> stockList, List<InvestmentStrategy> stratList,
                             String portfolioName, int flag)
          throws Exception {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.newDocument();

    Element rootPortfolio = doc.createElement("portfolio");
    doc.appendChild(rootPortfolio);

    if (flag == 2) {
      for (int i = 0; i < stratList.size(); i++) {
        Element rootStrat = doc.createElement("strategy");
        rootStrat.setAttribute("type", "dca");
        rootPortfolio.appendChild(rootStrat);

        Element startDate = doc.createElement("startDate");
        startDate.appendChild(doc.createTextNode(stratList.get(i).getStartDate()));
        rootStrat.appendChild(startDate);

        Element endDate = doc.createElement("endDate");
        endDate.appendChild(doc.createTextNode(stratList.get(i).getEndDate()));
        rootStrat.appendChild(endDate);

        Element ticker = doc.createElement("ticker");
        ticker.appendChild(doc.createTextNode(stratList.get(i).getTicker()));
        rootStrat.appendChild(ticker);

        Element interval = doc.createElement("interval");
        interval.appendChild(doc.createTextNode(stratList.get(i).getInterval()));
        rootStrat.appendChild(interval);

        Element investAmt = doc.createElement("investAmt");
        investAmt.appendChild(doc.createTextNode(String.valueOf(stratList.get(i).getInvestAmt())));
        rootStrat.appendChild(investAmt);

        Element fee = doc.createElement("fee");
        fee.appendChild(doc.createTextNode(String.valueOf(stratList.get(i).getCommissionFee())));
        rootStrat.appendChild(fee);
      }
    }

    for (int i = 0; i < stockList.size(); i++) {
      Element rootStock = doc.createElement("stock");
      rootPortfolio.appendChild(rootStock);

      Element name = doc.createElement("name");
      name.appendChild(doc.createTextNode(stockList.get(i).getTicker()));
      rootStock.appendChild(name);

      Element quant = doc.createElement("quantity");
      quant.appendChild(doc.createTextNode(stockList.get(i).getQuantity() + ""));
      rootStock.appendChild(quant);

      if (flag == 2) {
        Element cP = doc.createElement("costPrice");
        cP.appendChild(doc.createTextNode(stockList.get(i).getCostPrice() + ""));
        rootStock.appendChild(cP);

        Element pD = doc.createElement("purchaseDate");
        pD.appendChild(doc.createTextNode(stockList.get(i).getPurDate() + ""));
        rootStock.appendChild(pD);

        Element type = doc.createElement("transactionType");
        type.appendChild(doc.createTextNode(stockList.get(i).getTransaction()));
        rootStock.appendChild(type);

        Element fee = doc.createElement("fee");
        fee.appendChild(doc.createTextNode(stockList.get(i).getCommissionFee() + ""));
        rootStock.appendChild(fee);
      }
    }

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    Files.deleteIfExists(Path.of(getDefaultPath() + portfolioName + ".xml"));
    StreamResult result =
            new StreamResult(new File(getDefaultPath() + portfolioName + ".xml"));
    transformer.transform(source, result);
  }

  @Override
  public boolean copyFileFromPath(String fpath) {
    try {
      String[] f = fpath.split("/");

      Path src = Paths.get(fpath);
      Path dest = Paths.get(getDefaultPath() + f[f.length - 1]);
      Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public abstract List<InvestmentStrategy> getStrategyFromPortfolio(String portfolioName, int i);

  protected abstract String getDefaultPath();
}
