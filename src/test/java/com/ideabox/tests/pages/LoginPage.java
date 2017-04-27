package com.ideabox.tests.pages;

import com.ideabox.tests.utils.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {

  @FindBy (id="username")
  WebElement username;

  @FindBy (id="password")
  WebElement password;

  @FindBy (css="form button")
  WebElement submitButton;

  protected String pageLink="/login";

private WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver= driver;
    PageFactory.initElements(driver, this);
  }

  public void fillUserName(String name) {
    username.sendKeys(name);
  }

  public void fillPassword(String pass) {
    password.clear();
    password.sendKeys(pass);
  }

  public void waitUntilLoginPageIsVisible() {
    new WebDriverWait(driver, 1)
      .until(ExpectedConditions.visibilityOf(username));
    new WebDriverWait(driver, 1)
      .until(ExpectedConditions.visibilityOf(password));
    new WebDriverWait(driver, 1)
      .until(ExpectedConditions.visibilityOf(submitButton));
  }

  public void submitForm() {
    submitButton.click();
  }

  public IdeasListPage doLogin(String name, String pass){
    fillUserName(name);
    fillPassword(pass);
    submitForm();
    return  new IdeasListPage(driver);
  }

  public IdeasListPage userIsLoggedIn() {
    return new IdeasListPage(driver);
  }

  public String getPageUrl(){
    return Common.getBaseUrl()+pageLink;
  }
}
