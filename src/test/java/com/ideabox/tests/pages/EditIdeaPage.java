package com.ideabox.tests.pages;

import com.ideabox.tests.utils.Common;
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

  @FindBy (className = "idea-details-content-block")
  WebElement ideaContent;

  @FindBy (css = "div.idea-details-title>h3")
  WebElement ideaSummary;

  @FindBy (className = "idea-details-content-description-body")
  WebElement ideaDetails;

  @FindBy (className ="idea-details-control-block")
  WebElement ideaControl;

  @FindBy (className = "ideas-list-status-selector-block")
  WebElement statusSelect;

  @FindBys(@FindBy(className = "ui-select-choices-row"))
  List<WebElement> statusesAvailable;

  @FindBy(className="ui-select-choices")
  WebElement statusesDropDown;

  @FindBy (className="ideas-list-status-label")
    WebElement statusLabel;

  By statusLabelLocator = By.className("ideas-list-status-label");
  By statusDropDownLocator = By.className("ui-select-choices");
  By statusNotEditable= By.cssSelector("ideas-list-status-selector-block non-editable-selector");

  public EditIdeaPage(WebDriver driver) {
    super(driver);
  }

  public void waitUntilPageIsVisible() {
    new WebDriverWait(driver, 1)
      .until(ExpectedConditions.visibilityOf(ideaControl));
    new WebDriverWait(driver, 1)
      .until(ExpectedConditions.visibilityOf(ideaContent));
  }


  public List<String> getStatusesList(){
    List<String> statuses = new ArrayList<>();
    if(isStatusEditable()){
      openStatusList();
    for(WebElement statusItem: statusesAvailable){
      statuses.add(statusItem.findElement(statusLabelLocator).getText());
    }
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
    if(!Common.isElementPresent(statusDropDownLocator, driver)){
      statusSelect.click();
    }
  }

  public boolean isStatusEditable(){
    return !Common.isElementPresent(statusNotEditable,driver);
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

  public String getIdeaSummary() {
    return ideaSummary.getText();
  }

  public String getIdeaDetails() {
    return ideaDetails.getText();
  }
}
