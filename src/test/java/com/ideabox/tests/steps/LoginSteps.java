package com.ideabox.tests.steps;

import com.ideabox.tests.pages.IdeasListPage;
import com.ideabox.tests.pages.LoginPage;
import com.ideabox.tests.utils.BaseUtil;
import com.ideabox.tests.utils.Common;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

public class LoginSteps {

  private BaseUtil base;

  LoginPage lPage;
  IdeasListPage listPage;

  public LoginSteps(BaseUtil base){
    this.base= base;
  }

  @Given("^I open Ideabox URL$") public void iOpenIdeaboxURL()
    throws Throwable {
    base.driver.manage().deleteAllCookies();
    base.driver.navigate().to(Common.getBaseUrl());
  }

  @And("^Login page is shown$") public void loginPageIsShown()
    throws Throwable {
    lPage = new LoginPage(base.driver);
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
    listPage = new IdeasListPage(base.driver);
    base.driver.navigate().to(listPage.getPageUrl());
    lPage = new LoginPage(base.driver);
    if(lPage.getPageUrl().equals(base.driver.getCurrentUrl()))
    {
      lPage.waitUntilLoginPageIsVisible();
      listPage = lPage.doLogin(login,pass);
    }else {
      listPage = new IdeasListPage(base.driver);
    }
  }

  @And("^I do logout$") public void iDoLogout() throws Throwable {
    listPage = new IdeasListPage(base.driver);
    listPage.doLogout();
  }
}
