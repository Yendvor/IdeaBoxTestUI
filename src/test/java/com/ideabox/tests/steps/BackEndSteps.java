package com.ideabox.tests.steps;

import com.ideabox.tests.models.Ideas;
import com.ideabox.tests.services.IdeaService;
import com.ideabox.tests.utils.Common;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */
@ContextConfiguration("/cucumber.xml")
public class BackEndSteps {
  private static final Logger logger = LoggerFactory.getLogger(BackEndSteps.class);

  @Autowired Ideas myIdea;

  @Given("^default idea is created via BE$")
  public void defaultIdeaIsCreatedViaBE() throws Throwable {
    myIdea.setID(Common.getEntityFromResponse(IdeaService.addIdea(myIdea),"id"));
    Assert.assertNotNull(myIdea.getID(),"Ideas id should not be null when myIdea is created");
    logger.info("Default myIdea is created: " + myIdea.getID());
  }

  @And("^idea is deleted via BE$") public void ideaIsDeletedViaBE()
    throws Throwable {

  }

  @Given("^Idea status is set to \"([^\"]*)\" via BE$")
  public void ideaStatusIsSetToViaBE(String status) throws Throwable {
    logger.info("Changing myIdea status: " + status);
    IdeaService.changeStatus(myIdea, status);
  }

  @Given("^Idea is taken through the \"([^\"]*)\" via BE$")
  public void ideaIsTakenThroughTheViaBE(String statusesWorkflow) throws Throwable {
    if (!statusesWorkflow.isEmpty()){
      ArrayList<String> statusesList=  new ArrayList<>(Arrays.asList(statusesWorkflow.split(",")));
      for(String status: statusesList){
        logger.info("Changing myIdea status: " + status);
        IdeaService.changeStatus(myIdea, status);
      }
    }
  }
}
