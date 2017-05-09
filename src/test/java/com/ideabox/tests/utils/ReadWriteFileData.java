package com.ideabox.tests.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

}
