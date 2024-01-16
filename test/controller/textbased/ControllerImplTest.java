package controller.textbased;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import model.MainModel;
import view.textbased.View;

import static org.junit.Assert.assertEquals;

/**
 * Java Class associated with isolation testing of the controller with respect to Model and View.
 */
public class ControllerImplTest {
  InputStream in;
  PrintStream out;
  ByteArrayOutputStream bytes;

  MainModel model;
  View view;
  Controller controller;
  StringBuilder log;
  StringBuilder logV;

  @Before
  public void testSetUp() {
    bytes = new ByteArrayOutputStream();
    out = new PrintStream(bytes);
    log = new StringBuilder();
    log.append("Input:");
    model = new MockFlexiModel(log, true, "uniqueCode", 0.0);

    logV = new StringBuilder();
    logV.append("Output: ");
    view = new MockView(logV);
  }

  @Test
  public void testExitInBeginning() {
    in = new ByteArrayInputStream("2".getBytes());
    controller = new ControllerImpl(model, view, in, out);
    controller.goController();
    assertEquals("Input:", log.toString());
    assertEquals("Output: printWelcome printUserMenu exitProgram ", logV.toString());
  }

  // test user creation (new)

  @Test
  public void testUserDirectoryExist() {
    in = new ByteArrayInputStream("1 test 3 2".getBytes());
    controller = new ControllerImpl(model, view, in, out);
    controller.goController();
    assertEquals("Input: test", log.toString());
    assertEquals("Output: printWelcome printUserMenu askForUserName printPortfolioMenu " +
            "exitProgram printUserMenu exitProgram ", logV.toString());
  }
}


