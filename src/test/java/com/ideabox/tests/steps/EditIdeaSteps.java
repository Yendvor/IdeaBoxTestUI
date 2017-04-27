package com.ideabox.tests.steps;

import com.ideabox.tests.pages.EditIdeaPage;
import com.ideabox.tests.pages.IdeasListPage;
import com.ideabox.tests.utils.BaseUtil;
import cucumber.api.java.en.And;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */
public class EditIdeaSteps extends BaseUtil{
  private static final Logger logger = LoggerFactory.getLogger(EditIdeaPage.class);

  private BaseUtil base;
  EditIdeaPage editPage;
  IdeasListPage listPage;

  public EditIdeaSteps(BaseUtil base) {
    this.base=base;
  }

  @And("^Edit idea page is shown$") public void editIdeaPageIsShown()
    throws Throwable {
    editPage = new EditIdeaPage(base.driver);
    editPage.waitUntilPageIsVisible();
  }

  @And("^Idea status available$") public void ideaStatusAvailable(List<String> statuses) throws Throwable {
    editPage.openStatusList();
    logger.info("statusList"+ editPage.getStatusesList());
    for(String status: statuses)
    Assert.assertTrue(editPage.getStatusesList().contains(status),"Status "+status+" is not present in list of statuses: "+ editPage.getStatusesList());
  }

  @And("^I change status to \"([^\"]*)\"$") public void iChangeStatusTo(String newStatus)
    throws Throwable {
    editPage = new EditIdeaPage(base.driver);
    Assert.assertNotNull(editPage.getStatusElementByName(newStatus),"Status element with text "+newStatus+" was not found");
    editPage.selectStatus(editPage.getStatusElementByName(newStatus));
    editPage=editPage.submitStatusChange(newStatus);
    Assert.assertEquals(newStatus,editPage.getCurrentIdeaStatus(),"New idea status does not match");
    base.myIdea.setStatus(newStatus);
  }

  @And("^I go to idea list page$") public void iGoToIdeaListPage() throws Throwable {
    editPage = new EditIdeaPage(base.driver);
    listPage=editPage.openAllIdeasPage();
  }

  @And("^Idea statuses \"([^\"]*)\" are unavailable$")
  public void ideaStatusesAreUnavailable(String statusesNegative) throws Throwable {
    editPage = new EditIdeaPage(base.driver);
    ArrayList<String> statusesListNegative=  new ArrayList<>(Arrays.asList(statusesNegative.split(",")));
    editPage.openStatusList();
    logger.info("statusList"+ editPage.getStatusesList());
    for (String status: statusesListNegative) {
      Assert.assertFalse(editPage.getStatusesList().contains(status),"Status "+status+" should NOT be present in list of statuses: "+ editPage.getStatusesList());
    }
  }

  @And("^Idea summary is \"([^\"]*)\"$") public void ideaSummaryIs(String summary) throws Throwable {
    editPage = new EditIdeaPage(base.driver);
    Assert.assertEquals(summary, editPage.getIdeaSummary(), "Idea summary does not match");

  }

  @And("^Idea details is \"([^\"]*)\"$") public void ideaDetailsIs(String details) throws Throwable {
    Assert.assertEquals(details, editPage.getIdeaDetails(), "Idea details does not match");
  }
}
