package com.ideabox.tests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

@CucumberOptions(features = "src/test/resources/features/IdeaUnavailableStatuses.feature",
  format = {"pretty", "html:report/UnavailableStatusesCheck-IdeaBox-html-report", "json:report/UnavailableStatusesCheck-IdeaBox-report.json"},
  glue = "com.ideabox.tests.steps")
public class UnavailableStatusesCheckRunner extends AbstractTestNGCucumberTests {
}
