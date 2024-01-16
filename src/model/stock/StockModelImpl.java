package model.stock;

/**
 * This class implements the stock interface and is used to get the ticker symbol and quantity of
 * a stock.
 */
public class StockModelImpl implements StockModel {

  private final String ticker;
  private final double quant;
  private final double costPrice;
  private final String purDate;
  private final String transaction;
  private final double commissionFee;


  /**
   * This constructor initializes the ticker symbol and quantity of a stock to the given values.
   *
   * @param t a string value that signifies a ticker symbol
   * @param q a double value that signifies the quantity of a stock
   */
  public StockModelImpl(String t, double q, double cP, String pD, String tT, double commFee) {
    this.ticker = t;
    this.quant = q;
    this.costPrice = cP;
    this.purDate = pD;
    this.transaction = tT;
    this.commissionFee = commFee;
  }

  @Override
  public String getTicker() {
    return this.ticker;
  }

  @Override
  public double getQuantity() {
    return this.quant;
  }

  @Override
  public double getCostPrice() {
    return this.costPrice;
  }

  @Override
  public String getPurDate() {
    return this.purDate;
  }

  @Override
  public String getTransaction() {
    return this.transaction;
  }

  @Override
  public double getCommissionFee() {
    return this.commissionFee;
  }
}
