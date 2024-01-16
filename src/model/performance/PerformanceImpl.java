package model.performance;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import model.portfolio.PortfolioFlexibleImpl;
import model.portfolio.PortfolioModel;
import model.stock.StockModel;

/**
 * This class implements the PerformanceModel interface and implements the methods to generate a
 * bar graph depicting the performance of a portfolio over time.
 */
public class PerformanceImpl implements PerformanceModel {

  long timestampScale;
  String timeStampDuration;
  List<String> timeStamps;

  List<Double> valueOnTimeStamp;

  ArrayList<String> months;

  PortfolioModel portfolioModel;

  /**
   * The constructor initializes all the values of the PerformanceImpl object.
   */
  public PerformanceImpl() {
    timestampScale = 0;
    timeStampDuration = "";
    timeStamps = new ArrayList<>();
    valueOnTimeStamp = new ArrayList<>();
    months = new ArrayList<String>();
    months.addAll(List.of("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"));
    portfolioModel = new PortfolioFlexibleImpl();
  }

  @Override
  public boolean returnPerformanceTimestampScale(String fromDate, String toDate) {
    timestampScale = 0;
    timeStampDuration = "";
    timeStamps = new ArrayList<>();
    valueOnTimeStamp = new ArrayList<>();

    LocalDate fromD = LocalDate.parse(fromDate);
    LocalDate toD = LocalDate.parse(toDate);
    long diff = ChronoUnit.DAYS.between(fromD, toD);

    if (diff < 1) {
      return false; //error
    } else if (diff >= 1 && diff <= 30) {
      timeStampDuration = "days";
      timestampScale = 1;
      populateTimeStamp(timestampScale, fromD, toD);
    } else if (diff > 30 && diff <= 31 * 5) {
      timeStampDuration = "days";
      timestampScale = (int) Math.ceil((double) diff / 30);
      populateTimeStamp(timestampScale, fromD, toD);
    } else if (diff > 31 * 5 && diff <= 31 * 30) {
      timeStampDuration = "months";
      timestampScale = 1;
      populateTimeStamp(31, fromD, toD);
    } else if (diff > 31 * 30 && diff <= 31 * 30 * 2) { // 30 months - 60 months
      timestampScale = 5; //avg of 30 and 60 - 30
      timeStampDuration = "months";
      populateTimeStamp(31 * timestampScale, fromD, toD);
    } else if (diff > 31 * 30 * 2 && diff <= 31 * 12 * 30) {
      timestampScale = 1;
      timeStampDuration = "years";
      populateTimeStamp(366, fromD, toD);
    } else {
      return false;
    }
    return true;
  }

  private void populateTimeStamp(long scale, LocalDate fromDate, LocalDate toDate) {
    LocalDate temp = fromDate;

    LocalDate newDate = YearMonth.from(toDate).atEndOfMonth();
    if (timeStampDuration.equals("months") && LocalDate.now().isBefore(newDate)) {
      toDate = LocalDate.now().minusDays(1);
    } else if (timeStampDuration.equals("months")) {
      toDate = newDate;
    } else if (timeStampDuration.equals("years")) {
      String endOfYear;
      if (toDate.getYear() == LocalDate.now().getYear()) {
        endOfYear = toDate.getYear() + "-" + (toDate.getMonthValue() - 1) + "-31";
      } else {
        endOfYear = toDate.getYear() + "-12-31";
      }
      toDate = LocalDate.parse(endOfYear);
    }

    while (!temp.isAfter(toDate)) {
      int year = temp.getYear();

      if (timeStampDuration.equals("days")) {
        timeStamps.add(temp.toString());
      } else if (timeStampDuration.equals("months")) {
        String month = months.get(temp.getMonthValue() - 1);
        timeStamps.add(month + " " + year);
      } else if (timeStampDuration.equals("years")) {
        timeStamps.add(String.valueOf(year));
      }
      temp = temp.plusDays(scale);
    }
  }

  @Override
  public double getScaleForBarGraph(String fromDate, String tillDate, List<StockModel> currData) {

    LocalDate fromD = LocalDate.parse(fromDate);
    LocalDate toD = LocalDate.parse(tillDate);
    String val;
    LocalDate temp = fromD;
    double maxVal = 0.0;
    double minVal = 2147483647;

    LocalDate newDate = YearMonth.from(toD).atEndOfMonth();
    if (timeStampDuration.equals("months") && LocalDate.now().isBefore(newDate)) {
      toD = LocalDate.now().minusDays(1);
    } else if (timeStampDuration.equals("months")) {
      toD = newDate;
    }

    if (timeStampDuration.equals("days")) {
      while (!temp.isAfter(toD)) {
        val = portfolioModel.getFlexiValueOnDate(temp.toString(), currData);
        maxVal = Math.max(maxVal, Double.parseDouble(val));
        minVal = Math.min(minVal, Double.parseDouble(val));
        valueOnTimeStamp.add(Double.parseDouble(val));
        temp = temp.plusDays(1);
      }
    } else if (timeStampDuration.equals("months")) {

      while (!temp.isAfter(toD)) {
        LocalDate endOfMonth = YearMonth.from(temp).atEndOfMonth();

        if (LocalDate.now().isBefore(endOfMonth)) {
          endOfMonth = LocalDate.now();
        }

        //        if (endOfMonth.getDayOfWeek().toString().trim().equals("SUNDAY")) {
        //          endOfMonth = endOfMonth.minusDays(2);
        //        } else if (endOfMonth.getDayOfWeek().toString().trim().equals("SATURDAY")) {
        //          endOfMonth = endOfMonth.minusDays(1);
        //        }
        val = portfolioModel.getFlexiValueOnDate(endOfMonth.toString(), currData);
        maxVal = Math.max(maxVal, Double.parseDouble(val));
        minVal = Math.min(minVal, Double.parseDouble(val));
        valueOnTimeStamp.add(Double.parseDouble(val));
        temp = temp.plusDays(31);
      }
    } else if (timeStampDuration.equals("years")) {
      // check if weekend ?
      String endOfYear;

      if (toD.getYear() == LocalDate.now().getYear()) {
        if (LocalDate.now().getMonthValue() == 1) {
          toD = LocalDate.parse(LocalDate.now().getYear() - 1 + "-12-31");
        } else {
          toD = LocalDate.parse(toD.getYear() + "-" + (toD.getMonthValue() - 1) + "-01");
        }
      }
      while (!temp.isAfter(toD)) {
        if (temp.getYear() == LocalDate.now().getYear()) {
          endOfYear = String.valueOf(toD);
        } else {
          endOfYear = temp.getYear() + "-12-31";
        }
        LocalDate eoy = LocalDate.parse(endOfYear);
        val = portfolioModel.getFlexiValueOnDate(eoy.toString(), currData);
        maxVal = Math.max(maxVal, Double.parseDouble(val));
        minVal = Math.min(minVal, Double.parseDouble(val));
        valueOnTimeStamp.add(Double.parseDouble(val));
        temp = temp.plusDays(366);
      }
    }
    return maxVal / 50;
  }

  @Override
  public List<String> getTimeStampForYaxis() {
    return timeStamps;
  }

  @Override
  public List<Double> getValueTimeStampForXaxis() {
    return valueOnTimeStamp;
  }

}
