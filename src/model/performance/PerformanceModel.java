package model.performance;

import java.util.List;

import model.stock.StockModel;

/**
 * This interface defines the methods which are used to generate a bar graph depicting the
 * performance of a portfolio over time.
 */
public interface PerformanceModel {

  /**
   * This method is used to calculate the scale of time stamp eg: days, months and years.
   *
   * @param fromDate signifies the date at which the performance visualization must begin
   * @param toDate   signifies the date till which the performance visualization should be depicted
   * @return true if the scale is calculated properly, else returns false
   */
  boolean returnPerformanceTimestampScale(String fromDate, String toDate);

  /**
   * This method is used to retrieve the scale of the bar graph for x-axis, i.e. the value of a *.
   *
   * @param fromDate signifies the date at which the performance visualization must begin
   * @param tillDate signifies the date till which the performance visualization should be depicted
   * @param currData depicts a list of stock model objects
   * @return a value that depics the scale of bar graph for x-axis
   */
  double getScaleForBarGraph(String fromDate, String tillDate, List<StockModel> currData);

  /**
   * This method is used to retrieve a list of time stamps for the y-axis.
   *
   * @return a list of time stamps for the y-axis
   */
  List<String> getTimeStampForYaxis();

  /**
   * This method is used to retrieve a list of time stamp values for x-axis.
   *
   * @return a list of values depicting the value of portfolio on corresponding time stamp.
   */
  List<Double> getValueTimeStampForXaxis();

}
