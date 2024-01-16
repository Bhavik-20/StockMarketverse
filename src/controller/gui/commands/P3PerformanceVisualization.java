package controller.gui.commands;

import java.util.List;

import model.MainModel;
import view.gui.GuiView;

/**
 * Command class to execute the feature of displaying the performance of a portfolio over a period
 * of time.
 */
public class P3PerformanceVisualization implements Command {

  private String uname;
  private String portfolioName;
  private GuiView view;
  private final String startDate;
  private String endDate;

  /**
   * Constructor to initialize variables needed to execute the feature.
   *
   * @param uname         username
   * @param portfolioName portfolio name
   * @param startDate     start date of the performance evaluation
   * @param endDate       end date of the performance evaluation
   * @param view          GuiView object to display messages
   */
  public P3PerformanceVisualization(String uname, String portfolioName, String startDate,
                                    String endDate, GuiView view) {
    this.uname = uname;
    this.portfolioName = portfolioName;
    this.view = view;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public void runCommand(MainModel flexibleModel) {
    view.setRemovePanel("Page3_GetDatesPerformanceAnalysis");
    if (flexibleModel.checkDateFormat(startDate)) {
      if (flexibleModel.checkValidDateAndName(uname, portfolioName, startDate)
              .equals("Incorrect Date Format")) {
        view.showError("Error: Start date entered is an invalid date.");
      } else if (flexibleModel.checkFutureDate(startDate)) {
        view.showError("Error: The entered start date is in the future. Please enter " +
                "a past date.");
      } else {
        // validation
        if (flexibleModel.checkDateFormat(endDate)) {
          if (flexibleModel.checkValidDateAndName(uname, portfolioName, endDate)
                  .equals("Incorrect Date Format")) {
            view.showError("Error: End date entered is an invalid date.");
          } else if (flexibleModel.checkFutureDate(endDate)) {
            view.showError("Error: The entered end date is in the future. Please enter " +
                    "a past date.");
          } else {
            if (flexibleModel.returnPerformanceTimestampScale(startDate, endDate)) {
              double scale = flexibleModel.getScaleBarGraph(startDate, endDate);
              List<Integer> yaxis = flexibleModel.getNumOfStars(scale);
              List<String> xaxis = flexibleModel.getTimeStampForYaxis();
              view.printBarChart(startDate, endDate, portfolioName, xaxis, yaxis, scale);
            } else {
              view.showError("Error: The difference between the start date mentioned " +
                      "should be more than 5 days and less than 30 years.");
            }
          }
        } else {
          view.showError("Error: End date entered is an invalid date.");
        }
      }
    } else {
      view.showError("Error: The selected start date is an invalid date. Please select" +
              " a valid start date.");
    }
  }
}
