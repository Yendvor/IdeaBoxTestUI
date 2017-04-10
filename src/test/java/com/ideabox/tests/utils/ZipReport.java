package com.ideabox.tests.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by tdvoryanchenko on 9/8/16.
 */
public class ZipReport {

  private List<String> fileList;
  private static final String OUTPUT_ZIP_FILE = "target/RunwayBE-auto";
  private static final String SOURCE_FOLDER = "target/cucumber-html-reports/";

  public ZipReport(){
    fileList = new ArrayList<String>();
  }

  public void doZipReport(){
    String suiteName = System.getProperty("suite");
    if (suiteName.isEmpty()){
      suiteName = "general";
    }
    String zipFileName=OUTPUT_ZIP_FILE+"-"+suiteName+"-report.zip";
    this.generateFileList(new File(SOURCE_FOLDER));
    this.zipIt(zipFileName);
  }

  public void zipIt(String zipFile){

    byte[] buffer = new byte[1024];

    try{

      FileOutputStream fos = new FileOutputStream(zipFile);
      ZipOutputStream zos = new ZipOutputStream(fos);

      for(String file : this.fileList){
        ZipEntry ze= new ZipEntry(file);
        zos.putNextEntry(ze);

        FileInputStream in =
          new FileInputStream(file);

        int len;
        while ((len = in.read(buffer)) > 0) {
          zos.write(buffer, 0, len);
        }

        in.close();
      }

      zos.closeEntry();
      //remember close it
      zos.close();

      System.out.println("Done");
    }catch(IOException ex){
      ex.printStackTrace();
    }
  }
  public void generateFileList(File node){
    //add file only
    System.out.println("File name " +node.getPath());
    if(node.isFile()){
      fileList.add(node.getPath());
    }

    if(node.isDirectory()){
      String[] subNote = node.list();
      for(String filename : subNote){
        System.out.println("Dir name " +node.getPath());
        generateFileList(new File(node, filename));
      }
    }

  }

  private String generateZipEntry(String file){
    return file.substring(SOURCE_FOLDER.length()+1, file.length());
  }

  public static String getZipReport(){
      List<String> results = new ArrayList<String>();
      File[] files = new File("target").listFiles(new FilenameFilter(){
        @Override
        public boolean accept(File dir, String name)
        {
          return name.endsWith(".zip");}
      });
      for (File file : files) {
        if (file.isFile()) {
          results.add(file.getPath());
        }
      }
    return results.get(0);
  }

}
