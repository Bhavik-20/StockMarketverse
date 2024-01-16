package view.gui;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import controller.gui.ControllerGui;

/**
 * This class represents the page that asks user to get the inputs to apply dollar cost
 * averaging strategy to a portfolio.
 */
class Page3GetDCAInputs implements Page {

  private JFrame frame;
  private JPanel mainPanel;
  private JTextField freqVal;
  private JButton investBtn;
  private JComboBox<String> duration;
  private JCheckBox isNeverEnding;
  CommissionFee.MyColors color;
  CommissionFee.DatePanel startDatePanel;
  CommissionFee.DatePanel endDatePanel;
  CommissionFee commission;
  CommissionFee.HeadingPanel headingPanel;
  CommissionFee.InvestPanel investPanel;
  CommissionFee.StockWtSymbol stockWtPanel;

  /**
   * The constructor sets up the panel which can be added to the main frame.
   *
   * @param frame frame signifies JFrame which represents the application window.
   */
  public Page3GetDCAInputs(JFrame frame) {
    this.frame = frame;
    color = new CommissionFee.MyColors();

    mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
            10, 10), new EtchedBorder()));
    mainPanel.setBackground(color.bgcol);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    headingPanel = new CommissionFee.HeadingPanel(mainPanel, "Apply Recurring Dollar Cost "
            + "Avg Strategy" +
            " to Portfolio");

    startDatePanel = new CommissionFee.DatePanel(mainPanel, "start date:", 2060);

    JPanel ongoingPanel = new JPanel();
    ongoingPanel.setLayout(new FlowLayout());

    endDatePanel = new CommissionFee.DatePanel(ongoingPanel, "end date:", 2060);
    endDatePanel.setPanel();

    isNeverEnding = new JCheckBox("Is this an ongoing strategy? ");
    isNeverEnding.addActionListener(evt -> {
      if (isNeverEnding.isSelected()) {
        endDatePanel.removePanel();
        frame.repaint();
      } else {
        endDatePanel.setPanel();
        frame.repaint();
      }
    });
    ongoingPanel.add(isNeverEnding);

    JPanel investmentPanel = new JPanel();
    investmentPanel.setLayout(new FlowLayout());
    investPanel = new CommissionFee.InvestPanel(investmentPanel);
    commission = new CommissionFee(investmentPanel);
    investPanel.setPanel();
    commission.setPanel();

    JPanel freqPanel = new JPanel();
    freqPanel.setLayout(new FlowLayout());
    JLabel freqLabel = new JLabel("Enter frequency of recurrence: ");
    freqVal = new JTextField(10);
    freqVal.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()
                == KeyEvent.VK_BACK_SPACE) {
          freqVal.setEditable(true);
        } else {
          CommissionFee.Error er = new CommissionFee.Error();
          er.showError(" Only numeric values allowed!");
          freqVal.setText("");
        }
      }
    });

    String[] options = {"days", "months", "years"};
    duration = new JComboBox<String>();
    for (int i = 0; i < options.length; i++) {
      duration.addItem(options[i]);
    }
    freqPanel.add(freqLabel);
    freqPanel.add(freqVal);
    freqPanel.add(duration);

    stockWtPanel = new CommissionFee.StockWtSymbol(mainPanel);

    JPanel notePanel = new JPanel();
    notePanel.setBackground(color.panecol);
    JLabel noteLabel = new JLabel("<html><center><h4 style='color: #2980B9';> NOTE: Stocks " +
            "and their dates must be entered in the following manner: TICKER1 WEIGHT1, " +
            "TICKER2 WEIGHT2, etc. <br/>Eg: AAPL 50, " +
            "GOOG 50. </h4></center></html>");
    notePanel.add(noteLabel);


    JPanel submitPanel = new JPanel();

    submitPanel.setBackground(color.panecol);
    investBtn = new JButton("Invest");
    submitPanel.add(investBtn);

    headingPanel.setPanel();
    mainPanel.add(investmentPanel);
    startDatePanel.setPanel();
    mainPanel.add(ongoingPanel);
    mainPanel.add(freqPanel);
    stockWtPanel.setPanel();
    mainPanel.add(notePanel);
    mainPanel.add(submitPanel);
  }

  @Override
  public void setPanel() {
    frame.add(mainPanel, BorderLayout.CENTER);
    frame.setVisible(true);
  }

  @Override
  public void removePanel() {
    frame.remove(mainPanel);
  }

  @Override
  public Map<String, String> getInputs() {
    Map<String, String> inp = new HashMap<>();
    inp.put("startDate", startDatePanel.yearComboBox.getSelectedItem() + "-"
            + startDatePanel.monthComboBox.getSelectedItem()
            + "-" + startDatePanel.dayComboBox.getSelectedItem());
    if (isNeverEnding.isSelected()) {
      inp.put("endDate", "2060-12-31");
    } else {
      inp.put("endDate", endDatePanel.yearComboBox.getSelectedItem() + "-"
              + endDatePanel.monthComboBox.getSelectedItem()
              + "-" + endDatePanel.dayComboBox.getSelectedItem());
    }
    inp.put("freq", freqVal.getText() + " " + duration.getSelectedItem());
    inp.put("investAmt", investPanel.getInvestVal());
    inp.put("commFee", commission.getCommission());
    inp.put("tickerWts", stockWtPanel.getStockWt());
    return inp;
  }

  @Override
  public void addFeatures(ControllerGui features) {
    investBtn.addActionListener(evt -> features.goGui("DCA_submit"));
  }

  @Override
  public void resetInputs() {
    investPanel.reset();
    freqVal.setText("");
    stockWtPanel.reset();
    commission.reset();
    startDatePanel.reset();
    endDatePanel.reset();
  }
}
