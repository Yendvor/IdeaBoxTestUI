package com.ideabox.tests.steps;

import com.ideabox.tests.pages.IdeasListPage;
import com.ideabox.tests.pages.LoginPage;
import com.ideabox.tests.utils.Common;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

public class LoginSteps {


  LoginPage lPage;
  IdeasListPage listPage;

  WebDriver driver;
  public LoginSteps() throws MalformedURLException {
    Hooks hook=  new Hooks();
    driver = hook.driver;
  }

  @Given("^I open Ideabox URL$") public void iOpenIdeaboxURL()
    throws Throwable {
    driver.navigate().to(Common.getBaseUrl());
  }

  @And("^Login page is shown$") public void loginPageIsShown()
    throws Throwable {
    lPage = new LoginPage(driver);
    lPage.waitUntilLoginPageIsVisible();
  }

  @When("^I fill login \"([^\"]*)\"$") public void iFillLogin(String login)
    throws Throwable {
    lPage.fillUserName(login);
  }

  @And("^I fill password \"([^\"]*)\"$") public void iFillPassword(String password) throws Throwable {
    lPage.fillPassword(password);
  }

  @And("^I submit login form$") public void iSubmitLoginForm() throws Throwable {
     lPage.submitForm();
  }
  @Then("^I am logged in$") public void iAmLoggedIn() throws Throwable {
    listPage = lPage.userIsLoggedIn();
  }

  @And("^I'm logged in as \"([^\"]*)\"$") public void iMLoggedInAs(String user) throws Throwable {
    String login = Common.getPropByKey(user+"Login", "userData");
    String pass = Common.getPropByKey(user+"Password", "userData");
    driver.navigate().to(Common.getBaseUrl());
    lPage = new LoginPage(driver);
    lPage.waitUntilLoginPageIsVisible();
    listPage = lPage.doLogin(login,pass);
  }
}
