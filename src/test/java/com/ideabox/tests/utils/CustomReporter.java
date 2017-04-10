package com.ideabox.tests.utils;

/**
 * Created by tdvoryanchenko on 9/7/16.
 */
public class CustomReporter {

  public CustomReporter(){}

  public static void generateFinalReport(){
    ReportMerger finalReport = new ReportMerger();
    finalReport.mergeReports(ReadWriteFileData.getListOfReportNames());
  }
  public static void cleanReportDirectory(){
    ReadWriteFileData.deleteFolder(ReadWriteFileData.getFileByName("report"));
  }
}
