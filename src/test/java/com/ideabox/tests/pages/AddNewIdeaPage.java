package com.ideabox.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by tdvoryanchenko on 4/3/17.
 */
public class AddNewIdeaPage extends GeneralIdeaPage{

  @FindBy (tagName = "add-idea-component")
  WebElement addDialod;

  @FindBy (id="ideaSummary")
  WebElement summary;

  @FindBy (id="ideaDetails")
  WebElement details;

  @FindBy (id="checkbox")
  WebElement isAnonimous;

  @FindBy (className="dialog-cancel-btn")
  WebElement cancelButton;

  @FindBy (className="dialog-primary-btn")
  WebElement submitButton;


  public AddNewIdeaPage(WebDriver driver) {
    super(driver);
  }

  public void fillSummary(String summaryText){
    summary.sendKeys(summaryText);
  }

  public void fillDetails(String detailsText){
    details.sendKeys(detailsText);
  }

  public void saveIdea(){
    submitButton.click();
  }

  public void cancelIdea(){
    cancelButton.click();
  }

  public void addIdea(String summaryText, String detailsText){
    fillSummary(summaryText);
    fillDetails(detailsText);
    saveIdea();
  }

  public void waitUntilPageIsVisible() {
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(addDialod));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(summary));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(details));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(isAnonimous));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(cancelButton));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(submitButton));
  }

}
