package model.database;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import model.strategy.InvestmentStrategy;

/**
 * This class implements the FileIO interface and implements various read and write methods on a
 * file to write and read the composition of a portfolio to and from the website.
 */
public class DatabaseImmutableImpl extends AbstractDatabase {

  private final String defaultPath;

  /**
   * This constructor initializes the default path of a file to the given path.
   *
   * @param p signifies the path that is set as default path for a file
   */
  public DatabaseImmutableImpl(String p) {
    super();
    this.defaultPath = p;
  }

  @Override
  public List<InvestmentStrategy> getStrategyFromPortfolio(String portfolioName, int i) {
    return null;
  }

  @Override
  protected String getDefaultPath() {
    return defaultPath;
  }

  @Override
  public String readFromPortfolio(String portfolioName, int flag) {
    try {
      StringBuilder sb = new StringBuilder("");
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
            sb.append(ele.getElementsByTagName("name").item(0).getTextContent());
            sb.append(" -> ");
            sb.append(ele.getElementsByTagName("quantity").item(0).getTextContent()
                    + " stocks " + "\n");
          } else {
            return "Portfolio contains invalid ticker symbols";
          }
        }
      }
      return sb.toString();
    } catch (Exception e) {
      if (e.toString().equals("java.lang.NullPointerException")) {
        return "Incorrect file format";
      }
      return "File Not Found";
    }
  }

  @Override
  public List readFromFlexiPortfolio(String portfolioName, int i) {
    return null;
  }
}
