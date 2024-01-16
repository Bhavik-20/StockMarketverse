[1mdiff --git a/res/Screenshots.zip b/res/Screenshots.zip[m
[1mdeleted file mode 100644[m
[1mindex 7d3b063..0000000[m
Binary files a/res/Screenshots.zip and /dev/null differ
[1mdiff --git a/src/Main.java b/src/Main.java[m
[1mindex 19e4ae6..4270c87 100644[m
[1m--- a/src/Main.java[m
[1m+++ b/src/Main.java[m
[36m@@ -27,17 +27,17 @@[m [mpublic class Main {[m
     View view = new ViewImpl(System.out);[m
 [m
     if (args.length > 0 && args[0].equals("text")) {[m
[31m-    //            GuiView guiView = new GuiViewImpl("Stock-Marketverse");[m
[31m-    //            ControllerGui guiController = new ControllerGuiImpl(mainModel, guiView);[m
[32m+[m[32m//                  GuiView guiView = new GuiViewImpl("Stock-Marketverse");[m
[32m+[m[32m//                  ControllerGui guiController = new ControllerGuiImpl(mainModel, guiView);[m
       Controller controller = new ControllerImpl(mainModel, view, System.in,[m
               System.out);[m
       controller.goController();[m
     } else {[m
       GuiView guiView = new GuiViewImpl("Stock-Marketverse");[m
       ControllerGui guiController = new ControllerGuiImpl(mainModel, guiView);[m
[31m-      //            Controller controller = new ControllerImpl(mainModel, view, System.in,[m
[31m-      //                    System.out);[m
[31m-      //            controller.goController();[m
[32m+[m[32m//                  Controller controller = new ControllerImpl(mainModel, view, System.in,[m
[32m+[m[32m//                          System.out);[m
[32m+[m[32m//                  controller.goController();[m
     }[m
   }[m
 [m
[1mdiff --git a/src/controller/textbased/ControllerFlexi.java b/src/controller/textbased/ControllerFlexi.java[m
[1mindex 4055fb9..1a4cd0b 100644[m
[1m--- a/src/controller/textbased/ControllerFlexi.java[m
[1m+++ b/src/controller/textbased/ControllerFlexi.java[m
[36m@@ -190,7 +190,7 @@[m [mpublic class ControllerFlexi implements Controller {[m
       String res = flexibleModel.mainModViewPortfolio(uname, fname, date);[m
       if (res.equals("Incorrect file format") || res.equals("You have no stocks in this portfolio")[m
               || res.equals("Portfolio contains invalid ticker symbols")) {[m
[31m-        res ="No Stocks";[m
[32m+[m[32m        res = "No Stocks";[m
       }[m
       view.printFileComposition(res);[m
     }[m
[1mdiff --git a/src/model/api/ApiImpl.java b/src/model/api/ApiImpl.java[m
[1mindex c55d42a..188370e 100644[m
[1m--- a/src/model/api/ApiImpl.java[m
[1m+++ b/src/model/api/ApiImpl.java[m
[36m@@ -73,16 +73,13 @@[m [mpublic class ApiImpl implements Api {[m
   @Override[m
   public double getStockValueOnDate(String stockSymbol, String date) {[m
     LocalDate dateObj = LocalDate.parse(date);[m
[31m-    if (dateObj.getDayOfWeek().toString().trim().equals("SUNDAY")) {[m
[31m-      dateObj = dateObj.minusDays(2);[m
[31m-    } else if (dateObj.getDayOfWeek().toString().trim().equals("SATURDAY")) {[m
[31m-      dateObj = dateObj.minusDays(1);[m
[31m-    }[m
     date = dateObj.toString();[m
     if (cache.containsKey(stockSymbol)) {[m
       if (cache.get(stockSymbol).containsKey(date)) {[m
         double amt = Double.parseDouble(cache.get(stockSymbol).get(date));[m
         return amt;[m
[32m+[m[32m      } else {[m
[32m+[m[32m        return getStockValueOnDate(stockSymbol, LocalDate.parse(date).plusDays(1).toString());[m
       }[m
     }[m
     String apiOutput = callApi(stockSymbol);[m
[1mdiff --git a/src/view/gui/CommissionFee.java b/src/view/gui/CommissionFee.java[m
[1mindex 71201c3..e16f5c3 100644[m
[1m--- a/src/view/gui/CommissionFee.java[m
[1m+++ b/src/view/gui/CommissionFee.java[m
[36m@@ -1,13 +1,33 @@[m
 package view.gui;[m
 [m
 [m
[32m+[m[32mimport org.jfree.chart.ChartFactory;[m
[32m+[m[32mimport org.jfree.chart.ChartPanel;[m
[32m+[m[32mimport org.jfree.chart.JFreeChart;[m
[32m+[m[32mimport org.jfree.chart.plot.CategoryPlot;[m
[32m+[m[32mimport org.jfree.chart.plot.PlotOrientation;[m
[32m+[m[32mimport org.jfree.data.category.DefaultCategoryDataset;[m
[32m+[m
[32m+[m[32mimport java.awt.FlowLayout;[m
[32m+[m[32mimport java.awt.BorderLayout;[m
 import java.awt.event.KeyAdapter;[m
 import java.awt.event.KeyEvent;[m
[32m+[m[32mimport java.util.List;[m
[32m+[m[32mimport java.awt.Color;[m
[32m+[m[32mimport java.awt.Dimension;[m
 [m
[31m-import java.awt.FlowLayout;[m
[32m+[m[32mimport javax.swing.JTable;[m
[32m+[m[32mimport javax.swing.JOptionPane;[m
 import javax.swing.JPanel;[m
[32m+[m[32mimport javax.swing.JFrame;[m
[32m+[m[32mimport javax.swing.BorderFactory;[m
[32m+[m[32mimport javax.swing.JTextArea;[m
 import javax.swing.JLabel;[m
[32m+[m[32mimport javax.swing.JScrollPane;[m
 import javax.swing.JTextField;[m
[32m+[m[32mimport javax.swing.JComboBox;[m
[32m+[m[32mimport javax.swing.border.EmptyBorder;[m
[32m+[m[32mimport javax.swing.border.EtchedBorder;[m
 [m
 class CommissionFee {[m
 [m
[36m@@ -55,4 +75,383 @@[m [mclass CommissionFee {[m
   public void reset() {[m
     commission.setText("");[m
   }[m
[32m+[m
[32m+[m[32m  static class DatePanel {[m
[32m+[m
[32m+[m[32m    public JComboBox<String> yearComboBox;[m
[32m+[m[32m    public JComboBox<String> monthComboBox;[m
[32m+[m[32m    public JComboBox<String> dayComboBox;[m
[32m+[m
[32m+[m[32m    MyColors colors;[m
[32m+[m[32m    private JPanel frame;[m
[32m+[m[32m    private JPanel datePanel;[m
[32m+[m
[32m+[m[32m    public DatePanel(JPanel frame, String var, int year) {[m
[32m+[m
[32m+[m[32m      this.frame = frame;[m
[32m+[m[32m      colors = new MyColors();[m
[32m+[m
[32m+[m[32m      datePanel = new JPanel();[m
[32m+[m[32m      datePanel.setLayout(new FlowLayout());[m
[32m+[m[32m      datePanel.setBackground(colors.panecol);[m
[32m+[m[32m      JLabel enterDate = new JLabel("Select " + var);[m
[32m+[m[32m      //new JLabel("Select the date at which you wish to get the value of the portfolio:");[m
[32m+[m[32m      datePanel.add(enterDate);[m
[32m+[m
[32m+[m[32m      JLabel ddLabel = new JLabel("dd: ");[m
[32m+[m[32m      datePanel.add(ddLabel);[m
[32m+[m
[32m+[m[32m      dayComboBox = new JComboBox<String>();[m
[32m+[m[32m      for (int i = 1; i < 32; i++) {[m
[32m+[m[32m        if (i >= 10) {[m
[32m+[m[32m          dayComboBox.addItem(i + "");[m
[32m+[m[32m        } else {[m
[32m+[m[32m          dayComboBox.addItem("0" + i);[m
[32m+[m[32m        }[m
[32m+[m[32m      }[m
[32m+[m[32m      datePanel.add(dayComboBox);[m
[32m+[m
[32m+[m[32m      JLabel mmLabel = new JLabel("mm: ");[m
[32m+[m[32m      datePanel.add(mmLabel);[m
[32m+[m
[32m+[m[32m      monthComboBox = new JComboBox<String>();[m
[32m+[m[32m      for (int i = 1; i < 13; i++) {[m
[32m+[m[32m        if (i >= 10) {[m
[32m+[m[32m          monthComboBox.addItem(i + "");[m
[32m+[m[32m        } else {[m
[32m+[m[32m          monthComboBox.addItem("0" + i);[m
[32m+[m[32m        }[m
[32m+[m[32m      }[m
[32m+[m[32m      datePanel.add(monthComboBox);[m
[32m+[m
[32m+[m[32m      JLabel yyyyLabel = new JLabel("yyyy: ");[m
[32m+[m[32m      datePanel.add(yyyyLabel);[m
[32m+[m
[32m+[m[32m      yearComboBox = new JComboBox<String>();[m
[32m+[m[32m      for (int i = year; i > 1940; i--) {[m
[32m+[m[32m        yearComboBox.addItem(i + "");[m
[32m+[m[32m      }[m
[32m+[m[32m      datePanel.add(yearComboBox);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setPanel() {[m
[32m+[m[32m      frame.add(datePanel);[m
[32m+[m[32m      frame.setVisible(true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void removePanel() {[m
[32m+[m[32m      frame.remove(datePanel);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void reset() {[m
[32m+[m[32m      dayComboBox.setSelectedItem(null);[m
[32m+[m[32m      yearComboBox.setSelectedItem(null);[m
[32m+[m[32m      monthComboBox.setSelectedItem(null);[m
[32m+[m[32m    }[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class Error {[m
[32m+[m
[32m+[m[32m    public Error() {[m
[32m+[m[32m      // nothing to set[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void showError(String s) {[m
[32m+[m[32m      JOptionPane.showMessageDialog(null, s, "An Error Occurred!",[m
[32m+[m[32m              JOptionPane.ERROR_MESSAGE);[m
[32m+[m[32m    }[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class HeadingPanel {[m
[32m+[m
[32m+[m[32m    private final JPanel frame;[m
[32m+[m[32m    private JPanel headingPanel;[m
[32m+[m
[32m+[m[32m    MyColors colors;[m
[32m+[m
[32m+[m[32m    public HeadingPanel(JPanel frame, String str) {[m
[32m+[m[32m      this.frame = frame;[m
[32m+[m[32m      headingPanel = new JPanel();[m
[32m+[m
[32m+[m[32m      colors = new MyColors();[m
[32m+[m[32m      headingPanel.setBackground(colors.panecol);[m
[32m+[m
[32m+[m[32m      JLabel title = new JLabel("<html><h2 style = 'color: #2980B9'>" + str + "</h2></html>");[m
[32m+[m
[32m+[m[32m      headingPanel.add(title);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setPanel() {[m
[32m+[m[32m      frame.add(headingPanel);[m
[32m+[m[32m      frame.setVisible(true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void removePanel() {[m
[32m+[m[32m      frame.removeAll();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class InvestPanel {[m
[32m+[m
[32m+[m[32m    private JPanel frame;[m
[32m+[m[32m    private final JPanel investPanel;[m
[32m+[m[32m    private JTextField investVal;[m
[32m+[m[32m    MyColors colors;[m
[32m+[m
[32m+[m[32m    public InvestPanel(JPanel frame) {[m
[32m+[m[32m      this.frame = frame;[m
[32m+[m[32m      investPanel = new JPanel();[m
[32m+[m[32m      investPanel.setLayout(new FlowLayout());[m
[32m+[m[32m      JLabel enterInvestVal = new JLabel("Enter Investment Value: $");[m
[32m+[m[32m      investVal = new JTextField(5);[m
[32m+[m[32m      investVal.addKeyListener(new KeyAdapter() {[m
[32m+[m[32m        public void keyPressed(KeyEvent ke) {[m
[32m+[m[32m          if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()[m
[32m+[m[32m                  == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() <= '.') {[m
[32m+[m[32m            investVal.setEditable(true);[m
[32m+[m[32m          } else {[m
[32m+[m[32m            Error er = new Error();[m
[32m+[m[32m            er.showError(" Only numeric values allowed!");[m
[32m+[m[32m            investVal.setText("");[m
[32m+[m[32m          }[m
[32m+[m[32m        }[m
[32m+[m[32m      });[m
[32m+[m[32m      investPanel.add(enterInvestVal);[m
[32m+[m[32m      investPanel.add(investVal);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setPanel() {[m
[32m+[m[32m      frame.add(investPanel);[m
[32m+[m[32m      frame.setVisible(true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void removePanel() {[m
[32m+[m[32m      frame.removeAll();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public String getInvestVal() {[m
[32m+[m[32m      return investVal.getText();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void reset() {[m
[32m+[m[32m      investVal.setText("");[m
[32m+[m[32m    }[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class MyColors {[m
[32m+[m
[32m+[m[32m    public final Color bgcol;[m
[32m+[m[32m    public final Color panecol;[m
[32m+[m
[32m+[m[32m    public MyColors() {[m
[32m+[m[32m      bgcol = new Color(0xAED6F1);[m
[32m+[m[32m      panecol = new Color(0xEAEDED);[m
[32m+[m[32m    }[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class TickerSymbol {[m
[32m+[m
[32m+[m[32m    private JPanel frame;[m
[32m+[m
[32m+[m[32m    private JPanel tickerPanel;[m
[32m+[m[32m    private JTextField tickerInput;[m
[32m+[m[32m    MyColors colors;[m
[32m+[m
[32m+[m[32m    public TickerSymbol(JPanel frame) {[m
[32m+[m[32m      this.frame = frame;[m
[32m+[m[32m      tickerPanel = new JPanel();[m
[32m+[m[32m      tickerPanel.setLayout(new FlowLayout());[m
[32m+[m[32m      colors = new MyColors();[m
[32m+[m[32m      tickerPanel.setBackground(colors.panecol);[m
[32m+[m[32m      JLabel tickerLabel = new JLabel("Enter the ticker symbol: ");[m
[32m+[m[32m      tickerInput = new JTextField(10);[m
[32m+[m[32m      tickerPanel.add(tickerLabel);[m
[32m+[m[32m      tickerPanel.add(tickerInput);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setPanel() {[m
[32m+[m[32m      frame.add(tickerPanel);[m
[32m+[m[32m      frame.setVisible(true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void removePanel() {[m
[32m+[m[32m      frame.removeAll();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public String getTicker() {[m
[32m+[m[32m      return tickerInput.getText();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void reset() {[m
[32m+[m[32m      tickerInput.setText("");[m
[32m+[m[32m    }[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class StockWtSymbol {[m
[32m+[m
[32m+[m[32m    private JPanel frame;[m
[32m+[m[32m    private JPanel stockWtPanel;[m
[32m+[m[32m    private JTextArea stockWt;[m
[32m+[m
[32m+[m[32m    public StockWtSymbol(JPanel frame) {[m
[32m+[m[32m      this.frame = frame;[m
[32m+[m[32m      stockWtPanel = new JPanel();[m
[32m+[m[32m      stockWtPanel.setLayout(new FlowLayout());[m
[32m+[m[32m      JLabel stockWtLabel = new JLabel("Enter Stocks and their Weights:");[m
[32m+[m[32m      stockWt = new JTextArea(4, 30);[m
[32m+[m[32m      stockWtPanel.add(stockWtLabel);[m
[32m+[m[32m      stockWtPanel.add(stockWt);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setPanel() {[m
[32m+[m[32m      frame.add(stockWtPanel);[m
[32m+[m[32m      frame.setVisible(true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void removePanel() {[m
[32m+[m[32m      frame.removeAll();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public String getStockWt() {[m
[32m+[m[32m      return stockWt.getText();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void reset() {[m
[32m+[m[32m      stockWt.setText("");[m
[32m+[m[32m    }[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class QtyStock {[m
[32m+[m
[32m+[m[32m    private JPanel frame;[m
[32m+[m
[32m+[m[32m    private JPanel qtyPanel;[m
[32m+[m[32m    private JTextField qtyVal;[m
[32m+[m[32m    MyColors colors;[m
[32m+[m
[32m+[m[32m    public QtyStock(JPanel frame) {[m
[32m+[m[32m      this.frame = frame;[m
[32m+[m[32m      qtyPanel = new JPanel();[m
[32m+[m[32m      qtyPanel.setLayout(new FlowLayout());[m
[32m+[m[32m      colors = new MyColors();[m
[32m+[m[32m      qtyPanel.setBackground(colors.panecol);[m
[32m+[m[32m      JLabel qtyLabel = new JLabel("Enter quantity: ");[m
[32m+[m[32m      qtyVal = new JTextField(10);[m
[32m+[m
[32m+[m[32m      qtyVal.addKeyListener(new KeyAdapter() {[m
[32m+[m[32m        public void keyPressed(KeyEvent ke) {[m
[32m+[m[32m          if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()[m
[32m+[m[32m                  == KeyEvent.VK_BACK_SPACE) {[m
[32m+[m[32m            qtyVal.setEditable(true);[m
[32m+[m[32m          } else {[m
[32m+[m[32m            Error er = new Error();[m
[32m+[m[32m            er.showError(" Only numeric values allowed!");[m
[32m+[m[32m            qtyVal.setText("");[m
[32m+[m[32m          }[m
[32m+[m[32m        }[m
[32m+[m[32m      });[m
[32m+[m[32m      qtyPanel.add(qtyLabel);[m
[32m+[m[32m      qtyPanel.add(qtyVal);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setPanel() {[m
[32m+[m[32m      frame.add(qtyPanel);[m
[32m+[m[32m      frame.setVisible(true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void removePanel() {[m
[32m+[m[32m      frame.removeAll();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public String getQty() {[m
[32m+[m[32m      return qtyVal.getText();[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void reset() {[m
[32m+[m[32m      qtyVal.setText("");[m
[32m+[m[32m    }[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class PrintPortfolioScrollPanel {[m
[32m+[m
[32m+[m[32m    private JScrollPane scrollPanel;[m
[32m+[m[32m    private JFrame frame;[m
[32m+[m
[32m+[m[32m    private JPanel mainPanel;[m
[32m+[m
[32m+[m[32m    public PrintPortfolioScrollPanel(JFrame frame, JTable jtable) {[m
[32m+[m[32m      this.frame = frame;[m
[32m+[m[32m      mainPanel = new JPanel();[m
[32m+[m[32m      mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,[m
[32m+[m[32m              10, 10), new EtchedBorder()));[m
[32m+[m
[32m+[m[32m      scrollPanel = new JScrollPane(jtable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,[m
[32m+[m[32m              JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);[m
[32m+[m[32m      scrollPanel.setPreferredSize(new Dimension(200, 300));[m
[32m+[m
[32m+[m[32m      mainPanel.add(scrollPanel);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setPanel() {[m
[32m+[m[32m      frame.add(mainPanel);[m
[32m+[m[32m      frame.setVisible(true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void removePanel() {[m
[32m+[m[32m      frame.remove(scrollPanel);[m
[32m+[m[32m    }[m
[32m+[m[32m  }[m
[32m+[m
[32m+[m[32m  static class PrintBarChart {[m
[32m+[m[32m    private JFrame frame;[m
[32m+[m
[32m+[m[32m    private JPanel mainPanel;[m
[32m+[m
[32m+[m[32m    MyColors colors;[m
[32m+[m
[32m+[m[32m    ChartPanel chartPanel;[m
[32m+[m
[32m+[m[32m    public PrintBarChart(JFrame frame, String startDate, String endDate, String portfolioName,[m
[32m+[m[32m                         java.util.List<String> xaxis, List<Integer> yaxis, double scale) {[m
[32m+[m[32m      this.frame = frame;[m
[32m+[m[32m      mainPanel = new JPanel();[m
[32m+[m[32m      colors = new MyColors();[m
[32m+[m[32m      mainPanel.setBackground(colors.bgcol);[m
[32m+[m[32m      mainPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10,[m
[32m+[m[32m              10, 10), new EtchedBorder()));[m
[32m+[m
[32m+[m
[32m+[m[32m      DefaultCategoryDataset dataset = new DefaultCategoryDataset();[m
[32m+[m[32m      String legend = "portfolio-value";[m
[32m+[m[32m      for (int i = 0; i < xaxis.size(); i++) {[m
[32m+[m[32m        dataset.addValue(yaxis.get(i) * scale, legend, xaxis.get(i));[m
[32m+[m[32m      }[m
[32m+[m
[32m+[m[32m      JFreeChart barChart = ChartFactory.createBarChart("Performance of portfolio "[m
[32m+[m[32m                      + portfolioName + " from " + startDate + " to " +[m
[32m+[m[32m                      endDate, "Duration", "Value in Dollars ($)",[m
[32m+[m[32m              dataset,[m
[32m+[m[32m              PlotOrientation.HORIZONTAL,[m
[32m+[m[32m              true, true, false);[m
[32m+[m
[32m+[m[32m      CategoryPlot plot = barChart.getCategoryPlot();[m
[32m+[m[32m      plot.getRenderer().setSeriesPaint(0, new Color(0x005B96));[m
[32m+[m
[32m+[m[32m      chartPanel = new ChartPanel(barChart);[m
[32m+[m[32m      chartPanel.setPreferredSize(new Dimension(650, 450));[m
[32m+[m[32m      JScrollPane scrollPane = new JScrollPane(chartPanel);[m
[32m+[m[32m      mainPanel.add(scrollPane);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void setPanel() {[m
[32m+[m[32m      frame.add(mainPanel, BorderLayout.CENTER);[m
[32m+[m[32m      frame.setVisible(true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void removePanel() {[m
[32m+[m[32m      frame.removeAll();[m
[32m+