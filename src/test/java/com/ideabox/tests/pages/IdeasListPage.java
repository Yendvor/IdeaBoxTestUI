package com.ideabox.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by tdvoryanchenko on 4/3/17.
 */
public class IdeasListPage  extends GeneralIdeaPage{


  @FindBy (className = "list-headers-component")
  WebElement ideaListHeader;

  @FindBys(@FindBy (className = "ideas-list-idea-container"))
  List<WebElement> ideaItems;

  @FindBys(@FindBy(css = "idea-list-item>a"))
  List<WebElement> ideaLinks;

  By ideaSummaryLocator = By.className("ideas-list-summary-header");
  By ideaStatusLocator = By.className("ideas-list-status-label");

  public IdeasListPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
    pageLink = pageLink+"/ideas";
  }

  public void waitUntilPageIsVisible() {
    new WebDriverWait(driver, 3)
      .until(ExpectedConditions.visibilityOfElementLocated(ideaSummaryLocator));
    new WebDriverWait(driver, 3)
      .until(ExpectedConditions.visibilityOf(ideaItems.get(0)));
    new WebDriverWait(driver, 3)
      .until(ExpectedConditions.visibilityOf(ideaLinks.get(0)));
  }

  public EditIdeaPage openEditIdea(WebElement idea){
    logger.info("Idea element  "+idea.getTagName());
    //workaround while firefox .click() does not work for firefox 49+
    jsClick(idea, driver);
    //idea.click();
    return new EditIdeaPage(driver);
  }

  public WebElement findIdeaByName(String summary){
    for (WebElement ideaItem: ideaItems){
      if(ideaItem.findElement(ideaSummaryLocator).getText().equals(summary))
        return ideaItem;
    }
    return null;
  }

  public WebElement findIdeaByID(String id){
    for (WebElement ideaLink: ideaLinks){
      logger.info("Idea url is "+ideaLink.getAttribute("href"));
      if(ideaLink.getAttribute("href").endsWith(id))
        return ideaLink;
    }
    return null;
  }

  public String getIdeaStatus(WebElement idea){
    return idea.findElement(ideaStatusLocator).getText();
  }


}
