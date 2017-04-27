package com.ideabox.tests.steps;

import com.ideabox.tests.pages.AddNewIdeaPage;
import com.ideabox.tests.pages.IdeasListPage;
import com.ideabox.tests.utils.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

/**
 * Created by tdvoryanchenko on 4/13/17.
 */
public class AddIdea extends BaseUtil {

  AddNewIdeaPage addIdea;
  IdeasListPage lPage;

  private BaseUtil base;
  public AddIdea(BaseUtil base){
   this.base=base;
  }

  @When("^I open Add Idea dialog$") public void iOpenAddIdeaDialog()
    throws Throwable {
    lPage=new IdeasListPage(base.driver);
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
