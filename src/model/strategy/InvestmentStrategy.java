package model.strategy;

/**
 * An interface that supports high-level investment strategies like Dollar cost average.
 */
public interface InvestmentStrategy {

  /**
   * Signifies the start date of the strategy.
   *
   * @return date in string
   */
  String getStartDate();

  /**
   * Signifies the end date of the strategy.
   *
   * @return date in string
   */
  String getEndDate();

  /**
   * Signifies a string consisting of ticker and its weights like "TICKER1 WEIGHT1, TICKER2
   * WEIGHT2".
   *
   * @return string in the above format
   */
  String getTicker();

  /**
   * Signifies the interval at which strategy must be executed.
   *
   * @return value of interval in string
   */
  String getInterval();

  /**
   * Signifies the investment amount in the strategy.
   *
   * @return investment amount in double
   */
  double getInvestAmt();

  /**
   * Signifies the commission fee involved with the strategy.
   *
   * @return commission fee in double
   */
  double getCommissionFee();
}
