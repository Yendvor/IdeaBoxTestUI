package com.ideabox.tests.utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tdvoryanchenko on 8/11/16.
 */
public class ReportMerger {

  public ReportMerger(){}

  public void mergeReports (List<String> reportNames){
    File reportOutputDirectory = new File("report");
    List<String> jsonFiles = new ArrayList<>();
    jsonFiles.addAll(reportNames);

    String jenkinsBasePath = "";
    String buildNumber = "1.0";
    String projectName = "Environment is: "+ System.getProperty("env").toUpperCase();
    boolean skippedFails = true;
    boolean pendingFails = false;
    boolean undefinedFails = true;
    boolean runWithJenkins = false;
    boolean parallelTesting = false;

    Configuration configuration = new Configuration(reportOutputDirectory, projectName);
    // optional configuration
    configuration.setParallelTesting(parallelTesting);
    configuration.setRunWithJenkins(runWithJenkins);
    configuration.setBuildNumber(buildNumber);
    // addidtional metadata presented on main page
    configuration.addClassifications("Browser", System.getProperty("browser").toUpperCase());

    ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
    Reportable result = reportBuilder.generateReports();
  }


}
