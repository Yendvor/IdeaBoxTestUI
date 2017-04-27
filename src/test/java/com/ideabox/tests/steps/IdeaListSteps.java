package com.ideabox.tests.steps;

import com.ideabox.tests.pages.EditIdeaPage;
import com.ideabox.tests.pages.IdeasListPage;
import com.ideabox.tests.utils.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */
public class IdeaListSteps extends BaseUtil{

  protected static final Logger logger = LoggerFactory.getLogger(IdeasListPage.class);

  private BaseUtil base;
  IdeasListPage listPage;
  EditIdeaPage editPage;
  
  public IdeaListSteps(BaseUtil base) {
    this.base= base;
  }

  @Given("^Idea status is changed$") public void ideaStatusIs()
    throws Throwable {
    listPage= new IdeasListPage(base.driver);
    listPage.waitUntilPageIsVisible();
    Assert.assertEquals(listPage.getIdeaStatus(listPage.findIdeaByID(base.myIdea.getID())), base.myIdea.getStatus(), "Ideas status does not match");
  }

  @When("^I open idea$") public void iOpenIdea() throws Throwable {
    listPage= new IdeasListPage(base.driver);
    editPage = listPage.openEditIdea(listPage.findIdeaByID(base.myIdea.getID()));
  }

  @Given("^Idea status is \"([^\"]*)\"$") public void ideaStatusIs(String statusText) throws Throwable {
    listPage= new IdeasListPage(base.driver);
    listPage.waitUntilPageIsVisible();
    logger.info("Idea id is " +base.myIdea.getID()+ ", summary "+ base.myIdea.getStatus());
    Assert.assertEquals(listPage.getIdeaStatus(listPage.findIdeaByID(base.myIdea.getID())).toUpperCase(),statusText.toUpperCase(), "Ideas status does not match");
  }

  @And("^Idea List page is shown$") public void ideaListPageIsShown() throws Throwable {
    listPage = new IdeasListPage(base.driver);
    listPage.waitUntilPageIsVisible();
  }

  @When("^I open Idea with summary \"([^\"]*)\"$") public void iOpenIdeaWithSummary(String summary)
    throws Throwable {
    listPage= new IdeasListPage(base.driver);
    editPage = listPage.openEditIdea(listPage.findIdeaByName(summary));
  }
}
