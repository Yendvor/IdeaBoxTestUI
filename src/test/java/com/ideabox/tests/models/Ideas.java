package com.ideabox.tests.models;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStreamReader;

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
    setSummary("Default summary");
    setDetails("This is default idea details");
    setAnon(true);
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

  public JsonObject getCreateIdeaJson(){
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("summary", getSummary());
    jsonObject.addProperty("details", getDetails());
    jsonObject.addProperty("anonymous", getAnon());
    return jsonObject;
  }


  public JsonObject getIdeaStatusJson(){
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("status", this.getStatus());
    return jsonObject;
  }


  public static  Ideas readIdea(JsonReader reader) throws IOException {
    Ideas newIdea = new Ideas();
    reader.beginObject();
    while (reader.hasNext()) {
      String name = reader.nextName();
      if (name.equals("id")) {
        newIdea.setID(reader.nextString());
      } else if (name.equals("summary")) {
        newIdea.setSummary(reader.nextString());
      } else if (name.equals("details")) {
        newIdea.setDetails(reader.nextString());
      } else {
        reader.skipValue();
      }
    }
    reader.endObject();
    return newIdea;
  }

  public static String getIdeaIdFromResponse(HttpResponse response) {
    Ideas newIdea = new Ideas();
    JsonReader jsonReader = null;
    try {
      jsonReader =
        new JsonReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      newIdea=Ideas.readIdea(jsonReader);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        jsonReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return newIdea.getID();
  }
}
