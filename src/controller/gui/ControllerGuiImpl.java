package controller.gui;

import java.util.HashMap;
import java.util.Map;

import controller.gui.commands.Command;
import controller.gui.commands.P1Username;
import controller.gui.commands.P2CreateDcaPortfolio;
import controller.gui.commands.P2CreatePortfolio;
import controller.gui.commands.P2ExistingPortfolio;
import controller.gui.commands.P2UploadPortfolio;
import controller.gui.commands.P3CostBasis;
import controller.gui.commands.P3Dca;
import controller.gui.commands.P3GetPortfolioValue;
import controller.gui.commands.P3IFABuy;
import controller.gui.commands.P3MakeTransactions;
import controller.gui.commands.P3PerformanceVisualization;
import controller.gui.commands.P3ViewComposition;
import controller.gui.commands.P4BuyStocks;
import controller.gui.commands.P4SaveTransactions;
import controller.gui.commands.P4SellStocks;
import model.MainModel;
import view.gui.GuiView;

/**
 * Controller class that implements the ControllerGui interface and is responsible for linking
 * the model and view of the application.
 */
public class ControllerGuiImpl implements ControllerGui {

  private GuiView view;
  private MainModel model;
  private String uname;
  private Command cmd;
  private String portfolioName;
  private String date;

  private String ticker;
  private String quantity;
  private String commissionFee;
  private Map<String, String> inp;

  /**
   * Constructor to set the model and view objects to the MainModel and GuiView objects
   * respectively.
   *
   * @param m MainModel object
   * @param v GuiView object
   */
  public ControllerGuiImpl(MainModel m, GuiView v) {
    this.model = m;
    this.view = v;
    view.addFeatures(this);
    this.uname = "";
    cmd = null;
    portfolioName = "";
    date = "";
    inp = new HashMap<>();
    ticker = "";
    quantity = "";
    commissionFee = "";
  }

  @Override
  public void goGui(String ch) {
    switch (ch) {
      case "Back To Welcome Pg":
        view.reset("Page1");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page1");
        break;
      case "BackFromCreateDCA": // since both have same logic
      case "Back to Portfolio Options":
        view.reset("Page2");
        view.reset("Page2b_existing");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page2");
        break;
      case "Username":
        uname = view.getUsernameInput().get("username");
        cmd = new P1Username(uname, view);
        break;
      case "Portfolio Name":
        view.reset("Page2");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page2");
        view.setAddPanel("Page2b");
        break;
      case "Create Portfolio":
        inp = view.getPortfolioNameCreate();
        portfolioName = inp.get("portfolioName");
        uname = view.getUsernameInput().get("username");
        cmd = new P2CreatePortfolio(uname, portfolioName, view);
        break;
      case "Upload Portfolio":
        view.setRemovePanel("remove_all");
        view.setAddPanel("Dialog_path");
        String path = view.getUploadPath();
        if (!path.equals("null")) {
          uname = view.getUsernameInput().get("username");
          cmd = new P2UploadPortfolio(uname, path, view);
          view.setAddPanel("Page2");
        } else {
          view.setRemovePanel("remove_all");
          view.setAddPanel("Page2");
        }
        break;
      case "Existing Portfolio Name":
        view.reset("Page2b_existing");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page2");
        view.setAddPanel("Page2b_existing");
        break;
      case "Existing Portfolio":
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        uname = view.getUsernameInput().get("username");
        cmd = new P2ExistingPortfolio(uname, portfolioName, view);
        break;
      case "Portfolio Value":
        view.reset("Page3_GetPortfolioValue");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3");
        view.setAddPanel("Page3_GetPortfolioValue");
        break;
      case "Portfolio Value Date":
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        uname = view.getUsernameInput().get("username");
        inp = view.getDatePortfolioValue();
        date = inp.get("date_portfolioValue");
        cmd = new P3GetPortfolioValue(uname, portfolioName, date, view);
        break;
      case "Make Transaction":
        view.reset("Page3_MakeTx");
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        uname = view.getUsernameInput().get("username");
        cmd = new P3MakeTransactions(uname, portfolioName, view);
        break;
      case "Buy Stock":
        inp = view.getTransactionInputs();
        if (inp.get("transType").equals("Sell stocks")) {
          goGui("Sell Stock");
        } else {
          uname = view.getUsernameInput().get("username");
          inp = view.getPortfolioNameExisting();
          portfolioName = inp.get("portfolioName");
          inp = view.getTransactionInputs();
          ticker = inp.get("ticker");
          quantity = inp.get("quantity");
          date = inp.get("date_Transaction");
          commissionFee = inp.get("commissionFee");
          cmd = new P4BuyStocks(portfolioName, uname, ticker,
                  quantity, date, commissionFee, view);
        }
        break;
      case "Sell Stock":
        uname = view.getUsernameInput().get("username");
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        inp = view.getTransactionInputs();
        ticker = inp.get("ticker");
        quantity = inp.get("quantity");
        date = inp.get("date_Transaction");
        commissionFee = inp.get("commissionFee");
        cmd = new P4SellStocks(portfolioName, uname, ticker,
                quantity, date, commissionFee, view);
        break;
      case "Save Tx":
        uname = view.getUsernameInput().get("username");
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        cmd = new P4SaveTransactions(uname, portfolioName, view);
        break;
      case "View Portfolio":
        view.reset("Page3_ViewPortfolio");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3");
        view.setAddPanel("Page3_ViewPortfolio");
        break;
      case "View Composition":
        uname = view.getUsernameInput().get("username");
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        inp = view.getDateViewPortfolio();
        date = inp.get("date_viewPortfolio");
        cmd = new P3ViewComposition(uname, portfolioName, date, view);
        break;
      case "Cost Basis Date":
        view.reset("Page3_GetCostBasisValue");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3");
        view.setAddPanel("Page3_GetCostBasisValue");
        break;
      case "Cost Basis":
        uname = view.getUsernameInput().get("username");
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        inp = view.getCostBasisInputs();
        date = inp.get("date_costBasis");
        cmd = new P3CostBasis(uname, portfolioName, date, view);
        break;
      case "Performance Analysis":
        view.reset("Page3_GetDatesPerformanceAnalysis");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3");
        view.setAddPanel("Page3_GetDatesPerformanceAnalysis");
        break;
      case "Visualize Performance":
        uname = view.getUsernameInput().get("username");
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        inp = view.getPerformanceInputs();
        cmd = new P3PerformanceVisualization(uname, portfolioName, inp.get("startDate_pfm"),
                inp.get("endDate_pfm"), view);
        break;
      case "Create Portfolio DCA":
        view.reset("Page3_CreatePortfolioDCA");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3_CreatePortfolioDCA");
        break;
      case "Create_DCA":
        inp = view.getCreateDCA();
        uname = view.getUsernameInput().get("username");
        portfolioName = inp.get("portfolioname_DCA");
        String sDate = inp.get("startDate");
        String eDate = inp.get("endDate");
        String freq = inp.get("freq");
        String invAmt = inp.get("investAmt");
        commissionFee = inp.get("commFee");
        ticker = inp.get("tickerWts");
        cmd = new P2CreateDcaPortfolio(uname, portfolioName,
                sDate, eDate, freq, invAmt, commissionFee,
                ticker, view);
        break;
      case "DCA_show":
        view.reset("Page3_GetDCAInputs");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3");
        view.setAddPanel("Page3_GetDCAInputs");
        break;
      case "DCA_submit":
        uname = view.getUsernameInput().get("username");
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        inp = view.getDCAInputs();
        String startDate = inp.get("startDate");
        String endDate = inp.get("endDate");
        String interval = inp.get("freq");
        String iVal = inp.get("investAmt");
        commissionFee = inp.get("commFee");
        String wts = inp.get("tickerWts");
        cmd = new P3Dca(uname, portfolioName, startDate, endDate,
                iVal, interval, commissionFee, wts, view);
        break;
      case "IFA_show":
        view.reset("Page3_InvestFixedAmt");
        view.setRemovePanel("remove_all");
        view.setAddPanel("Page3");
        view.setAddPanel("Page3_InvestFixedAmt");
        break;
      case "IFA_submit":
        uname = view.getUsernameInput().get("username");
        inp = view.getPortfolioNameExisting();
        portfolioName = inp.get("portfolioName");
        inp = view.getInvestFixedAmtInputs();
        date = inp.get("date_IFA");
        String maps = inp.get("tickerWts");
        String iValue = inp.get("investAmt");
        commissionFee = inp.get("commFee");
        cmd = new P3IFABuy(uname, portfolioName, maps, iValue, date, commissionFee, view);
        break;
      default:
        view.showError("Invalid option selected");
        break;
    }
    if (cmd != null) {
      cmd.runCommand(model);
      cmd = null;
    }
  }
}
