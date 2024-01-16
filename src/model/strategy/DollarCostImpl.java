package model.strategy;

/**
 * A class that represents an object of the Dollar cost average strategy.
 */
public class DollarCostImpl implements InvestmentStrategy {
  private String startDate;
  private String endDate;
  private String ticker;
  private String interval;
  private double investAmt;
  private double fee;

  /**
   * Constructor to initialize an object of the strategy.
   *
   * @param sDate     start date
   * @param eDate     end date
   * @param ticker    string of stock symbols along with their weights
   * @param interval  frequency at which buying needs to be done
   * @param investAmt total investment amount
   * @param fee       commission fee per transaction
   */
  public DollarCostImpl(String sDate, String eDate, String ticker, String interval,
                        double investAmt, double fee) {
    this.startDate = sDate;
    this.endDate = eDate;
    this.ticker = ticker;
    this.interval = interval;
    this.investAmt = investAmt;
    this.fee = fee;
  }

  @Override
  public String getStartDate() {
    return this.startDate;
  }

  @Override
  public String getEndDate() {
    return this.endDate;
  }

  @Override
  public String getTicker() {
    return this.ticker;
  }

  @Override
  public String getInterval() {
    return this.interval;
  }

  @Override
  public double getInvestAmt() {
    return this.investAmt;
  }

  @Override
  public double getCommissionFee() {
    return this.fee;
  }
}