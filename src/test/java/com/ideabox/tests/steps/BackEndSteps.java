package com.ideabox.tests.steps;

import com.ideabox.tests.models.Ideas;
import com.ideabox.tests.services.IdeaService;
import com.ideabox.tests.utils.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */
public class BackEndSteps extends BaseUtil{
  private static final Logger logger = LoggerFactory.getLogger(BackEndSteps.class);

  private BaseUtil base;

  public BackEndSteps(BaseUtil base){
    this.base= base;
  }

  @Given("^default idea is created via BE$")
  public void defaultIdeaIsCreatedViaBE() throws Throwable {
    base.myIdea= Ideas.defaultIdea();
    base.myIdea.setID(IdeaService.addIdea(base.myIdea));
    Assert.assertNotNull(base.myIdea.getID(),"Ideas id should not be null when myIdea is created");
    logger.info("Default myIdea is created: " + base.myIdea.getID());
  }

  @And("^idea is deleted via BE$") public void ideaIsDeletedViaBE()
    throws Throwable {

  }

  @Given("^Idea status is set to \"([^\"]*)\" via BE$")
  public void ideaStatusIsSetToViaBE(String status) throws Throwable {
    logger.info("Changing myIdea status: " + status);
    IdeaService.changeStatus(base.myIdea, status);
  }

  @Given("^Idea is taken through the \"([^\"]*)\" via BE$")
  public void ideaIsTakenThroughTheViaBE(String statusesWorkflow) throws Throwable {
    if (!statusesWorkflow.isEmpty()){
      ArrayList<String> statusesList=  new ArrayList<>(Arrays.asList(statusesWorkflow.split(",")));
      for(String status: statusesList){
        logger.info("Changing myIdea status: " + status);
        IdeaService.changeStatus(base.myIdea, status);
      }
    }
  }
}
