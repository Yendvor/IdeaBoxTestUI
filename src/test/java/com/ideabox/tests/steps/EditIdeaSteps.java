package com.ideabox.tests.steps;

import com.ideabox.tests.models.Ideas;
import com.ideabox.tests.pages.EditIdeaPage;
import com.ideabox.tests.pages.IdeasListPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */
@ContextConfiguration("/cucumber.xml")
public class EditIdeaSteps {
  private static final Logger logger = LoggerFactory.getLogger(EditIdeaPage.class);

  @Autowired Ideas myIdea;
  EditIdeaPage editPage;
  IdeasListPage listPage;

  WebDriver driver;
  public EditIdeaSteps() throws MalformedURLException {
    Hooks hook=  new Hooks();
    driver = hook.driver;
  }

  @And("^Edit idea page is shown$") public void editIdeaPageIsShown()
    throws Throwable {
    editPage = new EditIdeaPage(driver);
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
    editPage = new EditIdeaPage(driver);
    editPage.openStatusList();
    Assert.assertNotNull(editPage.getStatusElementByName(newStatus),"Status element with text "+newStatus+" was not found");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", editPage.getStatusElementByName(newStatus));
    editPage=editPage.submitStatusChange(newStatus);
    Assert.assertEquals(newStatus,editPage.getCurrentIdeaStatus(),"New idea status does not match");
    myIdea.setStatus(newStatus);
  }

  @And("^I go to idea list page$") public void iGoToIdeaListPage() throws Throwable {
    editPage = new EditIdeaPage(driver);
    listPage=editPage.openAllIdeasPage();
  }

  @And("^Idea statuses \"([^\"]*)\" are unavailable$")
  public void ideaStatusesAreUnavailable(String statusesNegative) throws Throwable {
    editPage = new EditIdeaPage(driver);
    ArrayList<String> statusesListNegative=  new ArrayList<>(Arrays.asList(statusesNegative.split(",")));
    editPage.openStatusList();
    logger.info("statusList"+ editPage.getStatusesList());
    for (String status: statusesListNegative) {
      Assert.assertFalse(editPage.getStatusesList().contains(status),"Status "+status+" should NOT be present in list of statuses: "+ editPage.getStatusesList());
    }
  }

}
