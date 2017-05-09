package com.ideabox.tests.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
  private int retryCount = 0;
  private int maxRetryCount = 2;
  private static final Logger logger = LoggerFactory.getLogger(Retry.class);

  // Below method returns 'true' if the tests method has to be retried else 'false'
  //and it takes the 'Result' as parameter of the tests method that just ran
  public boolean retry(ITestResult result) {
    if (retryCount < maxRetryCount) {
      logger.info("Retrying tests " + result.getName() + " with status "
        + getResultStatusName(result.getStatus()) + " for the " + (retryCount+1) + " time(s).");
      retryCount++;
      return true;
    }

    return false;
  }

  public String getResultStatusName(int status) {
    String resultName = null;
    if(status==1)
      resultName = "SUCCESS";
    if(status==2)
      resultName = "FAILURE";
    if(status==3)
      resultName = "SKIP";
    return resultName;
  }
}
