package com.ideabox.tests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

@CucumberOptions(features = "src/test/resources/features/NewIdeaFlow.feature",
  format = {"pretty", "html:report/Flow-IdeaBox-html-report", "json:report/IdeaBox-report.json"},
  glue = "com.ideabox.tests.steps")
public class IdeaFlowRunner extends AbstractTestNGCucumberTests {
}
