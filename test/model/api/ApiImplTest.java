package model.api;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This is a jUnit class that tests all the methods of the ApiImpl class.
 */
public class ApiImplTest {

  Api api;

  String apiKey = "POTYVCGQVTLTNYLE";
  String stockSymbol;
  URL url;
  String purDate;

  @Before
  public void setup() {
    api = new ApiImpl();
    stockSymbol = "GOOG";
    purDate = "2022-11-11";
  }

  private String apiHelper(String ss, String apk) {
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return (output.toString());
  }

  private String[][] getApiData(String s) {
    List row;
    row = List.of(s.split("\n"));

    String[][] apiInfo = new String[row.size()][6];
    for (int i = 0; i < row.size(); i++) {
      apiInfo[i] = row.get(i).toString().split(",");
    }
    return apiInfo;
  }


  @Test
  public void testGetValue() {
    assertEquals("", api.getStockValueOnDate("AAPL", "2020-12-26"));
  }
}