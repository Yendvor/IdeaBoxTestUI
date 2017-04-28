package com.ideabox.tests.services;

import com.google.gson.JsonObject;
import com.ideabox.tests.config.Configuration;
import com.ideabox.tests.models.Ideas;
import com.ideabox.tests.utils.Common;
import com.ideabox.tests.utils.REST;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

public class IdeaService {
  private static final Logger logger = LoggerFactory.getLogger(IdeaService.class);
  protected static String ideaboxUIURL = Configuration.getIdeaboxUIURL(Configuration.getEnvs());
  private static REST rest=new REST();



  public static String addIdea(Ideas idea) {
    //get Token
    Header token =getAuthToken(Common.getUserLogin("ldapUser"),Common.getUserPass("ldapUser"));
    JsonObject ideaPayLoad = idea.getCreateIdeaJson();
    logger.info("idea body:"+ideaPayLoad.toString());

    rest.setPostUrl(ideaboxUIURL+ Ideas.ideaUrl);
    //set user logged in header
    rest.setPostHeader(token);
    rest.doPost(rest.prepareBodyFromJson(ideaPayLoad));
    rest.checkResponseCode(201);
    return Ideas.getIdeaIdFromResponse(rest.getResponse());
  }

  public static void changeStatus(Ideas idea, String status) {
    idea.setStatus(status);
    Header token =getAuthToken(Common.getUserLogin("ldapUser"),Common.getUserPass("ldapUser"));

    JsonObject ideaPayLoad = idea.getIdeaStatusJson();
    logger.info("status body:"+ideaPayLoad.toString());

    rest.setPutUrl(ideaboxUIURL+ Ideas.ideaUrl+"/"+idea.getID()+ Ideas.statusUrl);
    //set user logged in header
    rest.setPutHeader(token);
    rest.doPut(rest.prepareBodyFromJson(ideaPayLoad));
    rest.checkResponseCode(202);
  }

  public static Header getAuthToken(String login,String pass){
    JsonObject authPayload = new JsonObject();
    authPayload.addProperty("login", login);
    authPayload.addProperty("password", pass);

    logger.info(""+authPayload.toString());
    rest.setPostUrl(ideaboxUIURL+"/proxy/ibengine/login");
    rest.doPost(rest.prepareBodyFromJson(authPayload));
    rest.checkResponseCode(200);
    System.out.println("Token is "+ rest.getHeader("X-AUTH-TOKEN").toString());
    return rest.getHeader("X-AUTH-TOKEN");
  }
}
