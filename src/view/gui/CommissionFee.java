package view.gui;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

class CommissionFee {

  private JPanel frame;
  private JPanel commissionFeePanel;
  private JTextField commission;
  MyColors colors;

  public CommissionFee(JPanel frame) {
    this.frame = frame;
    commissionFeePanel = new JPanel();
    commissionFeePanel.setLayout(new FlowLayout());
    //    commissionFeePanel.setBackground(colors.panecol);
    JLabel enterCF = new JLabel("Enter Commission Fee: $");
    commission = new JTextField(5);
    commission.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()
                == KeyEvent.VK_BACK_SPACE) {
          commission.setEditable(true);
        } else {
          Error er = new Error();
          er.showError(" Only numeric values allowed!");
          commission.setText("");
        }
      }
    });
    commissionFeePanel.add(enterCF);
    commissionFeePanel.add(commission);
  }

  public void setPanel() {
    frame.add(commissionFeePanel);
    frame.setVisible(true);
  }

  public void removePanel() {
    frame.removeAll();
  }

  public String getCommission() {
    return commission.getText();
  }

  public void reset() {
    commission.setText("");
  }

  static class DatePanel {

    public JComboBox<String> yearComboBox;
    public JComboBox<String> monthComboBox;
    public JComboBox<String> dayComboBox;

    MyColors colors;
    private JPanel frame;
    private JPanel datePanel;

    public DatePanel(JPanel frame, String var, int year) {

      this.frame = frame;
      colors = new MyColors();

      datePanel = new JPanel();
      datePanel.setLayout(new FlowLayout());
      datePanel.setBackground(colors.panecol);
      JLabel enterDate = new JLabel("Select " + var);
      //new JLabel("Select the date at which you wish to get the value of the portfolio:");
      datePanel.add(enterDate);

      JLabel ddLabel = new JLabel("dd: ");
      datePanel.add(ddLabel);

      dayComboBox = new JComboBox<String>();
      for (int i = 1; i < 32; i++) {
        if (i >= 10) {
          dayComboBox.addItem(i + "");
        } else {
          dayComboBox.addItem("0" + i);
        }
      }
      datePanel.add(dayComboBox);

      JLabel mmLabel = new JLabel("mm: ");
      datePanel.add(mmLabel);

      monthComboBox = new JComboBox<String>();
      for (int i = 1; i < 13; i++) {
        if (i >= 10) {
          monthComboBox.addItem(i + "");
        } else {
          monthComboBox.addItem("0" + i);
        }
      }
      datePanel.add(monthComboBox);

      JLabel yyyyLabel = new JLabel("yyyy: ");
      datePanel.add(yyyyLabel);

      yearComboBox = new JComboBox<String>();
      for (int i = year; i > 1940; i--) {
        yearComboBox.addItem(i + "");
      }
      datePanel.add(yearComboBox);
    }

    public void setPanel() {
      frame.add(datePanel);
      frame.setVisible(true);
    }

    public void removePanel() {
      frame.remove(datePanel);
    }

    public void reset() {
      dayComboBox.setSelectedItem(null);
      yearComboBox.setSelectedItem(null);
      monthComboBox.setSelectedItem(null);
    }
  }

  static class Error {

    public Error() {
      // nothing to set
    }

    public void showError(String s) {
      JOptionPane.showMessageDialog(null, s, "An Error Occurred!",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  static class HeadingPanel {

    private final JPanel frame;
    private JPanel headingPanel;

    MyColors colors;

    public HeadingPanel(JPanel frame, String str) {
      this.frame = frame;
      headingPanel = new JPanel();

      colors = new MyColors();
      headingPanel.setBackground(colors.panecol);

      JLabel title = new JLabel("<html><h2 style = 'color: #2980B9'>" + str + "</h2></html>");

      headingPanel.add(title);
    }

    public void setPanel() {
      frame.add(headingPanel);
      frame.setVisible(true);
    }

    public void removePanel() {
      frame.removeAll();
    }

  }

  static class InvestPanel {

    private JPanel frame;
    private final JPanel investPanel;
    private JTextField investVal;
    MyColors colors;

    public InvestPanel(JPanel frame) {
      this.frame = frame;
      investPanel = new JPanel();
      investPanel.setLayout(new FlowLayout());
      JLabel enterInvestVal = new JLabel("Enter Investment Value: $");
      investVal = new JTextField(5);
      investVal.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent ke) {
          if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()
                  == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() <= '.') {
            investVal.setEditable(true);
          } else {
            Error er = new Error();
            er.showError(" Only numeric values allowed!");
            investVal.setText("");
          }
        }
      });
      investPanel.add(enterInvestVal);
      investPanel.add(investVal);
    }

    public void setPanel() {
      frame.add(investPanel);
      frame.setVisible(true);
    }

    public void removePanel() {
      frame.removeAll();
    }

    public String getInvestVal() {
      return investVal.getText();
    }

    public void reset() {
      investVal.setText("");
    }
  }

  static class MyColors {

    public final Color bgcol;
    public final Color panecol;

    public MyColors() {
      bgcol = new Color(0xAED6F1);
      panecol = new Color(0xEAEDED);
    }
  }

  static class TickerSymbol {

    private JPanel frame;

    private JPanel tickerPanel;
    private JTextField tickerInput;
    MyColors colors;

    public TickerSymbol(JPanel frame) {
      this.frame = frame;
      tickerPanel = new JPanel();
      tickerPanel.setLayout(new FlowLayout());
      colors = new MyColors();
      tickerPanel.setBackground(colors.panecol);
      JLabel tickerLabel = new JLabel("Enter the ticker symbol: ");
      tickerInput = new JTextField(10);
      tickerPanel.add(tickerLabel);
      tickerPanel.add(tickerInput);
    }

    public void setPanel() {
      frame.add(tickerPanel);
      frame.setVisible(true);
    }

    public void removePanel() {
      frame.removeAll();
    }

    public String getTicker() {
      return tickerInput.getText();
    }

    public void reset() {
      tickerInput.setText("");
    }
  }

  static class StockWtSymbol {

    private JPanel frame;
    private JPanel stockWtPanel;
    private JTextArea stockWt;

    public StockWtSymbol(JPanel frame) {
      this.frame = frame;
      stockWtPanel = new JPanel();
      stockWtPanel.setLayout(new FlowLayout());
      JLabel stockWtLabel = new JLabel("Enter Stocks and their Weights:");
      stockWt = new JTextArea(4, 30);
      stockWtPanel.add(stockWtLabel);
      stockWtPanel.add(stockWt);
    }

    public void setPanel() {
      frame.add(stockWtPanel);
      frame.setVisible(true);
    }

    public void removePanel() {
      frame.removeAll();
    }

    public String getStockWt() {
      return stockWt.getText();
    }

    public void reset() {
      stockWt.setText("");
    }
  }

  static class QtyStock {

    private JPanel frame;

    private JPanel qtyPanel;
    private JTextField qtyVal;
    MyColors colors;

    public QtyStock(JPanel frame) {
      this.frame = frame;
      qtyPanel = new JPanel();
      qtyPanel.setLayout(new FlowLayout());
      colors = new MyColors();
      qtyPanel.setBackground(colors.panecol);
      JLabel qtyLabel = new JLabel("Enter quantity: ");
      qtyVal = new JTextField(10);

      qtyVal.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent ke) {
          if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()
                  == KeyEvent.VK_BACK_SPACE) {
            qtyVal.setEditable(true);
          } else {
            Error er = new Error();
            er.showError(" Only numeric values allowed!");
            qtyVal.setText("");
          }
        }
      });
      qtyPanel.add(qtyLabel);
      qtyPanel.add(qtyVal);
    }

    public void setPanel() {
      frame.add(qtyPanel);
      frame.setVisible(true);
    }

    public void removePanel() {
      frame.removeAll();
    }

    public String getQty() {
      return qtyVal.getText();
    }

    public void reset() {
      qtyVal.setText("");
    }
  }

  static class PrintPortfolioScrollPanel {

    private JScrollPane scrollPanel;
    private JFrame frame;

    private JPanel mainPanel;

    public PrintPortfolioScrollPanel(JFrame frame, JTable jtable) {
      this.frame = frame;
      mainPanel = new JPanel();
      mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
              10, 10), new EtchedBorder()));

      scrollPanel = new JScrollPane(jtable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
              JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPanel.setPreferredSize(new Dimension(200, 300));

      mainPanel.add(scrollPanel);
    }

    public void setPanel() {
      frame.add(mainPanel);
      frame.setVisible(true);
    }

    public void removePanel() {
      frame.remove(scrollPanel);
    }
  }

  static class PrintBarChart {
    private JFrame frame;

    private JPanel mainPanel;

    MyColors colors;

    ChartPanel chartPanel;

    public PrintBarChart(JFrame frame, String startDate, String endDate, String portfolioName,
                         java.util.List<String> xaxis, List<Integer> yaxis, double scale) {
      this.frame = frame;
      mainPanel = new JPanel();
      colors = new MyColors();
      mainPanel.setBackground(colors.bgcol);
      mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,
              10, 10), new EtchedBorder()));


      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      String legend = "portfolio-value";
      for (int i = 0; i < xaxis.size(); i++) {
        dataset.addValue(yaxis.get(i) * scale, legend, xaxis.get(i));
      }

      JFreeChart barChart = ChartFactory.createBarChart("Performance of portfolio "
                      + portfolioName + " from " + startDate + " to " +
                      endDate, "Duration", "Value in Dollars ($)",
              dataset,
              PlotOrientation.HORIZONTAL,
              true, true, false);

      CategoryPlot plot = barChart.getCategoryPlot();
      plot.getRenderer().setSeriesPaint(0, new Color(0x005B96));

      chartPanel = new ChartPanel(barChart);
      chartPanel.setPreferredSize(new Dimension(650, 450));
      JScrollPane scrollPane = new JScrollPane(chartPanel);
      mainPanel.add(scrollPane);
    }

    public void setPanel() {
      frame.add(mainPanel, BorderLayout.CENTER);
      frame.setVisible(true);
    }

    public void removePanel() {
      frame.removeAll();
    }

  }
}
