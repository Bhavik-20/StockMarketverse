package model.stock;

/**
 * This interface signifies a stock value in the portfolio which consists of a ticker symbol and
 * the quantity of that stock.
 */
public interface StockModel {

  /**
   * This method is used to get the ticker symbol of a stock.
   *
   * @return a string value that signifies a ticker symbol
   */
  String getTicker();

  /**
   * This method is used to get the quantity of a stock.
   *
   * @return a double value that depicts the quantity of a stock
   */
  double getQuantity();

  /**
   * This method is used to get the cost price of a stock.
   *
   * @return a double value that depicts the cost price of a stock
   */
  double getCostPrice();

  /**
   * This method is used to get the date at which a stock was purchased.
   *
   * @return a string value that denotes a value that depicts the date at which a stock was
   *         purchased
   */
  String getPurDate();

  /**
   * This method is used to get the type of transaction user has selected.
   *
   * @return a string value that denotes the type of transaction
   */
  String getTransaction();

  /**
   * This method is used to get the commission fee of transaction user has selected.
   *
   * @return double value representing the fee
   */
  double getCommissionFee();
}
