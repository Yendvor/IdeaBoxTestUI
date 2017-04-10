package com.ideabox.tests.steps;

import com.ideabox.tests.models.Ideas;
import com.ideabox.tests.pages.EditIdeaPage;
import com.ideabox.tests.pages.IdeasListPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */
@ContextConfiguration("/cucumber.xml")
public class IdeaListSteps {

  protected static final Logger logger = LoggerFactory.getLogger(IdeasListPage.class);

  @Autowired Ideas myIdea;
  IdeasListPage listPage;
  EditIdeaPage editPage;

  WebDriver driver;
  public IdeaListSteps() throws MalformedURLException {
    Hooks hook=  new Hooks();
    driver = hook.driver;
  }

  @Given("^Idea status is changed$") public void ideaStatusIs()
    throws Throwable {
    listPage= new IdeasListPage(driver);
    listPage.waitUntilPageIsVisible();
    Assert.assertEquals(listPage.getIdeaStatus(listPage.findIdeaByID(myIdea.getID())), myIdea.getStatus(), "Ideas status does not match");
  }

  @When("^I open idea$") public void iOpenIdea() throws Throwable {
    listPage= new IdeasListPage(driver);
    editPage = listPage.openEditIdea(listPage.findIdeaByID(myIdea.getID()));
  }

  @Given("^Idea status is \"([^\"]*)\"$") public void ideaStatusIs(String statusText) throws Throwable {
    listPage= new IdeasListPage(driver);
    listPage.waitUntilPageIsVisible();
    logger.info("Idea id is " + myIdea.getID()+ ", summary "+ myIdea.getStatus());

    Assert.assertEquals(listPage.getIdeaStatus(listPage.findIdeaByID(myIdea.getID())).toUpperCase(),statusText.toUpperCase(), "Ideas status does not match");

  }

  @And("^Idea List page is shown$") public void ideaListPageIsShown() throws Throwable {
    listPage= new IdeasListPage(driver);
    listPage.waitUntilPageIsVisible();
  }
}
