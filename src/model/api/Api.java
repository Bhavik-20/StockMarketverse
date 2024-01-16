package model.api;

/**
 * This interface defines the methods which are used to fetch different company ticker symbols and
 * their values on a particular date from the AlphaVantage API.
 */
public interface Api {

  //  /**
  //   * This method is used to fetch data by making a call to the API.
  //   *
  //   * @return a string that consists of value of a particular company on all past dates.
  //   */
  //  String callApi();

  /**
   * This method is used to get the value of a stock on a particular date.
   *
   * @return a double value that signifies the value of the stock on a given date.
   */
  double getStockValueOnDate(String stockName, String purDate);
}
