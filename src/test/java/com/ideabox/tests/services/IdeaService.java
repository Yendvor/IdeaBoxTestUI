package com.ideabox.tests.services;

import com.ideabox.tests.config.Configuration;
import com.ideabox.tests.models.Ideas;
import com.ideabox.tests.models.RequestPayload;
import com.ideabox.tests.utils.Common;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

public class IdeaService {
  private static final Logger logger = LoggerFactory.getLogger(IdeaService.class);
  protected static String ideaboxUIURL = Configuration.getIdeaboxUIURL(Configuration.getEnvs());
  private static String jsonTemplatesPath = "/src/test/resources/templates/";
  private static String JSON_Authorize = jsonTemplatesPath+"authorize.json";
  private static String JSON_IDEA = jsonTemplatesPath+"idea_post.json";
  private static String JSON_IDEA_Status = jsonTemplatesPath+"idea_status_post.json";


  public static Response addIdea(Ideas idea) {
    RequestPayload ideaPayload = new RequestPayload(JSON_IDEA)
      .parameter("summary", idea.getSummary())
      .parameter("details", idea.getDetails())
      .parameter("anonymous", idea.getAnon());

    logger.info("idea body:"+ideaPayload.toString());
    logger.info("Ideas create Url : "+ideaboxUIURL+ Ideas.ideaUrl);
    return given().
      contentType("application/json").
      header(getAuthToken(Common.getUserLogin("adminUser"),Common.getUserPass("adminUser"))).
      body(ideaPayload.toString()).
      when().
      post(ideaboxUIURL+ Ideas.ideaUrl).
      then().
      body(CoreMatchers.containsString("id")).
      body(CoreMatchers.containsString(idea.getSummary())).
      body("summary", Matchers.is(idea.getSummary())).
      body("details", Matchers.is(idea.getDetails())).
      extract().response();
  }

  public static Response changeStatus(Ideas idea, String status) {
    RequestPayload ideaPayload = new RequestPayload(JSON_IDEA_Status)
      .parameter("status",status);

    return given().
      contentType("application/json").
      header(getAuthToken(Common.getUserLogin("ldapUser"),Common.getUserPass("ldapUser"))).
      body(ideaPayload.toString()).
      when().
      put(ideaboxUIURL+ Ideas.ideaUrl+"/"+idea.getID()+ Ideas.statusUrl).
      then().
      statusCode(202).
      body(CoreMatchers.containsString("id")).
      body("status", Matchers.is(status)).
      body("summary", Matchers.is(idea.getSummary())).
      body("details", Matchers.is(idea.getDetails())).
      extract().response();
  }

  public static Header getAuthToken(String login,String pass){
    RequestPayload authPayload= new RequestPayload(JSON_Authorize)
      .parameter("login", login)
      .parameter("password", pass);
    logger.info(""+authPayload.toString());
    return given().
      contentType("application/json").
      body(authPayload.toString()).
      when().
      post(ideaboxUIURL+"/proxy/ibengine/login").
      then().
      statusCode(200).
      extract().headers().get("X-AUTH-TOKEN");
  }


}
