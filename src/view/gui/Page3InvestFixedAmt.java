package view.gui;

import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.gui.ControllerGui;

/**
 * This class represents a page that asks the user for inputs when they wish to invest a fixed
 * amount in the portfolio.
 */
class Page3InvestFixedAmt implements Page {

  private JFrame frame;
  private JPanel mainPanel;
  private JButton investBtn;
  CommissionFee.MyColors color;
  CommissionFee.DatePanel datePanel;
  CommissionFee commission;
  CommissionFee.HeadingPanel headingPanel;
  CommissionFee.InvestPanel investPanel;
  CommissionFee.StockWtSymbol stockWtPanel;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page3InvestFixedAmt(JFrame frame) {
    this.frame = frame;
    color = new CommissionFee.MyColors();

    mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
            10, 10),  new EtchedBorder()));
    mainPanel.setBackground(color.bgcol);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    headingPanel = new CommissionFee.HeadingPanel(mainPanel,"Invest a Fixed Amount in Portfolio");

    JPanel investmentPanel = new JPanel();
    investmentPanel.setLayout(new FlowLayout());
    investPanel = new CommissionFee.InvestPanel(investmentPanel);
    commission = new CommissionFee(investmentPanel);
    investPanel.setPanel();
    commission.setPanel();

    stockWtPanel = new CommissionFee.StockWtSymbol(mainPanel);

    JPanel notePanel = new JPanel();
    notePanel.setBackground(color.panecol);
    JLabel noteLabel = new JLabel("<html><center><h4 style='color: #2980B9';> NOTE: <br/> Stocks " +
            "and their dates must be entered in the following manner: TICKER1 WEIGHT1, " +
            "TICKER2 WEIGHT2, etc. <br/>Eg: AAPL 50, " +
            "GOOG 50. </h4></center></html>");
    notePanel.add(noteLabel);

    datePanel = new CommissionFee.DatePanel(mainPanel, "date: ", 2060);
    JPanel submitPanel = new JPanel();

    submitPanel.setBackground(color.panecol);
    investBtn = new JButton("Invest");
    submitPanel.add(investBtn);

    headingPanel.setPanel();
    mainPanel.add(investmentPanel);
    stockWtPanel.setPanel();
    mainPanel.add(notePanel);
    datePanel.setPanel();
    mainPanel.add(submitPanel);
  }

  @Override
  public void setPanel() {
    frame.add(mainPanel);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    frame.remove(mainPanel);
  }

  @Override
  public Map<String, String> getInputs() {
    Map<String, String> inp = new HashMap<>();
    inp.put("investAmt", investPanel.getInvestVal());
    inp.put("commFee", commission.getCommission());
    inp.put("tickerWts", stockWtPanel.getStockWt());
    inp.put("date_IFA", datePanel.yearComboBox.getSelectedItem() + "-"
            + datePanel.monthComboBox.getSelectedItem()
            + "-" + datePanel.dayComboBox.getSelectedItem());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    investBtn.addActionListener(evt -> features.goGui("IFA_submit"));
  }

  @Override
  public void resetInputs() {
    investPanel.reset();
    stockWtPanel.reset();
    commission.reset();
    datePanel.reset();
  }
}
