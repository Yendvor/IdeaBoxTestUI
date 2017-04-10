package com.ideabox.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tdvoryanchenko on 4/5/17.
 */
public class GeneralIdeaPage {

  protected static final Logger logger = LoggerFactory.getLogger(GeneralIdeaPage.class);

  @FindBy(className = "ideas-list-tabs-container")
  protected WebElement header;

  @FindBy(css = "button.logout-label")
  protected WebElement logout;

  @FindBy(xpath = "//span[contains(@class,'ideas-list-tab-name')]/span[text()='All']")
  protected WebElement allIdeas;

  @FindBy(xpath = "//span[contains(@class,'ideas-list-tab-name')]/span[text()='Mine']")
  protected WebElement myIdeas;

  @FindBy(className = "add-idea-button-container")
  protected WebElement addIdea;

  @FindBy(className = "feedback-button-container")
  protected WebElement leaveFeedback;

  protected WebDriver driver;
  protected String pageLink = "/ideabox";

  protected By parentLocator = By.xpath("..");

  public GeneralIdeaPage(WebDriver driver) {
    this.driver = driver;
        PageFactory.initElements(driver, this);
  }

  public IdeasListPage openAllIdeasPage(){
    allIdeas.click();
    return new IdeasListPage(driver);
  }

  public AddNewIdeaPage openAddIdeaPage(){
    addIdea.click();
    return new AddNewIdeaPage(driver);
  }

  public void waitUntilPageIsVisible() {
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(allIdeas));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(logout));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(myIdeas));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(leaveFeedback));
    new WebDriverWait(driver, 10)
      .until(ExpectedConditions.visibilityOf(addIdea));
  }

}
