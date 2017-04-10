package com.ideabox.tests.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class ReadWriteFileData {
  Properties property;

  ReadWriteFileData() {
    property = new Properties();
  }

  public static Properties loadProperties(String fileName) {
   String path = "src/test/resources/" + fileName + ".properties";
    File file = new File(path);
    Properties prop = new Properties();
    try (FileInputStream fileInput = new FileInputStream(file)) {
      prop.load(fileInput);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prop;
  }

  public static void writeProperty(String value, String fileName, String field) {
    Properties prop;
    String path = "src/test/resources/" + fileName + ".properties";
    // get old values from file
    prop = loadProperties(fileName);
    try (OutputStream output = new FileOutputStream(path)) {
      // set/edit the property value
      prop.setProperty(field, value);
      // save properties to project root folder
      prop.store(output, null);
    } catch (IOException io) {
      io.printStackTrace();
    }
  }

  public static void clearProperty(String fileName) {
    Properties prop = new Properties();
    String path = "src/test/resources/" + fileName + ".properties";
    try (OutputStream output = new FileOutputStream(path)) {
      // save properties to project root folder
      prop.store(output, null);
    } catch (IOException io) {
      io.printStackTrace();
    }
  }

  public static void deleteFolder(File folder) {
    File[] files = folder.listFiles();
    if(files!=null) { //some JVMs return null for empty dirs
      for(File f: files) {
        if(f.isDirectory()) {
          deleteFolder(f);
        } else {
          f.delete();
        }
      }
    }
  }

  public static File getFileByName(String fileName){
    return new File(fileName);
  }

  public static void printProperties(Properties property) {
    Enumeration KeyValues = property.keys();
    while (KeyValues.hasMoreElements()) {
      String key = (String) KeyValues.nextElement();
      String value = property.getProperty(key);
      System.out.println(key + ":- " + value);
    }
  }

  public static List<String> getListOfReportNames(){
    List<String> results = new ArrayList<String>();
    File[] files = new File("report").listFiles(new FilenameFilter(){
      @Override
      public boolean accept(File dir, String name)
      {
        return name.endsWith(".json");}
    });
    for (File file : files) {
      if (file.isFile()) {
        results.add(file.getPath());
      }
    }
    return results;
  }

  public static String getFileContent(String path) {
    String text= "";
    try {
       text = new String(Files.readAllBytes(Paths.get(path)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return text;
  }

  public static void writeFile( String fileName, String value) {
    try {
      FileUtils.writeStringToFile(new File(fileName),value );
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
