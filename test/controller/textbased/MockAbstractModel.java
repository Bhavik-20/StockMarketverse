package controller.textbased;

import java.util.List;

import model.MainModel;

/**
 * This class is a Mock model for the actual MainAbstractModel to help test controller in isolation.
 */
public abstract class MockAbstractModel implements MainModel {

  protected StringBuilder log;
  protected final boolean uniqueBool;

  /**
   * Constructor initializes all the fields of the AbstractMainModel object.
   */
  public MockAbstractModel(StringBuilder log, boolean uniqueBool) {
    this.log = log;
    this.uniqueBool = uniqueBool;
  }

  @Override
  public boolean userDirectoryExist(String uname) {
    log.append(" " + uname);
    return uniqueBool;
  }

  @Override
  public boolean checkFileIsXML(String path) {
    log.append(" " + path);
    return uniqueBool;
  }

  boolean checkValidPath(String path) {
    log.append(" " + path);
    return uniqueBool;
  }

  @Override
  public boolean checkFutureDate(String date) {
    log.append(" " + date);
    return uniqueBool;
  }

  @Override
  public boolean checkFileNameFormat(String portfolioName) {
    log.append(" " + portfolioName);
    return uniqueBool;
  }


  @Override
  public boolean checkDateFormat(String date) {
    log.append(" " + date);
    return uniqueBool;
  }

  @Override
  public boolean mainModCheckPathExists(String path) {
    log.append(" " + path);
    return uniqueBool;
  }

  @Override
  public boolean checkTodayDate(String date) {
    log.append(" " + date);
    return uniqueBool;
  }

  @Override
  public boolean checkHoliday(String date) {
    log.append(" " + date);
    return uniqueBool;
  }

  List<String> getTickerFromCSV() {
    return null;
  }

}
