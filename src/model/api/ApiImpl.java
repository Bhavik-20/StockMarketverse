package model.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements the API interface and contains all the methods that can be performed on
 * the AlphaVantage API.
 */
public class ApiImpl implements Api {
  final String apiKey;
  URL url = null;
  HashMap<String, Map<String, String>> cache;


  /**
   * This constructor defines the stock symbol and the date for the stock object.
   */
  public ApiImpl() {
    cache = new HashMap<>();
    apiKey = "POTYVCGQVTLTNYLE";
  }


  private String callApi(String stockSymbol) {
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

  private String[][] getApiData(String s, String stockSymbol) {
    List row;
    row = List.of(s.split("\n"));
    String[][] apiInfo = new String[row.size()][6];
    Map<String, String> temp = new HashMap<>();
    for (int i = 0; i < row.size(); i++) {

      apiInfo[i] = row.get(i).toString().split(",");
      temp.put(apiInfo[i][0], apiInfo[i][4]);
      cache.put(stockSymbol, temp);
    }
    return apiInfo;
  }

  @Override
  public double getStockValueOnDate(String stockSymbol, String date) {
    LocalDate dateObj = LocalDate.parse(date);
    date = dateObj.toString();
    if (cache.containsKey(stockSymbol)) {
      if (cache.get(stockSymbol).containsKey(date)) {
        double amt = Double.parseDouble(cache.get(stockSymbol).get(date));
        return amt;
      } else {
        return getStockValueOnDate(stockSymbol, LocalDate.parse(date).plusDays(1).toString());
      }
    }
    String apiOutput = callApi(stockSymbol);
    if (!apiOutput.contains("No price data found for")) {
      String[][] data = getApiData(apiOutput, stockSymbol);
      for (int i = 0; i < data.length; i++) {
        if (data[i][0].contains(date)) {
          double amt = Double.parseDouble(data[i][4]);
          return amt;
        }
      }
    }
    if (dateObj.isEqual(LocalDate.now())) {
      return getStockValueOnDate(stockSymbol, dateObj.minusDays(1).toString());
    }
    return getStockValueOnDate(stockSymbol, LocalDate.parse(date).plusDays(1).toString());
  }
}
