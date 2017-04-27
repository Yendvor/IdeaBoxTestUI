package com.ideabox.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ConfirmDialogPage {

  @FindBy (className="dialog_content")
  WebElement dialogContentBlock;

  @FindBy (xpath="//div[contains(@class,'control-button-item')]/div[text()='CHANGE']")
  WebElement submitButton;

  @FindBy (xpath="//div[contains(@class,'control-button-item')]/div[text()='CANCEL']")
  WebElement cancelButton;

private WebDriver driver;

  public ConfirmDialogPage(WebDriver driver) {
    this.driver= driver;
    PageFactory.initElements(driver, this);
  }

  public EditIdeaPage submitDialog() {
    waitUntilllCOnfirmDIalogIsVisible();
    submitButton.click();
    return new EditIdeaPage(driver);
  }

  public void waitUntilllCOnfirmDIalogIsVisible() {
    new WebDriverWait(driver, 1)
      .until(ExpectedConditions.visibilityOf(submitButton));
    new WebDriverWait(driver, 1)
      .until(ExpectedConditions.visibilityOf(cancelButton));
    new WebDriverWait(driver, 1)
      .until(ExpectedConditions.visibilityOf(dialogContentBlock));
  }

}
