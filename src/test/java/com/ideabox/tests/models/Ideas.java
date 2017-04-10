package com.ideabox.tests.models;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

public class Ideas {
  String id;
  String summary;
  String details;
  String status;
  boolean anon;
  public static String ideaUrl = "/proxy/ibengine/ideas";
  public static String statusUrl = "/status";


  public Ideas(){
    this.summary = "Default summary";
    this.details = "This is default idea details";
    this.anon = true;
  }

  public String getID() {
    return this.id;
  }
  public String getSummary() {
    return this.summary;
  }
  public String getDetails() {
    return this.details;
  }
  public String getStatus() {
    return this.status;
  }
  public boolean getAnon() {
    return this.anon;
  }

  public void setID(String id) {
    this.id=id;
  }
  public void setSummary(String summary) {
    this.summary=summary;
  }
  public void setDetails(String details) {
    this.details=details;
  }
  public void setStatus(String status) {
    this.status=status;
  }
  public void setAnon(boolean anon) {
    this.anon=anon;
  }

  public static Ideas defaultIdea() {
    return new Ideas();
  }
}
