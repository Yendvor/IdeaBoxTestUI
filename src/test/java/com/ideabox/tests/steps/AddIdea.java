package com.ideabox.tests.steps;

import com.ideabox.tests.pages.AddNewIdeaPage;
import com.ideabox.tests.pages.IdeasListPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * Created by tdvoryanchenko on 4/13/17.
 */
public class AddIdea {

  AddNewIdeaPage addIdea;
  IdeasListPage lPage;

  WebDriver driver;
  public AddIdea() throws MalformedURLException {
    Hooks hook=  new Hooks();
    driver = hook.driver;
  }

  @When("^I open Add Idea dialog$") public void iOpenAddIdeaDialog()
    throws Throwable {
    lPage=new IdeasListPage(driver);
    addIdea=lPage.openAddIdeaPage();
    addIdea.waitUntilPageIsVisible();
  }

  @And("^I fill summary with \"([^\"]*)\"$")
  public void iFillSummaryWith(String summaryText) throws Throwable {
    addIdea.fillSummary(summaryText);
  }

  @And("^I fill details with \"([^\"]*)\"$") public void iFillDetailsWith(String detailsText)
    throws Throwable {
    addIdea.fillDetails(detailsText);
  }

  @And("^I post the idea$") public void iPostTheIdea() throws Throwable {
    addIdea.saveIdea();
  }

}
