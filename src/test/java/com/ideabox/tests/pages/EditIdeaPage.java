package com.ideabox.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdvoryanchenko on 4/3/17.
 */
public class EditIdeaPage extends GeneralIdeaPage{


  @FindBy (className ="idea-details-control-block")
  WebElement ideaControl;

  @FindBy (className = "idea-details-content-block")
  WebElement ideaContent;

  @FindBy (className = "ideas-list-status-selector-block")
  WebElement statusSelect;

  @FindBys(@FindBy(className = "ui-select-choices-row"))
  List<WebElement> statusesAvailable;

  @FindBy(className="ui-select-choices")
  WebElement statusesDropDown;


  @FindBy (className="ideas-list-status-label")
    WebElement statusLabel;

  By statusLabelLocator = By.className("ideas-list-status-label");

  public EditIdeaPage(WebDriver driver) {
    super(driver);
  }

  public void waitUntilPageIsVisible() {
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(ideaControl));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(ideaContent));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(statusSelect));
  }


  public List<String> getStatusesList(){
    List<String> statuses = new ArrayList<>();
    for(WebElement statusItem: statusesAvailable){
      statuses.add(statusItem.findElement(statusLabelLocator).getText());
    }
    return statuses;
  }

  public WebElement getStatusElementByName(String text){
    logger.info("Status found: "+statusesAvailable.size());
    for(WebElement statusItem: statusesAvailable){
      if(statusItem.findElement(statusLabelLocator).getText().equals(text))
        return statusItem;
    }
    return null;
  }

  public void openStatusList() {
    statusSelect.click();
  }

  public String getCurrentIdeaStatus() {
    return statusLabel.getText();
  }

  public EditIdeaPage submitStatusChange(String newStatus){
    if(newStatus.equalsIgnoreCase("REJECTED")||newStatus.equalsIgnoreCase("DONE")){
      ConfirmDialogPage dPage = new ConfirmDialogPage(driver);
      return  dPage.submitDialog();
    }
    return this;
  }

  public void selectStatus(WebElement statusElementByName) {
    jsClick(statusElementByName, driver);
  }
}
